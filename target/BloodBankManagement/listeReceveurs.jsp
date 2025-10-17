<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Receveurs</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="p-4">
<h2>Liste des Receveurs</h2>
<a href="receveurs?action=add" class="btn btn-primary mb-3">+ Ajouter un Receveur</a>
<table class="table table-bordered table-striped">
    <thead>
    <tr>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Groupe</th>
        <th>Priorité</th>
        <th>Statut</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="r" items="${receveurs}">
        <tr>
            <td>${r.nom}</td>
            <td>${r.prenom}</td>
            <td>${r.groupeSanguin}</td>
            <td>${r.priorite}</td>
            <td>${r.statut}</td>
            <td>
                <a href="receveurs?action=delete&id=${r.id}" class="btn btn-danger btn-sm">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
