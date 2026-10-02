<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Отели</title>
</head>
<body>

<h1>Справочник отелей</h1>

<p>
    <g:link controller="hotel" action="create" class="btn">Добавить новый отель</g:link>
</p>

<g:if test="${hotelCount > 0}">
    <p>Всего отелей: <b>${hotelCount}</b></p>

    <table>
        <thead>
        <tr>
            <th>Звездность</th>
            <th>Название</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${hotelList}" var="h">
            <tr>
                <td><g:each in="${1..h.stars}"></g:each></td>
                <td>
                    <div>${h.name} <small>(${h.country.name})</small></div>
                    <g:if test="${h.website}">
                        <div><a href="${h.website}" target="_blank" rel="noopener">Перейти на сайт</a></div>
                    </g:if>
                </td>
                <td class="actions">
                    <g:link action="edit" id="${h.id}">Редактировать</g:link>
                    <g:link action="delete" id="${h.id}"
                            onclick="return confirm('Удалить отель?');">Удалить</g:link>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:if test="${hotelCount > params.int('max')}">
            <g:paginate total="${hotelCount}"/>
        </g:if>
    </div>
</g:if>

<g:if test="${hotelCount == 0}">
    <p>Отелей пока нет. <g:link action="create">Добавить первый</g:link></p>
</g:if>

</body>
</html>