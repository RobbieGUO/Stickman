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

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

/**
 *
 * @author Beka
 *
 */
public class NeckFX extends BodyPartFX {

	public static enum SHAPE {
		DEFAULT, FADEIN, FADEOUT
	};
	
	HeadFX mHeadFX;
	
	URL url;
    ColModelImporter imorter;
    MeshView neckMeshView;
    
    PhongMaterial material;

    public NeckFX.SHAPE mShape = NeckFX.SHAPE.DEFAULT;
    
	public NeckFX(HeadFX head) {
		mHeadFX = head;
		mLength = 8;
		mSize = new Dimension(4, mLength);
		mColor = Color.rgb(242, 227, 217, 1);
		
		material = new PhongMaterial();
		material.setDiffuseColor(mColor);
		
		url  = getClass().getClassLoader().getResource("BodyParts/neck.dae");
        imorter = new ColModelImporter();
        imorter.read(url);
        neckMeshView=  (MeshView) imorter.getImport()[0];
        neckMeshView.setMaterial(material);
		
		
		init();
	}

	@Override
	public void setShape(String s) {
		SHAPE shape = SHAPE.valueOf(s);
		mShape = (shape != null) ? shape : SHAPE.DEFAULT;
	}

	@Override
	public void resetShape() {
		mShape = NeckFX.SHAPE.DEFAULT;
	}
	
	public Point getBodyStartPosition() {
		return new Point(mEnd.x, mEnd.y+10);
	}

	@Override
	public void calculate(int step) {
		mStart = mHeadFX.getNeckStartPosition();
		mEnd = new Point(mStart.x, mStart.y + mLength);

		clearChildren(this);
		
		neckMeshView.setTranslateX(mStart.x);
		neckMeshView.setTranslateY(mStart.y + 5);
		neckMeshView.setTranslateZ(-105);
        
		Rotate rx = new Rotate(mXRotation,  Rotate.X_AXIS);
		Rotate ry = new Rotate(mYRotation, Rotate.Y_AXIS);
		Rotate rz = new Rotate(mZRotation, Rotate.Z_AXIS);
		
		Translate translation = new Translate(mXTranslation, mYTranslation, mZTranslation);
		
		neckMeshView.getTransforms().clear();
		neckMeshView.getTransforms().addAll(rx, ry, rz, translation);
		
		switch(mShape)
		{
		case FADEIN:
			if(step == 2)
			{
				mColor = new Color(mColor.getRed(), mColor.getGreen(), mColor.getBlue(), 0.0);
				update();
				neckMeshView.setVisible(false);
			}
			else if(mColor.getOpacity() != 0.0)
			{
				mColor = new Color(mColor.getRed(), mColor.getGreen(), mColor.getBlue(), mColor.getOpacity() - 0.052);
				update();
			}
			break;
			
		case FADEOUT:
			neckMeshView.setVisible(true);
			
			if(step == 2)
			{
				mColor = new Color(mColor.getRed(), mColor.getGreen(), mColor.getBlue(), 1.0);
				update();
			}
			else if(mColor.getOpacity() != 1.0)
			{
				mColor = new Color(mColor.getRed(), mColor.getGreen(), mColor.getBlue(), mColor.getOpacity() + 0.052);
				update();
			}
			break;
		}
		
		this.getChildren().add(neckMeshView);
	}
	
	@Override
	public void update()
	{
		material.setDiffuseColor(mColor);
		neckMeshView.setMaterial(material);
	}
	@Override
	protected void recordColor(){
		if(mHeadFX.mStickmanFX.setCharacterInvisible == false)
			mColorRecorder = mColor;
    }
}
