/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.stickmanfx.animation.facefx;

import de.dfki.stickmanfx.StickmanFX;
import de.dfki.stickmanfx.StickmanStageController;
import de.dfki.stickmanfx.animationlogic.AnimationContentFX;
import de.dfki.stickmanfx.animationlogic.AnimationFX;
import java.util.ArrayList;

/**
 *
 * @author Beka Aptsiauri
 *
 */
public class Contempt extends AnimationFX 
{
	public Contempt() {
		mAnimType = ANIMTYPE.ON;
	}
	
    public Contempt(StickmanFX sm, int duration, boolean block) {
        super(sm, duration, block);
    }

    @Override
    public void playAnimation() {
        // Contempt
        mAnimationPartFX = new ArrayList<>();
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMouthFX, "shape", "CONTEMPT"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "CONTEMPT"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "shape", "CONTEMPT"));
        playAnimationPart(500);

        pauseAnimation(1200);

        // no Contempt
        mAnimationPartFX = new ArrayList<>();
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMouthFX, "shape", "CONTEMPTEND"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "CONTEMPTEND"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "shape", "CONTEMPTEND"));
        playAnimationPart(500);
        
        StickmanStageController.currentRadioButton.setSelected(false);
    }
}
