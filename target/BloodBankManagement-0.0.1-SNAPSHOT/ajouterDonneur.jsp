<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un Donneur</title>
    <meta charset="UTF-8">
    <style>
        /* Reset et styles de base */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #2c3e50;
            margin-top: 40px;
        }

        form {
            background-color: #fff;
            max-width: 500px;
            margin: 40px auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #34495e;
        }

        input[type="text"],
        input[type="number"],
        input[type="date"],
        select {
            width: 100%;
            padding: 10px 12px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus,
        input[type="number"]:focus,
        input[type="date"]:focus,
        select:focus {
            border-color: #3498db;
            outline: none;
        }

        button {
            background-color: #3498db;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #2980b9;
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

        hr {
            border: none;
            border-top: 1px solid #ddd;
            margin: 20px 0;
        }
    </style>

    <script>
        function verifierConditions() {
            const checkboxes = document.querySelectorAll('input[type="checkbox"]');
            for (let checkbox of checkboxes) {
                if (!checkbox.checked) {
                    alert("⚠️ Vous devez cocher toutes les conditions avant de continuer !");
                    return false;
                }
            }
            return true;
        }
    </script>
</head>
<body>

<h1>Ajouter un nouveau donneur</h1>

<form action="${pageContext.request.contextPath}/ajouter-donneur" method="post" onsubmit="return verifierConditions()">
    <hr>

    <label>Nom :</label>
    <input type="text" name="nom" required>

    <label>Prénom :</label>
    <input type="text" name="prenom" required>

    <label>Âge :</label>
    <input type="number" name="age" min="18" required>

    <label>Sexe :</label>
    <select name="sexe" required>
        <option value="">-- Choisir --</option>
        <option value="Homme">Homme</option>
        <option value="Femme">Femme</option>
    </select>

    <label>CIN :</label>
    <input type="text" name="cin" required>

    <label>Téléphone :</label>
    <input type="text" name="telephone" required>

    <label>Date de naissance :</label>
    <input type="date" name="dateNaissance" required>

    <label>Poids (en kg) :</label>
    <input type="number" name="poids" required>

    <label>Groupe sanguin :</label>
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
    </select>

    <button type="submit">Ajouter</button>
</form>

<a href="${pageContext.request.contextPath}/donneurs">⬅ Retour à la liste</a>

</body>
</html>
