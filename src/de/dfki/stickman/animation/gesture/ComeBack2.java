/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.stickman.animation.gesture;

import de.dfki.stickman.Stickman;
import de.dfki.stickman.animationlogic.Animation;
import de.dfki.stickman.animationlogic.AnimationContent;
import de.dfki.stickman.body.Body;
import de.dfki.stickman.body.FaceWrinkle;
import de.dfki.stickman.body.Head;
import de.dfki.stickman.body.LeftEye;
import de.dfki.stickman.body.LeftEyebrow;
import de.dfki.stickman.body.LeftForeArm;
import de.dfki.stickman.body.LeftHand;
import de.dfki.stickman.body.LeftLeg;
import de.dfki.stickman.body.LeftShoulder;
import de.dfki.stickman.body.LeftUpperArm;
import de.dfki.stickman.body.Mouth;
import de.dfki.stickman.body.Neck;
import de.dfki.stickman.body.RightEye;
import de.dfki.stickman.body.RightEyebrow;
import de.dfki.stickman.body.RightForeArm;
import de.dfki.stickman.body.RightHand;
import de.dfki.stickman.body.RightLeg;
import de.dfki.stickman.body.RightShoulder;
import de.dfki.stickman.body.RightUpperArm;

import java.util.ArrayList;

/**
 *
 * @author Patrick Gebhard
 *
 */
public class ComeBack2 extends Animation
{
	private Stickman mStickman;
	
	public ComeBack2(Stickman sm, int duration, boolean block)
	{	
		super(sm, duration, block);
		mStickman = sm;
	}

	@Override
	public void playAnimation()
	{
		float mScaleRecord = 0.0f;
		

		for (int j = 0; j < 19; j++)
			mStickman.mScale = mStickman.mScale*0.95f;
		
		mScaleRecord = mStickman.mScale;	
		mStickman.wordShowControler = true;
		
//		Show words		
//		for(int i=0; i<15;i++)
//		{
//			mStickman.mScale = mStickman.mScale*1.05f;
//			if(mStickman.mScale >= 1)
//				mStickman.mScale = 1;
//			mAnimationPart = new ArrayList<>();
//			mAnimationPart.add(new AnimationContent(mStickman.mWordShow, "shape", "SAYHI"));
//			playAnimationPart(2);	
//		}
		
//		show stars
		mStickman.mScale = 1.334445f;
		mAnimationPart = new ArrayList<>();
		mAnimationPart.add(new AnimationContent(mStickman.mWordShow, "shape", "STARSDISAPPEAR"));
		playAnimationPart(1000);	
		
//		disappeared words or stars
		mAnimationPart = new ArrayList<>();
		mAnimationPart.add(new AnimationContent(mStickman.mWordShow, "shape", "DEFAULT"));
		playAnimationPart(2);
		
		mStickman.wordShowControler = false;
		mStickman.mScale = mScaleRecord;
		
		int rotationUnit = 5;

		// bring upper arm and fore arm in position
		mAnimationPart = new ArrayList<>();
		mAnimationPart.add(new AnimationContent(mStickman.mLeftUpperArm, "rotate", rotationUnit*2));
		mAnimationPart.add(new AnimationContent(mStickman.mLeftForeArm, "rotate", rotationUnit*32));
		mAnimationPart.add(new AnimationContent(mStickman.mLeftHand, "rotate", rotationUnit*32));
		playAnimationPart(20);
		pauseAnimation(20);

		
		for (int i = 0; i < 2; i++)
		{		
			// wave right		
			for (int j = 0; j < 9; j++)
			{
				mAnimationPart = new ArrayList<>();
				mAnimationPart.add(new AnimationContent(mStickman.mLeftForeArm, "rotate", -rotationUnit));
				mAnimationPart.add(new AnimationContent(mStickman.mLeftHand, "rotate", -rotationUnit));
				
				mStickman.mScale = mStickman.mScale*1.05f;
				if(mStickman.mScale >= 1.3)
					mStickman.mScale = 1.334445f;
				playAnimationPart(20);		
			}
					
			// wave left
			for (int j = 0; j < 9; j++)
			{
				mAnimationPart = new ArrayList<>();
				mAnimationPart.add(new AnimationContent(mStickman.mLeftForeArm, "rotate", rotationUnit));
				mAnimationPart.add(new AnimationContent(mStickman.mLeftHand, "rotate", rotationUnit));
			
				mStickman.mScale = mStickman.mScale*1.05f;
				if(mStickman.mScale >= 1.3)
					mStickman.mScale = 1.334445f;
				playAnimationPart(20);			
			}			
		}
		
		// go back in the default position
		mAnimationPart = new ArrayList<>();
		mAnimationPart.add(new AnimationContent(mStickman.mLeftUpperArm, "rotate", -rotationUnit*2));
		mAnimationPart.add(new AnimationContent(mStickman.mLeftForeArm, "rotate", -rotationUnit * 32));
		mAnimationPart.add(new AnimationContent(mStickman.mLeftHand, "rotate", -rotationUnit * 32));
		playAnimationPart(20);	
	}
}