<%@ page import="com.mycompany.mavenproject1.modelo.entidade.Cliente" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalhes do Cliente</title>
</head>
<body>
    <h1>Detalhes do Cliente</h1>
    <p>ID: ${cliente.id}</p>
    <p>Nome: ${cliente.nome}</p>
    <p>Email: ${cliente.email}</p>
    <p>Telefone: ${cliente.telefone}</p>
    <p>CPF: ${cliente.cpf}</p>
    <a href="clientes">Voltar à listagem</a>
</body>
</html>

