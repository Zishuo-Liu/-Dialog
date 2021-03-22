package com.liuzishuo.nbdialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.liuzishuo.nbdialog.R;

public class AlertDialog extends Dialog {
    private AlertController mAlert;

    public AlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mAlert = new AlertController(this, getWindow());
    }


    public void setText(int viewId, CharSequence text) {
        mAlert.setText(viewId, text);
    }

    public <T extends View> T getView(int viewId) {
        return mAlert.getView(viewId);
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        mAlert.setOnClickListener(viewId, listener);

    }

    public static class Builder {
        private AlertController.AlertParams P;

        public Builder(Context context) {
            this(context, R.style.Theme_AppCompat_Dialog);
        }

        public Builder(Context context, int themeResId) {
            P = new AlertController.AlertParams(context, themeResId);
        }

        public AlertDialog create() {
            final AlertDialog dialog = new AlertDialog(P.mContext, P.mThemeResId);
            try {
                P.apply(dialog.mAlert);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            dialog.setCancelable(P.cancelable);
            if (P.cancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.onCancelListener);
            dialog.setOnDismissListener(P.onDismissListener);
            if (P.onKeyListener != null) {
                dialog.setOnKeyListener(P.onKeyListener);
            }
            return dialog;
        }

        public AlertDialog show() {
            final AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }

        public Builder setContentView(View view) {
            P.layoutId = 0;
            P.mView = view;
            return this;
        }

        public Builder setContentView(int viewId) {
            P.layoutId = viewId;
            P.mView = null;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            P.cancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.onCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.onDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            P.onKeyListener = onKeyListener;
            return this;
        }

        public Builder setText(int viewId, CharSequence text) {
            P.textArray.put(viewId, text);
            return this;
        }

        public Builder setOnClickListener(int viewId, View.OnClickListener listener) {
            P.clickArray.put(viewId, listener);
            return this;
        }

        public Builder fullWidth() {
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        public Builder fromBottom(boolean isAnimation) {
            if (isAnimation) {
                //这里需要自己自定义一个动画，替换后面的
                P.mAnimations = R.style.Theme_AppCompat_DayNight;
            }
            P.mGravity = Gravity.BOTTOM;
            return this;
        }

        public Builder setWidthAndHeight(int width, int height) {
            P.mWidth = width;
            P.mHeight = height;
            return this;
        }

        public Builder addDefaultAnimation() {
            P.mAnimations = R.style.Theme_AppCompat_DayNight;
            return this;
        }

        public Builder setAnimation(int styleAnimation) {
            P.mAnimations = styleAnimation;
            return this;
        }
    }
}
