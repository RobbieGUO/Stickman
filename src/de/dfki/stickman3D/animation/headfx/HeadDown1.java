/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.stickman3D.animation.headfx;

import java.util.ArrayList;

import de.dfki.stickman3D.Stickman3D;
import de.dfki.stickman3D.StickmanStageController;
import de.dfki.stickman3D.animationlogic.AnimationContent3D;
import de.dfki.stickman3D.animationlogic.Animation3D;

/**
 *
 * @author Beka Aptsiauri
 *
 */
public class HeadDown1 extends Animation3D {

	public HeadDown1() {
		mAnimType = ANIMTYPE.ON;
	}

	public HeadDown1(Stickman3D sm, int duration, boolean block) {
		super(sm, duration, block);
	}

	@Override
	public void playAnimation() {
		mAnimationPartFX = new ArrayList<>();
		mAnimationPartFX.add(new AnimationContent3D(mStickmanFX.mHeadFX, "rotate", 15));
		mAnimationPartFX.add(new AnimationContent3D(mStickmanFX.mLeftEyeFX, "shape", "LOOKDOWN"));
		mAnimationPartFX.add(new AnimationContent3D(mStickmanFX.mRightEyeFX, "shape", "LOOKDOWN"));
		playAnimationPart(mDuration);

		pauseAnimation(1000);

		// blink up
		mAnimationPartFX = new ArrayList<>();
		mAnimationPartFX.add(new AnimationContent3D(mStickmanFX.mHeadFX, "rotate", -15));
		mAnimationPartFX.add(new AnimationContent3D(mStickmanFX.mLeftEyeFX, "shape", "LOOKDOWNEND"));
		mAnimationPartFX.add(new AnimationContent3D(mStickmanFX.mRightEyeFX, "shape", "LOOKDOWNEND"));
		playAnimationPart(mDuration);

		if (StickmanStageController.currentRadioButton != null)
			StickmanStageController.currentRadioButton.setSelected(false);
	}
}
