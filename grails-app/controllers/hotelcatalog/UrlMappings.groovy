package hotelcatalog

class UrlMappings {
    static mappings = {
        "/$controller/$action?/$id?(.$format)?" { constraints {} }
        "/"(controller: 'hotel', action: 'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}