package com.example.sanakazi.ratingbaranimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txt_willgo_count,txt_wontgo_count,txt_maybe_count;
    ProgressBar progressBar_willgo,progressBar_wontgo,progressBar_maybe;
    double totalPollPercent;

    Double[] pollsList = new Double[]{7.0,11.0,10.0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_willgo_count = (TextView)findViewById(R.id.txt_willgo_count);
        progressBar_willgo = (ProgressBar)findViewById(R.id.progressBar_willgo);

        txt_wontgo_count = (TextView)findViewById(R.id.txt_wontgo_count);
        progressBar_wontgo = (ProgressBar)findViewById(R.id.progressBar_wontgo);

        txt_maybe_count = (TextView)findViewById(R.id.txt_maybe_count);
        progressBar_maybe = (ProgressBar)findViewById(R.id.progressBar_maybe);

        styling();

    }


    private void styling()
    {
        totalPollPercent = pollsList[0] + pollsList[1] + pollsList[2];

        final int willgoPercent = (int) ((pollsList[0] / totalPollPercent) * 100);
        final int maybePercent = (int) ((pollsList[1] / totalPollPercent) * 100);
        final int wontgoPercent = (int) ((pollsList[2] / totalPollPercent) * 100);

        //will go
        ProgressBarAnimation anim = new ProgressBarAnimation(progressBar_willgo, 0, willgoPercent);
        anim.setDuration(1000);
        progressBar_willgo.startAnimation(anim);

        txt_willgo_count.setText("" + willgoPercent + "%");


        //wont go
        ProgressBarAnimation anim1 = new ProgressBarAnimation(progressBar_wontgo, 0, wontgoPercent);
        anim1.setDuration(1000);
        progressBar_wontgo.startAnimation(anim1);

        txt_wontgo_count.setText("" + wontgoPercent + "%");

        //may be
        ProgressBarAnimation anim2 = new ProgressBarAnimation(progressBar_maybe, 0, maybePercent);
        anim2.setDuration(1000);
        progressBar_maybe.startAnimation(anim2);

        txt_maybe_count.setText("" + maybePercent + "%");
    }

    public class ProgressBarAnimation extends Animation {
        private ProgressBar progressBar;
        private float from;
        private float to;

        public ProgressBarAnimation(ProgressBar progressBar, float from, float to) {
            super();
            this.progressBar = progressBar;
            this.from = from;
            this.to = to;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            float value = from + (to - from) * interpolatedTime;
            progressBar.setProgress((int) value);
        }

    }
}
