package GerenciaEvento.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Master
 */
public class Conexao { 
    public static Connection getConexao() {
        try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gerenciamento_evento", "root", "");
        System.out.print("Conectado com sucesso!");
        return connection;
        } catch(SQLException e){
            System.out.print("Erro ao conectar com o banco de dados!\nErro: " + e);
        }
        return null;
    }
}
