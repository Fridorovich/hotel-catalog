package hotelcatalog

import grails.gorm.transactions.Transactional

/**
 * Сервис только для поиска отелей.
 */
@Transactional(readOnly = true)
class SearchService {

    /**
     * Поиск отелей по названию, вхождение и стране.
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
     * Количество отелей по тому же фильтру
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
}