package de.dfki.stickman.animation.gesture;

import de.dfki.stickman.Stickman;
import de.dfki.stickman.animationlogic.Animation;
import de.dfki.stickman.animationlogic.AnimationContent;
import java.util.ArrayList;

/**
 *
 * @author Patrick Gebhard
 *
 */
public class CoverMouth extends Animation {
   
	public CoverMouth(Stickman sm, int duration, boolean block) {
		super(sm, duration, block);
	}

	@Override
	public void playAnimation() {
		int rotationUnit = 16;

		// bring upper arm and fore arm in position
		mAnimationPart = new ArrayList<>();
		mAnimationPart.add(new AnimationContent(mStickman.mLeftShoulder, "rotate", -rotationUnit * 3));
		mAnimationPart.add(new AnimationContent(mStickman.mLeftUpperArm, "rotate", rotationUnit));
		mAnimationPart.add(new AnimationContent(mStickman.mLeftForeArm, "rotate", rotationUnit * 9));
		mAnimationPart.add(new AnimationContent(mStickman.mLeftHand, "rotate", rotationUnit * 9));
		playAnimationPart(200);

		pauseAnimation(1200);

		// go back in the default position
		mAnimationPart = new ArrayList<>();
		mAnimationPart.add(new AnimationContent(mStickman.mLeftShoulder, "rotate", +rotationUnit * 3));
		mAnimationPart.add(new AnimationContent(mStickman.mLeftUpperArm, "rotate", -rotationUnit));
		mAnimationPart.add(new AnimationContent(mStickman.mLeftForeArm, "rotate", -rotationUnit * 9));
		mAnimationPart.add(new AnimationContent(mStickman.mLeftHand, "rotate", -rotationUnit * 9));
		playAnimationPart(300);
	}
}
