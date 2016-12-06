package de.dfki.stickman3D.animationlogic;

import java.util.concurrent.Semaphore;

import de.dfki.stickman3D.Stickman3D;

/**
 *
 * @author Beka Aptsiauri
 *
 */
public class AnimationPause {
	private final Stickman3D mStickmanFX;
	private final Animation mAnimationFX;
	public Semaphore mPauseEnd = new Semaphore(0);

	public AnimationPause(Stickman3D sm, Animation a, int duration) {
		mStickmanFX = sm;
		mAnimationFX = a;

		new WaitThread(duration).start();

		// block this until WaitThread will unblock
		try {
			mPauseEnd.acquire(1);
		} catch (InterruptedException ex) {
			mStickmanFX.mLogger.severe(ex.getMessage());
		}

		// tell animation to proceed
		mAnimationFX.mAnimationPartStart.release();
	}

	private class WaitThread extends Thread {
		int mSleepTime = 0;

		public WaitThread(int time) {
			mSleepTime = time;
		}

		@Override
		public void run() {
			// directly go to sleep
			try {
				sleep(mSleepTime);
			} catch (InterruptedException ex) {
				mStickmanFX.mLogger.severe(ex.getMessage());
			}
			mPauseEnd.release();
		}
	}
}