package hotelcatalog

/**
 * Контроллер отдельной страницы поиска.
 */
class SearchController {

    SearchService searchService
    CountryService countryService

    /**
     * Страница поиска
     * GET /search/index
     */
    def index() {
        [
                countries: countryService.listAllSorted(),
                searchQuery: null,
                selectedCountryId: null
        ]
    }

    /**
     * Страница результатов поиска
     * GET /search/results?q=...&countryId=...
     */
    def results() {
        params.max = Math.min(params.int('max') ?: 10, 100)
        params.offset = params.int('offset') ?: 0

        String searchQuery = params.q
        Long selectedCountryId = params.countryId ? params.long('countryId') : null

        List<Hotel> hotels = searchService.searchHotels(searchQuery, selectedCountryId, params)
        int hotelCount = searchService.countHotels(searchQuery, selectedCountryId)

        [
                hotelList: hotels,
                hotelCount: hotelCount,
                countries: countryService.listAllSorted(),
                searchQuery: searchQuery,
                selectedCountryId: selectedCountryId
        ]
    }
}