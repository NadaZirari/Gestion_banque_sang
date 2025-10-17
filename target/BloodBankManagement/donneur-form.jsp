<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        <c:choose>
            <c:when test="${not empty donneur}">Modifier</c:when>
            <c:otherwise>Ajouter</c:otherwise>
        </c:choose> un donneur
    </title>
    <style>
        /* Styles de base */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f2f5;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #2c3e50;
            margin: 40px 0 20px;
        }

        form {
            background-color: #fff;
            max-width: 600px;
            margin: 20px auto 40px;
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 6px 15px rgba(0,0,0,0.1);
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: 600;
            color: #34495e;
        }

        input[type="text"],
        input[type="number"],
        input[type="date"],
        select {
            width: 100%;
            padding: 10px 14px;
            margin-bottom: 18px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
            transition: border-color 0.3s, box-shadow 0.3s;
        }

        input[type="text"]:focus,
        input[type="number"]:focus,
        input[type="date"]:focus,
        select:focus {
            border-color: #3498db;
            box-shadow: 0 0 5px rgba(52,152,219,0.4);
            outline: none;
        }

        .checkbox-group {
            margin-bottom: 20px;
            padding: 10px 0;
            border-top: 1px solid #eee;
        }

        .checkbox-group label {
            font-weight: normal;
            display: flex;
            align-items: center;
            margin-bottom: 8px;
            cursor: pointer;
        }

        .checkbox-group input[type="checkbox"] {
            margin-right: 10px;
            width: 18px;
            height: 18px;
            accent-color: #3498db;
        }

        button {
            background-color: #3498db;
            color: white;
            padding: 14px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
            font-weight: bold;
            transition: background-color 0.3s, transform 0.2s;
        }

        button:hover {
            background-color: #2980b9;
            transform: translateY(-2px);
        }

        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #3498db;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }

        h3 {
            margin-top: 20px;
            color: #2c3e50;
            border-bottom: 1px solid #ddd;
            padding-bottom: 5px;
        }
    </style>

   
</head>
<body>

<h1>
    <c:choose>
        <c:when test="${not empty donneur}">Modifier</c:when>
        <c:otherwise>Ajouter</c:otherwise>
    </c:choose> un donneur
</h1>

<form action="${pageContext.request.contextPath}/donneurs" method="post" onsubmit="return verifierConditions()">
    <input type="hidden" name="id" value="${donneur.id}" />

    <label>Nom:</label>
    <input type="text" name="nom" value="${donneur.nom}" required/>

    <label>Prénom:</label>
    <input type="text" name="prenom" value="${donneur.prenom}" required/>

    <label>Date de naissance:</label>
    <input type="date" name="dateNaissance" value="${donneur.dateNaissance}" required/>

    <label>Poids (kg):</label>
    <input type="number" name="poids" step="0.1" value="${donneur.poids}" required/>

    <label>Sexe:</label>
    <select name="sexe" required>
        <option value="">--Choisir--</option>
        <option value="Homme" ${donneur.sexe == 'Homme' ? 'selected' : ''}>Homme</option>
        <option value="Femme" ${donneur.sexe == 'Femme' ? 'selected' : ''}>Femme</option>
    </select>

    <label>CIN:</label>
    <input type="text" name="cin" value="${donneur.cin}" required/>

    <label>Téléphone:</label>
    <input type="text" name="telephone" value="${donneur.telephone}" required/>

    <label>Groupe sanguin:</label>
    <select name="groupeSanguin" required>
        <option value="">--Choisir--</option>
        <option value="O+">O+</option>
        <option value="O-">O-</option>
        <option value="A+">A+</option>
        <option value="A-">A-</option>
        <option value="B+">B+</option>
        <option value="B-">B-</option>
        <option value="AB+">AB+</option>
        <option value="AB-">AB-</option>
    </select>

    <h3>Contre-indications (cochez si c'est vrai)</h3>
    <div class="checkbox-group">
        <label><input class="cond" type="checkbox" name="hepatiteB" ${donneur.hepatiteB ? 'checked' : ''}/> Hépatite B</label>
        <label><input class="cond" type="checkbox" name="hepatiteC" ${donneur.hepatiteC ? 'checked' : ''}/> Hépatite C</label>
        <label><input class="cond" type="checkbox" name="vih" ${donneur.vih ? 'checked' : ''}/> VIH</label>
        <label><input class="cond" type="checkbox" name="diabeteInsulinoDependant" ${donneur.diabeteInsulinoDependant ? 'checked' : ''}/> Diabète insulino-dépendant</label>
        <label><input class="cond" type="checkbox" name="grossesse" ${donneur.grossesse ? 'checked' : ''}/> Grossesse</label>
        <label><input class="cond" type="checkbox" name="allaitement" ${donneur.allaitement ? 'checked' : ''}/> Allaitement</label>
    </div>

    <button type="submit">Enregistrer</button>
</form>

<p><a href="${pageContext.request.contextPath}/donneurs">⬅ Retour</a></p>
</body>
</html>
