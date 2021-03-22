package com.liuzishuo.nbdialog.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


class AlertController {
    private AlertDialog mDialog;
    private Window mWindow;

    public void setHelper(AlertViewHelper helper) {
        mHelper = helper;
    }

    private AlertViewHelper mHelper;

    public AlertController(AlertDialog alertDialog, Window window) {
        this.mDialog = alertDialog;
        this.mWindow = window;
    }

    public AlertDialog getDialog() {
        return mDialog;
    }

    public Window getWindow() {
        return mWindow;
    }

    public static class AlertParams {
        public Context mContext;
        public int mThemeResId;
        public boolean cancelable = true;//点击空白是否能够取消
        public DialogInterface.OnCancelListener onCancelListener;//取消监听
        public DialogInterface.OnDismissListener onDismissListener;//消失监听
        public DialogInterface.OnKeyListener onKeyListener;//按键监听
        public View mView;//布局
        public int layoutId;//布局的layout的id
        public SparseArray<CharSequence> textArray = new SparseArray<>();
        public SparseArray<View.OnClickListener> clickArray = new SparseArray<>();
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mAnimations = 0;
        public int mGravity = Gravity.CENTER;
        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;


        public AlertParams(Context context, int themeResId) {
            this.mContext = context;
            this.mThemeResId = themeResId;
        }

        public void apply(AlertController mAlert) throws IllegalAccessException {
            AlertViewHelper helper = null;
            if (mThemeResId != 0) {
                helper = new AlertViewHelper(mContext, layoutId);
            }
            if (mView != null) {
                helper = new AlertViewHelper();
                helper.setContentView(mView);
            }
            if (helper == null) {
                throw new IllegalAccessException("请设置布局");
            }

            mAlert.getDialog().setContentView(helper.getContentView());

            mAlert.setHelper(helper);

            for (int i = 0; i < textArray.size(); i++) {
                mAlert.setText(textArray.keyAt(i), textArray.valueAt(i));
            }

            for (int i = 0; i < clickArray.size(); i++) {
                mAlert.setOnClickListener(clickArray.keyAt(i), clickArray.valueAt(i));
            }


            Window window = mAlert.getWindow();
            window.setGravity(mGravity);
            if (mAnimations != 0) {
                window.setWindowAnimations(mAnimations);
            }
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = mWidth;
            params.height = mHeight;
            window.setAttributes(params);

        }
    }

    public void setText(int viewId, CharSequence text) {
        mHelper.setText(viewId, text);
    }

    public <T extends View> T getView(int viewId) {
        return mHelper.getView(viewId);
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        mHelper.setOnClickListener(viewId, listener);

    }
}
