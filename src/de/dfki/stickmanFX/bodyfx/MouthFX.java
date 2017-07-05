package de.dfki.stickmanFX.bodyfx;

import de.dfki.common.Gender;
import de.dfki.stickmanSwing.StickmanSwing;
import de.dfki.stickmanFX.animationlogic.AnimatorFX;
import java.awt.Dimension;
import java.awt.Point;

import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;

/**
 *
 * @author Beka Aptsiauri
 *
 */
public class MouthFX extends BodyPartFX {

    public static enum SHAPE {
        DEFAULT, SMILE, SMILEEND, SAD, SADEND, ANGRY, ANGRYEND,
        ANGRYSMALLMOUTH, ANGRYSMALLMOUTHEND, SURPRISED, SURPRISEDEND,
        HAPPY, HAPPYEND, DISGUSTED, DISGUSTEDEND, CONTEMPT, CONTEMPTEND,
        EXCITED, EXCITEDEND, EMBARRASSED, EMBARRASSEDEND, FEAR, FEAREND, O,
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN,
        TWELVE, THIRTEEN, FOURTEEN, NINETEEN, TWENTY, TURNRIGHT, TURNLEFT
    };

    HeadFX mHeadFX;
    Path mPath;
    public MouthFX.SHAPE mShape = MouthFX.SHAPE.DEFAULT;
    private String faceFlag = "front";

    public MouthFX(HeadFX head) {
        mHeadFX = head;
        mLength = 20;
        mSize = new Dimension(mLength * 2, 5);
        mDefaultRotationPoint = mHeadFX.mDefaultRotationPoint;
        mColor = Color.rgb(mHeadFX.mStickmanFX.mType == Gender.TYPE.FEMALE ? 64 : 32, 0, 0, (128 * 100 / 255) / 100f);
        mPath = new Path();
        this.getChildren().add(mPath);
        init();
    }

    @Override
    public void setShape(String s) {
        MouthFX.SHAPE shape = MouthFX.SHAPE.valueOf(s);
        mShape = (shape != null) ? shape : MouthFX.SHAPE.DEFAULT;
    }

    @Override
    public void resetShape() {
        mShape = MouthFX.SHAPE.DEFAULT;
    }

    @Override
    public void createShape() {
        mStart = mHeadFX.getMouthPostion();
        mEnd = new Point(mStart.x + mLength / 2, mStart.y);

        double movement;

        clearDrawObjects();
        clearChildren(this);

        mPath = new Path();

        switch (mShape) {
            case DEFAULT:
//			if (mHeadFX.mStickmanFX.setCharacterInvisible == false)
//				mColorRecorder = mColor;
                if (mHeadFX.mStickmanFX.setCharacterInvisible == true) {
                    if (mHeadFX.mStickmanFX.fadeControler == true) // Added by Robbie
                    {
                        int fadeFactor = mHeadFX.mStickmanFX.mMouthFX.mShapeAnimationStep * 6;
                        if (fadeFactor <= 12) {
                            fadeFactor = 0;
                        }
                        mColor = Color.rgb(mHeadFX.mStickmanFX.mType == Gender.TYPE.FEMALE ? 64 : 32, 0, 0,
                                (fadeFactor * 100 / 255) / 100f);
                    } else {
                        int fadeFactor = (20 - mHeadFX.mStickmanFX.mMouthFX.mShapeAnimationStep) * 6;
                        if (fadeFactor >= 107) {
                            mColor = mColorRecorder;
                        } else {
                            mColor = Color.rgb(mHeadFX.mStickmanFX.mType == Gender.TYPE.FEMALE ? 64 : 32, 0, 0, (fadeFactor * 100 / 255) / 100f);
                        }
                    }
                }
                if (faceFlag.equals("left")) {
                    mPath.getElements().add(new MoveTo(mStart.x + mLength + 25, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y + 1, mStart.x + mLength + 20, mStart.y));
                } else if (faceFlag.equals("right")) {
                    mPath.getElements().add(new MoveTo(mStart.x - 45, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y + 1, mStart.x - 40, mStart.y));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1, mEnd.x, mEnd.y));
                }
                break;

