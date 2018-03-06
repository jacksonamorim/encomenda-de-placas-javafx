package br.com.placas.controllers;

import br.com.placas.beans.Placas;
import br.com.placas.dao.PlacasDAO;
import br.com.placas.database.Conexao;
import br.com.placas.util.Mensagem;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

public class PlacaController implements Initializable {
    Conexao conex = new Conexao();
    Placas placa = new Placas();
    PlacasDAO dao = new PlacasDAO();
    Mensagem mensagem = new Mensagem();
    @FXML
    private JFXComboBox<String> cbCliente,cbCorPlaca,cbCorFrase,cbStatus;
    @FXML
    private JFXTextArea txFrase;
    @FXML
    private Label lblQL;
    @FXML
    private JFXTextField txAltura,txLargura,txValorPlaca,txValorEntrada,txValorTotal;
    @FXML
    private JFXDatePicker dtDataEntrega;
    @FXML
    private JFXButton btnNovo,btnSalvar,btnCancelar,btnCalcular;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherClientes();
        ObservableList<String> cor_placa = FXCollections.observableArrayList("Branca","Cinza");
        cbCorPlaca.setItems(cor_placa);
        ObservableList<String> cor_frase = FXCollections.observableArrayList("Azul","Amarelo","Preto","Verde","Vermelho");
        cbCorFrase.setItems(cor_frase);
        ObservableList<String> status = FXCollections.observableArrayList("Aberto");
        cbStatus.setItems(status);
        cbStatus.setValue("Aberto");
        
    }    
    
    @FXML
    private void novo(ActionEvent event) {
        cbCliente.setDisable(false);
        cbCorFrase.setDisable(false);
        cbCorPlaca.setDisable(false);
        txFrase.setDisable(false);
        txAltura.setDisable(false);
        txLargura.setDisable(false);
        txValorPlaca.setDisable(false);
        txValorEntrada.setDisable(false);
        txValorTotal.setDisable(false);
        dtDataEntrega.setDisable(false);
        btnNovo.setDisable(true);
        btnSalvar.setDisable(false);
        btnCancelar.setDisable(false);
        btnCalcular.setDisable(false);
    }
    
    @FXML
    private void salvar(ActionEvent event) {
        placa.setCor_placa(cbCorPlaca.getValue());
        placa.setCor_frase(cbCorFrase.getValue());
        placa.setFrase(txFrase.getText());
        placa.setAltura(Double.parseDouble(txAltura.getText()));
        placa.setLargura(Double.parseDouble(txLargura.getText()));
        placa.setValor_placa(Double.parseDouble(txValorPlaca.getText().replace(",", ".")));
        placa.setValor_entrada(Double.parseDouble(txValorEntrada.getText().replace(",", ".")));
        placa.setValor_total(Double.parseDouble(txValorTotal.getText().replace(",", ".")));
        placa.setData_entrega(String.valueOf(dtDataEntrega.getValue()));
        placa.setStatus(cbStatus.getValue());
        placa.setCliente(cbCliente.getValue());
        if (cbCliente.getValue()==null) {
            mensagem.mensagem("Escolha um CLIENTE para continuar.");
            cbCliente.requestFocus();
        }else if (cbCorPlaca.getValue()==null) {
            mensagem.mensagem("Escolha a COR DA PLACA para continuar.");
            cbCorPlaca.requestFocus();
        }else if (cbCorFrase.getValue()==null) {
            mensagem.mensagem("Escolha a COR DA FRASE para continuar.");
            cbCorFrase.requestFocus();
        }else if (txAltura.getText().isEmpty()==true) {
            mensagem.mensagem("Digite a ALTURA da placa para continuar.");
            txAltura.requestFocus();
        }else if (txLargura.getText().isEmpty()==true) {
            mensagem.mensagem("Digite a LARGURA da plca para continuar.");
            txLargura.requestFocus();
        }else if (txValorEntrada.getText().isEmpty()==true) {
            mensagem.mensagem("Digite o VALOR DE ENTRADA para continuar.");
            txValorEntrada.requestFocus();
        }else if (dtDataEntrega.getValue()==null) {
            mensagem.mensagem("Escolha a DATA DE ENTREGA para continuar.");
            dtDataEntrega.requestFocus();
        }else{
            dao.salvar(placa);
            cbCliente.setValue(null);
            cbCorFrase.setValue(null);
            cbCorPlaca.setValue(null);
            txFrase.setText(null);
            lblQL.setText("0");
            txAltura.setText(null);
            txLargura.setText(null);
            txValorPlaca.setText(null);
            txValorEntrada.setText(null);
            txValorTotal.setText(null);
            dtDataEntrega.setValue(null);
            cbCliente.setDisable(true);
            cbCorFrase.setDisable(true);
            cbCorPlaca.setDisable(true);
            txFrase.setDisable(true);
            txAltura.setDisable(true);
            txLargura.setDisable(true);
            txValorPlaca.setDisable(true);
            txValorEntrada.setDisable(true);
            txValorTotal.setDisable(true);
            dtDataEntrega.setDisable(true);
            cbStatus.setDisable(true);
            btnNovo.setDisable(false);
            btnSalvar.setDisable(true);
            btnCancelar.setDisable(true);
            btnCalcular.setDisable(true);
        }
    }
    
    @FXML
    private void cancelar(ActionEvent event) {
        cbCliente.setValue(null);
        cbCorFrase.setValue(null);
        cbCorPlaca.setValue(null);
        txFrase.setText(null);
        lblQL.setText("0");
        txAltura.setText(null);
        txLargura.setText(null);
        txValorPlaca.setText(null);
        txValorEntrada.setText(null);
        txValorTotal.setText(null);
        dtDataEntrega.setValue(null);
        cbStatus.setValue("Aberto");
        cbCliente.setDisable(true);
        cbCorFrase.setDisable(true);
        cbCorPlaca.setDisable(true);
        txFrase.setDisable(true);
        txAltura.setDisable(true);
        txLargura.setDisable(true);
        txValorPlaca.setDisable(true);
        txValorEntrada.setDisable(true);
        txValorTotal.setDisable(true);
        dtDataEntrega.setDisable(true);
        btnNovo.setDisable(false);
        btnSalvar.setDisable(true);
        btnCancelar.setDisable(true);
        btnCalcular.setDisable(true);
    }
    
    @FXML
    private void quantLetras(KeyEvent event){
        int spaces = 0;
        int letras = txFrase.getText().length();
        for(char c : txFrase.getText().toCharArray()){
            if (c == ' ') {
                spaces++;
            }
        }
        int frase = letras - spaces;
        lblQL.setText(String.valueOf(frase));
    }
    
    @FXML
    private void calculos(ActionEvent event){
        Locale.setDefault(new Locale("pt", "BR"));
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("#,##0.00");
        double quantL = Double.parseDouble(lblQL.getText());
        double altura = Double.parseDouble(txAltura.getText());
        double largura = Double.parseDouble(txLargura.getText());
        double area = altura * largura;
        double custo_material = area * 147.30;
        double custo_desenho = quantL * 0.32;
        double valor_placa = custo_material + custo_desenho;
        double valor_parcial = valor_placa/2;
        txValorPlaca.setText(String.valueOf(df.format(valor_placa)));
        double entrada = Double.parseDouble(txValorEntrada.getText());
        Double valor_total = valor_placa - entrada;
        if (entrada<valor_parcial) {
            mensagem.mensagem("O valor mínimo de entrada é de 50% do valor da placa");
        }else{
            txValorTotal.setText(String.valueOf(df.format(valor_total)));
        }
    }
    
    public void preencherClientes(){
        conex.conecta();
        conex.executaSql("select *from clientes order by nome");
        try {
            ObservableList<String> op = FXCollections.observableArrayList();
            conex.rs.first();
            do{
                String cliente = conex.rs.getString("nome");
                op.add(cliente);
            }while(conex.rs.next());
            cbCliente.setItems(op);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher lista de clientes" + ex);
        }
        conex.desconecta();
    }
}
