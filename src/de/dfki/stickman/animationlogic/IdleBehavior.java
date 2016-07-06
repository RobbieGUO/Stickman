package de.dfki.stickman.animationlogic;

import de.dfki.stickman.Stickman;
import de.dfki.stickman.animation.environment.SimplexNoise;
/**
 *
 * @author Patrick Gebhard
 *
 */
public class IdleBehavior extends Thread {
	private int mSleepTime = 100;  // control the duration after one segment. control the speed of the wobble
	private Stickman mStickman;	
	private SimplexNoise mSimplexNoise;  // generate perlin noise Array 2d
	private int count1 = 1;    // index of perlin noise Array
	private int count2 = 1;    // index of perlin noise Array
	private UnconsciouslyAction mUnconsciouslyAction;
	private String mbehavior;
	
	public IdleBehavior(Stickman s, SimplexNoise noise, String g){
		mStickman = s;
		mSimplexNoise=noise;
		mUnconsciouslyAction = new UnconsciouslyAction(mStickman, mSimplexNoise, g);
		mUnconsciouslyAction.start();
	}

    @Override
    public void run() {
      	a:while(mStickman.mIdleRun){
      	// to generate index of perlin noise Array
        count1++;
        if(count1 == 200){ 
        	count1 = 0;
        	count2++;
        }
        if(count2 == 200)
        	count2 =1;
        
        mStickman.mWobble = ((mSimplexNoise.getNoise(count2,count1)*10))/20;
        System.out.printf("%.5f",mSimplexNoise.getNoise(count1,count2));
        System.out.println();
       	double mAdjust = mStickman.mWobble;  
       	
       	// 40 segments to achieve the wobble: come and back
       	for(int i=0; i<19; i++)
       	{
       		mStickman.mWobble= mStickman.mWobble + mAdjust;
       		mStickman.repaint();        		
       		try {
                   sleep(mSleepTime, 0);
            } catch (InterruptedException ex) {
                   mStickman.mLogger.severe(ex.getMessage());
              }
        }
       	
       	for(int i=0; i<19; i++)
       	{	
       		mStickman.mWobble= mStickman.mWobble - mAdjust;
       		if(i == 18)
       			mStickman.mWobble=0;       		
       			mStickman.repaint();
        		
       		try {
                   sleep(mSleepTime, 0);
            } catch (InterruptedException ex) {
                   mStickman.mLogger.severe(ex.getMessage());
              }     		
        }     
       }
    while(mUnconsciouslyAction.isAlive());
    }
}
