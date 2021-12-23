package com.example.phonebook.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.phonebook.R;
import com.example.phonebook.model.User;
import com.example.phonebook.database.Users;

public class UserFragment extends Fragment {

    private int position;

    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView phoneTextView = view.findViewById(R.id.phoneTextView);
        Button deleteUserButton = view.findViewById(R.id.deleteUserButton);
        Button changeUserButton = view.findViewById(R.id.changeUserButton);

        if (getArguments() != null) {
            position = getArguments().getInt("position", 1);
        }
        user = Users.get(getActivity()).getUserList().get(position);
        String name = String.format("%s %s", user.getFirstName(), user.getLastName());
        nameTextView.setText(name);
        phoneTextView.setText(user.getPhone());

        deleteUserButton.setOnClickListener(v -> {
            Users users = Users.get(getContext());
            users.deleteUser(user);
            getParentFragmentManager().popBackStack();
        });

        changeUserButton.setOnClickListener(v -> {
            Fragment changeUserFragment = new ChangeUserFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            changeUserFragment.setArguments(args);

            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.fragmentContainer, changeUserFragment).commit();
        });

        return view;
    }
}