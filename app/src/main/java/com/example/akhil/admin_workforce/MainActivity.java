package com.example.akhil.admin_workforce;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.akhil.admin_workforce.network.NetworkConnection;

public class MainActivity extends AppCompatActivity {
Button login;
    NetworkConnection networkConnection=new NetworkConnection(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText uname = (EditText) findViewById(R.id.input_email);
        final EditText pass= (EditText) findViewById(R.id.input_password);
        login=(Button)findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setMessage("Login.......");
                dialog.show();
                String mUname= uname.getText().toString();
                String mPass= pass.getText().toString();
                //Login network
                networkConnection.login(mUname,mPass);

            }
        });

    }
}
