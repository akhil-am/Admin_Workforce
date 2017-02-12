package com.example.akhil.admin_workforce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.akhil.admin_workforce.admin.AdminMain;
import com.example.akhil.admin_workforce.network.NetworkConnection;

public class MainActivity extends AppCompatActivity {
Button login;
    NetworkConnection networkConnection=new NetworkConnection(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(Button)findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //networkConnection.getJobData();
                Intent intent=new Intent(MainActivity.this, AdminMain.class);
                startActivity(intent);
            }
        });

    }
}
