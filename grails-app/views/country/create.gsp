<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><meta name="layout" content="main"/><title>Новая страна</title></head>
<body>
<h1>Новая страна</h1>

<g:hasErrors bean="${country}">
    <ul class="error">
        <g:eachError bean="${country}" var="error">
            <li><g:message error="${error}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>

<g:form controller="country" action="save">
    <p><label>Название:</label> <g:textField name="name" value="${country?.name}"/></p>
    <p><label>Столица:</label> <g:textField name="capital" value="${country?.capital}"/></p>
    <g:submitButton name="Сохранить" value="Сохранить"/>
    <g:link controller="country" action="index">Отмена</g:link>
</g:form>
</body>
</html>