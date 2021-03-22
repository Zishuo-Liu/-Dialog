package com.liuzishuo.nbdialog;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.liuzishuo.nbdialog.dialog.AlertDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cehsi(View view) {
        new AlertDialog.Builder(this)
                .setContentView(R.layout.dialog)
                .create()
                .show();
    }
}
