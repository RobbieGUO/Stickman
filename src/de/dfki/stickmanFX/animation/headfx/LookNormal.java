/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.stickmanFX.animation.headfx;

import de.dfki.common.Gender;
import de.dfki.stickmanFX.StickmanFX;
import de.dfki.stickmanFX.animationlogic.AnimationContentFX;
import de.dfki.stickmanFX.animationlogic.AnimationFX;
import java.util.ArrayList;

/**
 *
 * @author Robbie
 */
public class LookNormal extends AnimationFX {
    public LookNormal(StickmanFX sm, int duration, boolean block) {
        super(sm, duration, block);
    }

    @Override
    public void playAnimation() {
        int translationUnit = 15;

        // head Normal
       mAnimationPartFX = new ArrayList<>();
        // which bodyparts are involved - check dependencies
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "tilt", translationUnit));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyebrowFX, "tilt", translationUnit));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "tilt", translationUnit));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyebrowFX, "tilt", translationUnit));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mHeadFX, "tilt", translationUnit));

        if (mStickmanFX.mType == Gender.TYPE.MALE) {
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMaleHairFX, "tilt", translationUnit));
        } else {
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mFemaleHairFX, "tilt", translationUnit));
        }

        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMouthFX, "tilt", translationUnit));
        playAnimationPart(150);
    }
}