<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des donneurs</title>
    <style>
      table { border-collapse: collapse; width: 100%;}
      th, td { padding: 8px; border: 1px solid #ddd; }
      th { background: #f4f4f4; }
      .action { white-space: nowrap; }
    </style>
</head>
<body>
<h1>Liste des donneurs</h1>

<p><a href="donneurs?action=new">➕ Ajouter un donneur</a></p>

<table>
    <tr>
        <th>Nom</th><th>Prénom</th><th>Âge</th><th>Sexe</th><th>CIN</th><th>Téléphone</th>
        <th>Date Naissance</th><th>Poids</th><th>Groupe</th><th>Statut</th><th>Actions</th>
    </tr>
    <c:forEach var="donneur" items="${donneurs}">
        <tr>
            <td><c:out value="${donneur.nom}"/></td>
            <td><c:out value="${donneur.prenom}"/></td>
            <td><c:out value="${donneur.age}"/></td>
            <td><c:out value="${donneur.sexe}"/></td>
            <td><c:out value="${donneur.cin}"/></td>
            <td><c:out value="${donneur.telephone}"/></td>
            <td><fmt:formatDate value="${donneur.dateNaissance}" pattern="dd/MM/yyyy"/></td>
            <td><c:out value="${donneur.poids}"/></td>
            <td><c:out value="${donneur.groupeSanguin}"/></td>
            <td><c:out value="${donneur.statutDisponibilite}"/></td>
            <td class="action">
                <a href="donneurs?action=edit&amp;id=${donneur.id}">Modifier</a> |
                <a href="donneurs?action=delete&amp;id=${donneur.id}" onclick="return confirm('Supprimer ?')">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
