package com.mycompany.mavenproject1.controlador;

import com.mycompany.mavenproject1.modelo.dao.ClienteDao;
import com.mycompany.mavenproject1.modelo.dao.PedidoDao;
import com.mycompany.mavenproject1.modelo.entidade.Cliente;
import com.mycompany.mavenproject1.modelo.entidade.Pedido;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/PedidoControlador")
public class PedidoControlador extends HttpServlet {

    private PedidoDao pedidoDao;
    private ClienteDao clienteDao;

    @Override
    public void init() throws ServletException {
        super.init();
        pedidoDao = new PedidoDao();
        clienteDao = new ClienteDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String clienteNome = request.getParameter("clienteNome");
        String produto = request.getParameter("produto");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        Date dataPedido = Date.valueOf(request.getParameter("dataPedido"));

        Pedido pedido = new Pedido();

        try {
            Cliente cliente = clienteDao.findOrCreateCliente(clienteNome); // Pode lançar SQLException
            pedido.setCliente(cliente);
            pedido.setProduto(produto);
            pedido.setQuantidade(quantidade);
            pedido.setDataPedido(dataPedido);

            pedidoDao.salvar(pedido);

            response.sendRedirect("listagemPedidos.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erro ao processar o pedido: " + e.getMessage());
            request.getRequestDispatcher("cadastroPedido.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> produtos = new ArrayList<>();
        produtos.add("Produto A");
        produtos.add("Produto B");
        produtos.add("Produto C");

        request.setAttribute("produtos", produtos);
        request.getRequestDispatcher("cadastroPedido.jsp").forward(request, response);
    }
}
