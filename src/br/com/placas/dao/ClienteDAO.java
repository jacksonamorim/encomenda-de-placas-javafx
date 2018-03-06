package br.com.placas.dao;

import br.com.placas.beans.Cliente;
import br.com.placas.database.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO {
   Conexao conex = new Conexao();
   
   public void salvar(Cliente cliente){
       conex.conecta();
       try {
           PreparedStatement pst = conex.con.prepareStatement("insert into clientes(nome, telefone, endereco) values(?,?,?)");
           pst.setString(1, cliente.getNome());
           pst.setString(2, cliente.getTelefone());
           pst.setString(3, cliente.getEndereco());
           pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar clinte: " + ex.getMessage());
        }
       conex.desconecta();
   }
   
   public void editar(Cliente cliente){
       conex.conecta();
       try {
           PreparedStatement pst = conex.con.prepareStatement("update clientes set nome=?,telefone=?,endereco=? where id=?");
           pst.setString(1, cliente.getNome());
           pst.setString(2, cliente.getTelefone());
           pst.setString(3, cliente.getEndereco());
           pst.setInt(4, cliente.getId());
           pst.executeUpdate();
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao alterar dados: " + ex.getMessage());
       }
       conex.desconecta();
   }
   
   public void excluir(Cliente cliente){
       conex.conecta();
       try {
           PreparedStatement pst = conex.con.prepareStatement("delete from clientes where id=?");
           pst.setInt(1, cliente.getId());
           pst.executeUpdate();
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro excluir cliente: " + ex.getMessage());
       }
       conex.desconecta();
   }
   
   public List<Cliente> listarClientes() {
        conex.conecta();
        String sql = "SELECT * FROM clientes";
        List<Cliente> retorno = new ArrayList<>();
        try {
            PreparedStatement pst = conex.con.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();
            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setTelefone(resultado.getString("telefone"));
                cliente.setEndereco(resultado.getString("endereco"));
                retorno.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher tablea: " + ex.getMessage());
        }
        conex.desconecta();
        return retorno;
    }
}
