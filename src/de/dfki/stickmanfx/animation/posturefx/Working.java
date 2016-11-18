/*
100 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.stickmanfx.animation.posturefx;

import java.util.ArrayList;

import de.dfki.stickmanfx.StickmanFX;
import de.dfki.stickmanfx.StickmanStageController;
import de.dfki.stickmanfx.animationlogic.AnimationContentFX;
import de.dfki.stickmanfx.animationlogic.AnimationFX;
import de.dfki.stickmanfx.interior.Interior;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author Beka Aptsiauri
 *
 */
public class Working extends AnimationFX {
	public Working() {
		mAnimType = ANIMTYPE.ON;
	}

	private static boolean isInteriorElemtnLoaded = false;
	static Group table;
	static Group laptop;
	static Group chair;

	public Working(StickmanFX sm, int duration, boolean block) {
		super(sm, duration, block);
	}

	@Override
	public void playAnimation() {
		// Lade die InteriorElemente nur einmal
		if (!isInteriorElemtnLoaded) {
			isInteriorElemtnLoaded = true;
			table = Interior.createTable();
			laptop = Interior.createLaptop();
			chair = Interior.createChair();

			table.setTranslateY(461);
			table.setTranslateZ(-280);
			laptop.setTranslateZ(-220);
			laptop.setTranslateY(286);
			laptop.setTranslateX(-20);
			laptop.setRotationAxis(Rotate.Y_AXIS);
			laptop.setRotate(10);
			chair.setTranslateZ(-250);
			chair.setTranslateY(461);
			chair.setTranslateX(70);

			Platform.runLater(() -> {
				mStickmanFX.getChildren().addAll(table, laptop, chair);
			});
		}

		table.setVisible(true);
		laptop.setVisible(true);
		chair.setVisible(true);

		double recordLaptopXPosition = laptop.getTranslateX();
		double recordChairZPosition = chair.getTranslateZ();

		mAnimationPartFX = new ArrayList<>();
		if (mStickmanFX.mType == StickmanFX.TYPE.FEMALE)
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mUpperBodyAndHand, "ytranslate", 60));
		else
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mUpperBodyAndHand, "ytranslate", 50));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mDownBody, "ytranslate", 50));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftUpperLegFX, "rotate", -80));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeLegFX, "rotate", 90));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperLegFX, "rotate", -80));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeLegFX, "rotate", 90));

		TranslateTransition chairTr = new TranslateTransition(Duration.millis(400), chair);
		chairTr.setFromZ(chair.getTranslateZ());
		chairTr.setToZ(chair.getTranslateZ() - 50);
		chairTr.play();

		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftUpperArmFX, "rotate", 30));
		if (mStickmanFX.mType == StickmanFX.TYPE.FEMALE)
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "rotate", -88)); // <--
		else
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "rotate", -85));

		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "rotate", 30));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "zrotate", 20));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "rotate", -85));
		chairTr.play();
		playAnimationPart(500);

		mAnimationPartFX = new ArrayList<>();
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftUpperArmFX, "rotate", -45));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "rotate", 55));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftWrist, "rotate", -40));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftWrist, "yrotate", 50));

		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "rotate", -45));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "rotate", 55));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightWrist, "rotate", -40));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightWrist, "yrotate", -50));

		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mUpperBodyAndHand, "rotate", 10));

		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mHeadFX, "yrotate", 20));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "LOOKDOWN"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "shape", "LOOKDOWN"));
		playAnimationPart(500);

		// TastaturArbeit
		for (int i = 0; i < 40; i++) {
			if (i % 3 == 0) {
				mAnimationPartFX = new ArrayList<>();
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightFinger2, "rotate", 12));
				playAnimationPart(100);

				mAnimationPartFX = new ArrayList<>();
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightFinger2, "rotate", -12));
				playAnimationPart(100);

			} else if (i % 3 == 1) {
				mAnimationPartFX = new ArrayList<>();
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightFinger3, "rotate", 12));
				playAnimationPart(100);

				mAnimationPartFX = new ArrayList<>();
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightFinger3, "rotate", -12));
				playAnimationPart(100);
			} else if (i % 3 == 2) {
				mAnimationPartFX = new ArrayList<>();
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightFinger4, "rotate", 12));
				playAnimationPart(100);

				mAnimationPartFX = new ArrayList<>();
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightFinger4, "rotate", -12));
				playAnimationPart(100);
			}

			if (i == 5 || i == 15 || i == 25 || i == 35) {
				mAnimationPartFX = new ArrayList<>();
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "yrotate", 10));
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "yrotate", 50));
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "rotate", 15));
				playAnimationPart(150);

				pauseAnimation(200);

				mAnimationPartFX = new ArrayList<>();
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "yrotate", -10));
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "yrotate", -50));
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "rotate", -15));
				playAnimationPart(150);
			} else if (i == 10 || i == 20 || i == 30 || i == 39) {
				mAnimationPartFX = new ArrayList<>();
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "yrotate", -10));
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "yrotate", -50));
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "rotate", -15));
				playAnimationPart(150);

				pauseAnimation(200);

				mAnimationPartFX = new ArrayList<>();
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "yrotate", 10));
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "yrotate", 50));
				mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "rotate", 15));
				playAnimationPart(150);
			}
		}

		// Angry Start
		mAnimationPartFX = new ArrayList<>();
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mFaceWrinkleFX, "shape", "ANGRY"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyebrowFX, "shape", "ANGRY"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "ANGRY"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyebrowFX, "shape", "ANGRY"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "shape", "ANGRY"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMouthFX, "shape", "ANGRY"));
		playAnimationPart(300);

		// PC 3mal schlagen
		for (int i = 0; i < 3; i++) {
			mAnimationPartFX = new ArrayList<>();
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "rotate", -90));
			playAnimationPart(250);
			mAnimationPartFX = new ArrayList<>();
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "rotate", 90));
			playAnimationPart(250);
			pauseAnimation(400);
		}

		pauseAnimation(1000);

		// Kopf hinterneigen
		mAnimationPartFX = new ArrayList<>();
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mHeadFX, "yrotate", -20));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mHeadFX, "rotate", -30));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftUpperArmFX, "rotate", -100));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftWrist, "yrotate", -180));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftWrist, "rotate", 30));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "rotate", -80));
		playAnimationPart(500);

		mAnimationPartFX = new ArrayList<>();
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "LOOKDOWNEND"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "shape", "LOOKDOWNEND"));
		playAnimationPart(50);
		mAnimationPartFX = new ArrayList<>();
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "LOOKUP"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "shape", "LOOKUP"));
		playAnimationPart(50);

		// 4x Gesicht Beruehren
		for (int i = 0; i < 4; i++) {
			mAnimationPartFX = new ArrayList<>();
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftUpperArmFX, "rotate", 20));
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "rotate", -10));
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "zrotate", -10));
			playAnimationPart(300);
			pauseAnimation(150);
			mAnimationPartFX = new ArrayList<>();
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftUpperArmFX, "rotate", -20));
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "rotate", 10));
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "zrotate", 10));
			playAnimationPart(300);
		}

		mAnimationPartFX = new ArrayList<>();
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "LOOKUPEND"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "shape", "LOOKUPEND"));
		playAnimationPart(50);
		mAnimationPartFX = new ArrayList<>();
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "LOOKDOWN"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "shape", "LOOKDOWN"));
		playAnimationPart(50);

		pauseAnimation(1000);

		// Kopf wieder vorne Neigen
		mAnimationPartFX = new ArrayList<>();
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mHeadFX, "yrotate", 20));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mHeadFX, "rotate", 30));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftUpperArmFX, "rotate", 100));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftWrist, "yrotate", 180));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftWrist, "rotate", -30));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "rotate", 80));
		playAnimationPart(500);

		// 10mal PC schlagen
		for (int i = 0; i < 10; i++) {
			mAnimationPartFX = new ArrayList<>();
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "rotate", -90));
			playAnimationPart(150);
			mAnimationPartFX = new ArrayList<>();
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "rotate", 90));
			playAnimationPart(150);
		}

		// Hand neben dem PC platzieren
		mAnimationPartFX = new ArrayList<>();
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "yrotate", -60));
		playAnimationPart(150);

		// PC wegschmeissen
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400), laptop);
		translateTransition.setFromX(laptop.getTranslateX());
		translateTransition.setToX(laptop.getTranslateX() - 500);
		mAnimationPartFX = new ArrayList<>();
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "yrotate", 60));
		translateTransition.play();
		playAnimationPart(150);

		pauseAnimation(1000);

		TranslateTransition backchair = new TranslateTransition(Duration.millis(400), chair);
		backchair.setFromZ(chair.getTranslateZ() - 50);
		backchair.setToZ(recordChairZPosition);
		backchair.play();

		mAnimationPartFX = new ArrayList<>();
		if (mStickmanFX.mType == StickmanFX.TYPE.FEMALE)
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mUpperBodyAndHand, "ytranslate", -60));
		else
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mUpperBodyAndHand, "ytranslate", -50));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mDownBody, "ytranslate", -50));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftUpperLegFX, "rotate", 80));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeLegFX, "rotate", -90));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperLegFX, "rotate", 80));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeLegFX, "rotate", -90));

		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftUpperArmFX, "rotate", -30));
		if (mStickmanFX.mType == StickmanFX.TYPE.FEMALE)
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "rotate", 88));
		else
			mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "rotate", 85));

		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "rotate", -30));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "zrotate", -20));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "rotate", 85));
		playAnimationPart(500);

		mAnimationPartFX = new ArrayList<>();
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftUpperArmFX, "rotate", 45));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftForeArmFX, "rotate", -55));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftWrist, "rotate", 40));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftWrist, "yrotate", -50));

		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightUpperArmFX, "rotate", 45));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightForeArmFX, "rotate", -55));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightWrist, "rotate", 40));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightWrist, "yrotate", 50));

		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mUpperBodyAndHand, "rotate", -10));

		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mHeadFX, "yrotate", -20));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "LOOKDOWNEND"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "shape", "LOOKDOWNEND"));
		playAnimationPart(500);

		mAnimationPartFX = new ArrayList<>();
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mFaceWrinkleFX, "shape", "ANGRYEND"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyebrowFX, "shape", "ANGRYEND"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mLeftEyeFX, "shape", "ANGRYEND"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyebrowFX, "shape", "ANGRYEND"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mRightEyeFX, "shape", "ANGRYEND"));
		mAnimationPartFX.add(new AnimationContentFX(mStickmanFX.mMouthFX, "shape", "ANGRYEND"));
		playAnimationPart(300);

		TranslateTransition backtrans = new TranslateTransition(Duration.millis(400), laptop);
		backtrans.setFromX(laptop.getTranslateX() - 500);
		backtrans.setToX(recordLaptopXPosition);
		backtrans.play();

		pauseAnimation(1000);

		table.setVisible(false);
		laptop.setVisible(false);
		chair.setVisible(false);

		StickmanStageController.currentRadioButton.setSelected(false);
	}
}
