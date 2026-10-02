package hotelcatalog

class UrlMappings {
    static mappings = {
        "/$controller/$action?/$id?(.$format)?" { constraints {} }
        "/"(controller: 'search', action: 'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}