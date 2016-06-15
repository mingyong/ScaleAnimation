package com.jkxy.scaleanimation;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private ImageView ivMao, ivLv;
    private RelativeLayout root;
    private ScaleAnimation scNarrow = null;
    private ScaleAnimation scExpand = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(R.id.s1, R.id.s2);
    }

    /**
     * @param id1 第一个展示的图片
     * @param id2 第二个展示的图片
     */
    private void init(int id1, int id2) {
        ivMao = (ImageView) findViewById(id1);
        ivLv = (ImageView) findViewById(id2);
        root = (RelativeLayout) findViewById(R.id.root);
        showMao();

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivMao.getVisibility() == View.VISIBLE) {
                    initScN();
                    ivMao.startAnimation(scNarrow);

                } else {
                    initScN();
                    ivLv.startAnimation(scNarrow);
                }
            }
        });
    }

    private void initScN() {
        if (scNarrow == null) {
            scNarrow = new ScaleAnimation(1, 0, 1, 1, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
            scNarrow.setDuration(500);
            scNarrow.setAnimationListener(getListener());
        }
    }

    private void initScE() {
        if (scExpand == null) {
            scExpand = new ScaleAnimation(0, 1, 1, 1, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
            scExpand.setDuration(500);
        }
    }


    @NonNull
    private Animation.AnimationListener getListener() {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (ivMao.getVisibility() == View.VISIBLE) {
                    ivMao.setAnimation(null);
                    showLv();
                    initScE();
                    ivLv.startAnimation(scExpand);

                } else {
                    ivLv.setAnimation(null);
                    initScE();
                    showMao();
                    ivMao.startAnimation(scExpand);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }


    private void showMao() {
        ivMao.setVisibility(View.VISIBLE);
        ivLv.setVisibility(View.INVISIBLE);
    }

    private void showLv() {
        ivMao.setVisibility(View.INVISIBLE);
        ivLv.setVisibility(View.VISIBLE);
    }
}
