package br.com.placas.controllers;

import br.com.placas.beans.Placas;
import br.com.placas.dao.PlacasDAO;
import br.com.placas.database.Conexao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EncomendasController implements Initializable {
    Conexao conex = new Conexao();
    Placas placa = new Placas();
    PlacasDAO dao = new PlacasDAO();
    
    @FXML
    private JFXTextField txID;
    @FXML
    private JFXComboBox<String> cbStatus;
    @FXML
    private JFXButton btnSalvar,btnCancelar,btnEditar,btnExcluir;
    @FXML
    private TableView<Placas> tablePlacas;
    @FXML
    private TableColumn<Placas, Integer> id;
    @FXML
    private TableColumn<Placas, String> dataencomenda;
    @FXML
    private TableColumn<Placas, String> cliente;
    @FXML
    private TableColumn<Placas, String> dataentrega;
    @FXML
    private TableColumn<Placas, String> status;
    @FXML
    private TableColumn<Placas, Double> valorentrada;
    @FXML
    private TableColumn<Placas, Double> valorplaca;
    
    public List<Placas> list;
    public ObservableList<Placas> ob;
    
    ObservableList<String> status2 = FXCollections.observableArrayList("Pronto","Cancelado");
    ObservableList<String> status3 = FXCollections.observableArrayList("Cancelado","Fechado");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarEncomendas();
        tablePlacas.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> selecionarClientes(newValue));
    }    
    
    @FXML
    private void salvar(){
        placa.setStatus(cbStatus.getValue());
        placa.setId(Integer.parseInt(txID.getText()));
        dao.editar(placa);
        carregarEncomendas();
        txID.setText(null);
        cbStatus.setValue(null);
        cbStatus.setDisable(true);
        btnSalvar.setDisable(true);
        btnCancelar.setDisable(true);
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);
    }
    @FXML
    private void cancelar(){
        txID.setText(null);
        cbStatus.setValue(null);
        cbStatus.setDisable(true);
        btnSalvar.setDisable(true);
        btnCancelar.setDisable(true);
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);
    }
    @FXML
    private void editar(){
        cbStatus.setDisable(false);
        btnSalvar.setDisable(false);
        btnCancelar.setDisable(false);
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);
    }
    @FXML
    private void excluir(){
        carregarEncomendas();
        txID.setText(null);
        cbStatus.setValue(null);
        cbStatus.setDisable(true);
        btnSalvar.setDisable(true);
        btnCancelar.setDisable(true);
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);
    }
    
    public void carregarEncomendas(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        dataencomenda.setCellValueFactory(new PropertyValueFactory<>("data_encomenda"));
        dataentrega.setCellValueFactory(new PropertyValueFactory<>("data_entrega"));
        valorplaca.setCellValueFactory(new PropertyValueFactory<>("valor_placa"));
        valorentrada.setCellValueFactory(new PropertyValueFactory<>("valor_entrada"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        list = dao.listarPlacas();
        ob = FXCollections.observableArrayList(list);
        tablePlacas.setItems(ob);
    }
     
    public void selecionarClientes(Placas placa){
        if (placa != null) {
            txID.setText(String.valueOf(placa.getId()));
            cbStatus.setValue(placa.getStatus());
            cbStatus.setDisable(true);
            btnSalvar.setDisable(true);
            btnCancelar.setDisable(false);
            btnEditar.setDisable(false);
            btnExcluir.setDisable(false);
            if (cbStatus.getValue().equals("Aberto")) {
                cbStatus.setItems(status2);
            }else if (cbStatus.getValue().equals("Pronto")) {
                cbStatus.setItems(status3);
            }else if (cbStatus.getValue().equals("Fechado")) {
                cbStatus.setDisable(true);
                btnSalvar.setDisable(true);
                btnCancelar.setDisable(true);
                btnEditar.setDisable(true);
                btnExcluir.setDisable(true);
            }else if (cbStatus.getValue().equals("Cancelado")) {
                cbStatus.setDisable(true);
                btnSalvar.setDisable(true);
                btnCancelar.setDisable(true);
                btnEditar.setDisable(true);
                btnExcluir.setDisable(true);
            }
        } else {
            txID.setText(null);
            cbStatus.setValue(null);
        }
    }
}
