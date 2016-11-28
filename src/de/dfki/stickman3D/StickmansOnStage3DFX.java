package de.dfki.stickman3D;

import de.dfki.common.interfaces.Stickman;
import de.dfki.common.CommonStickmansOnStage;
import de.dfki.common.interfaces.StageStickman;
import de.dfki.common.interfaces.StageStickmanController;
import de.dfki.stickmanSwing.StickmanSwing;
import de.dfki.stickmanfx.xmlsettings.XmlTransform;

/**
 * Created by alvaro on 9/19/16.
 */
public class StickmansOnStage3DFX extends CommonStickmansOnStage{
    public StickmansOnStage3DFX(StageStickman stageStickman) {
        super(stageStickman);
    }
    private  XmlTransform mXmlTransform = new XmlTransform();
    public StickmansOnStage3DFX(StageStickman stickmanStageFX, StageStickmanController controllerFX) {
        super(stickmanStageFX, controllerFX);
    }

    @Override
    protected void addStickmanToStage(String name, boolean fullScreen, StickmanSwing.TYPE gender) {
        if (fullScreen) {
            Stickman stickman = new Stickman3D(name, Stickman3D.TYPE.MALE, stickmanStage.getFullScreenScale(), stickmanStage.getFullScreenDimension());
            putFullStickmanOnStage(name, stickman);
        }else{
            Stickman stickman = new Stickman3D(name, Stickman3D.TYPE.MALE, DEFAULT_SCALE);
            putFullStickmanOnStage(name, stickman);
        }
    }

    @Override
    protected void addStickmanToStage(String name, boolean fullScreen, StickmanSwing.TYPE gender, boolean onlyFace) {
        if (fullScreen) {
            Stickman stickman = new Stickman3D(name, Stickman3D.TYPE.MALE, stickmanStage.getFullScreenScale(), stickmanStage.getFullScreenDimension());
            putFullStickmanOnStage(name, stickman);
        }else{
            float scale = DEFAULT_SCALE;
            if(onlyFace){
                scale = 1.0f;
            }
            Stickman stickman = new Stickman3D(name, Stickman3D.TYPE.MALE, scale);
            putFullStickmanOnStage(name, stickman);
        }
    }

    public XmlTransform getmXmlTransform(){
        return this.mXmlTransform;
    }
}