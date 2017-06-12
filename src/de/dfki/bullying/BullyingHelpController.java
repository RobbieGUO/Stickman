/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.bullying;

import de.dfki.stickmanFX.stage.StickmanStageFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

//    @FXML
//    private Label q1;
//    @FXML
//    private Label q2;
//    @FXML
//    private Label q3;
//    @FXML
//    private Label q4;
//    @FXML
//    private Label q5;
//    @FXML
//    private Label t1;
//    @FXML
//    private Label experience;
    @FXML
    private Button goTOExperience;
    @FXML
    private Button BillyHome;

    private Stage bullyingStage;
    private StickmanStageFX sStickmanStageFX;

    public void setDialogStage(Stage stage, StickmanStageFX s) {
        this.bullyingStage = stage;
        sStickmanStageFX = s;

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
        StickmanStageFX.setgoTOExperience(Boolean.FALSE);

//        t1.setText("Please click the label in the left side");
//        goTOExperience.setVisible(false);
//        setIdForLabel();
        goTOExperience.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StickmanStageFX.setgoTOExperience(Boolean.TRUE);
                sStickmanStageFX.changeBullyingStageFlag();
                bullyingStage.close();
            }
        });
    }

    @FXML
    private void handBillyHouse() {
        sStickmanStageFX.setBillyHouseFlag(Boolean.TRUE);
        sStickmanStageFX.changeBullyingStageFlag();
        bullyingStage.close();
    }

//    @FXML
//    private void handleq1() {
//        goTOExperience.setVisible(false);
//        t1.setText("Try to stop bullying.");
//    }
//
//    @FXML
//    private void handleq2() {
//        goTOExperience.setVisible(false);
//        t1.setText("Tell the teacher!");
//    }
//
//    @FXML
//    private void handleq3() {
//        goTOExperience.setVisible(false);
//        t1.setText("Yes !!!!!!");
//    }
//
//    @FXML
//    private void handleq4() {
//        goTOExperience.setVisible(false);
//        t1.setText("Tell the teacher. \n"
//                + "Do not ask a question twice!");
//    }
//
//    @FXML
//    private void handleq5() {
//        goTOExperience.setVisible(false);
//        t1.setText("I am scared, too! \n"
//                + ";=(");
//    }
//    
//    @FXML
//    private void experience() {
//        goTOExperience.setVisible(true);
//        t1.setText("In another class there was a girl,"
//                + "that stepped in for a younger classmate that was tormented, too.\n"
//                + "Do you want her to help you?");
//    }
//
//    
//    private void setIdForLabel() {
//        q1.setId("Menu");
//        q2.setId("Menu");
//        q3.setId("Menu");
//        q4.setId("Menu");
//        q5.setId("Menu");
//        experience.setId("Menu");
//    }
}
