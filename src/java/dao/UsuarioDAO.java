/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.PoolConexao.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Usuario;

/**
 *
 * @author Caroline
 */
public class UsuarioDAO {

    static Connection connection = null;

    public static Usuario buscaPorEmailESenha(String email, String senha) {

        try {

            connection = PoolConexao.getConexao();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuario WHERE usu_nome like ? AND usu_senha like ?");

            ps.setString(1, email);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();
            Usuario usuario;
            if (rs.next()) {
                usuario = new Usuario(rs.getString("usu_nome"), rs.getString("usu_senha"));
            } else {
                usuario = null;
            }

            connectionClose(connection);

            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
