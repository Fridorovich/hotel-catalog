package hotelcatalog

class Country {

    String name
    String capital

    static hasMany = [hotels: Hotel]

    static constraints = {
        name blank: false, unique: true, maxSize: 255
        capital blank: false, maxSize: 128
    }

    static mapping = {
        sort name: 'asc'
    }

    String toString() { name }
}