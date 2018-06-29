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
import model.Professor;

/**
 *
 * @author renil
 */
public class ProfessorDAO {
    
    static Connection connection = null;
    
    public static boolean cadastraProfessor(Professor p){

        try {
            connection = getConexao();

            PreparedStatement ps = connection.prepareStatement("INSERT INTO professor (prof_nome) VALUES (?)");

            ps.setString(1, p.getNome());
            
            ps.execute();

            connectionClose(connection) ;

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static List<Professor> buscaProfessor(String nome) {

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
}
