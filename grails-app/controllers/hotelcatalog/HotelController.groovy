package hotelcatalog

/**
 * Контроллер справочника отелей и главной страницы (поиск).
 */
class HotelController {

    HotelService hotelService
    CountryService countryService

    /**
     * Главная страница + поиск
     *
     * Если параметры q / countryId не заданы - показываются первые 10 отелей
     * Если заданы, то производится поиск по вхождению названия и/или фильтр по стране
     *
     * GET /
     */
    def index() {
        params.max = Math.min(params.int('max') ?: 10, 100)
        params.offset = params.int('offset') ?: 0

        String searchQuery = params.q
        Long selectedCountryId = params.countryId ? params.long('countryId') : null

        List<Hotel> hotels = hotelService.searchHotels(searchQuery, selectedCountryId, params)
        int hotelCount = hotelService.countHotels(searchQuery, selectedCountryId)

        [
                hotelList: hotels,
                hotelCount: hotelCount,
                countries: countryService.listAllSorted(),
                searchQuery: searchQuery,
                selectedCountryId: selectedCountryId,
                isSearch: (searchQuery || selectedCountryId)
        ]
    }

    /**
     * Форма создания нового отеля
     * GET /hotel/create
     */
    def create() {
        [hotel: new Hotel(), countries: countryService.listAllSorted()]
    }

    /**
     * Сохранение нового отеля
     * POST /hotel/save
     */
    def save() {
        Hotel hotel = hotelService.createHotel(params)
        if (!hotelService.saveHotel(hotel)) {
            render(view: 'create', model: [hotel: hotel, countries: countryService.listAllSorted()])
            return
        }
        flash.message = "Отель успешно добавлен"
        redirect(action: 'index')
    }

    /**
     * Форма редактирования отеля
     * GET /hotel/edit/{id}
     */
    def edit(Long id) {
        Hotel hotel = hotelService.getHotel(id)
        if (!hotel) {
            redirect(action: 'index')
            return
        }
        [hotel: hotel, countries: countryService.listAllSorted()]
    }

    /**
     * Сохранение изменений отеля
     * POST /hotel/update/{id}
     */
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

    /**
     * Удаление отеля
     * POST/GET /hotel/delete/{id}
     */
    def delete(Long id) {
        if (hotelService.deleteHotel(id)) {
            flash.message = "Отель удалён"
        }
        redirect(action: 'index')
    }
}