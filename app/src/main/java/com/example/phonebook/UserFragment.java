package com.example.phonebook;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class UserFragment extends Fragment {
    private User user;
    private int position;
    private TextView nameTextView;
    private TextView phoneTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getActivity().getIntent().getIntExtra("position",1);
        user = Users.get(getActivity()).getUserList().get(position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        nameTextView = view.findViewById(R.id.nameTextView);
        phoneTextView = view.findViewById(R.id.phoneTextView);
        Button deleteUserButton = view.findViewById(R.id.deleteUserButton);
        Button changeUserButton = view.findViewById(R.id.changeUserButton);

        String name = user.getFirstName() + " " + user.getLastName();
        nameTextView.setText(name);
        phoneTextView.setText(user.getPhone());

        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users = Users.get(getContext());
                users.deleteUser(user);
                getActivity().onBackPressed();
            }
        });

        changeUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangeUserActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        position = getActivity().getIntent().getIntExtra("position",1);
        user = Users.get(getActivity()).getUserList().get(position);
        String name = user.getFirstName() + " " + user.getLastName();
        nameTextView.setText(name);
        phoneTextView.setText(user.getPhone());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
    }
}