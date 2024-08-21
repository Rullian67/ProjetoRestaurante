package com.mycompany.mavenproject1.controlador;

import com.mycompany.mavenproject1.modelo.dao.MesaDao;
import com.mycompany.mavenproject1.modelo.entidade.Mesa;
import com.mycompany.mavenproject1.servico.WebConstantes;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/MesaControlador")
public class MesaControlador extends HttpServlet {
    private MesaDao mesaDao = new MesaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcao = request.getParameter("opcao");
        if (opcao != null && opcao.equals("excluir")) {
            int numero = Integer.parseInt(request.getParameter("numero"));
            mesaDao.excluirPorNumero(numero);
        }
        List<Mesa> mesas = mesaDao.buscarTodas();
        request.setAttribute("mesas", mesas);
        request.getRequestDispatcher("/CadastroMesa.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcao = request.getParameter("opcao");

        if (opcao.equals("salvar")) {
            int numero = Integer.parseInt(request.getParameter("numero"));
            int capacidade = Integer.parseInt(request.getParameter("capacidade"));
            boolean disponivel = Boolean.parseBoolean(request.getParameter("disponivel"));

            Mesa mesa = new Mesa(numero, capacidade, disponivel);
            mesaDao.salvar(mesa);
        }

        doGet(request, response);
    }
}
