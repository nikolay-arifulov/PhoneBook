package com.example.phonebook.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.phonebook.R;
import com.example.phonebook.model.User;
import com.example.phonebook.database.Users;

public class AddUserFragment extends Fragment {

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
        Button insertUserButton = view.findViewById(R.id.insertUserButton);
        firstNameEditText = view.findViewById(R.id.firstNameEditText);
        lastNameEditText = view.findViewById(R.id.lastNameEditText);
        phoneEditText = view.findViewById(R.id.phoneEditText);

        insertUserButton.setOnClickListener(v -> {
            Users users = Users.get(getActivity());
            User user = new User();
            user.setFirstName(firstNameEditText.getText().toString());
            user.setLastName(lastNameEditText.getText().toString());
            user.setPhone(phoneEditText.getText().toString());
            users.addUser(user);
            getParentFragmentManager().popBackStack();
        });
        return view;
    }
}
