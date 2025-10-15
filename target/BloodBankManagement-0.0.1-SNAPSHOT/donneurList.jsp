<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%@ taglib prefix="fmt" uri="http://jakarta.ee/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Liste des donneurs</title>
</head>
<body>

<h1>Liste des donneurs</h1>

<table border="1">
    <tr>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Groupe Sanguin</th>
    </tr>

    <c:forEach var="donneur" items="${donneurs}">
        <tr>
            <td>${donneur.nom}</td>
            <td>${donneur.prenom}</td>
            <td>${donneur.groupeSanguin}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
