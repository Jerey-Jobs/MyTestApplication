package net.oschina.app.improve.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import net.oschina.app.R;
import net.oschina.app.emoji.EmojiKeyboardFragment;
import net.oschina.app.emoji.Emojicon;
import net.oschina.app.emoji.InputHelper;
import net.oschina.app.emoji.OnEmojiClickListener;

/**
 * 底部操作的抽取
 * Created by thanatos on 16/6/21.
 */
public class KeyboardInputDelegation {

    private Context context;
    private boolean isLastEmpty = true;

    private CoordinatorLayout mCoorLayout;
    private View mScrollerView;
    private View mWrapperView;

    private EditText mViewInput;
    private ImageView mViewShare;
    private ImageView mViewFavor;
    private ImageView mViewEmoji;
    private FrameLayout mKeyboardFrame;

    private KeyboardActionDelegation mActionDelegation;

    private KeyboardInputDelegation(Context context) {
        this.context = context;
    }

    public static KeyboardInputDelegation delegation(Context context, CoordinatorLayout mCoorLayout, View mScrollerView) {
        KeyboardInputDelegation delegator = new KeyboardInputDelegation(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_input_wrap, mCoorLayout, false);
        delegator.setWrapperView(view);
        delegator.setCoorLayout(mCoorLayout);
        delegator.setScrollerView(mScrollerView);
        mCoorLayout.addView(view);
        return delegator;
    }

    public void showEmoji(FragmentManager fragManager) {
        if (mKeyboardFrame == null)
            mKeyboardFrame = (FrameLayout) mWrapperView.findViewById(R.id.emoji_keyboard_fragment);
        if (mViewEmoji == null)
            mViewEmoji = (ImageView) mWrapperView.findViewById(R.id.iv_emoji);
        mViewEmoji.setVisibility(View.VISIBLE);

        final EmojiKeyboardFragment mKeyboardFragment = new EmojiKeyboardFragment();
        mKeyboardFragment.setDelegate(true);
        mKeyboardFragment.setOnEmojiClickListener(new OnEmojiClickListener() {
            @Override
            public void onDeleteButtonClick(View v) {
                InputHelper.backspace(mViewInput);
            }

            @Override
            public void onEmojiClick(Emojicon v) {
                mActionDelegation.onEmotionItemSelected(v);
            }
        });
        mCoorLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    onTurnBack();
                }
                return false;
            }
        });

        fragManager.beginTransaction()
                .replace(R.id.emoji_keyboard_fragment, mKeyboardFragment)
                .commit();

        mActionDelegation = KeyboardActionDelegation.delegation(context, mViewInput, mViewEmoji, mKeyboardFrame, new KeyboardActionDelegation.OnActionChangeListener() {
            @Override
            public void onHideEmotionPanel(KeyboardActionDelegation delegation) {
                mKeyboardFragment.hideEmojiKeyBoard();
            }

            @Override
            public void onShowEmotionPanel(KeyboardActionDelegation delegation) {
                mKeyboardFragment.showEmojiKeyBoard();
            }
        });
    }

    public void showShare(View.OnClickListener l) {
        if (mViewShare == null)
            mViewShare = (ImageView) mWrapperView.findViewById(R.id.iv_share);
        if (l != null) mViewShare.setOnClickListener(l);
        mViewShare.setVisibility(View.VISIBLE);
    }

    public void showFavor(View.OnClickListener l) {
        if (mViewFavor == null)
            mViewFavor = (ImageView) mWrapperView.findViewById(R.id.iv_fav);
        if (l != null) mViewFavor.setOnClickListener(l);
        mViewFavor.setVisibility(View.VISIBLE);
    }

    private void setWrapperView(View view) {
        this.mWrapperView = view;
        mViewInput = (EditText) this.mWrapperView.findViewById(R.id.et_input);
    }

    private void setCoorLayout(CoordinatorLayout view) {
        this.mCoorLayout = view;
    }

    private void setScrollerView(View view) {
        this.mScrollerView = view;
    }

    public void setAdapter(final KeyboardInputAdapter mInputAdapter) {
        mViewInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
//                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    mInputAdapter.onSubmit(v, v.getText().toString());
                    return true;
                }
                return false;
            }
        });
        mViewInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    mInputAdapter.onBackSpace(v);
                    if (!TextUtils.isEmpty(mViewInput.getText().toString())) {
                        isLastEmpty = false;
                        return false;
                    }
                    if (TextUtils.isEmpty(mViewInput.getText().toString()) && !isLastEmpty) {
                        isLastEmpty = true;
                        return false;
                    }
                    mInputAdapter.onFinalBackSpace(v);
                    return false;
                }
                return false;
            }
        });
    }

    public EditText getInputView() {
        return mViewInput;
    }

    public void notifyWrapper() {
        FloatingAutoHideDownBehavior.showBottomLayout(mCoorLayout, mScrollerView, mWrapperView);
    }

    public boolean onTurnBack() {
        return mActionDelegation == null || mActionDelegation.onTurnBack();
    }

    public void onBackSpace() {

    }

    public static abstract class KeyboardInputAdapter {
        public abstract void onSubmit(TextView v, String content);

        public void onBackSpace(View v) {
        }

        public void onFinalBackSpace(View v) {
        }
    }
}
