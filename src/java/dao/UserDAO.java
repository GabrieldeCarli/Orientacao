/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import static dao.PoolConexao.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Orientacao;
import model.Orientacao;
import model.Professor;
import model.Professor;
import model.Usuario;
import model.Usuario;

/**
 *
 * @author Caroline
 */
public class UserDAO {

    static Connection connection = null;

    public Usuario buscaPorEmailESenha(String email, String senha) {

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

            connectionClose(connection) ;

            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean cadastraProfessor(String nome) throws Exception {

        try {
            connection = getConexao();

            PreparedStatement ps = connection.prepareStatement("INSERT INTO professor (prof_nome) VALUES (?)");

            ps.setString(1, nome);
            
            ps.execute();

            connectionClose(connection) ;

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean cadastraOrientacao(String tema, String aluno, int professor_id) throws Exception {

        try {
             connection = getConexao();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO orientacao (prof_id, ori_aluno, ori_tema) VALUES (?,?,?)");

            ps.setInt(1, professor_id);
            ps.setString(2, aluno);
            ps.setString(3, tema);

            ps.execute();

            PoolConexao.connectionClose(connection) ;

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public  List<Professor> buscaProfessor(String nome) {

        try {
            connection = getConexao();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM professor WHERE prof_nome like ? ORDER BY prof_nome");
            ps.setString(1, "%"+nome+"%");

            ResultSet rs = ps.executeQuery();
            List<Professor> professores = new ArrayList<>();
                        
            while (rs.next()) {
                professores.add(new Professor(rs.getString("prof_nome")));
            }
            connectionClose(connection) ;
            return professores;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Orientacao> buscaOrientacao(String aluno, String professor, String tema) {

        try {
             connection = getConexao();
            String sql = "SELECT orientacao.ori_aluno, orientacao.ori_tema, professor.prof_nome FROM orientacao "
                    + "INNER JOIN professor ON professor.prof_id = orientacao.prof_id "
                    + "WHERE CONCAT(orientacao.ori_aluno, orientacao.ori_tema, professor.prof_nome) like ?";
         
            PreparedStatement ps = connection.prepareStatement(sql);
            String search = aluno+tema+professor;
            ps.setString(1, "%"+search+"%");


            ResultSet rs = ps.executeQuery();
            List<Orientacao> orientacoes = new ArrayList<>();
            while (rs.next()) {
                orientacoes.add(new Orientacao(rs.getString("ori_aluno"), rs.getString("prof_nome"), rs.getString("ori_tema")));
            }
            
            connectionClose(connection) ;
            return orientacoes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
