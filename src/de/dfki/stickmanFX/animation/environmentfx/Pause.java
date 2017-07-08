/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.stickmanFX.animation.environmentfx;

import de.dfki.stickmanFX.StickmanFX;
import de.dfki.stickmanFX.animationlogic.AnimationFX;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robbie
 */
public class Pause extends AnimationFX {

    public Pause(StickmanFX sm, int duration, boolean block) {
        super(sm, duration, block);
    }

    @Override
    public void playAnimation() {
//        mStickman.mScale time to delay
        String sParameter = (String) mParameter;
        sParameter = sParameter.trim();

        try {
            mStickmanFX.mScale = Float.parseFloat(sParameter);
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }

//        try {
//            Thread.sleep((long) (mStickmanFX.mScale));
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Pause.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
