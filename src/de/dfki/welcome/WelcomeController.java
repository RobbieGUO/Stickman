/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.welcome;

import de.dfki.common.Gender;
import de.dfki.stickmanFX.StickmanFX;
import de.dfki.stickmanFX.stage.StickmanStageFX;
import de.dfki.stickmanFX.stage.StickmansOnStageFX;
import de.dfki.stickmanFX.xmlsettings.StickmanDataFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Robbie
 */
public class WelcomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField userName;
    @FXML
    private ComboBox<Integer> userAge;
    @FXML
    private ComboBox<String> userGender;
    @FXML
    private Button userContinue;

    private Stage welcomeStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initialCombobox();

        userContinue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String sName = userName.getText();
                if (!sName.isEmpty()) {
                    StickmanStageFX.setUserName(sName);
                } else {
                    StickmanStageFX.setUserName("User");
                }

                String sGender = userGender.getValue();

                StickmanStageFX.setUserGender(sGender);

                int iAge = userAge.getValue();
                StickmanStageFX.setUserAge(iAge);

                StickmanStageFX.setbullyStart(Boolean.TRUE);
                welcomeStage.close();
            }
        });
    }

    public void setDialogStage(Stage stage) {
        this.welcomeStage = stage;
    }

    private void initialCombobox() {
        for (int i = 8; i < 40; i++) {
            userAge.getItems().add(i);
        }
        userAge.setValue(15);
        userGender.getItems().addAll("Male", "Female");
        userGender.setValue("Male");
    }

}
