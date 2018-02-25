package com.krvang.lindved.songlistapp.bll;

import android.graphics.Color;
import android.widget.CheckBox;

import java.security.InvalidParameterException;

/**
 * Created by Lindved on 25-02-2018.
 */

public class BPMManager implements IBPMManager {

    private final int COUNT_PURPLE = 160;
    private final int COUNT_RED = 141;
    private final int COUNT_ORANGE = 129;
    private final int COUNT_GREEN = 101;
    private final int COUNT_BLACK = 0;

    @Override
    public int getBPMColor(int bpm) {
        if(bpm >= COUNT_PURPLE)
            return Color.rgb(255, 0, 238);
        else if(bpm >= COUNT_RED)
            return Color.RED;
        else if(bpm >= COUNT_ORANGE)
            return Color.rgb(255, 161, 0);
        else if (bpm >= COUNT_GREEN)
            return Color.GREEN;
        else if(bpm >= COUNT_BLACK)
            return Color.BLACK;
        throw new InvalidParameterException("Must be a positive Integer");
    }
}
