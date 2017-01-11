package com.zhangwx.argbshift;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangwx.argbshift.Utils.ViewUtils;
import com.zhangwx.argbshift.Widget.CustomEditText;

import java.util.Locale;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "zhang";
    private CustomEditText mEditTextA;
    private CustomEditText mEditTextR;
    private CustomEditText mEditTextG;
    private CustomEditText mEditTextB;
    private TextView mResult;
    private ImageView mColorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(ViewUtils.$(this, R.id.toolbar));
        initFab();

        mResult = ViewUtils.$(this, R.id.result);
        mColorView = ViewUtils.$(this, R.id.color);
        mEditTextA = ViewUtils.$(this, R.id.TextA);
        mEditTextR = ViewUtils.$(this, R.id.TextR);
        mEditTextG = ViewUtils.$(this, R.id.TextG);
        mEditTextB = ViewUtils.$(this, R.id.TextB);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initFab() {
        ViewUtils.$(this, R.id.fab).setOnClickListener(view -> actShiftRx());
    }

    private void actShiftRx() {
        Observable.just("0x")
                .map(s -> s + shift(mEditTextA.getText().toString()))
                .map(s -> s + shift(mEditTextR.getText().toString()))
                .map(s -> s + shift(mEditTextG.getText().toString()))
                .map(s -> s + shift(mEditTextB.getText().toString()))
                .subscribe(this::change);
    }

    private String shift(String value) {
        return Integer.toHexString(Integer.valueOf(value));
    }

    private void change(String s) {
        final String result = String.format(Locale.getDefault(), getResources().getString(R.string.hex_result), s);
        mResult.setText(Html.fromHtml(result));
        final long color = Long.decode(s.trim());
        Log.e(TAG, "change : color = " + color);
        mColorView.setBackgroundColor((int) color);
    }

}
