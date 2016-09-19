package de.dfki.stickman.stagecontroller;

import de.dfki.common.CommonStickman;
import de.dfki.common.StageStickman;
import de.dfki.common.StageStickmanController;
import de.dfki.common.CommonStickmansOnStage;
import de.dfki.stickman.Stickman;
import de.dfki.stickman.StickmanStage;

/**
 * Created by alvaro on 9/19/16.
 */
public class StageController implements StageStickmanController {
    private CommonStickmansOnStage commonStickmansOnStage;
    private StageStickman stickmanStage;
    private boolean fullScreen = false;

    public StageController(){
        commonStickmansOnStage = new StickmansOnStage(stickmanStage, this);
        stickmanStage = StickmanStage.getInstance();
        stickmanStage.setStickamnsOnStage(commonStickmansOnStage);

    }
    @Override
    public void clearStage() {

    }

    @Override
    public void animate(String stickmanname, String name, int duration, String text, boolean block) {
        Stickman sm = (Stickman) commonStickmansOnStage.getStickman(stickmanname);
        sm.doAnimation(name, duration, text, block);
    }

    @Override
    public boolean ismNetwork() {
        return false;
    }

    @Override
    public void sendTimeMarkInformation(String timemark) {

    }

    @Override
    public void sendAnimationUpdate(String state, String id) {

    }

    @Override
    public void addStickman(String name) {
        commonStickmansOnStage.addStickman(name, fullScreen);
    }

    @Override
    public CommonStickman getStickman(String name) {
        return commonStickmansOnStage.getStickman(name);
    }

    @Override
    public void launchStickmanStage() {

    }

    @Override
    public StageStickman getStickmanStage() {
        return stickmanStage;
    }

    public CommonStickmansOnStage getCommonStickmansOnStage() {
        return commonStickmansOnStage;
    }

    @Override
    public String getStageIdentifier() {
        return null;
    }

    @Override
    public void setFullScreen(boolean fullScreen) {
        fullScreen = fullScreen;
    }
}
