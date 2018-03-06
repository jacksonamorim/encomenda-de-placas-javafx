package br.com.placas.controllers;

import br.com.placas.beans.Cliente;
import br.com.placas.dao.ClienteDAO;
import br.com.placas.database.Conexao;
import br.com.placas.util.MaskFieldUtil;
import br.com.placas.util.Mensagem;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class ClienteController implements Initializable {
    Conexao conex = new Conexao();
    Cliente cliente = new Cliente();
    ClienteDAO dao = new ClienteDAO();
    Mensagem mensagem = new Mensagem();
    int flag=0;
    
    @FXML
    private JFXTextField txID,txNome,txTelefone,txEndereco;
    @FXML
    private JFXButton btnNovo,btnSalvar,btnCancelar,btnEditar,btnExcluir;
    @FXML
    private TableView<Cliente> tableClientes;
    @FXML
    private TableColumn<Cliente, Integer> columnID;
    @FXML
    private TableColumn<Cliente, String> columnNome;
    @FXML
    private TableColumn<Cliente, String> columnTelefone;
    @FXML
    private TableColumn<Cliente, String> columnEndereco;
    
    private List<Cliente> list;
    private ObservableList<Cliente> ob;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarClientes();
        tableClientes.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> selecionarClientes(newValue));
        MaskFieldUtil.foneField(this.txTelefone);
    }    
    
    @FXML
    private void novo(ActionEvent event){
        flag=1;
        txNome.setDisable(false);
        txTelefone.setDisable(false);
        txEndereco.setDisable(false);
        btnNovo.setDisable(true);
        btnSalvar.setDisable(false);
        btnCancelar.setDisable(false);
    }
    @FXML
    private void salvar(ActionEvent event){
        if (flag==1) {
            cliente.setNome(txNome.getText());
            cliente.setTelefone(txTelefone.getText());
            cliente.setEndereco(txEndereco.getText());
            if (txNome.getText().isEmpty()==true) {
                mensagem.mensagem("Preencha o campo NOME para continuar.");
                txNome.requestFocus();
            }else if (txTelefone.getText().isEmpty()==true) {
                mensagem.mensagem("Preencha o campo TELEFONE para continuar.");
                txTelefone.requestFocus();
            }else{
                dao.salvar(cliente);
                carregarClientes();
                txID.setText(null);
                txNome.setText(null);
                txTelefone.setText("");
                txEndereco.setText(null);
                txNome.setDisable(true);
                txTelefone.setDisable(true);
                txEndereco.setDisable(true);
                btnNovo.setDisable(false);
                btnSalvar.setDisable(true);
                btnCancelar.setDisable(true);
                btnEditar.setDisable(true);
                btnExcluir.setDisable(true);
            }
        }else{
            cliente.setNome(txNome.getText());
            cliente.setTelefone(txTelefone.getText());
            cliente.setEndereco(txEndereco.getText());
            cliente.setId(Integer.parseInt(txID.getText()));
            if (txNome.getText().isEmpty()==true) {
                mensagem.mensagem("Preencha o campo NOME para continuar.");
                txNome.requestFocus();
            }else if (txTelefone.getText().isEmpty()==true) {
                mensagem.mensagem("Preencha o campo TELEFONE para continuar.");
                txTelefone.requestFocus();
            }else{
                dao.editar(cliente);
                carregarClientes();
                txID.setText(null);
                txNome.setText(null);
                txTelefone.setText(null);
                txEndereco.setText(null);
                txNome.setDisable(true);
                txTelefone.setDisable(true);
                txEndereco.setDisable(true);
                btnNovo.setDisable(false);
                btnSalvar.setDisable(true);
                btnCancelar.setDisable(true);
                btnEditar.setDisable(true);
                btnExcluir.setDisable(true);
            }
        }
    }
    @FXML
    private void cancelar(ActionEvent event){
        txID.setText(null);
        txNome.setText(null);
        txTelefone.setText(null);
        txEndereco.setText(null);
        txNome.setDisable(true);
        txTelefone.setDisable(true);
        txEndereco.setDisable(true);
        btnNovo.setDisable(false);
        btnSalvar.setDisable(true);
        btnCancelar.setDisable(true);
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);
    }
    @FXML
    private void editar(ActionEvent event){
        flag=2;
        txNome.setDisable(false);
        txTelefone.setDisable(false);
        txEndereco.setDisable(false);
        btnNovo.setDisable(true);
        btnSalvar.setDisable(false);
        btnCancelar.setDisable(false);
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);
    }
    @FXML
    private void excluir(ActionEvent event){
        cliente.setId(Integer.parseInt(txID.getText()));
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este usuario?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma==JOptionPane.YES_OPTION) {
            dao.excluir(cliente);
            carregarClientes();
            txID.setText(null);
            txNome.setText(null);
            txTelefone.setText(null);
            txEndereco.setText(null);
            txNome.setDisable(true);
            txTelefone.setDisable(true);
            txEndereco.setDisable(true);
            btnNovo.setDisable(false);
            btnSalvar.setDisable(true);
            btnCancelar.setDisable(true);
            btnEditar.setDisable(true);
            btnExcluir.setDisable(true);
        }
    }
    
    public void carregarClientes(){
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        columnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        list = dao.listarClientes();
        ob = FXCollections.observableArrayList(list);
        tableClientes.setItems(ob);
    }
    
    public void selecionarClientes(Cliente cli){
        if (cli != null) {
            txID.setText(String.valueOf(cli.getId()));
            txNome.setText(cli.getNome());
            txEndereco.setText(cli.getEndereco());
            txTelefone.setText(cli.getTelefone());
            txNome.setDisable(true);
            txTelefone.setDisable(true);
            txEndereco.setDisable(true);
            btnSalvar.setDisable(true);
            btnCancelar.setDisable(false);
            btnEditar.setDisable(false);
            btnExcluir.setDisable(false);
            btnNovo.setDisable(true);
        } else {
            txID.setText(null);
            txNome.setText(null);
            txEndereco.setText(null);
            txTelefone.setText(null);
        }
    }
}
