package com.example.phonebook.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.phonebook.R;
import com.example.phonebook.User;
import com.example.phonebook.database.Users;

public class ChangeUserFragment extends Fragment {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneEditText;
    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int position = getArguments().getInt("position", 1);
            user = Users.get(getActivity()).getUserList().get(position);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_user, container, false);
        Button changeUserButton = view.findViewById(R.id.changeUserButton);
        firstNameEditText = view.findViewById(R.id.firstNameEditText);
        lastNameEditText = view.findViewById(R.id.lastNameEditText);
        phoneEditText = view.findViewById(R.id.phoneEditText);

        firstNameEditText.setText(user.getFirstName());
        lastNameEditText.setText(user.getLastName());
        phoneEditText.setText(user.getPhone());

        changeUserButton.setOnClickListener(v -> {
            Users users = Users.get(getContext());
            user.setFirstName(firstNameEditText.getText().toString());
            user.setLastName(lastNameEditText.getText().toString());
            user.setPhone(phoneEditText.getText().toString());
            users.changeUser(user);

            getParentFragmentManager().popBackStack();
        });
        return view;
    }
}