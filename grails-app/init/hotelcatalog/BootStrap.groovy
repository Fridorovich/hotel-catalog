package hotelcatalog // <--- ВАЖНО: Указываем пакет, такой же, как у доменных классов

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        if (Environment.current == Environment.DEVELOPMENT) {
            try {
                if (Country.count() == 0) {
                    println "Initializing data"

                    def ru = new Country(name: 'Россия', capital: 'Москва').save(failOnError: true)
                    def fr = new Country(name: 'Франция', capital: 'Париж').save(failOnError: true)
                    def it = new Country(name: 'Италия', capital: 'Рим').save(failOnError: true)
                    def es = new Country(name: 'Испания', capital: 'Мадрид').save(failOnError: true)

                    new Hotel(name: 'Гранд Отель Москва', stars: 5, country: ru, website: 'https://grand-hotel.ru').save(failOnError: true)
                    new Hotel(name: 'Метрополь', stars: 5, country: ru, website: 'https://metropol-moscow.ru').save(failOnError: true)
                    new Hotel(name: 'Космос', stars: 4, country: ru).save(failOnError: true)
                    new Hotel(name: 'Измайлово', stars: 3, country: ru).save(failOnError: true)
                    new Hotel(name: 'Ritz Paris', stars: 5, country: fr, website: 'https://ritzparis.com').save(failOnError: true)
                    new Hotel(name: 'Hotel de Crillon', stars: 5, country: fr).save(failOnError: true)
                    new Hotel(name: 'Ibis Paris', stars: 3, country: fr).save(failOnError: true)
                    new Hotel(name: 'Hotel Roma', stars: 4, country: it, website: 'https://hotelroma.it').save(failOnError: true)
                    new Hotel(name: 'Grand Hotel Plaza', stars: 5, country: it).save(failOnError: true)
                    new Hotel(name: 'Hotel Madrid', stars: 4, country: es).save(failOnError: true)
                    new Hotel(name: 'Hotel Barcelona', stars: 5, country: es, website: 'https://hbcn.es').save(failOnError: true)

                    println "Data initialized successfull"
                }
            } catch (Exception e) {
                println "Error during bootstrap initialization: ${e.message}"
                e.printStackTrace()
            }
        }
    }

    def destroy = {}
}