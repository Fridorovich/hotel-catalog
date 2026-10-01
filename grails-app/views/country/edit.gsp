<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><meta name="layout" content="main"/><title>Редактирование страны</title></head>
<body>
<h1>Редактирование страны</h1>

<g:hasErrors bean="${country}">
    <ul class="error">
        <g:eachError bean="${country}" var="err">
            <li><g:message error="${err}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>

<g:form controller="country" action="update" id="${country.id}">
    <g:hiddenField name="id" value="${country.id}"/>
    <p><label>Название:</label> <g:textField name="name" value="${country?.name}"/></p>
    <p><label>Столица:</label> <g:textField name="capital" value="${country?.capital}"/></p>
    <g:submitButton name="Сохранить" value="Сохранить"/>
    <g:link controller="country" action="index">Отмена</g:link>
</g:form>
</body>
</html>