package hotelcatalog

import grails.gorm.transactions.Transactional

@Transactional(readOnly = true)
class HotelController {

    def index() {
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
                countryId : params.countryId,
                isSearch  : (params.q || params.countryId)
        ]
    }

    @Transactional
    def create() {
        [hotel: new Hotel(), countries: Country.list(sort: 'name')]
    }

    @Transactional
    def save() {
        def hotel = new Hotel()
        hotel.properties = params
        if (params.country?.id) {
            hotel.country = Country.get(params.country.id as Long)
        }
        if (!hotel.save()) {
            render(view: 'create', model: [hotel: hotel, countries: Country.list(sort: 'name')])
            return
        }
        flash.message = "Отель успешно добавлен"
        redirect(action: 'index')
    }

    @Transactional
    def edit(Long id) {
        def hotel = Hotel.get(id)
        if (!hotel) { redirect(action: 'index'); return }
        [hotel: hotel, countries: Country.list(sort: 'name')]
    }

    @Transactional
    def update(Long id) {
        def hotel = Hotel.get(id)
        if (!hotel) { redirect(action: 'index'); return }
        hotel.properties = params
        if (params.country?.id) {
            hotel.country = Country.get(params.country.id as Long)
        }
        if (!hotel.save()) {
            render(view: 'edit', model: [hotel: hotel, countries: Country.list(sort: 'name')])
            return
        }
        flash.message = "Отель обновлён"
        redirect(action: 'index')
    }

    @Transactional
    def delete(Long id) {
        def hotel = Hotel.get(id)
        if (hotel) {
            hotel.delete(flush: true)
            flash.message = "Отель удалён"
        }
        redirect(action: 'index')
    }
}