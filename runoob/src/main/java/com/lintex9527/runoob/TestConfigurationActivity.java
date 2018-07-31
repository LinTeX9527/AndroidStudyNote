package com.lintex9527.runoob;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class TestConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_configuration);

        initViews();
    }

    private void initViews() {
        TextView txtResult = (TextView) findViewById(R.id.tvStatus);
        StringBuilder status = new StringBuilder();
        // 获取系统的Configuration对象
        Configuration cfg = getResources().getConfiguration();
        status.append("densityDpi: " + cfg.densityDpi + "\n");
        status.append("fontScale: " + cfg.fontScale + "\n");
        status.append("hardKeyboardHidden: " + cfg.hardKeyboardHidden + "\n");
        status.append("keyboard: " + cfg.keyboard + "\n");
        status.append("keyboardHidden: " + cfg.keyboardHidden + "\n");
        status.append("locale: " + cfg.locale + "\n");
        status.append("mcc: " + cfg.mcc + "\n");
        status.append("mnc: " + cfg.mnc + "\n");
        status.append("navigation: " + cfg.navigation + "\n");
        status.append("navigationHidden: " + cfg.navigationHidden + "\n");
        status.append("orientation: " + cfg.orientation + "\n");
        status.append("screenHeightDp: " + cfg.screenHeightDp + "\n");
        status.append("screenWidthDp: " + cfg.screenWidthDp + "\n");
        status.append("screenLayout: " + cfg.screenLayout + "\n");
        status.append("smallestScreenWidthDp: " + cfg.smallestScreenWidthDp + "\n");
        status.append("uiMode: " + cfg.uiMode + "\n");
        txtResult.setText(status.toString());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        String screen = (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) ? "横屏":"竖屏";
        Toast.makeText(TestConfigurationActivity.this, "系统屏幕方向发生改变" + screen, Toast.LENGTH_SHORT).show();
    }
}
