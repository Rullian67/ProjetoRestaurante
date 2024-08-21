/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.modelo.dao;

/**
 *
 * @author rulli
 */

import com.mycompany.mavenproject1.modelo.entidade.Prato;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PratoDao extends GenericoDAO<Prato> {

    public void salvar(Prato p) {
        String insert = "INSERT INTO Prato (nome, descricao, preco, categoria_id, fornecedor_id, inventario_id) VALUES (?, ?, ?, ?, ?, ?)";
        save(insert, p.getNome(), p.getDescricao(), p.getPreco(), p.getCategoriaId(), p.getFornecedorId(), p.getInventarioId());
    }

    public void alterar(Prato p) {
        String update = "UPDATE Prato SET nome = ?, descricao = ?, preco = ?, categoria_id = ?, fornecedor_id = ?, inventario_id = ? WHERE id = ?";
        save(update, p.getNome(), p.getDescricao(), p.getPreco(), p.getCategoriaId(), p.getFornecedorId(), p.getInventarioId(), p.getId());
    }

    public void excluir(Prato p) {
        String delete = "DELETE FROM Prato WHERE id = ?";
        save(delete, p.getId());
    }

    public Prato buscarPorId(int id) {
        String select = "SELECT * FROM Prato WHERE id = ?";
        return buscarPorId(select, new PratoRowMapper(), id);
    }

    public List<Prato> buscarTodos() {
        String select = "SELECT * FROM Prato";
        return buscarTodos(select, new PratoRowMapper());
    }

    public static class PratoRowMapper implements RowMapper<Prato> {
        @Override
        public Prato mapRow(ResultSet rs) throws SQLException {
            Prato prato = new Prato();
            prato.setId(rs.getInt("id"));
            prato.setNome(rs.getString("nome"));
            prato.setDescricao(rs.getString("descricao"));
            prato.setPreco(rs.getDouble("preco"));
            prato.setCategoriaId(rs.getInt("categoria_id"));
            prato.setFornecedorId(rs.getInt("fornecedor_id"));
            prato.setInventarioId(rs.getInt("inventario_id"));
            return prato;
        }
    }
}

