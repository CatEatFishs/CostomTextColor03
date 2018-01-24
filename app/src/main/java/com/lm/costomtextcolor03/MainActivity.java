package com.lm.costomtextcolor03;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ColorTrackTextView mColorTrackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mColorTrackView = (ColorTrackTextView) findViewById(R.id.colorTrackView);
    }
    public void leftToRright(View view) {
        mColorTrackView.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentProgess = (float) animation.getAnimatedValue();
                mColorTrackView.setCurrentProgess( currentProgess);

            }
        });
        valueAnimator.start();
    }

    public void rightToLeft(View view) {
        mColorTrackView.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentProgess = (float) animation.getAnimatedValue();
                mColorTrackView.setCurrentProgess( currentProgess);

            }
        });
        valueAnimator.start();
    }


}
