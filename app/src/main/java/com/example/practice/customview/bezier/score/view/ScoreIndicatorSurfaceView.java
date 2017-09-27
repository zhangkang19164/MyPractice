package com.example.practice.customview.bezier.score.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * create time : 2017/09/19
 * desc        :
 */

public class ScoreIndicatorSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    public ScoreIndicatorSurfaceView(Context context) {
        this(context, null);
    }

    public ScoreIndicatorSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScoreIndicatorSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
