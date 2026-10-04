package hotelcatalog

import grails.gorm.transactions.Transactional

@Transactional(readOnly = true)
class CountryService {
    List<Country> listCountries(Map params) {
        Country.createCriteria().list(max: params.max, offset: params.offset) {
            order('name', 'asc')
        }
    }

    List<Country> searchCountries(String searchQuery, Map params) {
        Country.createCriteria().list(max: params.max, offset: params.offset) {
            if (searchQuery) {
                ilike('name', "%${searchQuery}%")
            }
            order('name', 'asc')
        }
    }

    int countCountries(String searchQuery) {
        Country.createCriteria().count {
            if (searchQuery) {
                ilike('name', "%${searchQuery}%")
            }
        }
    }

    List<Country> listAllSorted() {
        Country.list(sort: 'name')
    }

    Country getCountry(Long id) {
        Country.get(id)
    }

    boolean hasHotels(Long id) {
        def country = Country.get(id)
        country?.hotels && !country.hotels.isEmpty()
    }

    @Transactional
    Country createCountry(Map data) {
        new Country(data)
    }

    @Transactional
    boolean saveCountry(Country country) {
        country.save()
    }

    @Transactional
    boolean deleteCountry(Long id) {
        def country = Country.get(id)
        if (!country) return false
        country.delete(flush: true)
        true
    }
}