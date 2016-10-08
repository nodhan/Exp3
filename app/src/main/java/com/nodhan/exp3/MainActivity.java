package com.nodhan.exp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText user, pass;
    DBConnection dbConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.password);
        dbConnection = DBConnection.createDbConnection(getApplicationContext());

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userData = user.getText().toString();
                String passData = pass.getText().toString();
                boolean flag = true;

                if (userData.equals("")) {
                    user.setError("This field is required!");
                    flag = false;
                }
                if (passData.equals("")) {
                    pass.setError("This field is required!");
                    flag = false;
                }
                if (flag) {
                    if (userData.equals("admin") && passData.equals("qwe")) {
                        startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                    } else {
                        if (dbConnection.checkValidity(userData, passData)) {
                            startActivity(new Intent(getApplicationContext(), UserActivity.class).putExtra("user", userData));
                        } else {
                            Toast.makeText(getApplicationContext(), "Check details", Toast.LENGTH_SHORT).show();
                        }
                    }
                    user.setText("");
                    pass.setText("");
                }
            }
        });
    }
}
