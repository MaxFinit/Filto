package com.maxfin.filto.feed.view.cardstack.internal;

import android.view.animation.Interpolator;

import com.maxfin.filto.feed.view.cardstack.Direction;


public interface AnimationSetting {
    Direction getDirection();
    int getDuration();
    Interpolator getInterpolator();
}
