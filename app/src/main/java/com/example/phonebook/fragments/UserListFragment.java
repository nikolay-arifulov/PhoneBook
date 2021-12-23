package com.example.phonebook.fragments;

import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonebook.R;
import com.example.phonebook.model.User;
import com.example.phonebook.database.Users;

import java.util.ArrayList;

public class UserListFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        Button addUserButton = view.findViewById(R.id.addUserButton);
        RecyclerView usersRecyclerView = view.findViewById(R.id.usersRecyclerView);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        usersRecyclerView.setAdapter(new UserAdapter(getUserNames()));

        addUserButton.setOnClickListener(v -> {
            Fragment addUserFragment = new AddUserFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.fragmentContainer, addUserFragment).commit();
        });

        return view;
    }

    private ArrayList<String> getUserNames() {
        ArrayList<User> usersList = Users.get(getActivity()).getUserList();
        ArrayList<String> userNames = new ArrayList<>();

        for (User user:usersList)
            userNames.add(user.getFirstName() + " " + user.getLastName());
        return userNames;
    }
}

class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private final ArrayList<String> names;

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;

        public UserViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.nameTextView);
        }
    }

    public UserAdapter(ArrayList<String> names) {
        this.names = names;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAdapter.UserViewHolder holder, int position) {
        holder.nameTextView.setText(names.get(position));

        holder.itemView.setOnClickListener(v -> {
            Fragment userFragment = new UserFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            userFragment.setArguments(args);

            FragmentManager fragmentManager = ((AppCompatActivity) holder.itemView.getContext()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.fragmentContainer, userFragment).commit();
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }
}