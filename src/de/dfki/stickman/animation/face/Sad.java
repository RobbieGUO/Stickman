/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.stickman.animation.face;

import de.dfki.stickman.Stickman;
import de.dfki.stickman.animationlogic.Animation;
import de.dfki.stickman.animationlogic.AnimationContent;
import java.util.ArrayList;

/**
 *
 * @author Patrick Gebhard
 *
 */
public class Sad extends Animation {

	public Sad(Stickman sm, int duration, boolean block) {
		super(sm, duration, block);
	}

	@Override
	public void playAnimation() {
		int translationUnit = 3;

		// smile
		mAnimationPart = new ArrayList<>();
		mAnimationPart.add(new AnimationContent(mStickman.mMouth, "shape", "SAD"));
		mAnimationPart.add(new AnimationContent(mStickman.mLeftEyebrow, "shape", "DISGUSTED"));
		mAnimationPart.add(new AnimationContent(mStickman.mRightEyebrow, "shape", "DISGUSTED"));
		playAnimationPart(20);
		
		pauseAnimation(1200);

		// no smile
		mAnimationPart = new ArrayList<>();
		mAnimationPart.add(new AnimationContent(mStickman.mMouth, "shape", "DEFAULT"));
		mAnimationPart.add(new AnimationContent(mStickman.mLeftEyebrow, "shape", "DEFAULT"));
		mAnimationPart.add(new AnimationContent(mStickman.mRightEyebrow, "shape", "DEFAULT"));
		playAnimationPart(20);
	}
}
