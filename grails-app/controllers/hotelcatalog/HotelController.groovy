package hotelcatalog

class HotelController {

    HotelService hotelService
    CountryService countryService

    def index() {
        params.max = Math.min(params.int('max') ?: 10, 100)
        params.offset = params.int('offset') ?: 0

        String searchQuery = params.q
        Long selectedCountryId = params.countryId ? params.long('countryId') : null

        List<Hotel> hotels = hotelService.searchHotels(searchQuery, selectedCountryId, params)
        int hotelCount = hotelService.countHotels(searchQuery, selectedCountryId)

        [
                hotelList      : hotels,
                hotelCount     : hotelCount,
                countries      : countryService.listAllSorted(),
                searchQuery    : searchQuery,
                selectedCountryId: selectedCountryId,
                isSearch       : (searchQuery || selectedCountryId)
        ]
    }

    def create() {
        [hotel: new Hotel(), countries: countryService.listAllSorted()]
    }

    def save() {
        Hotel hotel = hotelService.createHotel(params)
        if (!hotelService.saveHotel(hotel)) {
            render(view: 'create', model: [hotel: hotel, countries: countryService.listAllSorted()])
            return
        }
        flash.message = "Отель успешно добавлен"
        redirect(action: 'index')
    }

    def edit(Long id) {
        Hotel hotel = hotelService.getHotel(id)
        if (!hotel) {
            redirect(action: 'index')
            return
        }
        [hotel: hotel, countries: countryService.listAllSorted()]
    }

    def update(Long id) {
        Hotel hotel = hotelService.getHotel(id)
        if (!hotel) {
            redirect(action: 'index')
            return
        }

        hotel.properties = params
        if (params.country?.id) {
            hotel.country = countryService.getCountry(params.country.id as Long)
        }

        if (!hotelService.saveHotel(hotel)) {
            render(view: 'edit', model: [hotel: hotel, countries: countryService.listAllSorted()])
            return
        }
        flash.message = "Отель обновлён"
        redirect(action: 'index')
    }

    def delete(Long id) {
        if (hotelService.deleteHotel(id)) {
            flash.message = "Отель удалён"
        }
        redirect(action: 'index')
    }
}