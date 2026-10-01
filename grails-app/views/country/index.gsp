<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><meta name="layout" content="main"/><title>Страны</title></head>
<body>
<h1>Справочник стран</h1>

<g:form controller="country" action="index" method="GET">
    <label>Название:</label>
    <g:textField name="q" value="${q}"/>
    <g:submitButton name="Найти" value="Найти"/>
</g:form>

<p><g:link controller="country" action="create" class="btn">Добавить страну</g:link></p>

<g:if test="${countryCount == 0}">
    <p>Ни одной страны не найдено</p>
</g:if>

<g:if test="${countryCount > 0}">
    <p>Всего: <b>${countryCount}</b></p>
    <table>
        <thead><tr><th>Название</th><th>Столица</th><th>Действия</th></tr></thead>
        <tbody>
        <g:each in="${countryList}" var="c">
            <tr>
                <td>${c.name}</td>
                <td>${c.capital}</td>
                <td class="actions">
                    <g:link action="edit" id="${c.id}">Редактировать</g:link>
                    <g:link action="delete" id="${c.id}"
                            onclick="return confirm('Удалить страну?');">Удалить</g:link>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <g:paginate total="${countryCount}" params="${[q: q]}"/>
    </div>
</g:if>
</body>
</html>