package de.dfki.reeti.animationlogic;

import de.dfki.reeti.Reeti;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Beka Aptsiauri
 *
 */
public class EventAnimationSchedulerReeti extends Thread {

    Reeti mStickmanFX;
    boolean mRunning = true;
    public LinkedBlockingQueue<AnimationReeti> mAnimationQueue = new LinkedBlockingQueue<>();
    public Semaphore mTheBlockOfHell = new Semaphore(1);

    public EventAnimationSchedulerReeti(Reeti s) {
        setName(s.mName + "'s Event AnimationScheduler");
        mStickmanFX = s;
    }

    public void introduce(AnimationReeti a) {
        try {
            mStickmanFX.mLogger.info("AnimationSwing " + a + " added to event animation scheduler");

            mAnimationQueue.put(a);
        } catch (InterruptedException ex) {
            mStickmanFX.mLogger.severe(ex.getMessage());
        }
    }

    public void proceed(AnimationReeti a) {
        removeAnimation(a);
        mTheBlockOfHell.release();
    }

    public void removeAnimation(AnimationReeti a) {
        mAnimationQueue.remove(a);
    }

    public synchronized void end() {
        mRunning = false;

        // throw in a last animationFX that unblocks the scheduler letting him end
        try {
            mAnimationQueue.put(new AnimationReeti(mStickmanFX, 1, false) {
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(EventAnimationSchedulerReeti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (mRunning) {
            try {
                // serialize all animations here ...
                mTheBlockOfHell.acquire(1);

                // get the next animationFX in the animationFX queue
                AnimationReeti animationFX = mAnimationQueue.take();

                // tell the animationFX to render itself
                animationFX.mAnimationStart.release();

                // unblock the scheduler if animationFX is not blocking
                if (!animationFX.mBlocking) {
                    mTheBlockOfHell.release();
                    removeAnimation(animationFX);
                }
            } catch (InterruptedException ex) {
                mStickmanFX.mLogger.severe(ex.getMessage());
            }
        }
    }
}