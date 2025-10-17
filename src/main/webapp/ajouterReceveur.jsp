<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un Receveur</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="p-4">
<h2>Ajouter un Receveur</h2>
<form method="post" action="receveurs">
    <div class="mb-3">
        <label>Nom :</label>
        <input type="text" name="nom" class="form-control" required>
    </div>
    <div class="mb-3">
        <label>Prénom :</label>
        <input type="text" name="prenom" class="form-control" required>
    </div>
    <div class="mb-3">
        <label>CIN :</label>
        <input type="text" name="cin" class="form-control" required>
    </div>
    <div class="mb-3">
        <label>Téléphone :</label>
        <input type="text" name="telephone" class="form-control" required>
    </div>
    <div class="mb-3">
        <label>Sexe :</label>
        <select name="sexe" class="form-control">
            <option>Homme</option>
            <option>Femme</option>
        </select>
    </div>
    <div class="mb-3">
        <label>Groupe sanguin :</label>
        <select name="groupeSanguin" class="form-control">
            <option>O_NEGATIF</option>
            <option>O_POSITIF</option>
            <option>A_NEGATIF</option>
            <option>A_POSITIF</option>
            <option>B_NEGATIF</option>
            <option>B_POSITIF</option>
            <option>AB_NEGATIF</option>
            <option>AB_POSITIF</option>
        </select>
    </div>
    <div class="mb-3">
        <label>Priorité :</label>
        <select name="priorite" class="form-control">
            <option>NORMAL</option>
            <option>URGENT</option>
            <option>CRITIQUE</option>
        </select>
    </div>
    <button type="submit" class="btn btn-success">Enregistrer</button>
    <a href="receveurs?action=list" class="btn btn-secondary">Annuler</a>
</form>
</body>
</html>
