package br.com.placas.controllers;

import br.com.placas.main.Login;
import br.com.placas.database.Conexao;
import br.com.placas.main.Main;
import br.com.placas.util.Mensagem;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController implements Initializable {
    Conexao con = new Conexao();
    Mensagem msg = new Mensagem();
    @FXML
    private StackPane root;
    @FXML
    private JFXTextField txUsuario;
    @FXML
    private JFXPasswordField txSenha;
    @FXML
    private JFXButton btnLogin;
    
    public static StackPane rootA;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //con.conecta();
        rootA=root;
    }    
    
    @FXML
    private void logar(ActionEvent event){
        if (txUsuario.getText().equals("admin")&&txSenha.getText().equals("admin")) {
            try{
                new Main().start(new Stage());
                FadeTransition fade = new FadeTransition(Duration.seconds(2), PrincipalController.root0);
                fade.setFromValue(0);
                fade.setToValue(1);
                fade.setCycleCount(1);
                fade.play();
                Login.getStage().close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            msg.mensagem("Usuários e/ou Senha inválidos...");
        }
    }
    @FXML
    private void sair(ActionEvent event){
        ScaleTransition st = new ScaleTransition(Duration.seconds(0.5), root);
        st.setToX(0);
        st.setToY(0);
        st.setFromX(1);
        st.setFromY(1);
        st.play();
        st.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
