<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Отели</title>
</head>
<body>

<h1>Поиск отелей</h1>

<g:form controller="hotel" action="index" method="GET" class="filter">
    <label>Название отеля:</label>
    <g:textField name="q" value="${searchQuery}"/>

    <label>Страна:</label>
    <g:select name="countryId"
              from="${countries}"
              optionKey="id"
              optionValue="name"
              value="${selectedCountryId}"
              noSelection="['': 'любая']"/>

    <g:submitButton name="Найти" value="Найти"/>
    <g:link controller="hotel" action="index" class="btn">Сброс</g:link>
</g:form>

<p>
    <g:link controller="hotel" action="create" class="btn">Добавить новый отель</g:link>
</p>

<g:if test="${isSearch && hotelCount == 0}">
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
                    <div class="actions">
                        <g:link action="edit" id="${hotel.id}">Редактировать</g:link>
                        <g:link action="delete" id="${hotel.id}"
                                onclick="return confirm('Удалить отель?');">Удалить</g:link>
                    </div>
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

</body>
</html>