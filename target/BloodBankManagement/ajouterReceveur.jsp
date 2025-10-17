<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un Receveur</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .form-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        h2 {
            margin-bottom: 25px;
            text-align: center;
            color: #343a40;
        }
        .btn-group {
            display: flex;
            justify-content: space-between;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Ajouter un Receveur</h2>
    <form method="post" action="receveurs">
        <div class="mb-3">
            <label class="form-label">Nom :</label>
            <input type="text" name="nom" class="form-control" placeholder="Entrez le nom" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Prénom :</label>
            <input type="text" name="prenom" class="form-control" placeholder="Entrez le prénom" required>
        </div>
        <div class="mb-3">
            <label class="form-label">CIN :</label>
            <input type="text" name="cin" class="form-control" placeholder="Ex: AA123456" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Téléphone :</label>
            <input type="text" name="telephone" class="form-control" placeholder="Ex: 0612345678" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Sexe :</label>
            <select name="sexe" class="form-select">
                <option value="Homme">Homme</option>
                <option value="Femme">Femme</option>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label">Groupe sanguin :</label>
            <select name="groupeSanguin" class="form-select">
                <option value="O_POS">O+</option>
                <option value="O_NEG">O-</option>
                <option value="A_POS">A+</option>
                <option value="A_NEG">A-</option>
                <option value="B_POS">B+</option>
                <option value="B_NEG">B-</option>
                <option value="AB_POS">AB+</option>
                <option value="AB_NEG">AB-</option>
            </select>
        </div>
        <div class="mb-4">
            <label class="form-label">Priorité :</label>
            <select name="priorite" class="form-select">
                <option value="NORMAL">NORMAL</option>
                <option value="URGENT">URGENT</option>
                <option value="CRITIQUE">CRITIQUE</option>
            </select>
        </div>
        <div class="btn-group">
            <button type="submit" class="btn btn-success">Enregistrer</button>
            <a href="receveurs?action=list" class="btn btn-secondary">Annuler</a>
        </div>
    </form>
</div>
</body>
</html>
