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
public class Embarrassed extends AnimationFX 
{
	public Embarrassed() {
		mAnimType = ANIMTYPE.EmotionExpression;
	}
	
    public Embarrassed(StickmanFX sm, int duration, boolean block) 
    {
        super(sm, duration, block);
    }

    @Override
    public void playAnimation() {

        // embarrassed
        mAnimationPartFX = new ArrayList<>();
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMouthFX, "shape", "EMBARRASSED"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "EMBARRASSED"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyebrowFX, "shape", "EMBARRASSED"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mFaceWrinkleFX, "shape", "EMBARRASSED"));   ///Add by Robbie
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "shape", "EMBARRASSED"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyebrowFX, "shape", "EMBARRASSED"));
        playAnimationPart(500);

        pauseAnimation(1200);

        // no embarrassed
        mAnimationPartFX = new ArrayList<>();
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMouthFX, "shape", "EMBARRASSEDEND"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "EMBARRASSEDEND"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyebrowFX, "shape", "EMBARRASSEDEND"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mFaceWrinkleFX, "shape", "EMBARRASSEDEND"));   ///Add by Robbie
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "shape", "EMBARRASSEDEND"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyebrowFX, "shape", "EMBARRASSEDEND"));
        playAnimationPart(500);
        
        StickmanStageController.currentEmotionRadioButton.setSelected(false);
    }
}
