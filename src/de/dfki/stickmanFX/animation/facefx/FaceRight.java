/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.stickmanFX.animation.facefx;

import de.dfki.common.Gender;
import de.dfki.stickmanFX.StickmanFX;
import de.dfki.stickmanFX.animationlogic.AnimationContentFX;
import de.dfki.stickmanFX.animationlogic.AnimationFX;
import java.util.ArrayList;
import javafx.application.Platform;

/**
 *
 * @author Robbie
 */
public class FaceRight extends AnimationFX {

    public FaceRight() {
        mAnimType = AnimationFX.ANIMTYPE.EmotionExpression;
    }

    public FaceRight(StickmanFX sm, int duration, boolean block) {
        super(sm, duration, block);
    }

    @Override
    public void playAnimation() {

        mStickmanFX.doAnimation("StopIdle", 10, true);
        int rotationUnit = 20;

        mStickmanFX.mMouthFX.setFaceflag("right");
        //////// FaceFront first
        mAnimationPartFX = new ArrayList<>();

        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "shape", "DEFAULT"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyebrowFX, "shape", "DEFAULT"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "DEFAULT"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyebrowFX, "shape", "DEFAULT"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMouthFX, "shape", "DEFAULT"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMaleHairFX, "shape", "FRONT"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mHeadFX, "shape", "FRONT"));

        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftShoulderFX, "shape", "FRONT"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftUpperArmFX, "shape", "FRONT"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "shape", "FRONT"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftHandFX, "shape", "FRONT"));

        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightShoulderFX, "shape", "FRONT"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "shape", "FRONT"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "shape", "FRONT"));
        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightHandFX, "shape", "FRONT"));

        if (mStickmanFX.mType == Gender.TYPE.FEMALE) {
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mFemaleHairFX, "shape", "FRONT"));
        } else {
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMaleHairFX, "shape", "FRONT"));
        }
        playAnimationPart(50);

        Platform.runLater(() -> {
            mStickmanFX.mOrientation = StickmanFX.ORIENTATION.FRONT;
            mStickmanFX.mLeftUpperLegFX.calculate(0);
            mStickmanFX.mLeftUpperLegFX.createShape();
            mStickmanFX.mLeftForeLegFX.calculate(0);
            mStickmanFX.mLeftForeLegFX.createShape();

            mStickmanFX.mRightUpperLegFX.calculate(0);
            mStickmanFX.mRightUpperLegFX.createShape();

            mStickmanFX.mRightForeLegFX.calculate(0);
            mStickmanFX.mRightForeLegFX.createShape();

            mStickmanFX.mLeftFootFX.calculate(0);
            mStickmanFX.mLeftFootFX.createShape();

            mStickmanFX.mRightFootFX.calculate(0);
            mStickmanFX.mRightFootFX.createShape();

            mStickmanFX.mLeftShoulderFX.mRotation = -70;
            mStickmanFX.mLeftUpperArmFX.mRotation = -23;
            mStickmanFX.mLeftForeArmFX.mRotation = 20;
            mStickmanFX.mLeftHandFX.mRotation = 30;
            mStickmanFX.mLeftShoulderFX.calculate(0);
            mStickmanFX.mLeftShoulderFX.createShape();
            mStickmanFX.mLeftUpperArmFX.calculate(0);
            mStickmanFX.mLeftUpperArmFX.createShape();
            mStickmanFX.mLeftForeArmFX.calculate(0);
            mStickmanFX.mLeftForeArmFX.createShape();
            mStickmanFX.mLeftHandFX.calculate(0);
            mStickmanFX.mLeftHandFX.createShape();

            mStickmanFX.mRightShoulderFX.mRotation = 70;
            mStickmanFX.mRightUpperArmFX.mRotation = 23;
            mStickmanFX.mRightForeArmFX.mRotation = -20;
            mStickmanFX.mRightHandFX.mRotation = -30;
            mStickmanFX.mRightShoulderFX.calculate(0);
            mStickmanFX.mRightShoulderFX.createShape();
            mStickmanFX.mRightUpperArmFX.calculate(0);
            mStickmanFX.mRightUpperArmFX.createShape();
            mStickmanFX.mRightForeArmFX.calculate(0);
            mStickmanFX.mRightForeArmFX.createShape();
            mStickmanFX.mRightHandFX.calculate(0);
            mStickmanFX.mRightHandFX.createShape();

            mStickmanFX.update();
        });

        Platform.runLater(() -> {
            //////// FaceRight
            mAnimationPartFX = new ArrayList<>();
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftShoulderFX, "rotate", rotationUnit * 2));
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftUpperArmFX, "rotate", rotationUnit));
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "rotate", rotationUnit));
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftHandFX, "rotate", rotationUnit));

//        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftShoulderFX, "shape", "RIGHT"));
//        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftUpperArmFX, "shape", "RIGHT"));
//        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "shape", "RIGHT" ));
//        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftHandFX, "shape", "RIGHT"));
//        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightShoulderFX, "rotate", -rotationUnit*6 + 6));
//        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "rotate", -rotationUnit-8));
//        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "rotate", rotationUnit*4 ));
//        mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightHandFX, "rotate", rotationUnit*4));
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightShoulderFX, "shape", "RIGHT"));
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "shape", "RIGHT"));
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "shape", "RIGHT"));
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightHandFX, "shape", "RIGHT"));

            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "TURNRIGHT"));
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyebrowFX, "shape", "TURNRIGHT"));
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMouthFX, "shape", "TURNRIGHT"));
            mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mHeadFX, "shape", "RIGHT"));

            if (mStickmanFX.mType == Gender.TYPE.FEMALE) {
                mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mFemaleHairFX, "shape", "RIGHT"));
            } else {
                mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMaleHairFX, "shape", "RIGHT"));
            }

            playAnimationPart(50);
        });

        Platform.runLater(() -> {
            mStickmanFX.mOrientation = StickmanFX.ORIENTATION.LEFT;
            mStickmanFX.mLeftUpperLegFX.calculate(0);
            mStickmanFX.mLeftUpperLegFX.createShape();
            mStickmanFX.mLeftForeLegFX.calculate(0);
            mStickmanFX.mLeftForeLegFX.createShape();

            mStickmanFX.mRightUpperLegFX.calculate(0);
            mStickmanFX.mRightUpperLegFX.createShape();

            mStickmanFX.mRightForeLegFX.calculate(0);
            mStickmanFX.mRightForeLegFX.createShape();

            mStickmanFX.mLeftFootFX.calculate(0);
            mStickmanFX.mLeftFootFX.createShape();

            mStickmanFX.mRightFootFX.calculate(0);
            mStickmanFX.mRightFootFX.createShape();
            mStickmanFX.update();
        });

    }
}
