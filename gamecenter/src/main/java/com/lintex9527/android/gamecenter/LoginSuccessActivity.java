package com.lintex9527.android.gamecenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class LoginSuccessActivity extends AppCompatActivity {

    private String username = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);

        Intent intent = getIntent();
        username = intent.getStringExtra(MainActivity.KEY_USERNAME);
        findViewById(R.id.welcome_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "欢迎老用户\n" + username, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
