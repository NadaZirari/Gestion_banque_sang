<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des donneurs</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f6fa;
            margin: 20;
            padding: 0 20px;
        }

        h1 {
            text-align: center;
            margin: 30px 0 20px;
            color: #2c3e50;
        }

        p a {
            display: inline-block;
            margin-bottom: 20px;
            background-color: #27ae60;
            color: #fff;
            text-decoration: none;
            padding: 10px 16px;
            border-radius: 6px;
            font-weight: bold;
            transition: background-color 0.3s;
        }

        p a:hover {
            background-color: #219150;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 10px rgba(0,0,0,0.08);
        }

        th, td {
            padding: 12px 15px;
            border-bottom: 1px solid #eee;
            text-align: left;
        }

        th {
            background-color: #3498db;
            color: #fff;
            text-transform: uppercase;
            font-size: 14px;
        }

        tr:hover {
            background-color: #f1f7fc;
        }

        .action a {
            color: #3498db;
            text-decoration: none;
            margin-right: 8px;
            font-weight: bold;
            transition: color 0.3s;
        }

        .action a:hover {
            color: #1d6fa5;
        }

        @media (max-width: 900px) {
            table, thead, tbody, th, td, tr {
                display: block;
            }
            th {
                position: absolute;
                top: -9999px;
                left: -9999px;
            }
            tr {
                border: 1px solid #ddd;
                margin-bottom: 15px;
                border-radius: 8px;
                padding: 10px;
                background-color: #fff;
                box-shadow: 0 2px 5px rgba(0,0,0,0.05);
            }
            td {
                border: none;
                position: relative;
                padding-left: 50%;
                text-align: left;
            }
            td:before {
                position: absolute;
                left: 15px;
                width: 45%;
                white-space: nowrap;
                font-weight: bold;
                color: #555;
            }
            td:nth-of-type(1):before { content: "Nom"; }
            td:nth-of-type(2):before { content: "Prénom"; }
            td:nth-of-type(3):before { content: "Âge"; }
            td:nth-of-type(4):before { content: "Sexe"; }
            td:nth-of-type(5):before { content: "CIN"; }
            td:nth-of-type(6):before { content: "Téléphone"; }
            td:nth-of-type(7):before { content: "Date Naissance"; }
            td:nth-of-type(8):before { content: "Poids"; }
            td:nth-of-type(9):before { content: "Groupe"; }
            td:nth-of-type(10):before { content: "Statut"; }
            td:nth-of-type(11):before { content: "Actions"; }
        }
    </style>
</head>
<body>

<h1>Liste des donneurs</h1>

<p><a href="donneurs?action=new">➕ Ajouter un donneur</a></p>

<table>
    <thead>
    <tr>
        <th>Nom</th><th>Prénom</th><th>Âge</th><th>Sexe</th><th>CIN</th><th>Téléphone</th>
        <th>Poids</th><th>Groupe</th><th>Statut</th><th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="donneur" items="${donneurs}">
        <tr>
            <td><c:out value="${donneur.nom}"/></td>
            <td><c:out value="${donneur.prenom}"/></td>
            <td><c:out value="${donneur.age}"/></td>
            <td><c:out value="${donneur.sexe}"/></td>
            <td><c:out value="${donneur.cin}"/></td>
            <td><c:out value="${donneur.telephone}"/></td>
            
            <td><c:out value="${donneur.poids}"/></td>
            <td><c:out value="${donneur.groupeSanguin}"/></td>
            <td><c:out value="${donneur.statutDisponibilite}"/></td>
            <td class="action">
                <a href="donneurs?action=edit&amp;id=${donneur.id}">Modifier</a> |
                <a href="donneurs?action=delete&amp;id=${donneur.id}" onclick="return confirm('Supprimer ?')">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
