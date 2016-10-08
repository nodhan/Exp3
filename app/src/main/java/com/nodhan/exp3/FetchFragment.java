package com.nodhan.exp3;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Nodhan on 08-09-2016.
 */
public class FetchFragment extends Fragment {

    static boolean flag = false;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DBConnection dbConnection;
    EditText editTextInputFragmentFetch;
    Button edit;

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
        View view = inflater.inflate(R.layout.fragment_fetch, container, false);
        edit = (Button) view.findViewById(R.id.button2);
        editTextInputFragmentFetch = (EditText) view.findViewById(R.id.editTextInputFragmentFetch);
        dbConnection = new DBConnection(getActivity().getApplicationContext(), "employee", null, 1);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editTextInputFragmentFetch.getText().toString();
                if (dbConnection.checkId(id)) {
                    DisplayFragment.id = id;
                    if (flag) DisplayFragment.flag = true;
                    fragmentManager = getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(android.R.id.content, new DisplayFragment(), "DISPLAY");
                    flag = false;
                    EditActivity.showBackAlert = true;
                    fragmentTransaction.commit();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Invalid ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
