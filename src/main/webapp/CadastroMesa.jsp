<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <title>Cadastro Mesa</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
    <style>
        /* Mesma estilização das outras páginas */
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
        <h1>Cadastro Mesa</h1>
        <form id="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/MesaControlador" method="post">
            <input type="hidden" name="opcao" value="${opcao}" id="opcao" />
            
            <p>
                <label>Número:</label>
                <input type="number" name="numero" value="${numero}" />
            </p>
            <p>
                <label>Capacidade:</label>
                <input type="number" name="capacidade" value="${capacidade}" />
            </p>
            <p>
                <label>Disponível:</label>
                <select name="disponivel">
                    <option value="true" ${disponivel == 'true' ? 'selected' : ''}>Sim</option>
                    <option value="false" ${disponivel == 'false' ? 'selected' : ''}>Não</option>
                </select>
            </p>
            <input type="submit" value="Salvar" onclick="submitForm('salvar'); return false;" />
            <button type="button" onclick="submitForm('cancelar')">Cancelar</button>
        </form>

        ${mensagem}

        <table>
            <c:if test="${not empty mesas}">
                <tr>
                    <th>Número</th>
                    <th>Capacidade</th>
                    <th>Disponível</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="mesa" items="${mesas}">
                <tr>
                    <td>${mesa.numero}</td>
                    <td>${mesa.capacidade}</td>
                    <td>${mesa.disponivel}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}${URL_BASE}/MesaControlador" method="get">
                            <input type="hidden" name="numero" value="${mesa.numero}" />
                            <input type="hidden" name="capacidade" value="${mesa.capacidade}" />
                            <input type="hidden" name="disponivel" value="${mesa.disponivel}" />
                            <input type="hidden" name="opcao" value="editar" />
                            <button type="submit" class="button edit-button">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}${URL_BASE}/MesaControlador" method="get">
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
