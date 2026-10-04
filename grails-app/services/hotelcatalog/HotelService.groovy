package hotelcatalog

import grails.gorm.transactions.Transactional

@Transactional(readOnly = true)
class HotelService {

    List<Hotel> searchHotels(String searchQuery, Long countryId, Map params) {
        Hotel.createCriteria().list(max: params.max, offset: params.offset) {
            if (searchQuery) ilike('name', "%${searchQuery}%")
            if (countryId) country { eq('id', countryId) }
            order('stars', 'desc')
            order('name', 'asc')
        }
    }

    int countHotels(String searchQuery, Long countryId) {
        Hotel.createCriteria().count {
            if (searchQuery) ilike('name', "%${searchQuery}%")
            if (countryId) country { eq('id', countryId) }
        }
    }

    List<Hotel> listHotels(Map params) {
        Hotel.createCriteria().list(max: params.max, offset: params.offset) {
            order('stars', 'desc')
            order('name', 'asc')
        }
    }

    int countAllHotels() {
        Hotel.count()
    }

    Hotel getHotel(Long id) {
        Hotel.get(id)
    }

    // -------- запись --------

    @Transactional
    Hotel createHotel(Map data) {
        def hotel = new Hotel()
        hotel.properties = data
        if (data.country?.id) {
            hotel.country = Country.get(data.country.id as Long)
        }
        hotel
    }

    @Transactional
    boolean saveHotel(Hotel hotel) {
        hotel.save()
    }

    @Transactional
    boolean deleteHotel(Long id) {
        def hotel = Hotel.get(id)
        if (!hotel) return false
        hotel.delete(flush: true)
        true
    }
}