<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Решение СЛАУ методом Гаусса</title>
</head>
<body>


<h2>Загрузка файлов</h2>

<form method="post" action="uploadFile"
      enctype="multipart/form-data">

    Выбор файлов
    <br />
    Файл матрицы А
    <br>
    <input type="file" name="file"  />
    <br />
    Файл столбца B
    <br>
    <input type="file" name="file" />
    <br />
    <br />
    <select name="mode" id="mode" onchange="getSelectMode()">
        <option value="one">Однопоточный</option>
        <option value="many">Многопоточный</option>
    </select>
    <div id="dataServer"  hidden>
            <input type="text" id="serverIp">
            <input type="text" id="serverPort">
            <input type="button" value="Добавить" onclick="setServers()">
        <ol id="listServers"></ol>
        <input type="text" name="servers" id="servers" hidden>
    </div>
    <br>
    <input type="submit" value="Загрузить и решить" />
</form>

</body>
</html>
<script type="text/javascript">
    function getSelectMode() {
        var e = document.getElementById("mode");
        if (e.value == "many") {
            document.getElementById("dataServer").hidden = false;
        }
        else
        {
            document.getElementById("dataServer").hidden = true;
        }
    }

    function setServers() {
        var ip = document.getElementById("serverIp");
        var port = document.getElementById("serverPort");
        var list = document.getElementById("listServers");
        var entry = document.createElement('li');
        entry.appendChild(document.createTextNode(ip.value + ":" + port.value));
        entry.setAttribute('value', ip.value + ":" + port.value);
        list.appendChild(entry);
        var server = document.getElementById("servers");
        server.value = server.value+";"+ip.value+":"+port.value;
        ip.value = "";
        port.value = "";
    }
</script>
