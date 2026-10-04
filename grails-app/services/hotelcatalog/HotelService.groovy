package hotelcatalog

import grails.gorm.transactions.Transactional

/**
 * Сервис для работы со справочником отелей.
 */
@Transactional(readOnly = true)
class HotelService {

    /**
     * Поиск отелей по названию, вхождению и стране.
     *
     * @param searchQuery - строка поиска
     * @param countryId - id страны
     * @param params - map с max и offset для пагинации
     */
    List<Hotel> searchHotels(String searchQuery, Long countryId, Map params) {
        Hotel.createCriteria().list(max: params.max, offset: params.offset) {
            if (searchQuery) {
                ilike('name', "%${searchQuery}%")
            }
            if (countryId) {
                country { eq('id', countryId) }
            }
            order('stars', 'desc')
            order('name', 'asc')
        }
    }

    /**
     * Количество отелей по тому же фильтру, что и searchHotels()
     * Нужно для пагинации
     */
    int countHotels(String searchQuery, Long countryId) {
        Hotel.createCriteria().count {
            if (searchQuery) {
                ilike('name', "%${searchQuery}%")
            }
            if (countryId) {
                country { eq('id', countryId) }
            }
        }
    }

    /**
     * Список всех отелей с пагинацией
     */
    List<Hotel> listHotels(Map params) {
        Hotel.createCriteria().list(max: params.max, offset: params.offset) {
            order('stars', 'desc')
            order('name', 'asc')
        }
    }

    /**
     * Общее количество отелей в системе
     */
    int countAllHotels() {
        Hotel.count()
    }

    /**
     * Получить отель по id
     */
    Hotel getHotel(Long id) {
        Hotel.get(id)
    }

    /**
     * Создать новый объект Hotel из параметров
     */
    @Transactional
    Hotel createHotel(Map data) {
        def hotel = new Hotel()
        hotel.properties = data
        if (data.country?.id) {
            hotel.country = Country.get(data.country.id as Long)
        }
        hotel
    }

    /**
     * Сохранить отель
     */
    @Transactional
    boolean saveHotel(Hotel hotel) {
        hotel.save()
    }

    /**
     * Удалить отель по id
     */
    @Transactional
    boolean deleteHotel(Long id) {
        def hotel = Hotel.get(id)
        if (!hotel) return false
        hotel.delete(flush: true)
        true
    }
}