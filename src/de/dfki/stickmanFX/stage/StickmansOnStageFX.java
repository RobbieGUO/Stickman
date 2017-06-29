package de.dfki.stickmanFX.stage;

import de.dfki.common.*;
import de.dfki.common.interfaces.Stickman;
import de.dfki.common.interfaces.StickmanStage;
import de.dfki.common.interfaces.StageRoom;
import de.dfki.stickmanSwing.StickmanSwing;
import de.dfki.stickmanFX.StickmanFX;
import de.dfki.stickmanFX.xmlsettings.XmlTransformFX;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Created by alvaro on 9/19/16.
 */
public class StickmansOnStageFX extends StickmansOnStage {

    private static String StickmanName = null;
    
    public StickmansOnStageFX(StickmanStage stickmanStage) {
        super(stickmanStage);
    }
    private XmlTransformFX mXmlTransform = new XmlTransformFX();

    public StickmansOnStageFX(StickmanStage stickmanStageFX, StageRoom controllerFX) {
        super(stickmanStageFX, controllerFX);
    }

    @Override
    protected void addStickmanToStage(String name, boolean fullScreen, Gender.TYPE gender) {
        if (fullScreen) {
            Stickman stickman = new StickmanFX(name, gender, stickmanStage.getFullScreenScale(), stickmanStage.getFullScreenDimension());

            if (stickman instanceof StickmanFX) {
                ((StickmanFX) stickman).setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        if (e.getButton().equals(MouseButton.PRIMARY)) {
                            System.out.println(((StickmanFX) stickman).mName);
                            StickmanName = ((StickmanFX) stickman).mName;
                        }

                    }
                });

            }

            putFullStickmanOnStage(name, stickman);
        } else {
            Stickman stickman = new StickmanFX(name, gender, DEFAULT_SCALE);
            if (stickman instanceof StickmanFX) {
                ((StickmanFX) stickman).setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        if (e.getButton().equals(MouseButton.PRIMARY)) {
                            System.out.println(((StickmanFX) stickman).mName);
                            StickmanName = ((StickmanFX) stickman).mName;
                        }

                    }
                });

            }
            putFullStickmanOnStage(name, stickman);
        }
    }

    @Override
    protected void addStickmanToStage(String name, boolean fullScreen, Gender.TYPE gender, boolean onlyFace) {
        if (fullScreen) {
            Stickman stickman = new StickmanFX(name, gender, stickmanStage.getFullScreenScale(), stickmanStage.getFullScreenDimension());
            if (stickman instanceof StickmanFX) {
                ((StickmanFX) stickman).setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        if (e.getButton().equals(MouseButton.PRIMARY)) {
                            System.out.println(((StickmanFX) stickman).mName);
                            StickmanName = ((StickmanFX) stickman).mName;
                        }

                    }
                });

            }
            putFullStickmanOnStage(name, stickman);
        } else {
            float scale = DEFAULT_SCALE;
            if (onlyFace) {
                scale = 9.0f;
            }
            Stickman stickman = new StickmanFX(name, gender, scale, onlyFace);
            if (stickman instanceof StickmanFX) {
                ((StickmanFX) stickman).setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        if (e.getButton().equals(MouseButton.PRIMARY)) {
                            System.out.println(((StickmanFX) stickman).mName);
                            StickmanName = ((StickmanFX) stickman).mName;
                        }

                    }
                });

            }
            putFullStickmanOnStage(name, stickman);
        }
    }

    public XmlTransformFX getmXmlTransform() {
        return this.mXmlTransform;
    }
    
    public static String returnStickmanName(){
        return StickmanName;
    }
    
    public static void changeStickmanName(String s){
        StickmanName = s;
    }
}
