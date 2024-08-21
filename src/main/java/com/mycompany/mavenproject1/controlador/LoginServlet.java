package com.mycompany.mavenproject1.controlador;

import com.mycompany.mavenproject1.modelo.dao.ClienteDao;
import com.mycompany.mavenproject1.modelo.entidade.Cliente;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private ClienteDao clienteDao = new ClienteDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");

        Cliente cliente = clienteDao.buscarPorUsername(nome);

        if (cliente != null && cliente.getSenha().equals(senha)) {
            HttpSession session = request.getSession();
            session.setAttribute("cliente", cliente);
            response.sendRedirect("CadastroPedido.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid nome or senha");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
