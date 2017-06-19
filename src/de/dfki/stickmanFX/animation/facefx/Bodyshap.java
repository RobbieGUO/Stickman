/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.stickmanFX.animation.facefx;

import de.dfki.stickmanFX.StickmanFX;
import de.dfki.stickmanFX.animationlogic.AnimationContentFX;
import de.dfki.stickmanFX.animationlogic.AnimationFX;
import de.dfki.stickmanFX.bodyfx.BodyFX;
import de.dfki.stickmanFX.bodyfx.FemaleHairFX;
import java.util.ArrayList;
import javafx.application.Platform;

/**
 * An angry facial movement is created in this class. The face moves from the
 * default state to the angry state, and then comes back to the default state.
 *
 * @author Beka Aptsiauri
 */
public class Bodyshap extends AnimationFX {

    /**
     *
     * @param sm StickmanSwing
     * @param duration Control the speed of the movement from one emotion state
     * to another emotion state.
     * @param block block or not the others movements, when one movement is not
     * finished.
     */
    public Bodyshap() {
        mAnimType = AnimationFX.ANIMTYPE.EmotionExpression;
    }

    public Bodyshap(StickmanFX sm, int duration, boolean block) {
        super(sm, duration, block);
    }

    /**
     * This method creates the angry facial movement.
     */
    @Override
    public void playAnimation() {

        Platform.runLater(() -> {
            String sParameter = (String) mParameter;
            sParameter = sParameter.trim();
            BodyFX.BODYSHAPE shape = BodyFX.BODYSHAPE.valueOf(sParameter);
            mStickmanFX.mBodyFX.mShape = (shape != null) ? shape : BodyFX.BODYSHAPE.DEFAULT;
            mStickmanFX.update();
        });
    }
}
