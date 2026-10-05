package hotelcatalog

/**
 * Контроллер справочника стран.
 */
class CountryController {

    CountryService countryService

    /**
     * Список стран с пагинацией и фильтром по названию
     * GET /country/index
     */
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

    /**
     * Форма создания новой страны
     * GET /country/create
     */
    def create() {
        [country: new Country()]
    }

    /**
     * Сохранение новой страны
     * POST /country/save
     * При ошибке валидации возвращает ту же форму с ошибками
     */
    def save() {
        Country country = countryService.createCountry(params)
        if (!countryService.saveCountry(country)) {
            render(view: 'create', model: [country: country])
            return
        }
        flash.message = "Страна добавлена"
        redirect(action: 'index')
    }

    /**
     * Форма редактирования страны
     * GET /country/edit/{id}
     */
    def edit(Long id) {
        Country country = countryService.getCountry(id)
        if (!country) {
            redirect(action: 'index')
            return
        }
        [country: country]
    }

    /**
     * Сохранение изменений страны
     * POST /country/update/{id}
     */
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

    /**
     * Удаление страны
     * Запрещено, если у страны есть отели
     */
    def delete(Long id) {
        if (countryService.deleteCountry(id)) {
            flash.message = "Страна удалена"
        }
        redirect(action: 'index')
    }
}