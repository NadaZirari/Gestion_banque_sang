<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>BloodBank Management</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #d32f2f, #b71c1c);
            color: #fff;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
            justify-content: center;
        }

        h1 {
            font-size: 2.2rem;
            margin-bottom: 40px;
            text-shadow: 1px 1px 4px rgba(0,0,0,0.3);
        }

        ul {
            list-style: none;
            padding: 0;
            display: flex;
            gap: 30px;
        }

        li {
            margin: 0;
        }

        a {
            text-decoration: none;
            background-color: #fff;
            color: #b71c1c;
            font-weight: bold;
            padding: 12px 24px;
            border-radius: 8px;
            transition: all 0.3s ease;
            box-shadow: 0 3px 6px rgba(0,0,0,0.2);
        }

        a:hover {
            background-color: #f44336;
            color: white;
            transform: scale(1.05);
        }

        footer {
            position: absolute;
            bottom: 10px;
            font-size: 0.9rem;
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <h1>Bienvenue au Système de Gestion de Banque de Sang</h1>
    <ul>
        <li><a href="${pageContext.request.contextPath}/donneurs">Gestion des Donneurs</a></li>
        <li><a href="${pageContext.request.contextPath}/receveurs">Gestion des Receveurs</a></li>
    </ul>

    <footer>© 2025 - BloodBank Management System</footer>
</body>
</html>
