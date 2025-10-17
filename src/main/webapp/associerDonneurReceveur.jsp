<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Association Donneur - Receveur</title>
    <style>
        body { font-family: Arial; background: #f9f9f9; padding: 20px; }
        form { background: white; padding: 20px; border-radius: 8px; width: 50%; margin: auto; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        select, button { width: 100%; padding: 10px; margin: 10px 0; }
        button { background: crimson; color: white; border: none; border-radius: 5px; cursor: pointer; }
        .msg { text-align: center; font-weight: bold; }
        .ok { color: green; }
        .err { color: red; }
    </style>
</head>
<body>
    <h2 style="text-align:center;">Associer un Donneur à un Receveur</h2>

    <c:if test="${not empty message}">
        <p class="msg ok">${message}</p>
    </c:if>
    <c:if test="${not empty erreur}">
        <p class="msg err">${erreur}</p>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/associer">
        <label for="donneurId">Donneur disponible :</label>
        <select name="donneurId" id="donneurId" required>
            <c:forEach var="d" items="${donneurs}">
                <option value="${d.id}">${d.nom} ${d.prenom} - ${d.groupeSanguin}</option>
            </c:forEach>
        </select>

        <label for="receveurId">Receveur en attente :</label>
        <select name="receveurId" id="receveurId" required>
            <c:forEach var="r" items="${receveurs}">
                <option value="${r.id}">${r.nom} ${r.prenom} - ${r.groupeSanguin} (${r.situation})</option>
            </c:forEach>
        </select>

        <button type="submit">Associer</button>
    </form>
</body>
</html>
