package com.vincent.tabdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;

import com.vincent.tabdemo.tools.AppManager;

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
        Activity aty = AppManager.getActivity(MainActivity.class);
        if (aty != null && !aty.isFinishing()) {
            finish();
        }

        final View view = View.inflate(this, R.layout.start_app, null);
        setContentView(view);

        AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(1000);
        view.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                gotoMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
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
