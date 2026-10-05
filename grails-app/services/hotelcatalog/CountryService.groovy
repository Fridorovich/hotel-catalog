package hotelcatalog

import grails.gorm.transactions.Transactional

/**
 * Сервис для работы со справочником стран.
 */
@Transactional(readOnly = true)
class CountryService {

    /**
     * Список стран с пагинацией
     */
    List<Country> listCountries(Map params) {
        Country.createCriteria().list(max: params.max, offset: params.offset) {
            order('name', 'asc')
        }
    }

    /**
     * Поиск стран по названию с пагинацией
     *
     * @param searchQuery - строка поиска
     * @param params - map с max и offset для пагинации
     */
    List<Country> searchCountries(String searchQuery, Map params) {
        Country.createCriteria().list(max: params.max, offset: params.offset) {
            if (searchQuery) {
                ilike('name', "%${searchQuery}%")
            }
            order('name', 'asc')
        }
    }

    /**
     * Количество стран по тому же фильтру, что и searchCountries()
     * Нужно для пагинации
     */
    int countCountries(String searchQuery) {
        Country.createCriteria().count {
            if (searchQuery) {
                ilike('name', "%${searchQuery}%")
            }
        }
    }

    /**
     * Все страны, отсортированные по названию
     */
    List<Country> listAllSorted() {
        Country.list(sort: 'name')
    }

    /**
     * Получить страну по id
     */
    Country getCountry(Long id) {
        Country.get(id)
    }

    /**
     * Создать новый объект Country из параметров
     */
    @Transactional
    Country createCountry(Map data) {
        new Country(data)
    }

    /**
     * Сохранить страну. Возвращает true при успехе
     */
    @Transactional
    boolean saveCountry(Country country) {
        country.save()
    }

    /**
     * Удалить страну по id
     */
    @Transactional
    boolean deleteCountry(Long id) {
        def country = Country.get(id)
        if (!country) return false
        country.hotels?.clear()
        country.delete(flush: true)
        true
    }
}