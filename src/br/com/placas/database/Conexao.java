package br.com.placas.database;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexao {
    public Statement stm;
    public ResultSet rs;
    private String driver = "com.mysql.jdbc.Driver";
    private String caminho = "jdbc:mysql://localhost:3306/placasdb";
    private String usuario = "root";
    private String senha = "";
    public Connection con;
    
    public void conecta(){
        System.setProperty("jdbc.Drivers", driver);
        try {
            con=DriverManager.getConnection(caminho, usuario, senha);
            //JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao conectar com o banco de dados" + ex);
        }
    }
    
    public void executaSql(String sql){
        try {
            stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro executar SQL: " + ex.getMessage());
        }
    }
    
    public void desconecta(){
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + ex);
        }
    }
}
