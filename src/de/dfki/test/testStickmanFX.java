package de.dfki.test;

import de.dfki.common.interfaces.StageRoom;
import de.dfki.reeti.stage.StageRoomReeti;
import de.dfki.stickman3D.stage.StageRoom3D;
import de.dfki.stickmanFX.stage.StageRoomFX;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Created by alvaro on 9/13/16.
 */
public class testStickmanFX {

    public static void main(String[] args) {

        StageRoom stickmanStage = new StageRoomFX(0, 0, true);
        stickmanStage.addStickman("Patrick");
//        stickmanStage.launchConfiguration();
        stickmanStage.launchStickmanStage(true);
    }
}
