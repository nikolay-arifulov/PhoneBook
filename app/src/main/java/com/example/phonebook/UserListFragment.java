package com.example.phonebook;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserListFragment extends Fragment {
    private RecyclerView usersRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        Button addUserButton = view.findViewById(R.id.addUserButton);
        usersRecyclerView = view.findViewById(R.id.usersRecyclerView);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddUserActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        recyclerViewInit();
    }

    private void recyclerViewInit(){
        Users users = Users.get(getActivity());
        ArrayList<User> usersList = users.getUserList();
        ArrayList<String> userNames = new ArrayList<>();

        for (User user:usersList) {
            userNames.add(user.getFirstName() + " " + user.getLastName());
        }

        UserAdapter userAdapter = new UserAdapter(userNames);
        usersRecyclerView.setAdapter(userAdapter);
    }
}

class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    ArrayList<String> names;

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public UserViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }

    public UserAdapter(ArrayList<String> names) {
        this.names = names;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(UserAdapter.UserViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView nameTextView = cardView.findViewById(R.id.nameTextView);
        nameTextView.setText(names.get(position));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cardView.getContext(), UserActivity.class);
                intent.putExtra("position", position);
                cardView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }
}