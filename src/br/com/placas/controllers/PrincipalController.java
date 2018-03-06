package br.com.placas.controllers;

import br.com.placas.main.Login;
import br.com.placas.main.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PrincipalController implements Initializable {
    @FXML
    private StackPane root, rootP;
    @FXML
    private AnchorPane home;
    @FXML
    private JFXButton btnHome,btnInfo,btnCliente,btnEncomenda,btnEncomendas,btnLogout,btnClose;
    
    public static StackPane root0, root2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        root0=rootP;
        root2=root;
    }    
    
    @FXML
    private void handleButton(ActionEvent event ) throws IOException{
        if (event.getSource()==btnHome) {
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.7), home);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            fadeIn.play();
            home.toFront();
        }else if (event.getSource()==btnInfo) {
            AnchorPane info = FXMLLoader.load(getClass().getResource("/br/com/placas/view/Info.fxml"));
            JFXDialog development = new JFXDialog(root, info, JFXDialog.DialogTransition.CENTER);
            development.show();
        }else if (event.getSource()==btnCliente) {
            AnchorPane cliente = FXMLLoader.load(getClass().getResource("/br/com/placas/view/Cliente.fxml"));
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.7), cliente);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            fadeIn.play();
            root.getChildren().addAll(cliente);
            cliente.toFront();
        }else if (event.getSource()==btnEncomenda) {
            AnchorPane encomenda = FXMLLoader.load(getClass().getResource("/br/com/placas/view/Placa.fxml"));
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.7), encomenda);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            fadeIn.play();
            root.getChildren().addAll(encomenda);
            encomenda.toFront();
        }else if (event.getSource()==btnEncomendas) {
            AnchorPane encomendas = FXMLLoader.load(getClass().getResource("/br/com/placas/view/Encomendas.fxml"));
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.7), encomendas);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            fadeIn.play();
            root.getChildren().addAll(encomendas);
            encomendas.toFront();
        } else if (event.getSource()==btnLogout) {
            try {
                new Login().start(new Stage());
                ScaleTransition stIn = new ScaleTransition(Duration.seconds(0.5), LoginController.rootA);
                stIn.setFromX(0);//Começa do valor minimo do eixo X 
                stIn.setFromY(0);// Começa do valor minimo do eixo Y
                stIn.setToX(1);// Seta para o valor maximo do card
                stIn.setToY(1);// Seta para o valor maximo do card
                stIn.setCycleCount(1);//Conta o numero de vezes que vai fazer a ação
                stIn.play(); // Inicia a transição(Efeito zoom)
                Main.getStage().close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (event.getSource()==btnClose) {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), rootP);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
            fadeOut.play();
            fadeOut.setOnFinished((e) -> {
                Platform.exit();
            });
        }
    }
}
