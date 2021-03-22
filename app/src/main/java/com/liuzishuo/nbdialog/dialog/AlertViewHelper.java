package com.liuzishuo.nbdialog.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

class AlertViewHelper {
    private View mContentView = null;
    private SparseArray<WeakReference<View>> mViews;//防止oom

    public AlertViewHelper(Context context, int layoutId) {
        this();
        mContentView = LayoutInflater.from(context).inflate(layoutId, null);
    }


    public AlertViewHelper() {
        mViews = new SparseArray<>();
    }

    public void setContentView(View view) {
        this.mContentView = view;
    }

    public void setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        if (tv != null) {
            tv.setText(text);
        }
    }

    public <T extends View> T getView(int viewId) {
        WeakReference<View> weakReference = mViews.get(viewId);
        View view = null;
        if (weakReference != null) {
            view = weakReference.get();
        }
        if (view == null) {
            view = mContentView.findViewById(viewId);
            if (view != null) {
                mViews.put(viewId, new WeakReference<View>(view));
            }
        }
        return (T) view;
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }

    }

    public View getContentView() {
        return mContentView;
    }
}
