package hotelcatalog

class CountryController {

    CountryService countryService

    def index() {
        params.max = Math.min(params.int('max') ?: 10, 100)
        params.offset = params.int('offset') ?: 0

        String searchQuery = params.q

        List<Country> countries = countryService.searchCountries(searchQuery, params)
        int countryCount = countryService.countCountries(searchQuery)

        [
                countryList : countries,
                countryCount: countryCount,
                searchQuery : searchQuery
        ]
    }

    def create() {
        [country: new Country()]
    }

    def save() {
        Country country = countryService.createCountry(params)
        if (!countryService.saveCountry(country)) {
            render(view: 'create', model: [country: country])
            return
        }
        flash.message = "Страна добавлена"
        redirect(action: 'index')
    }

    def edit(Long id) {
        Country country = countryService.getCountry(id)
        if (!country) {
            redirect(action: 'index')
            return
        }
        [country: country]
    }

    def update(Long id) {
        Country country = countryService.getCountry(id)
        if (!country) {
            redirect(action: 'index')
            return
        }

        country.properties = params
        if (!countryService.saveCountry(country)) {
            render(view: 'edit', model: [country: country])
            return
        }
        flash.message = "Страна обновлена"
        redirect(action: 'index')
    }

    def delete(Long id) {
        if (countryService.hasHotels(id)) {
            flash.message = "Нельзя удалить страну, у которой есть отели"
            redirect(action: 'index')
            return
        }
        if (countryService.deleteCountry(id)) {
            flash.message = "Страна удалена"
        }
        redirect(action: 'index')
    }
}