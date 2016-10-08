package com.nodhan.exp3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    TextView id, name, dept, desgn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        DBConnection dbConnection = DBConnection.createDbConnection(getApplicationContext());

        String user = getIntent().getStringExtra("user");
        String blah[][] = dbConnection.displayData(false, user);

        id = (TextView) findViewById(R.id.viewId);
        name = (TextView) findViewById(R.id.viewName);
        desgn = (TextView) findViewById(R.id.viewDesgn);
        dept = (TextView) findViewById(R.id.viewDept);

        id.setText(blah[0][0]);
        name.setText(blah[0][1]);
        dept.setText(blah[0][2]);
        desgn.setText(blah[0][3]);

    }
}
