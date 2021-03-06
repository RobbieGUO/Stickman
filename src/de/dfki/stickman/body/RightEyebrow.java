package de.dfki.stickman.body;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.GeneralPath;

import de.dfki.stickman.animationlogic.Animator;

/**
 *
 * @author Patrick Gebhard
 *
 */
public class RightEyebrow extends BodyPart {

	public static enum SHAPE {

		DEFAULT, ANGRY, ANGRYEND, DISGUSTED, DISGUSTEDEND, SURPRISED, SURPRISEDEND, EXCITED, EXCITEDEND, EMBARRASSED, EMBARRASSEDEND
	};

	Head mHead;
	public RightEyebrow.SHAPE mShape = RightEyebrow.SHAPE.DEFAULT;

	public RightEyebrow(Head head) {
		mHead = head;
		mLength = 16;
		mSize = new Dimension(mLength, 5);
		mDefaultRotationPoint = mHead.mDefaultRotationPoint;
		mColor = new Color(0, 0, 0, 64);
		mStroke = new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

		init();
	}

	@Override
	public void setShape(String s) {
		RightEyebrow.SHAPE shape = RightEyebrow.SHAPE.valueOf(s);
		mShape = (shape != null) ? shape : RightEyebrow.SHAPE.DEFAULT;
	}

	@Override
	public void resetShape() {
		mShape = RightEyebrow.SHAPE.DEFAULT;
	}

	@Override
	public void createShape() {
//		mStart: right side
//		mEnd: left side
		mStart = mHead.getRightEyebrowPostion();
		mEnd = new Point(mStart.x - mLength, mStart.y);

		double movement;
		
		clearDrawObjects();
		GeneralPath gp = new GeneralPath();

		switch (mShape) {
			case DEFAULT:
				
				if(mHead.mStickman.setCharacterInvisible == true)
				{
					if(mHead.mStickman.fadeControler==true)             //Added by Robbie
					{
						int fadeFactor = (int) (mHead.mStickman.mMouth.mShapeAnimationStep*3.2);
						if(fadeFactor<=6) fadeFactor=0;
						mColor = new Color(0, 0, 0, fadeFactor);
					}
					else
					{
						int fadeFactor = (int)((20-mHead.mStickman.mMouth.mShapeAnimationStep)*3.2);
						if(fadeFactor >= 57) fadeFactor=0;
						if(fadeFactor >= 54) fadeFactor=64;
						mColor = new Color(0, 0, 0, fadeFactor);
					}
				}
				
				gp = new GeneralPath();
				gp.moveTo(mStart.x, mStart.y);
				gp.quadTo((mStart.x + mEnd.x) / 2, mStart.y - 3, mEnd.x, mEnd.y);
				break;
				
			case ANGRY:	
				movement = Animator.sMAX_ANIM_STEPS - mShapeAnimationStep;
				
				gp.moveTo(mStart.x + movement/4, mStart.y + movement/4);
				gp.quadTo((mStart.x + movement/4 + mEnd.x + movement/3) / 2, mStart.y + movement/4 - 3, mEnd.x + movement/4, mEnd.y);
				break;
				
			case ANGRYEND:	
				movement = mShapeAnimationStep - 1;
				if(movement<=1)
				{
					gp.moveTo(mStart.x, mStart.y);
					gp.quadTo((mStart.x + mEnd.x) / 2, mStart.y - 3, mEnd.x, mEnd.y);
				}
				else
				{
					gp.moveTo(mStart.x + movement/4, mStart.y + movement/4);
					gp.quadTo((mStart.x + movement/4 + mEnd.x + movement/3) / 2, mStart.y + movement/4 - 3, mEnd.x + movement/4, mEnd.y);
				}		
				break;
				
			case DISGUSTED:			
				movement = Animator.sMAX_ANIM_STEPS - mShapeAnimationStep;
				
				gp.moveTo(mStart.x, mStart.y - movement/4);
				gp.quadTo((mStart.x + mEnd.x) / 2, mStart.y - 3 + movement/7 , mEnd.x + movement/10, mEnd.y);
				break;
				
			case DISGUSTEDEND:			
				movement = mShapeAnimationStep - 1;
				if(movement<=1)
				{
					gp.moveTo(mStart.x, mStart.y);
					gp.quadTo((mStart.x + mEnd.x) / 2, mStart.y - 3, mEnd.x, mEnd.y);
				}
				else
				{
					gp.moveTo(mStart.x, mStart.y - movement/4);
					gp.quadTo((mStart.x + mEnd.x) / 2, mStart.y - 3 + movement/7 , mEnd.x + movement/10, mEnd.y);
				}
				break;
				
			case SURPRISED:
				movement = Animator.sMAX_ANIM_STEPS - mShapeAnimationStep;

				gp.moveTo(mStart.x, mStart.y-movement/7);
				gp.quadTo((mStart.x + mEnd.x) / 2, mStart.y-3-movement/7 , mEnd.x, mEnd.y-movement/7);
				break;
				
			case SURPRISEDEND:
				movement = mShapeAnimationStep - 1;
				if(movement<=1)
				{
					gp.moveTo(mStart.x, mStart.y);
					gp.quadTo((mStart.x + mEnd.x) / 2, mStart.y - 3, mEnd.x, mEnd.y);
				}
				else
				{
					gp.moveTo(mStart.x, mStart.y-movement/7);
					gp.quadTo((mStart.x + mEnd.x) / 2, mStart.y-3-movement/7 , mEnd.x, mEnd.y-movement/7);
				}
				break;
				
			case EXCITED:
				movement = Animator.sMAX_ANIM_STEPS - mShapeAnimationStep;
				
				gp.moveTo(mStart.x, mStart.y-movement/4);
				gp.quadTo((mStart.x + mEnd.x) / 2, mStart.y-3 -movement/5 , mEnd.x, mEnd.y-movement/4);
				break;
				
			case EXCITEDEND:
				movement = mShapeAnimationStep - 1;
				if(movement<=1)
				{
					gp.moveTo(mStart.x, mStart.y);
					gp.quadTo((mStart.x + mEnd.x) / 2, mStart.y - 3, mEnd.x, mEnd.y);
				}
				else
				{
					gp.moveTo(mStart.x, mStart.y-movement/4);
					gp.quadTo((mStart.x + mEnd.x) / 2, mStart.y-3 -movement/5 , mEnd.x, mEnd.y-movement/4);
				}
				break;
				
			case EMBARRASSED:
				movement = Animator.sMAX_ANIM_STEPS - mShapeAnimationStep;

				gp.moveTo(mStart.x + movement/2, mStart.y + movement/3);
				gp.quadTo((mStart.x + movement/2 + mEnd.x + movement/2) / 2, mStart.y - 3 + movement/10*7, mEnd.x + movement/2, mEnd.y + movement/2);				
				break;
				
			case EMBARRASSEDEND:
				movement = mShapeAnimationStep - 1;
				if(movement<=1)
				{
					gp.moveTo(mStart.x, mStart.y);
					gp.quadTo((mStart.x + mEnd.x) / 2, mStart.y - 3, mEnd.x, mEnd.y);
				}
				else
				{
					gp.moveTo(mStart.x + movement/2, mStart.y + movement/3);
					gp.quadTo((mStart.x + movement/2 + mEnd.x + movement/2) / 2, mStart.y - 3 + movement/10*7, mEnd.x + movement/2, mEnd.y + movement/2);				
				}
				break;
		}

		addToDrawObjects(gp);
	}

}
