package com.mycompany.mavenproject1.controlador;

import com.mycompany.mavenproject1.modelo.dao.ClienteDao;
import com.mycompany.mavenproject1.modelo.entidade.Cliente;
import com.mycompany.mavenproject1.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/ClienteControlador")
public class ClienteControlador extends HttpServlet {
    private ClienteDao clienteDao = new ClienteDao();
    private Object ClienteDAO;
    private Object cliente;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcao = request.getParameter("opcao");
        if (opcao != null && opcao.equals("excluir")) {
            String cpf = request.getParameter("cpf");
            clienteDao.excluirPorCpf(cpf);
        }
        List<Cliente> clientes = clienteDao.buscarTodos();
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("/CadastroCliente.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcao = request.getParameter("opcao");

        if (opcao.equals("salvar")) {
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String telefone = request.getParameter("telefone");
            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("senha");

            Cliente cliente = new Cliente(nome, email, telefone, cpf, senha);
            clienteDao.salvar(cliente);
        }

        doGet(request, response);
    }
    
   private void mostrarDetalhesCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    try {
        Cliente cliente = clienteDao.buscarPorId(id);  // Certifique-se de que este método existe e está funcionando
        if (cliente != null) {
            request.setAttribute("cliente", cliente);
            RequestDispatcher dispatcher = request.getRequestDispatcher("detalhesCliente.jsp");
            dispatcher.forward(request, response);
        } else {
            // Cliente não encontrado, redirecionar para uma página de erro ou mensagem
            response.sendRedirect("erro.jsp?mensagem=Cliente não encontrado");
        }
    } catch (NumberFormatException e) {
        // Tratar o caso em que o parâmetro 'id' não é um número válido
        response.sendRedirect("erro.jsp?mensagem=ID inválido");
    }
}

}

