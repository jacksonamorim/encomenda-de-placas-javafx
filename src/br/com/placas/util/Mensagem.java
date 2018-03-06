package br.com.placas.util;

import br.com.placas.controllers.PrincipalController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Mensagem {
    public void mensagem(String msg){
        JFXDialogLayout content = new JFXDialogLayout();
        content.setStyle("-fx-font-size: 16px;");
        content.setHeading(new Text("ATENÇÃO"));
        content.setBody(new Text(msg));
        JFXDialog dialog = new JFXDialog(PrincipalController.root2, content, JFXDialog.DialogTransition.TOP);
        JFXButton btn = new JFXButton("OK");
        btn.setTextFill(Color.WHITE);
        btn.setStyle("-fx-background-color: #b51f1f");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        content.setActions(btn);
        dialog.show();
    }
    
}
