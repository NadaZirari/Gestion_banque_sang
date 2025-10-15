
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un Donneur</title>
</head>
<body>

<h1>Ajouter un nouveau donneur</h1>

<form action="${pageContext.request.contextPath}/ajouter-donneur" method="post">
    <label>Nom :</label><br>
    <input type="text" name="nom" required><br><br>

    <label>Prénom :</label><br>
    <input type="text" name="prenom" required><br><br>

    <label>Groupe sanguin :</label><br>
    <select name="groupeSanguin" required>
        <option value="">-- Choisir --</option>
        <option value="O+">O+</option>
        <option value="O-">O-</option>
        <option value="A+">A+</option>
        <option value="A-">A-</option>
        <option value="B+">B+</option>
        <option value="B-">B-</option>
        <option value="AB+">AB+</option>
        <option value="AB-">AB-</option>
    </select><br><br>

    <button type="submit">Ajouter</button>
    
</form>

<br>
<a href="${pageContext.request.contextPath}/donneurs">⬅ Retour à la liste</a>

</body>
</html>