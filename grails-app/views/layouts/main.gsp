<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><g:layoutTitle default="Каталог отелей"/></title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        nav { background: #2c3e50; padding: 10px; margin-bottom: 20px; }
        nav a { color: #fff; margin-right: 20px; text-decoration: none; font-weight: bold; }
        nav a:hover { text-decoration: underline; }
        table { border-collapse: collapse; width: 100%; margin-top: 15px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; vertical-align: top; }
        th { background: #ecf0f1; }
        .pagination a, .pagination span { margin-right: 5px; }
        .error { color: #c0392b; background: #fdecea; padding: 10px; }
        .flash { background: #eafaf1; padding: 10px; margin-bottom: 15px; }
        form.filter label { margin-right: 5px; }
        .actions a { margin-right: 10px; }
        .btn { display: inline-block; padding: 5px 10px; background: #3498db; color: #fff; text-decoration: none; border-radius: 3px; }
        .btn:hover { background: #2980b9; }
    </style>
</head>
<body>
<nav>
    <g:link controller="hotel" action="index">Отели</g:link>
    <g:link controller="country" action="index">Страны</g:link>
</nav>

<g:if test="${flash.message}">
    <div class="flash">${flash.message}</div>
</g:if>

<g:layoutBody/>
</body>
</html>