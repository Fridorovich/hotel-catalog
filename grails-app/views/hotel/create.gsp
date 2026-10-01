<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><meta name="layout" content="main"/><title>Новый отель</title></head>
<body>
<h1>Новый отель</h1>

<g:hasErrors bean="${hotel}">
    <ul class="error">
        <g:eachError bean="${hotel}" var="err">
            <li><g:message error="${err}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>

<g:form controller="hotel" action="save">
    <p><label>Название:</label> <g:textField name="name" value="${hotel?.name}"/></p>
    <p><label>Страна:</label>
        <g:select name="country.id" from="${countries}" optionKey="id" optionValue="name"
                  value="${hotel?.country?.id}" noSelection="['': '-- выберите --']"/>
    </p>
    <p><label>Звездность:</label>
        <g:select name="stars" from="${1..5}" value="${hotel?.stars}"/>
    </p>
    <p><label>Сайт:</label> <g:textField name="website" value="${hotel?.website}"/></p>
    <g:submitButton name="Сохранить" value="Сохранить"/>
    <g:link controller="hotel" action="index">Отмена</g:link>
</g:form>
</body>
</html>