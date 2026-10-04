package hotelcatalog

import grails.gorm.transactions.Transactional

@Transactional(readOnly = true)
class SearchService {
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