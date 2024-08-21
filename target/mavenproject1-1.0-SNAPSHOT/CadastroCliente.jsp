
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <title>Cadastro Cliente</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        
        .container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 600px;
        }
        
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        
        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="tel"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        
        input[type="submit"],
        button {
            width: 100%;
            padding: 10px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin-top: 10px;
        }
        
        input[type="submit"]:hover,
        button:hover {
            background-color: #555;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }
        
        th {
            background-color: #333;
            color: white;
        }
        
        .button {
            padding: 5px 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            color: white;
        }
        
        .edit-button {
            background-color: #4CAF50;
        }
        
        .delete-button {
            background-color: #f44336;
        }
    </style>
    <script>
        function submitForm(opcaoValue) {
            document.getElementById("opcao").value = opcaoValue;
            document.getElementById("cadastroForm").submit();
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>Cadastro Cliente</h1>
        <form id="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" method="post">
            <input type="hidden" name="opcao" value="${opcao}" id="opcao" />
            
            <p>
                <label>Nome:</label>
                <input type="text" name="nome" value="${nome}" />
            </p>
            <p>
                <label>Email:</label>
                <input type="email" name="email" value="${email}" />
            </p>
            <p>
                <label>Telefone:</label>
                <input type="tel" name="telefone" value="${telefone}" />
            </p>
            <p>
                <label>CPF:</label>
                <input type="text" name="cpf" value="${cpf}" />
            </p>
            <p>
                <label>Senha:</label>
                <input type="password" name="senha" value="${senha}" />
            </p>
            <input type="submit" value="Salvar" onclick="submitForm('salvar'); return false;" />
            <button type="button" onclick="submitForm('cancelar')">Cancelar</button>
        </form>

        ${mensagem}

        <table>
            <c:if test="${not empty clientes}">
                <tr>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>Telefone</th>
                    <th>CPF</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="cliente" items="${clientes}">
                <tr>
                    <td>${cliente.nome}</td>
                    <td>${cliente.email}</td>
                    <td>${cliente.telefone}</td>
                    <td>${cliente.cpf}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" method="get">
                            <input type="hidden" name="nome" value="${cliente.nome}" />
                            <input type="hidden" name="email" value="${cliente.email}" />
                            <input type="hidden" name="telefone" value="${cliente.telefone}" />
                            <input type="hidden" name="cpf" value="${cliente.cpf}" />
                            <input type="hidden" name="opcao" value="editar" />
                            <button type="submit" class="button edit-button">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" method="get">
                            <input type="hidden" name="opcao" value="excluir" />
                            <button type="submit" class="button delete-button">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
