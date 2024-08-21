package com.mycompany.mavenproject1.modelo.dao;

import com.mycompany.mavenproject1.modelo.dao.ConnectionFactory;
import com.mycompany.mavenproject1.modelo.entidade.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PedidoDao {

    public void salvar(Pedido pedido) {
        String sql = "INSERT INTO Pedido (Cliente, Produtos, Quantidade, Data_do_Pedido, Total) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, pedido.getCliente().getNome());
            stmt.setString(2, pedido.getProduto());
            stmt.setInt(3, pedido.getQuantidade());
            stmt.setDate(4, new java.sql.Date(pedido.getDataPedido().getTime()));
            stmt.setDouble(5, pedido.getTotal());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Tratar exceções adequadamente
        }
    }
}
