<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Pedido</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
    <div class="container">
        <h1>Cadastrar Novo Pedido</h1>

        <form id="cadastroForm" action="${pageContext.request.contextPath}/PedidoControlador" method="post">
            <h2>Dados do Cliente</h2>
            <p>
                <label for="clienteNome">Nome:</label>
                <input type="text" name="clienteNome" id="clienteNome" required>
            </p>

            <h2>Dados do Pedido</h2>
            <p>
                <label for="produto">Produto:</label>
                <input type="text" name="produto" id="produto" required>
            </p>
            <p>
                <label for="quantidade">Quantidade:</label>
                <input type="number" name="quantidade" id="quantidade" required>
            </p>
            <p>
                <label for="dataPedido">Data do Pedido:</label>
                <input type="date" name="dataPedido" id="dataPedido" required>
            </p>
            <input type="submit" value="Cadastrar Pedido" />
        </form>

        <a href="listagemPedidos.jsp">Voltar à Listagem de Pedidos</a>
    </div>
</body>
</html>
