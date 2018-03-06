package br.com.placas.dao;

import br.com.placas.beans.Placas;
import br.com.placas.database.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PlacasDAO {
    Conexao conex = new Conexao();
    int idCliente;
    public void salvar(Placas placa){
        buscaCliente(placa.getCliente());
        conex.conecta();
        try {
            PreparedStatement pst = conex.con.prepareStatement("insert into placas"
            + "(cor_placa,cor_frase,frase,altura,largura,valor_placa,valor_entrada,"
            + "valor_total,data_entrega,status_encomenda,id_cliente) values(?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, placa.getCor_placa());
            pst.setString(2, placa.getCor_frase());
            pst.setString(3, placa.getFrase());
            pst.setDouble(4, placa.getAltura());
            pst.setDouble(5, placa.getLargura());
            pst.setDouble(6, placa.getValor_placa());
            pst.setDouble(7, placa.getValor_entrada());
            pst.setDouble(8, placa.getValor_total());
            pst.setString(9, placa.getData_entrega());
            pst.setString(10, placa.getStatus());
            pst.setInt(11, idCliente);
            pst.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Placa encomendada");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao encomendar placa" + ex);
        }
        conex.desconecta();
    }
    
    public void editar(Placas placa){
        conex.conecta();
        try {
            PreparedStatement pst = conex.con.prepareStatement("update placas set status_encomenda=? where id=?");
            pst.setString(1, placa.getStatus());
            pst.setInt(2, placa.getId());
            pst.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Status alterado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar status" + e);
        }
        conex.desconecta();
    }
    
    public void excluir(){
        conex.desconecta();
        
        conex.desconecta();
    }
    
    public void buscaCliente(String nome){
        conex.conecta();
        conex.executaSql("select *from clientes where nome='"+nome+"'");
        try {
            conex.rs.first();
            idCliente=conex.rs.getInt("id");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar Clientes: " + ex.getMessage());
        }
        conex.desconecta();
    }
    
    public List<Placas> listarPlacas() {
        conex.conecta();
        String sql = "select P.id,C.nome,P.data_encomenda,P.data_entrega,P.valor_placa,P.valor_entrada,P.status_encomenda from placas as P inner join clientes as C on (P.id_cliente=C.id) order by id";
        List<Placas> retorno = new ArrayList<>();
        try {
            PreparedStatement pst = conex.con.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();
            while (resultado.next()) {
                Placas placa = new Placas();
                placa.setId(resultado.getInt("id"));
                placa.setCliente(resultado.getString("nome"));
                placa.setData_encomenda(resultado.getString("data_encomenda"));
                placa.setData_entrega(resultado.getString("data_entrega"));
                placa.setValor_placa(resultado.getDouble("valor_placa"));
                placa.setValor_entrada(resultado.getDouble("valor_entrada"));
                placa.setStatus(resultado.getString("status_encomenda"));
                retorno.add(placa);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher tabela: " + ex.getMessage());
        }
        conex.desconecta();
        return retorno;
    }
}
