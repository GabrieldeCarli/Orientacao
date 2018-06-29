/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.PoolConexao.connectionClose;
import static dao.PoolConexao.getConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Orientacao;

/**
 *
 * @author renil
 */
public class OrientacaoDAO {
    
    static Connection connection = null;

    public static boolean cadastraOrientacao(Orientacao o) {

        try {
            connection = getConexao();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO orientacao (prof_id, ori_aluno, ori_tema) VALUES (?,?,?)");

            ps.setInt(1, o.getProfessor());
            ps.setString(2, o.getAluno());
            ps.setString(3, o.getTema());

            ps.execute();

            PoolConexao.connectionClose(connection);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OrientacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static List<Orientacao> buscaOrientacao(String aluno, String professor, String tema) {

        try {
            connection = getConexao();
            String sql = "SELECT orientacao.ori_aluno, orientacao.ori_tema, professor.prof_id FROM orientacao "
                    + "INNER JOIN professor ON professor.prof_id = orientacao.prof_id "
                    + "WHERE CONCAT(orientacao.ori_aluno, orientacao.ori_tema, professor.prof_nome) like ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            String search = aluno + tema + professor;
            ps.setString(1, "%" + search + "%");

            ResultSet rs = ps.executeQuery();
            List<Orientacao> orientacoes = new ArrayList<>();
            while (rs.next()) {
                orientacoes.add(new Orientacao(rs.getString("ori_aluno"), rs.getInt("prof_id"), rs.getString("ori_tema")));
            }

            connectionClose(connection);
            return orientacoes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
