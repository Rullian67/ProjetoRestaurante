package com.mycompany.mavenproject1.modelo.dao;

import com.mycompany.mavenproject1.modelo.entidade.Cliente;
import com.mycompany.mavenproject1.modelo.entidade.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// Supondo que a variável connection seja do tipo Connection
public class ClienteDao extends GenericoDAO<Cliente> {

    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    public void salvar(Cliente c) {
        String insert = "INSERT INTO Cliente (nome, email, telefone, cpf, senha) VALUES (?, ?, ?, ?, ?)";
        save(insert, c.getNome(), c.getEmail(), c.getTelefone(), c.getCpf(), c.getSenha());
    }

    public void alterar(Cliente c) {
        String update = "UPDATE Cliente SET nome = ?, email = ?, telefone = ?, cpf = ?, senha = ? WHERE id = ?";
        save(update, c.getNome(), c.getEmail(), c.getTelefone(), c.getCpf(), c.getSenha(), c.getId());
    }

    public void excluir(Cliente c) {
        String delete = "DELETE FROM Cliente WHERE id = ?";
        save(delete, c.getId());
    }

    public Cliente buscarPorId(int id) {
        String select = "SELECT * FROM Cliente WHERE id = ?";
        return buscarPorId(select, new ClienteRowMapper(), id);
    }

    public List<Cliente> buscarTodos() {
        String select = "SELECT * FROM Cliente";
        return buscarTodos(select, new ClienteRowMapper());
    }

    private Connection getConnection() throws SQLException {
        return connectionFactory.getConnection();
    }

  public void excluirPorCpf(String cpf) throws SQLException {
    String sql = "DELETE FROM Cliente WHERE cpf = ?";
    
    try (Connection conn = ConnectionFactory.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, cpf);
        int rowsAffected = stmt.executeUpdate();
        
        if (rowsAffected == 0) {
            throw new SQLException("Nenhum cliente encontrado com o CPF fornecido.");
        }
    }
}



    public Cliente findOrCreateCliente(String clienteNome) throws SQLException {
    Cliente cliente = null;
    
    // Primeiro, tente encontrar o cliente pelo nome
    String selectSql = "SELECT * FROM Cliente WHERE nome = ?";
    try (Connection conn = ConnectionFactory.getInstance().getConnection();
         PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
        
        selectStmt.setString(1, clienteNome);
        ResultSet rs = selectStmt.executeQuery();
        
        if (rs.next()) {
            // Cliente encontrado
            cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setSenha(rs.getString("senha"));
        } else {
            // Cliente não encontrado, então crie um novo
            String insertSql = "INSERT INTO Cliente (nome) VALUES (?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                insertStmt.setString(1, clienteNome);
                insertStmt.executeUpdate();
                
                // Obtenha o ID gerado para o novo cliente
                ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int newId = generatedKeys.getInt(1);
                    cliente = new Cliente();
                    cliente.setId(newId);
                    cliente.setNome(clienteNome);
                }
            }
        }
    }
    
    return cliente;
}


    public static class ClienteRowMapper implements RowMapper<Cliente> {
        @Override
        public Cliente mapRow(ResultSet rs) throws SQLException {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setSenha(rs.getString("senha"));
            return cliente;
        }
    }

    public Cliente buscarPorUsername(String nome) {
        String select = "SELECT * FROM Cliente WHERE nome = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setString(1, nome);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultSet.getInt("id"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setSenha(resultSet.getString("senha"));
                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Corrigido: Método para buscar um pedido por ID
    public Pedido buscarPedidoPorId(int id) throws SQLException {
        String query = "SELECT * FROM Pedido WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPedido(rs);
                } else {
                    return null;
                }
            }
        }
    }

    private Pedido mapResultSetToPedido(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getInt("id"));
       // pedido.setCliente(rs.getString("Cliente"));
       // pedido.setData(rs.getDate("Data_do_Pedido"));
        pedido.setProduto(rs.getString("Produtos"));
        pedido.setQuantidade(rs.getInt("Quantidade"));
         pedido.setTotal(rs.getDouble("Total"));  // Mantém o tipo double
        return pedido;
    }
}
