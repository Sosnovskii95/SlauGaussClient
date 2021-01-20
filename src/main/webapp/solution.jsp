<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Решение СЛАУ методом Гаусса</title>
</head>
<body>
<h2>Результат вычислений</h2>
<h3>Режим расчета: <%=request.getAttribute("mode")%></h3>
<h4>Время затраченное на вычисления: <%=request.getAttribute("time")%> </h4>
<form action="downloadFile" method="post">
    <input type="text" name="pathName" value="<%=request.getAttribute("pathName")%>" hidden>
    <input type="submit" value="Скачать результат">
</form>
</body>
</html>
