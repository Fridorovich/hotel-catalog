package hotelcatalog

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
                    def de = new Country(name: 'Германия', capital: 'Берлин').save(failOnError: true)
                    def gb = new Country(name: 'Великобритания', capital: 'Лондон').save(failOnError: true)
                    def jp = new Country(name: 'Япония', capital: 'Токио').save(failOnError: true)
                    def us = new Country(name: 'США', capital: 'Вашингтон').save(failOnError: true)
                    new Hotel(name: 'Гранд Отель Москва', stars: 5, country: ru, website: 'https://grand-hotel.ru').save(failOnError: true)
                    new Hotel(name: 'Метрополь', stars: 5, country: ru, website: 'https://metropol-moscow.ru').save(failOnError: true)
                    new Hotel(name: 'Националь', stars: 5, country: ru, website: 'https://national.ru').save(failOnError: true)
                    new Hotel(name: 'Космос', stars: 4, country: ru).save(failOnError: true)
                    new Hotel(name: 'Балчуг Кемпински', stars: 5, country: ru, website: 'https://baltschug.ru').save(failOnError: true)
                    new Hotel(name: 'Измайлово', stars: 3, country: ru).save(failOnError: true)
                    new Hotel(name: 'Азимут Отель', stars: 4, country: ru, website: 'https://azimut.ru').save(failOnError: true)
                    new Hotel(name: 'Ritz Paris', stars: 5, country: fr, website: 'https://ritzparis.com').save(failOnError: true)
                    new Hotel(name: 'Hotel de Crillon', stars: 5, country: fr).save(failOnError: true)
                    new Hotel(name: 'Le Bristol Paris', stars: 5, country: fr, website: 'https://lebristolparis.com').save(failOnError: true)
                    new Hotel(name: 'Ibis Paris', stars: 3, country: fr).save(failOnError: true)
                    new Hotel(name: 'Novotel Paris', stars: 4, country: fr, website: 'https://novotel.fr').save(failOnError: true)
                    new Hotel(name: 'Hotel Roma', stars: 4, country: it, website: 'https://hotelroma.it').save(failOnError: true)
                    new Hotel(name: 'Grand Hotel Plaza', stars: 5, country: it).save(failOnError: true)
                    new Hotel(name: 'Hotel Milano', stars: 4, country: it, website: 'https://hotelmilano.it').save(failOnError: true)
                    new Hotel(name: 'Hotel Firenze', stars: 3, country: it).save(failOnError: true)
                    new Hotel(name: 'Hotel Madrid', stars: 4, country: es).save(failOnError: true)
                    new Hotel(name: 'Hotel Barcelona', stars: 5, country: es, website: 'https://hbcn.es').save(failOnError: true)
                    new Hotel(name: 'Hotel Sevilla', stars: 4, country: es, website: 'https://hsevilla.es').save(failOnError: true)
                    new Hotel(name: 'Hotel Valencia', stars: 3, country: es).save(failOnError: true)
                    new Hotel(name: 'Hotel Berlin', stars: 5, country: de, website: 'https://hotelberlin.de').save(failOnError: true)
                    new Hotel(name: 'Hotel München', stars: 4, country: de).save(failOnError: true)
                    new Hotel(name: 'Hotel Hamburg', stars: 4, country: de, website: 'https://hotelhamburg.de').save(failOnError: true)
                    new Hotel(name: 'Hotel Köln', stars: 3, country: de).save(failOnError: true)
                    new Hotel(name: 'The Ritz London', stars: 5, country: gb, website: 'https://theritzlondon.com').save(failOnError: true)
                    new Hotel(name: 'Savoy Hotel', stars: 5, country: gb, website: 'https://savoy.co.uk').save(failOnError: true)
                    new Hotel(name: 'Premier Inn London', stars: 3, country: gb).save(failOnError: true)
                    new Hotel(name: 'Hotel Edinburgh', stars: 4, country: gb, website: 'https://hotedinburgh.uk').save(failOnError: true)
                    new Hotel(name: 'Hotel Tokyo', stars: 5, country: jp, website: 'https://hoteltokyo.jp').save(failOnError: true)
                    new Hotel(name: 'Hotel Osaka', stars: 4, country: jp).save(failOnError: true)
                    new Hotel(name: 'Hotel Kyoto', stars: 5, country: jp, website: 'https://hotelkyoto.jp').save(failOnError: true)
                    new Hotel(name: 'Hotel Sapporo', stars: 3, country: jp).save(failOnError: true)
                    new Hotel(name: 'Hotel New York', stars: 5, country: us, website: 'https://hotelny.com').save(failOnError: true)
                    new Hotel(name: 'Hotel Los Angeles', stars: 4, country: us).save(failOnError: true)
                    new Hotel(name: 'Hotel Chicago', stars: 4, country: us, website: 'https://hotelchicago.com').save(failOnError: true)
                    new Hotel(name: 'Hotel Miami', stars: 5, country: us, website: 'https://hotelmiami.com').save(failOnError: true)
                    new Hotel(name: 'Hotel Boston', stars: 3, country: us).save(failOnError: true)

                    println "Data initialized successfull: ${Country.count()} countries, ${Hotel.count()} hotels"
                }
            } catch (Exception e) {
                println "Error during bootstrap initialization: ${e.message}"
                e.printStackTrace()
            }
        }
    }

    def destroy = {}
}