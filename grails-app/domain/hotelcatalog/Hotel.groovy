package hotelcatalog

class Hotel {

    String name
    Integer stars
    String website

    static belongsTo = [country: Country]

    static constraints = {
        name blank: false, maxSize: 255, unique: 'country'
        country nullable: false
        stars nullable: false, range: 1..5
        website nullable: true, maxSize: 255,
                validator: { val, obj ->
                    if (val && !(val.startsWith('http://') || val.startsWith('https://'))) {
                        return ['hotel.website.invalid']
                    }
                }
    }

    static mapping = {
        sort stars: 'desc', name: 'asc'
    }

    String toString() { name }
}