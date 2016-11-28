/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.stickman3D.animation.facefx;

import de.dfki.stickman3D.Stickman3D;
import de.dfki.stickman3D.StickmanStageController;
import de.dfki.stickman3D.animationlogic.AnimationContentFX;
import de.dfki.stickman3D.animationlogic.AnimationFX;

import java.util.ArrayList;

/**
 * An angry facial movement is created in this class. The face moves from the
 * default state to the angry state, and then comes back to the default state.
 *
 * @author Beka Aptsiauri
 */
public class Angry2 extends AnimationFX 
{
	
	Stickman3D mStickmanFX;
	int rotationUnit;
	
	public Angry2() {
		mAnimType = ANIMTYPE.ON;
	}
	
	/**
    *
    * @param sm StickmanSwing
    * @param duration Control the speed of the movement from one emotion state
    * to another emotion state.
    * @param block block or not the others movements, when one movement is not
    * finished.
    */
    public Angry2(Stickman3D sm, int duration, boolean block)
    {
        super(sm, duration, block);
        mStickmanFX = sm;
    }

    /**
     * This method creates the angry facial movement.
     */
    @Override
	public void playAnimation() 
	{
		if(mStickmanFX.mType == Stickman3D.TYPE.MALE)
			rotationUnit = 20;
		else
			rotationUnit = 30;
		// angry
		mAnimationPartFX = new ArrayList<>();
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMouthFX, "shape", "ANGRY"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "ANGRY"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyebrowFX, "shape", "ANGRY"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyebrowFX, "shape", "ANGRY"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mFaceWrinkleFX, "shape", "ANGRY"));  
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "shape", "ANGRY"));
        playAnimationPart(mDuration);
        
        mAnimationPartFX = new ArrayList<>();
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftUpperArmFX, "zrotate", -30));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "zrotate", 60));
        
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "zrotate", 10));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "zrotate", -20));
        
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftWrist, "rotate", -15));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftWrist, "yrotate", -15));
        playAnimationPart(mDuration);
        
        //foot
        for(int i = 0; i<8; i++)
        {
            mAnimationPartFX = new ArrayList<>();
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightFootFX, "rotate", 40));
            playAnimationPart(200);
            
            mAnimationPartFX = new ArrayList<>();
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightFootFX, "rotate", -40));
            playAnimationPart(200);
        }
        
        for(int i = 0; i<7; i++)
        {
        	mAnimationPartFX = new ArrayList<>();
            if(i == 0 || i == 6)
            	mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mHeadFX, "yrotate", -10));
            else if(i % 2 == 1)
            	mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mHeadFX, "yrotate", 20));
            else if(i % 2 == 0)
            	mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mHeadFX, "yrotate", -20));
            playAnimationPart(200);
        }
                
		pauseAnimation(1200);

		// no angry
		mAnimationPartFX = new ArrayList<>();
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMouthFX, "shape", "ANGRYEND"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "ANGRYEND"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyebrowFX, "shape", "ANGRYEND"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyebrowFX, "shape", "ANGRYEND"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mFaceWrinkleFX, "shape", "ANGRYEND"));   ///Add by Robbie
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "shape", "ANGRYEND"));
        playAnimationPart(mDuration);
        
        mAnimationPartFX = new ArrayList<>();
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftUpperArmFX, "zrotate", 30));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "zrotate", -60));
        
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "zrotate", -10));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "zrotate", 20));
        
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftWrist, "rotate", 15));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftWrist, "yrotate", 15));
        playAnimationPart(mDuration);
        
        if(StickmanStageController.currentRadioButton != null)
        	StickmanStageController.currentRadioButton.setSelected(false);
	}
}