/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.stickmanfx.animation.headfx;

import de.dfki.stickmanfx.StickmanFX;
import de.dfki.stickmanfx.animationlogic.AnimationContentFX;
import de.dfki.stickmanfx.animationlogic.AnimationFX;
import java.util.ArrayList;
import javafx.application.Platform;

/**
 *
 * @author Beka
 *
 */
public class HeadTilt extends AnimationFX 
{
	

    public HeadTilt(StickmanFX sm, int duration, boolean block) 
    {
        super(sm, duration, block);
    }

    @Override
    public void playAnimation() {
        int translationUnit = 8;

        // head down
        mAnimationPartFX = new ArrayList<>();
        // which bodyparts are involved - check dependencies
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "tilt", translationUnit));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyebrowFX, "tilt", translationUnit));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "tilt", translationUnit));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyebrowFX, "tilt", translationUnit));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mHeadFX, "tilt", translationUnit));
        
        if(mStickmanFX.mType == StickmanFX.TYPE.MALE)
        	mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMaleHairFX, "tilt", translationUnit));
        else
        	mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mFemaleHairFX, "tilt", translationUnit));
        
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMouthFX, "tilt", translationUnit));

        playAnimationPart(150);

        pauseAnimation(200);

        mAnimationPartFX = new ArrayList<>();
        // which bodyparts are involved - check dependencies
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "tilt", -translationUnit));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyebrowFX, "tilt", -translationUnit));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "tilt", -translationUnit));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyebrowFX, "tilt", -translationUnit));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mHeadFX, "tilt", -translationUnit));
        
        if(mStickmanFX.mType == StickmanFX.TYPE.MALE)
        	mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMaleHairFX, "tilt", -translationUnit));
        else
        	mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mFemaleHairFX, "tilt", -translationUnit));
        
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMouthFX, "tilt", -translationUnit));
        playAnimationPart(150);
    }
}