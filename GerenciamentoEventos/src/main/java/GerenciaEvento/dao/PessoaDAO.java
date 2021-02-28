package GerenciaEvento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import GerenciaEvento.model.Pessoa;
import GerenciaEvento.utils.Conexao;

/**
 *
 * @author Master
 */
public class PessoaDAO {
    private Connection connection = Conexao.getConexao();
    
    public void save (Pessoa pessoa){
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO PESSOAS (NOME, SOBRENOME, NOMESALA, LOTACAO, NOMEESPACO) VALUES (?,?,?,?,?)");
            ps.setString(1, "NOME");
            ps.setString(2, "SOBRENOME");
            ps.setString(3, "NOMESALA");
            ps.setInt(4, 300);
            ps.setString(5, "NOMEEEPACO");
            ps.execute();
            JOptionPane.showMessageDialog(null, "Pessoa cadastrada com sucesso!");
        } catch(SQLException ex){
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void update (Pessoa pessoa){
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE PESSOAS SET NOME=?, SOBRENOME=? WHERE ID=?");
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getSobreNome());
            ps.setString(3, pessoa.getNomeSala());
            ps.setInt(4, pessoa.getLotacao());
            ps.setString(5, pessoa.getNomeEspaco());
            ps.setInt(6, pessoa.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Pessoa atualizada com sucesso!");
        } catch(SQLException ex){
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
     
    public void saveOrUpdate(Pessoa pessoa){
         if (pessoa.getId() == 0)
             save(pessoa);
         else
            update(pessoa); 
         
    }
    
    public void delete (Pessoa pessoa){
        try{
            PreparedStatement ps = connection.prepareStatement("DELETE FROM PESSOAS WHERE ID=?");
            ps.setInt(1, pessoa.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Pessoa excluida com sucesso!");
        } catch(SQLException ex){
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public List<Pessoa> getAll(){
        List<Pessoa> pessoas = new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PESSOAS");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("ID"));
                pessoa.setNome(rs.getString("NOME"));
                pessoa.setSobreNome(rs.getString("SOBRENOME"));
                pessoa.setNomeSala(rs.getString("NOMESALA"));
                pessoa.setLotacao(rs.getInt("LOTACAO"));
                pessoa.setNomeEspaco(rs.getString("NOMEESPACO"));
                pessoas.add(pessoa);
            }
            return pessoas;
        } catch(SQLException ex){
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }      
        return null;
    }
}
