package hotelcatalog

import grails.gorm.transactions.Transactional

@Transactional(readOnly = true)
class SearchController {

    def index() {
        [countries: Country.list(sort: 'name'), q: null, countryId: null]
    }

    @Transactional
    def results() {
        params.max = Math.min(params.int('max') ?: 10, 100)
        params.offset = params.int('offset') ?: 0

        def criteria = Hotel.createCriteria()
        def results = criteria.list(max: params.max, offset: params.offset) {
            if (params.q) {
                ilike('name', "%${params.q}%")
            }
            if (params.countryId) {
                country { eq('id', params.long('countryId')) }
            }
            order('stars', 'desc')
            order('name', 'asc')
        }

        [
                hotelList : results,
                hotelCount: results.totalCount,
                countries : Country.list(sort: 'name'),
                q         : params.q,
                countryId : params.countryId
        ]
    }
}