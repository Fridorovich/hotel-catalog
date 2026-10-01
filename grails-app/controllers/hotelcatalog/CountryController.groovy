package hotelcatalog

import grails.gorm.transactions.Transactional

@Transactional(readOnly = true)
class CountryController {

    def index() {
        params.max = Math.min(params.int('max') ?: 10, 100)
        params.offset = params.int('offset') ?: 0

        def criteria = Country.createCriteria()
        def results = criteria.list(max: params.max, offset: params.offset) {
            if (params.q) {
                ilike('name', "%${params.q}%")
            }
            order('name', 'asc')
        }

        [countryList: results, countryCount: results.totalCount, q: params.q]
    }

    @Transactional
    def create() { [country: new Country()] }

    @Transactional
    def save() {
        def country = new Country(params)
        if (!country.save()) {
            render(view: 'create', model: [country: country])
            return
        }
        flash.message = "Страна добавлена"
        redirect(action: 'index')
    }

    @Transactional
    def edit(Long id) {
        def country = Country.get(id)
        if (!country) { redirect(action: 'index'); return }
        [country: country]
    }

    @Transactional
    def update(Long id) {
        def country = Country.get(id)
        if (!country) { redirect(action: 'index'); return }
        country.properties = params
        if (!country.save()) {
            render(view: 'edit', model: [country: country])
            return
        }
        flash.message = "Страна обновлена"
        redirect(action: 'index')
    }

    @Transactional
    def delete(Long id) {
        def country = Country.get(id)
        if (!country) { redirect(action: 'index'); return }
        if (country.hotels) {
            flash.message = "Нельзя удалить страну, у которой есть отели"
            redirect(action: 'index')
            return
        }
        country.delete(flush: true)
        flash.message = "Страна удалена"
        redirect(action: 'index')
    }
}