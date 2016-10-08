package com.nodhan.exp3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class InsertActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    ArrayList<String> editTextsData;
    boolean flag = true;
    DBConnection dbConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        findViewById(R.id.insAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                relativeLayout = (RelativeLayout) findViewById(R.id.insertLayout);
                editTextsData = new ArrayList<>();
                dbConnection = DBConnection.createDbConnection(getApplicationContext());

                //getting data from EditText's
                for (int i = 0; i < relativeLayout.getChildCount(); i++)
                    if (relativeLayout.getChildAt(i) instanceof EditText)
                        checkForNull((EditText) relativeLayout.getChildAt(i));

                if (flag) {
                    boolean dbFlag = dbConnection.addData(editTextsData.get(0), editTextsData.get(1), editTextsData.get(2), editTextsData.get(3));
                    if (dbFlag) {
                        Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Value already there", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    /**
     * Checks if EditText is filled or not
     *
     * @param editText
     * @return true if not null, false if null
     */
    public void checkForNull(EditText editText) {
        String text = editText.getText().toString();
        if (text.equals("") || text.equals(null)) {
            editText.setError("Cannot be empty!");
            flag = false;
        } else {
            editTextsData.add(text);
        }
    }
}
