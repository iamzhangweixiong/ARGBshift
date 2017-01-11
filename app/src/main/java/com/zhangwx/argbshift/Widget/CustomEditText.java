package com.zhangwx.argbshift.Widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

import com.zhangwx.argbshift.Utils.RegexUtils;

/**
 * Created by zhangwx on 2017/1/11.
 */
public class CustomEditText extends EditText {

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (TextUtils.isEmpty(text)) return;
        final boolean isDigit = RegexUtils.checkDigit(text.toString());
        if (isDigit) {
            final int val = Integer.valueOf(text.toString());
            if (val < 0 || val > 255) {
                Toast.makeText(getContext(), "the value " + val + " is illegal (0 < value < 256)", Toast.LENGTH_LONG).show();
                setText("");
            }
        } else {
            Toast.makeText(getContext(), "the value is not a digital", Toast.LENGTH_SHORT).show();
            setText("");
        }
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }
}
