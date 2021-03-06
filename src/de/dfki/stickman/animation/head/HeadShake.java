/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.stickman.animation.head;

import de.dfki.stickman.Stickman;
import de.dfki.stickman.animationlogic.Animation;
import de.dfki.stickman.animationlogic.AnimationContent;
import java.util.ArrayList;

/**
 *
 * @author Patrick Gebhard
 *
 */
public class HeadShake extends Animation
{

	public HeadShake(Stickman sm, int duration, boolean block)
	{
		super(sm, duration, block);
	}

	@Override
	public void playAnimation()
	{
		int rotationUnit = 10;

		// Its action is strange for the first time!
		mAnimationPart = new ArrayList<>();
		mAnimationPart.add(new AnimationContent(mStickman.mRightEye, "rotate", -rotationUnit));
		mAnimationPart.add(new AnimationContent(mStickman.mRightEyebrow, "rotate", -rotationUnit));
		mAnimationPart.add(new AnimationContent(mStickman.mLeftEye, "rotate", -rotationUnit));
		mAnimationPart.add(new AnimationContent(mStickman.mLeftEyebrow, "rotate", -rotationUnit));
		mAnimationPart.add(new AnimationContent(mStickman.mHead, "rotate", -rotationUnit));
		mAnimationPart.add(new AnimationContent(mStickman.mMouth, "rotate", -rotationUnit));
		playAnimationPart(200);
		pauseAnimation(100);

		// shaking head 5 times from Robbie
		for (int i = 0; i < 3; i++)
		{
			mAnimationPart = new ArrayList<>();
			mAnimationPart.add(new AnimationContent(mStickman.mRightEye, "rotate", rotationUnit * 2));
			mAnimationPart.add(new AnimationContent(mStickman.mRightEyebrow, "rotate", rotationUnit * 2));
			mAnimationPart.add(new AnimationContent(mStickman.mLeftEye, "rotate", rotationUnit * 2));
			mAnimationPart.add(new AnimationContent(mStickman.mLeftEyebrow, "rotate", rotationUnit * 2));
			mAnimationPart.add(new AnimationContent(mStickman.mHead, "rotate", rotationUnit * 2));
			mAnimationPart.add(new AnimationContent(mStickman.mMouth, "rotate", rotationUnit * 2));
			playAnimationPart(200);
			pauseAnimation(100);

			mAnimationPart = new ArrayList<>();
			mAnimationPart.add(new AnimationContent(mStickman.mRightEye, "rotate", -rotationUnit * 2));
			mAnimationPart.add(new AnimationContent(mStickman.mRightEyebrow, "rotate", -rotationUnit * 2));
			mAnimationPart.add(new AnimationContent(mStickman.mLeftEye, "rotate", -rotationUnit * 2));
			mAnimationPart.add(new AnimationContent(mStickman.mLeftEyebrow, "rotate", -rotationUnit * 2));
			mAnimationPart.add(new AnimationContent(mStickman.mHead, "rotate", -rotationUnit * 2));
			mAnimationPart.add(new AnimationContent(mStickman.mMouth, "rotate", -rotationUnit * 2));
			playAnimationPart(200);
			pauseAnimation(100);
		}

		mAnimationPart = new ArrayList<>();
		mAnimationPart.add(new AnimationContent(mStickman.mRightEye, "rotate", rotationUnit));
		mAnimationPart.add(new AnimationContent(mStickman.mRightEyebrow, "rotate", rotationUnit));
		mAnimationPart.add(new AnimationContent(mStickman.mLeftEye, "rotate", rotationUnit));
		mAnimationPart.add(new AnimationContent(mStickman.mLeftEyebrow, "rotate", rotationUnit));
		mAnimationPart.add(new AnimationContent(mStickman.mHead, "rotate", rotationUnit));
		mAnimationPart.add(new AnimationContent(mStickman.mMouth, "rotate", rotationUnit));

		playAnimationPart(200);
		pauseAnimation(100);

	}

}
