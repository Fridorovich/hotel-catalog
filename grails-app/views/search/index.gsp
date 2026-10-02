<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Поиск отелей</title>
</head>
<body>

<h1>Поиск отелей</h1>

<g:form controller="search" action="results" method="GET" class="filter">
    <label>Название отеля:</label>
    <g:textField name="q" value="${q}"/>

    <label>Страна:</label>
    <g:select name="countryId" from="${countries}" optionKey="id" optionValue="name"
              value="${countryId}" noSelection="['': 'любая']"/>

    <g:submitButton name="Найти" value="Найти"/>
</g:form>

</body>
</html>