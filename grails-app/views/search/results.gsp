<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Результаты поиска</title>
</head>
<body>

<h1>Результаты поиска</h1>

<g:if test="${hotelCount == 0}">
    <p class="error">По Вашему запросу ничего не найдено</p>
</g:if>

<g:if test="${hotelCount > 0}">
    <p>Найдено отелей: <b>${hotelCount}</b></p>

    <table>
        <thead>
        <tr>
            <th>Звездность</th>
            <th>Название</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${hotelList}" var="hotel">
            <tr>
                <td><g:each in="${1..hotel.stars}">⭐</g:each></td>
                <td>
                    <div>${hotel.name} <small>(${hotel.country.name})</small></div>
                    <g:if test="${hotel.website}">
                        <div>
                            <a href="${hotel.website}" target="_blank" rel="noopener">Перейти на сайт</a>
                        </div>
                    </g:if>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:if test="${hotelCount > params.int('max')}">
            <g:paginate total="${hotelCount}"
                        params="${[q: searchQuery, countryId: selectedCountryId]}"
                        prev="Назад"
                        next="Вперёд"/>
        </g:if>
    </div>
</g:if>

<p style="margin-top: 20px;">
    <g:link controller="search" action="index" class="btn">Новый поиск</g:link>
</p>

</body>
</html>