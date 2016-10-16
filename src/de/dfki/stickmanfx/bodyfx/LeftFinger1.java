/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.stickmanfx.bodyfx;

import java.awt.Dimension;
import java.awt.Point;
import java.net.URL;

import com.interactivemesh.jfx.importer.col.ColModelImporter;

import de.dfki.stickmanfx.StickmanFX;
import de.dfki.stickmanfx.animationlogic.AnimatorFX;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 *
 * @author Beka
 *
 */
public class LeftFinger1 extends BodyPartFX 
{

    LeftWrist mLeftWrist;
    int mArmLength = 80;
    Dimension mSize = new Dimension(mArmLength, mArmLength);
    
    PhongMaterial material;

	Cylinder LeftFinger_1;

    public LeftFinger1(LeftWrist leftWrist) {
    	mLeftWrist = leftWrist;
        mColor = Color.rgb(242, 227, 217, 1);
        mDefaultRotation = -20;
        mXRotation = 0;
        mYRotation = 2;
        mZRotation = 220;
        mToDegree = mDefaultRotation;
        
        LeftFinger_1 = new Cylinder(3, 10);
        
        material = new PhongMaterial();
		material.setDiffuseColor(mColor);
		
		LeftFinger_1.setMaterial(material);
		
		mLeftWrist.leftWristGroup.getChildren().add(LeftFinger_1);
        
        init();
    }


    @Override
    public void calculate(int step) 
    {
		Rotate rx = new Rotate(mXRotation, 0, LeftFinger_1.getHeight()/2, 0, Rotate.X_AXIS);
		Rotate ry = new Rotate(mYRotation, 0, LeftFinger_1.getHeight()/2, 0,  Rotate.Y_AXIS);
		Rotate rz = new Rotate(mZRotation, 0, LeftFinger_1.getHeight()/2, 0,  Rotate.Z_AXIS);
		
		if(mLeftWrist.mLeftForeArmFX.mUpperArmFX.mBodyFX.mNeckFX.mHeadFX.mStickmanFX.mType == StickmanFX.TYPE.MALE)
		{
			LeftFinger_1.setTranslateX(mStart.x - 8);
			LeftFinger_1.setTranslateY(mStart.y - 5);
			LeftFinger_1.setTranslateZ(0);
		}
		else
		{
			LeftFinger_1.setTranslateX(mStart.x - 8);
			LeftFinger_1.setTranslateY(mStart.y - 5);
			LeftFinger_1.setTranslateZ(0);
		}
		
		LeftFinger_1.getTransforms().clear();
		LeftFinger_1.getTransforms().addAll(rx, ry, rz);
		
//        update();
    }

    @Override
    public void update() {
//        Color currentColor = Color.rgb(80, 80, 80);
        // draw outlines
//    	if (mUpperArmFX.mLeftShoulderFX.mBodyFX.mNeckFX.mHeadFX.mStickmanFX.setCharacterInvisible == false) {
//    		mColorRecorder = mColor;
//    	}
//        if (mUpperArmFX.mLeftShoulderFX.mBodyFX.mNeckFX.mHeadFX.mStickmanFX.setCharacterInvisible == true) {
//            if (mUpperArmFX.mLeftShoulderFX.mBodyFX.mNeckFX.mHeadFX.mStickmanFX.fadeControler == true) //Added by Robbie
//            {
//                int fadeFactor = mUpperArmFX.mLeftShoulderFX.mBodyFX.mNeckFX.mHeadFX.mStickmanFX.mMouthFX.mShapeAnimationStep * 12;
//                if (fadeFactor <= 24) {
//                    fadeFactor = 0;
//                }
//                mColor = new Color(mColor.getRed(), mColor.getGreen(), mColor.getBlue(), (fadeFactor * 100 / 255) / 100f);
//                //mColor = Color.rgb(80, 80, 80, (fadeFactor * 100 / 255) / 100f);
//                //g2.setColor(new Color(80, 80, 80,fadeFactor));
//            } else {
//                int fadeFactor = (20 - mUpperArmFX.mLeftShoulderFX.mBodyFX.mNeckFX.mHeadFX.mStickmanFX.mMouthFX.mShapeAnimationStep) * 12;
//                if (fadeFactor >= 216) {
//                	mColor = mColorRecorder;
//                }
//                else
//                	mColor = new Color(mColor.getRed(), mColor.getGreen(), mColor.getBlue(), (fadeFactor * 100 / 255) / 100f);
//                //mColor = Color.rgb(80, 80, 80, (fadeFactor * 100 / 255) / 100f);
//                //g2.setColor(new Color(80, 80, 80,fadeFactor));
//            }
//        }

//		g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
//
//		g2.draw(mArm);
    }

}
