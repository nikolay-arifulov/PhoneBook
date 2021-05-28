package com.example.phonebook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddUserFragment extends Fragment {
    private Button insertUserButton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        insertUserButton = view.findViewById(R.id.insertUserButton);
        firstNameEditText = view.findViewById(R.id.firstNameEditText);
        lastNameEditText = view.findViewById(R.id.lastNameEditText);
        phoneEditText = view.findViewById(R.id.phoneEditText);

        insertUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users = Users.get(getActivity());
                User user = new User();
                user.setFirstName(firstNameEditText.getText().toString());
                user.setLastName(lastNameEditText.getText().toString());
                user.setPhone(phoneEditText.getText().toString());
                users.addUser(user);
                getActivity().onBackPressed();
            }
        });
        return view;
    }


}