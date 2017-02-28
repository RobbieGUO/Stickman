package de.dfki.reeti.body;

import de.dfki.reeti.animationlogic.AnimatorReeti;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.awt.*;
import javafx.scene.effect.Light.Distant;
import javafx.scene.effect.Lighting;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.StrokeLineJoin;

/**
 *
 * @author Beka Aptsiauri
 *
 */
public class Mouth extends BodyPartFX {

    public static enum SHAPE {
        DEFAULT, MOUTHACTION, MOUTHACTIONEND, OPEN};

    Head mHeadFX;

    public Polygon currentDownLipPolygon;
    public Polygon currentUpperLipPolygon;
    Path mLips;
    
    public Point leftCorner;
    public Point rightCorner;
    public Point up;
    public Point down;
    private final int mouthLength = 32;
    
    private double rightCornerRegulator = 0;
    private double leftCornerRegulator = 0;
    private double upRegulator = 0;
    private double downRegulator = 0;
    
    private double recordLeftCornerRegulator;
    private double recordRightCornerRegulator;
    private double recordUpRegulator;
    private double recordDownRegulator;

    public Mouth.SHAPE mShape = Mouth.SHAPE.DEFAULT;

    public Mouth(Head head) {
        mHeadFX = head;
        mSize = new Dimension(mLength * 2, 5);

        mColor = Color.DARKGREY;

        currentUpperLipPolygon = new Polygon();
        currentDownLipPolygon = new Polygon();
        
        mLips = new Path();
        mLips.setId("upperLip");
        
        mStart = mHeadFX.getMouthPostion();
        leftCorner = new Point(-9, 35);
        rightCorner = new Point(leftCorner.x + mouthLength, leftCorner.y);
        up = new Point(leftCorner.x + mouthLength/2, leftCorner.y);
        down = new Point(up.x, up.y);
        Distant light = new Distant();
        light.setAzimuth(-135.0f);
        
        Lighting l = new Lighting();
        l.setLight(light);
        l.setSurfaceScale(5.0f);
        
        mLips.setEffect(l);

        init();

        mHeadFX.mHead.getChildren().addAll(mLips);
    }

    @Override
    public void init() {
        super.init();
        mLips.setTranslateX(mStart.x-7);
        mLips.setTranslateY(mStart.y + 24);
        mLips.setTranslateZ(-135.5);
    }

    @Override
    public void setShape(String s) {
        Mouth.SHAPE shape = Mouth.SHAPE.valueOf(s);
        mShape = (shape != null) ? shape : Mouth.SHAPE.DEFAULT;
    }

    @Override
    public void resetShape() {
        mShape = Mouth.SHAPE.DEFAULT;
    }

    @Override
    public void calculate(int step) {

        int x;
        int y;
        
        double movement;

        switch (mShape) {
            case DEFAULT:
                
                mLips.getElements().clear();
                mLips.setStrokeLineJoin(StrokeLineJoin.ROUND);
                mLips.setStrokeWidth(3);
                mLips.setStroke(mColor);
                mLips.getElements().add(new MoveTo(leftCorner.x, leftCorner.y));
                mLips.getElements().add(new QuadCurveTo(up.x, up.y, rightCorner.x, rightCorner.y));
                mLips.getElements().add(new QuadCurveTo(down.x, down.y, leftCorner.x, leftCorner.y));
                mLips.getElements().add(new ClosePath());
                mLips.setStyle("-fx-color: red");
                break;

            case MOUTHACTION:
                if(step == 20)
                {
                    recordDownRegulator = downRegulator;
                    recordUpRegulator = upRegulator;
                    recordLeftCornerRegulator = rightCornerRegulator;
                    recordRightCornerRegulator = leftCornerRegulator;
                    downRegulator = down.y;
                    upRegulator = up.y;
                    rightCornerRegulator = leftCorner.y;
                    leftCornerRegulator = rightCorner.y;
                }
                   
                downRegulator += recordDownRegulator/AnimatorReeti.sMAX_ANIM_STEPS;
                upRegulator += recordUpRegulator/AnimatorReeti.sMAX_ANIM_STEPS;
                rightCornerRegulator += recordLeftCornerRegulator/AnimatorReeti.sMAX_ANIM_STEPS;
                leftCornerRegulator += recordRightCornerRegulator/AnimatorReeti.sMAX_ANIM_STEPS;
                mLips.getElements().clear();
                mLips.getElements().add(new MoveTo(leftCorner.x, rightCornerRegulator));
                mLips.getElements().add(new QuadCurveTo(up.x, upRegulator, rightCorner.x, leftCornerRegulator));
                mLips.getElements().add(new QuadCurveTo(down.x, downRegulator, leftCorner.x, rightCornerRegulator));
                mLips.getElements().add(new ClosePath());
                
                if(step == 2)
                {
                    downRegulator = 0;
                    upRegulator = 0;
                    rightCornerRegulator = 0;
                    leftCornerRegulator = 0;
                }
                break;
                
            case MOUTHACTIONEND:
                if(step == 20)
                {
                    downRegulator = recordDownRegulator + down.y;
                    upRegulator = recordUpRegulator + up.y;
                    rightCornerRegulator = recordLeftCornerRegulator + leftCorner.y;
                    leftCornerRegulator = recordRightCornerRegulator + rightCorner.y;
                }
                
                downRegulator -= recordDownRegulator/AnimatorReeti.sMAX_ANIM_STEPS;
                upRegulator -= recordUpRegulator/AnimatorReeti.sMAX_ANIM_STEPS;
                rightCornerRegulator -= recordLeftCornerRegulator/AnimatorReeti.sMAX_ANIM_STEPS;
                leftCornerRegulator -= recordRightCornerRegulator/AnimatorReeti.sMAX_ANIM_STEPS;
                
                mLips.getElements().clear();
                mLips.getElements().add(new MoveTo(leftCorner.x, rightCornerRegulator));
                mLips.getElements().add(new QuadCurveTo(up.x, upRegulator, rightCorner.x, leftCornerRegulator));
                mLips.getElements().add(new QuadCurveTo(down.x, downRegulator, leftCorner.x, rightCornerRegulator));
                mLips.getElements().add(new ClosePath());
                
                if(step == 2)
                {
                    downRegulator = 0;
                    upRegulator = 0;
                    rightCornerRegulator = 0;
                    leftCornerRegulator = 0;
                }
                break;
                
            case OPEN:
                mLips.getElements().clear();
                mLips.getElements().add(new MoveTo(leftCorner.x, leftCorner.y));
                mLips.getElements().add(new QuadCurveTo(up.x, up.y-10, rightCorner.x, rightCorner.y));
                mLips.getElements().add(new QuadCurveTo(down.x, down.y+10, leftCorner.x, leftCorner.y));
                mLips.getElements().add(new ClosePath());
                break;

        }
    }

    public double getRightCornerRegulator() {
        return rightCornerRegulator;
    }

    public void setRightCornerRegulator(int leftCornerRegler) {
        this.rightCornerRegulator = leftCornerRegler;
    }

    public double getLeftCornerRegulator() {
        return leftCornerRegulator;
    }

    public void setLeftCornerRegulator(int rightCornerRegler) {
        this.leftCornerRegulator = rightCornerRegler;
    }

    public double getUpRegulator() {
        return upRegulator;
    }

    public void setUpRegulator(int upRegler) {
        this.upRegulator = upRegler;
    }

    public double getDownRegulator() {
        return downRegulator;
    }

    public void setDownRegulator(int downRegler) {
        this.downRegulator = downRegler;
    }
    

}