            case SMILE:
                movement = AnimatorFX.sMAX_ANIM_STEPS - mShapeAnimationStep;

                mPath.getElements().add(new MoveTo(mStart.x - mLength / 2 - movement / 3 * 2, mStart.y - movement / 2));
                mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1 + movement / 3 * 2, mEnd.x + movement / 3 * 2, mStart.y - movement / 2));
                break;

            case SMILEEND:
                movement = mShapeAnimationStep - 1;
                if (movement <= 1) {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1, mEnd.x, mEnd.y));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2 - movement / 3 * 2, mStart.y - movement / 2));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1 + movement / 3 * 2,
                            mEnd.x + movement / 3 * 2, mStart.y - movement / 2));
                }
                break;

            case SAD:
                movement = AnimatorFX.sMAX_ANIM_STEPS - mShapeAnimationStep;

//                mPath.getElements().add(new MoveTo(mStart.x - mLength / 2 - movement / 2, mStart.y + movement / 4));
//                1 SAD
//                mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - movement, mEnd.x + movement / 2, mEnd.y + movement / 4));
                // LIGHT SAD 1
//                mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - movement/4, mEnd.x + movement / 2, mEnd.y + movement / 4));
                // LIGHT SAD 2
                mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - 2, mEnd.x, mEnd.y));
                break;

            case SADEND:
                movement = mShapeAnimationStep - 1;
                if (movement <= 1) {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1, mEnd.x, mEnd.y));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2 - movement / 2, mStart.y + movement / 4));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - movement, mEnd.x + movement / 2, mEnd.y + movement / 4));
                }
                break;

            case ANGRY:
                movement = AnimatorFX.sMAX_ANIM_STEPS - mShapeAnimationStep;

                mPath.getElements().add(new MoveTo(mStart.x - mLength / 2 - movement / 4, mStart.y + movement / 10));
                mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1 - movement / 3 * 2, mEnd.x + movement / 4, mStart.y + movement / 10));

                break;

            case ANGRYEND:
                movement = mShapeAnimationStep - 1;

                if (movement <= 1) {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1, mEnd.x, mEnd.y));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2 - movement / 4, mStart.y + movement / 10));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1 - movement / 3 * 2, mEnd.x + movement / 4, mStart.y + movement / 10));
                }
                break;

            case ANGRYSMALLMOUTH:
                movement = AnimatorFX.sMAX_ANIM_STEPS - mShapeAnimationStep;

                mPath.getElements().add(new MoveTo(mStart.x - mLength / 2 + movement / 10, mStart.y + movement / 10));
                mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1 - movement / 4, mEnd.x - movement / 10, mStart.y + movement / 10));
                break;

            case ANGRYSMALLMOUTHEND:
                movement = mShapeAnimationStep - 1;

                if (movement <= 1) {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1, mEnd.x, mEnd.y));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2 + movement / 10, mStart.y + movement / 10));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1 - movement / 4, mEnd.x - movement / 10, mStart.y + movement / 10));
                }
                break;

            case SURPRISED:
                movement = AnimatorFX.sMAX_ANIM_STEPS - mShapeAnimationStep;

                mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                mPath.getElements().add(new QuadCurveTo(mStart.x - movement / 4 - 4, mStart.y - movement / 2, mStart.x, mStart.y - movement / 2 - 1));
                mPath.getElements().add(new QuadCurveTo(mStart.x + movement / 4 + 4, mStart.y - movement / 2, mEnd.x, mStart.y));
                mPath.getElements().add(new QuadCurveTo(mStart.x + movement / 4 + 4, mStart.y + movement / 2, mStart.x, mStart.y + movement / 2 + 1));
                mPath.getElements().add(new QuadCurveTo(mStart.x - movement / 4 - 4, mStart.y + movement / 2, mStart.x - mLength / 2, mStart.y));
                break;

            case SURPRISEDEND:
                movement = mShapeAnimationStep - 1;

                if (movement <= 1) {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1, mEnd.x, mEnd.y));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - movement / 4 - 4, mStart.y - movement / 2, mStart.x, mStart.y - movement / 2 - 1));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + movement / 4 + 4, mStart.y - movement / 2, mEnd.x, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + movement / 4 + 4, mStart.y + movement / 2, mStart.x, mStart.y + movement / 2 + 1));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - movement / 4 - 4, mStart.y + movement / 2, mStart.x - mLength / 2, mStart.y));
                }
                break;

            case HAPPY:
                movement = AnimatorFX.sMAX_ANIM_STEPS - mShapeAnimationStep;

                mPath.getElements().add(new MoveTo(mStart.x - mLength / 2 - movement / 2, mStart.y - movement / 4));
                mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1 + movement, mEnd.x + movement / 2, mStart.y - movement / 4));
                mPath.getElements().add(new LineTo(mStart.x - mLength / 2 - movement / 2, mStart.y - movement / 4));
                break;

            case HAPPYEND:
                movement = mShapeAnimationStep - 1;
                if (movement <= 1) {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1, mEnd.x, mEnd.y));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2 - movement / 2, mStart.y - movement / 4));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1 + movement, mEnd.x + movement / 2, mStart.y - movement / 4));
                    mPath.getElements().add(new LineTo(mStart.x - mLength / 2 - movement / 2, mStart.y - movement / 4));
                }
                break;

            case DISGUSTED:
                movement = mLength / 2 + (AnimatorFX.sMAX_ANIM_STEPS - mShapeAnimationStep) / 2;

                mPath.getElements().add(new MoveTo(mStart.x - movement, mStart.y));
                mPath.getElements().add(new QuadCurveTo(mStart.x - movement * 2 / 3, mStart.y - movement / 4, mStart.x - movement / 3, mStart.y));
                mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + movement / 4, mStart.x + movement / 3, mStart.y));
                mPath.getElements().add(new QuadCurveTo(mStart.x + movement * 2 / 3, mStart.y - movement / 4, mStart.x + movement, mEnd.y));
                break;

            case DISGUSTEDEND:
                movement = mLength / 2 + mShapeAnimationStep / 2;

                if (mShapeAnimationStep - 1 <= 1) {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1, mEnd.x, mEnd.y));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - movement, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - movement * 2 / 3, mStart.y - movement / 4, mStart.x - movement / 3, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + movement / 4, mStart.x + movement / 3, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + movement * 2 / 3, mStart.y - movement / 4, mStart.x + movement, mEnd.y));
                }
                break;

            case CONTEMPT:
                movement = AnimatorFX.sMAX_ANIM_STEPS - mShapeAnimationStep;

                mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - movement / 1.5, mEnd.x + movement / 2, mEnd.y - movement / 2));
                break;

            case CONTEMPTEND:
                movement = mShapeAnimationStep - 1;

                if (movement <= 1) {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1, mEnd.x, mEnd.y));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - movement / 1.5, mEnd.x + movement / 2, mEnd.y - movement / 2));
                }
                break;

            case FEAR:
                movement = AnimatorFX.sMAX_ANIM_STEPS - mShapeAnimationStep;

                mPath.getElements().add(new MoveTo(mStart.x - mLength / 2 - movement / 4, mStart.y));
                mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - movement / 2, mEnd.x + movement / 4, mEnd.y));
                mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - 1, mStart.x - mLength / 2 - movement / 4, mStart.y));
                break;

            case FEAREND:
                movement = mShapeAnimationStep - 1;

                if (movement <= 1) {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1, mEnd.x, mEnd.y));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2 - movement / 4, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - movement / 2, mEnd.x + movement / 4, mEnd.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - 1, mStart.x - mLength / 2 - movement / 4, mStart.y));
                }
                break;

            case EXCITED:
                movement = AnimatorFX.sMAX_ANIM_STEPS - mShapeAnimationStep;

                mPath.getElements().add(new MoveTo(mStart.x - mLength / 2 - movement / 3 * 2, mStart.y - movement / 2));
                mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + movement, mEnd.x + movement / 3 * 2, mStart.y - movement / 2));
                mPath.getElements().add(new LineTo(mStart.x - mLength / 2 - movement / 3 * 2, mStart.y - movement / 2));
                break;

            case EXCITEDEND:
                movement = mShapeAnimationStep - 1;

                if (movement <= 1) {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1, mEnd.x, mEnd.y));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2 - movement / 3 * 2, mStart.y - movement / 2));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + movement, mEnd.x + movement / 3 * 2, mStart.y - movement / 2));
                    mPath.getElements().add(new LineTo(mStart.x - mLength / 2 - movement / 3 * 2, mStart.y - movement / 2));
                }
                break;

            case EMBARRASSED:
                movement = (AnimatorFX.sMAX_ANIM_STEPS - mShapeAnimationStep);

                mPath.getElements().add(new MoveTo(mStart.x - mLength / 2 + movement / 10 * 7, mStart.y + movement / 20));
                mPath.getElements().add(new QuadCurveTo((mStart.x - mLength / 2 + mEnd.x + movement / 10 * 3) / 2, mStart.y + 1, mEnd.x + movement / 10 * 3, mEnd.y + movement / 20));
                break;

            case EMBARRASSEDEND:
                movement = mShapeAnimationStep - 1;

                if (movement <= 1) {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 1, mEnd.x, mEnd.y));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2 + movement / 10 * 7, mStart.y + movement / 20));
                    mPath.getElements().add(new QuadCurveTo((mStart.x - mLength / 2 + mEnd.x + movement / 10 * 3) / 2, mStart.y + 1, mEnd.x + movement / 10 * 3, mEnd.y + movement / 20));
                }
                break;

            case O:
                if (faceFlag.equals("left")) {
                    mPath.getElements().add(new MoveTo(mStart.x + mLength + 25, mStart.y - 2));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y - 2, mStart.x + mLength + 20, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y + 2, mStart.x + mLength + 25, mStart.y + 2));

                } else if (faceFlag.equals("right")) {
                    mPath.getElements().add(new MoveTo(mStart.x - 45, mStart.y - 2));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y - 2, mStart.x - 40, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y + 2, mStart.x - 45, mStart.y + 2));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - mLength / 2, mEnd.x, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + mLength / 2, mStart.x - mLength / 2, mStart.y));
                }
                break;

            case ONE:
            case SIX:
            case FOURTEEN:
            case NINETEEN:
                if (faceFlag.equals("left")) {
                    mPath.getElements().add(new MoveTo(mStart.x + mLength + 25, mStart.y - 1));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y - 1, mStart.x + mLength + 20, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y + 1, mStart.x + mLength + 25, mStart.y + 1));

                } else if (faceFlag.equals("right")) {
                    mPath.getElements().add(new MoveTo(mStart.x - 45, mStart.y - 1));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y + 1, mStart.x - 40, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y - 1, mStart.x - 40, mStart.y + 1));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - mLength / 5, mEnd.x, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + mLength / 5, mStart.x - mLength / 2, mStart.y));
                }
                break;

            case TWO:
                if (faceFlag.equals("left")) {
                    mPath.getElements().add(new MoveTo(mStart.x + mLength + 27, mStart.y - 4));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y - 2, mStart.x + mLength + 20, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y + 2, mStart.x + mLength + 23, mStart.y + 4));

                } else if (faceFlag.equals("right")) {
                    mPath.getElements().add(new MoveTo(mStart.x - 47, mStart.y - 4));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y - 2, mStart.x - 40, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y + 2, mStart.x - 43, mStart.y + 4));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2.8, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - mLength / 2.8, mEnd.x - mLength / 6, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + mLength / 2, mStart.x - mLength / 2.8, mStart.y));

                }
                break;

            case THREE:
            case TWENTY:
                if (faceFlag.equals("left")) {
                    mPath.getElements().add(new MoveTo(mStart.x + mLength + 26, mStart.y - 3));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y - 2, mStart.x + mLength + 20, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y + 2, mStart.x + mLength + 24, mStart.y + 3));

                } else if (faceFlag.equals("right")) {
                    mPath.getElements().add(new MoveTo(mStart.x - 46, mStart.y - 3));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y - 2, mStart.x - 40, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y + 2, mStart.x - 44, mStart.y + 3));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2.8, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - mLength / 2.5, mEnd.x - mLength / 6, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + mLength / 2.5, mStart.x - mLength / 2.8, mStart.y));
                }
                break;

            case FOUR:
                if (faceFlag.equals("left")) {
                    mPath.getElements().add(new MoveTo(mStart.x + mLength + 25, mStart.y - 2));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y - 2, mStart.x + mLength + 20, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y + 2, mStart.x + mLength + 25, mStart.y + 2));

                } else if (faceFlag.equals("right")) {
                    mPath.getElements().add(new MoveTo(mStart.x - 45, mStart.y - 2));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y - 2, mStart.x - 40, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y + 2, mStart.x - 45, mStart.y + 2));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - 3, mEnd.x, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + mLength / 2, mStart.x - mLength / 2, mStart.y));
                }
                break;

            case FIVE:
            case EIGHT:
                if (faceFlag.equals("left")) {
                    mPath.getElements().add(new MoveTo(mStart.x + mLength + 27, mStart.y - 4));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y - 2, mStart.x + mLength + 20, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y + 2, mStart.x + mLength + 23, mStart.y + 4));

                } else if (faceFlag.equals("right")) {
                    mPath.getElements().add(new MoveTo(mStart.x - 47, mStart.y - 4));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y - 2, mStart.x - 40, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y + 2, mStart.x - 43, mStart.y + 4));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2.8, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - mLength / 2, mEnd.x - mLength / 6, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + mLength / 2, mStart.x - mLength / 2.8, mStart.y));
                }
                break;

            case SEVEN:
                if (faceFlag.equals("left")) {
                    mPath.getElements().add(new MoveTo(mStart.x + mLength + 25, mStart.y - 1));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y - 1, mStart.x + mLength + 20, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y + 1, mStart.x + mLength + 25, mStart.y + 1));

                } else if (faceFlag.equals("right")) {
                    mPath.getElements().add(new MoveTo(mStart.x - 45, mStart.y - 1));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y + 1, mStart.x - 40, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y - 1, mStart.x - 40, mStart.y + 1));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 3, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - 3, mEnd.x - mLength / 5, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + 3, mStart.x - mLength / 3, mStart.y));
                }
                break;

            case NINE:
                if (faceFlag.equals("left")) {
                    mPath.getElements().add(new MoveTo(mStart.x + mLength + 26, mStart.y - 3));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y - 2, mStart.x + mLength + 20, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y + 2, mStart.x + mLength + 24, mStart.y + 3));

                } else if (faceFlag.equals("right")) {
                    mPath.getElements().add(new MoveTo(mStart.x - 46, mStart.y - 3));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y - 2, mStart.x - 40, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y + 2, mStart.x - 44, mStart.y + 3));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 3, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - mLength / 2.8, mEnd.x - mLength / 5, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + mLength / 1.6, mStart.x - mLength / 3, mStart.y));
                }
                break;

            case TEN:
                if (faceFlag.equals("left")) {
                    mPath.getElements().add(new MoveTo(mStart.x + mLength + 27, mStart.y - 4));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y - 2, mStart.x + mLength + 20, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y + 2, mStart.x + mLength + 23, mStart.y + 4));

                } else if (faceFlag.equals("right")) {
                    mPath.getElements().add(new MoveTo(mStart.x - 47, mStart.y - 4));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y - 2, mStart.x - 40, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y + 2, mStart.x - 43, mStart.y + 4));
                } else {
                    mPath.getElements().add(new MoveTo(mStart.x - mLength / 2.8, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y - mLength / 2.8, mEnd.x - mLength / 6, mStart.y));
                    mPath.getElements().add(new QuadCurveTo(mStart.x, mStart.y + mLength / 2, mStart.x - mLength / 2.8, mStart.y));
                }
                break;

            case TURNRIGHT:
                if (mHeadFX.mStickmanFX.setCharacterInvisible == true) {
                    if (mHeadFX.mStickmanFX.fadeControler == true) // Added by Robbie
                    {
                        int fadeFactor = mHeadFX.mStickmanFX.mMouthFX.mShapeAnimationStep * 6;
                        if (fadeFactor <= 12) {
                            fadeFactor = 0;
                        }
                        mColor = Color.rgb(mHeadFX.mStickmanFX.mType == Gender.TYPE.FEMALE ? 64 : 32, 0, 0,
                                (fadeFactor * 100 / 255) / 100f);
                    } else {
                        int fadeFactor = (20 - mHeadFX.mStickmanFX.mMouthFX.mShapeAnimationStep) * 6;
                        if (fadeFactor >= 107) {
                            mColor = mColorRecorder;
                        } else {
                            mColor = Color.rgb(mHeadFX.mStickmanFX.mType == Gender.TYPE.FEMALE ? 64 : 32, 0, 0, (fadeFactor * 100 / 255) / 100f);
                        }
                    }
                }

                mPath.getElements().add(new MoveTo(mStart.x - 45, mStart.y));
                mPath.getElements().add(new QuadCurveTo(mStart.x - 43, mStart.y + 1, mStart.x - 40, mStart.y));
                break;

            case TURNLEFT:
                if (mHeadFX.mStickmanFX.setCharacterInvisible == true) {
                    if (mHeadFX.mStickmanFX.fadeControler == true) // Added by Robbie
                    {
                        int fadeFactor = mHeadFX.mStickmanFX.mMouthFX.mShapeAnimationStep * 6;
                        if (fadeFactor <= 12) {
                            fadeFactor = 0;
                        }
                        mColor = Color.rgb(mHeadFX.mStickmanFX.mType == Gender.TYPE.FEMALE ? 64 : 32, 0, 0,
                                (fadeFactor * 100 / 255) / 100f);
                    } else {
                        int fadeFactor = (20 - mHeadFX.mStickmanFX.mMouthFX.mShapeAnimationStep) * 6;
                        if (fadeFactor >= 107) {
                            mColor = mColorRecorder;
                        } else {
                            mColor = Color.rgb(mHeadFX.mStickmanFX.mType == Gender.TYPE.FEMALE ? 64 : 32, 0, 0, (fadeFactor * 100 / 255) / 100f);
                        }
                    }
                }

                mPath.getElements().add(new MoveTo(mStart.x + mLength + 25, mStart.y));
                mPath.getElements().add(new QuadCurveTo(mStart.x + mLength + 22, mStart.y + 1, mStart.x + mLength + 20, mStart.y));
                break;

        }
        getChildren().add(mPath);
        addToDrawObjects(mPath);
        this.update();
    }

    protected void recordColor() {
        if (mHeadFX.mStickmanFX.setCharacterInvisible == false) {
            mColorRecorder = mColor;
        }
    }

    public void setFaceflag(String s) {
        faceFlag = s;
    }

    public String getFaceflag() {
        return faceFlag;
    }
}
