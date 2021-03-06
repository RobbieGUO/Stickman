/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.stickman.animation.environment.event;

import de.dfki.action.sequence.WordTimeMarkSequence;
import de.dfki.stickman.Stickman;
import de.dfki.stickman.animationlogic.AnimationContent;
import de.dfki.stickman.animationlogic.EventAnimation;
import de.dfki.stickman.environment.SpeechBubble;
import java.util.ArrayList;

/**
 *
 * @author Patrick Gebhard
 *
 */
public class Speaking extends EventAnimation {
	
	public Speaking() {
		super();
	}

	public Speaking(Stickman sm, int duration, boolean block) {
		super(sm, duration, block);
	}

	@Override
	public void playAnimation() {
		if (mParameter instanceof WordTimeMarkSequence) {
			mWTS = (WordTimeMarkSequence) mParameter;
		}

		mAnimationPart = new ArrayList<>();
		mAnimationPart.add(new AnimationContent(mStickman.mSpeechBubble, "shape", SpeechBubble.SHAPE.SPEAK.name(), mWTS));

		playEventAnimationPart();

		mAnimationPart.add(new AnimationContent(mStickman.mSpeechBubble, "shape", SpeechBubble.SHAPE.DEFAULT.name()));
		playAnimationPart(20);

	}
}
