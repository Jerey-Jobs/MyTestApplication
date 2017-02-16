package com.jerey.spanintextview;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TEXT = "This a demo for span";
    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;
    private TextView mTextView5;
    private TextView mTextView6;
    private TextView mTextView7;
    private TextView mTextView8;
    private TextView mTextView9;
    private TextView mTextView10;
    private TextView mTextView11;
    private TextView mTextView12;
    private TextView mTextView13;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView1 = (TextView) findViewById(R.id.text1);
        mTextView2 = (TextView) findViewById(R.id.text2);
        mTextView3 = (TextView) findViewById(R.id.text3);
        mTextView4 = (TextView) findViewById(R.id.text4);
        mTextView5 = (TextView) findViewById(R.id.text5);
        mTextView6 = (TextView) findViewById(R.id.text6);
        mTextView7 = (TextView) findViewById(R.id.text7);
        mTextView8 = (TextView) findViewById(R.id.text8);
        mTextView9 = (TextView) findViewById(R.id.text9);
        mTextView10 = (TextView) findViewById(R.id.text10);
        mTextView11 = (TextView) findViewById(R.id.text11);
        mTextView12 = (TextView) findViewById(R.id.text12);
        mTextView13 = (TextView) findViewById(R.id.text13);

        BulletSpan();
        quoteSpan();
        AlignmentSpan();
        UnderlineSpan();
        StrikethroughSpan();
        SubscriptSpan();
        BackgroundColorSpan();
        ForegroundColorSpan();
        StyleSpan();
        TypefaceSpan();
        AbsoluteSizeSpan();
        RelativeSizeSpan();
        ScaleXSpan();
    }

    private void BulletSpan() {
        BulletSpan span = new BulletSpan(15, Color.RED);
        SpannableString spannableString = new SpannableString(TEXT);
        spannableString.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView1.setText(spannableString);
    }

    private void quoteSpan() {
        QuoteSpan span = new QuoteSpan(Color.RED);
        SpannableString spannableString = new SpannableString(TEXT);
        spannableString.setSpan(span, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView2.setText(spannableString);
    }

    private void AlignmentSpan() {
        AlignmentSpan.Standard span = new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER);
        SpannableString spannableString = new SpannableString(TEXT);
        spannableString.setSpan(span, 0, 0, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView3.setText(spannableString);
    }

    private void UnderlineSpan() {
        UnderlineSpan span = new UnderlineSpan();
        SpannableString spannableString = new SpannableString(TEXT);
        spannableString.setSpan(span, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView4.setText(spannableString);
    }

    private void StrikethroughSpan() {
        StrikethroughSpan span = new StrikethroughSpan();
        SpannableString spannableString = new SpannableString(TEXT);
        spannableString.setSpan(span, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView5.setText(spannableString);
    }

    private void SubscriptSpan() {
        SubscriptSpan span = new SubscriptSpan();
        SpannableString spannableString = new SpannableString(TEXT);
        spannableString.setSpan(span, 0, spannableString.length() / 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView6.setText(spannableString);
    }

    private void BackgroundColorSpan() {
        BackgroundColorSpan span = new BackgroundColorSpan(Color.RED);
        SpannableString spannableString = new SpannableString(TEXT);
        spannableString.setSpan(span, 0, spannableString.length() / 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView7.setText(spannableString);
    }

    private void ForegroundColorSpan() {
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        SpannableString spannableString = new SpannableString(TEXT);
        spannableString.setSpan(span, 0, spannableString.length() / 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView8.setText(spannableString);
    }

    private void StyleSpan() {
        StyleSpan span = new StyleSpan(Typeface.BOLD);
        SpannableString spannableString = new SpannableString(TEXT);
        spannableString.setSpan(span, 0, spannableString.length() / 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView9.setText(spannableString);
    }

    private void TypefaceSpan() {
        TypefaceSpan span = new TypefaceSpan("serif");
        SpannableString spannableString = new SpannableString(TEXT);
        spannableString.setSpan(span, 0, spannableString.length() / 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView10.setText(spannableString);
    }

    private void AbsoluteSizeSpan() {
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(24, true);
        SpannableString spannableString = new SpannableString(TEXT);
        spannableString.setSpan(span, 0, spannableString.length() / 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView11.setText(spannableString);
    }

    private void RelativeSizeSpan() {
        RelativeSizeSpan span = new RelativeSizeSpan(2.0f);
        SpannableString spannableString = new SpannableString(TEXT);
        spannableString.setSpan(span, 0, spannableString.length() / 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView11.setText(spannableString);
    }

    private void ScaleXSpan() {
        ScaleXSpan span = new ScaleXSpan(2.0f);
        SpannableString spannableString = new SpannableString(TEXT);
        spannableString.setSpan(span, 0, spannableString.length() / 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView12.setText(spannableString);
    }
}
