/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.bullying;

import de.dfki.stickmanFX.stage.StickmanStageFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Robbie
 */
public class BullyingHelpController implements Initializable {

    @FXML
    private Label q1;
    @FXML
    private Label q2;
    @FXML
    private Label q3;
    @FXML
    private Label q4;
    @FXML
    private Label q5;
    @FXML
    private Label t1;

    private Stage bullyingStage;

    public void setDialogStage(Stage stage, StickmanStageFX s) {
        this.bullyingStage = stage;

        bullyingStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                s.changeBullyingStageFlag();
                s.changeBullyingVSMFlag();
            }
        });
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        t1.setText("Please click the label in the left side");
        setIdForLabel();
    }

    @FXML
    private void handleq1() {
        t1.setText("Try to stop bullying.");
    }

    @FXML
    private void handleq2() {
        t1.setText("Tell the teacher!");
    }

    @FXML
    private void handleq3() {
        t1.setText("Yes !!!!!!");
    }

    @FXML
    private void handleq4() {
        t1.setText("Tell the teacher. \n"
                + "Do not ask a question twice!");
    }

    @FXML
    private void handleq5() {
        t1.setText("I am scared, too! \n"
                + ";=(");
    }

    private void setIdForLabel() {
        q1.setId("Menu");
        q2.setId("Menu");
        q3.setId("Menu");
        q4.setId("Menu");
        q5.setId("Menu");
    }

}
