package de.dfki.stickmanFX.animation.facefx;

import de.dfki.stickmanFX.StickmanFX;
import de.dfki.stickmanFX.animationlogic.AnimationContentFX;
import de.dfki.stickmanFX.animationlogic.AnimationFX;

import java.util.ArrayList;

/**
 *
 * @author Patrick Gebhard
 *
 */
public class LookFront extends AnimationFX {

    public LookFront() {
        mAnimType = AnimationFX.ANIMTYPE.EmotionExpression;
    }
    
    public LookFront(StickmanFX sm, int duration, boolean block) {
        super(sm, duration, block);
    }

    @Override
    public void playAnimation() {
        int translationUnit = 3;

        // look left
        mAnimationPartFX = new ArrayList<>();
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "DEFAULT"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "shape", "DEFAULT"));
        playAnimationPart(500);

    }
}
