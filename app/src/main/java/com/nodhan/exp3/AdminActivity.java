package com.nodhan.exp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {

    TableLayout tableLayout;
    String[][] tokData;
    int NUM_FIELDS = 4;
    TextView textViews[];
    DBConnection dbConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        findViewById(R.id.insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), InsertActivity.class));
            }
        });

        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EditActivity.class));
            }
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DeleteActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }

    /**
     * Sets the data in AdminActivity
     */
    public void setData() {
        tableLayout = (TableLayout) findViewById(R.id.scrollTable); //Finding ScrollView
        tableLayout.removeAllViews();
        textViews = new TextView[NUM_FIELDS + 1]; //TextView array

        dbConnection = DBConnection.createDbConnection(getApplicationContext());
        tokData = dbConnection.displayData(true, "admin");

        if (tokData.length > 0) {
            for (int i = 0; i < tokData.length; i++) {

                TableRow row[] = new TableRow[NUM_FIELDS + 1]; //Row in TableLayout
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);

                String arr[] = {"ID : \t\t\t\t\t\t\t\t\t\t\t", "Name : \t\t\t\t\t\t\t\t\t", "Department : \t\t\t\t\t\t", "Designation : \t\t\t\t\t\t", "Designation : \t\t\t\t\t\t"};

                //Init row and TextView and setting the data
                for (int j = 0; j <= NUM_FIELDS; j++) {
                    row[j] = new TableRow(this);
                    row[j].setLayoutParams(layoutParams);
                    textViews[j] = new TextView(this);
                    if (j != NUM_FIELDS) {
                        textViews[j].setText(arr[j] + tokData[i][j]);
                    } else {
                        textViews[j].setText("");
                    }
                    textViews[j].setTextSize(20);
                    row[j].addView(textViews[j]);
                    tableLayout.addView(row[j]);
                }
            }
        } else {
            TableRow row = new TableRow(this); //Row in TableLayout
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
            row.setLayoutParams(layoutParams);
            TextView view = new TextView(this);
            view.setText("No data!");
            view.setTextSize(20);
            row.addView(view);
            tableLayout.addView(row);
        }

    }
}
