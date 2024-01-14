package com.jasononeal.roc;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;

public abstract class BaseActivity extends Activity{
    JWT jwt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        onViewReady(savedInstanceState, getIntent());
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        //To be used by child activities
        jwt = new JWT();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void showAlertDialog(String msg) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(null);
        dialogBuilder.setIcon(R.mipmap.ic_launcher);
        dialogBuilder.setMessage(msg);
        dialogBuilder.setPositiveButton(getString(R.string.ok), (dialog, which) -> dialog.cancel());

        dialogBuilder.setCancelable(false);
        dialogBuilder.show();
    }

    protected void showToast(String mToastMsg) {
        Toast.makeText(this, mToastMsg, Toast.LENGTH_LONG).show();
    }

    protected abstract int getContentView();

}
