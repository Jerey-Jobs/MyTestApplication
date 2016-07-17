package com.vincent.tabdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import butterknife.InjectView;

/**
 * Created by Xiamin on 2016/7/16.
 */
public class StartActivity extends Activity {
    @InjectView(R.id.start_button)
    Button gotoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 防止第三方跳转时出现双实例
//        Activity aty = AppManager.getActivity(MainActivity.class);
//        if (aty != null && !aty.isFinishing()) {
//            finish();
//        }

        final View view = View.inflate(this, R.layout.start_app, null);
        setContentView(view);

        AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);

        /**
         * 改成放大的属性动画
         */
        ObjectAnimator animtorAlpha = ObjectAnimator.ofFloat(view,"alpha",0.1f,1f);
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.2f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.2f);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(1500).play(animatorX).with(animatorY).with(animtorAlpha);
        set.start();

        set.addListener(new AnimatorListenerAdapter()
        {

            @Override
            public void onAnimationEnd(Animator animation)
            {

                startActivity(new Intent(StartActivity.this, MainActivity.class));
                StartActivity.this.finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


//        animation.setDuration(1000);
//        view.startAnimation(animation);
//
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                gotoMainActivity();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
    }

    /**
     * 界面跳转
     */
    private void gotoMainActivity()
    {
        Intent intent  = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


    void doButtonClick(View v)
    {
        switch (v.getId())
        {
            case R.id.start_button:
            {
                gotoMainActivity();
                break;
            }
            default:{

            }
        }
    }
}
