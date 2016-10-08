package com.nodhan.exp3;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Nodhan on 27-09-2016.
 */

public class DisplayFragment extends Fragment {
    static boolean flag = false;
    static String id;
    EditText editTextArr[];
    Button button;
    DBConnection dbConnection;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        editTextArr = new EditText[4];
        editTextArr[0] = (EditText) view.findViewById(R.id.editId);
        editTextArr[1] = (EditText) view.findViewById(R.id.editName);
        editTextArr[2] = (EditText) view.findViewById(R.id.editDept);
        editTextArr[3] = (EditText) view.findViewById(R.id.editDesgn);
        button = (Button) view.findViewById(R.id.editButton);
        if (flag) button.setText("Delete");
        dbConnection = DBConnection.createDbConnection(getActivity().getApplicationContext());
        String[][] data = dbConnection.displayData(false, id);
        for (int i = 0; i < 4; i++) {
            editTextArr[i].setText(data[0][i]);
            if (flag) editTextArr[i].setEnabled(false);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag) {
                    dbConnection.deleteData(id);
                    flag = false;
                } else if (checkForNull()){
                        dbConnection.updateData(getData());
                }
                getActivity().finish();
            }
        });
        return view;
    }

    private boolean checkForNull() {
        for (int i = 0; i < 4; i++) {
            String data = editTextArr[i].getText().toString();
            if (data.equals("") || data.equals(null)) {
                editTextArr[i].setError("This field is required!");
                return false;
            }
        }
        return true;
    }

    private String[] getData() {
        String data[] = new String[4];
        for (int i = 0; i < 4; i++) {
            data[i] = editTextArr[i].getText().toString();
        }
        return data;
    }


}
