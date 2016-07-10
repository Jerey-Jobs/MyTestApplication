package com.example.peoperanimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private Button moveButton;

    private int[] res = {R.id.imagea, R.id.imageb, R.id.imagec, R.id.imaged, R.id.imagee, R.id.imagef, R.id.imageg};
    private List<ImageView> imageViewList = new ArrayList<ImageView>();
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        moveButton = (Button) findViewById(R.id.move_button);
        ImagelistInit();

    }

    private void ImagelistInit() {
        for (int i = 0; i < res.length; i++) {
            ImageView imageView = (ImageView) findViewById(res[i]);
            imageView.setOnClickListener(this);
            imageViewList.add(imageView);
        }
    }


    public void doclick(View v) {
        switch (v.getId()) {
            case R.id.move_button: {
                /**
                 * 直接加载动画
                 */
//                ObjectAnimator.ofFloat(imageView,"translationX",0F,300F).setDuration(2000).start();
//                ObjectAnimator.ofFloat(imageView,"translationY",0F,300F).setDuration(2000).start();
//                ObjectAnimator.ofFloat(imageView,"rotation",0F,360F).setDuration(2000).start();


                /**
                 * 使用valueholder优化一起播放的效果
                 */
//                PropertyValuesHolder holder1 =PropertyValuesHolder.ofFloat("rotation",0,1800F);
//                PropertyValuesHolder holder2 =PropertyValuesHolder.ofFloat("translationX",0,360F);
//                PropertyValuesHolder holder3 =PropertyValuesHolder.ofFloat("translationY",0,360F);
//                ObjectAnimator.ofPropertyValuesHolder(imageView,holder1,holder2,holder3).setDuration(2000).start();


                /**
                 使用set 搞定更多效果
                 */
                AnimatorSet animatorset = new AnimatorSet();
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "translationX", 0F, 300F).setDuration(2000);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "translationY", 0F, 300F).setDuration(2000);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView, "rotation", 0F, 3600F).setDuration(2000);
                animatorset.setDuration(2000);
                animatorset.playTogether(animator1, animator2, animator3);
                animatorset.start();


                break;
            }
            case R.id.imageView: {
                Toast.makeText(MainActivity.this, "image click", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.alpha_button: {
                ObjectAnimator animator = ObjectAnimator.ofFloat(R.id.alpha_button, "alpha", 0f, 1f);
                animator.setDuration(1000);

                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        Log.i("iii", "onAnimationStart");
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        Log.i("iii", "onAnimationEnd");
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                animator.start();
                break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        Log.i("iii", "onClick R.id.imagea");
        switch (view.getId()) {
            case R.id.imagea: {

                Log.i("iii", "onClick R.id.imagea");
                if (flag == true) {
                    startAnim();
                    flag = false;
                }
                else
                {
                    closeAnim();
                    flag = true;
                }
                break;
            }
            case R.id.imageb:
            {
                Toast.makeText(MainActivity.this,"camera",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startAnim() {
        for (int i = 0; i < res.length; i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", 0f, i * 100f);
            animator.setDuration(500);
       //     animator.setInterpolator(new BounceInterpolator());
            animator.setStartDelay(i * 100);
            animator.start();
        }
    }
    private void closeAnim() {
        for (int i = 0; i < res.length; i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", i * 100f,0);
            animator.setDuration(500);
          //  animator.setStartDelay(i * 200);
            animator.start();
        }
    }

}
