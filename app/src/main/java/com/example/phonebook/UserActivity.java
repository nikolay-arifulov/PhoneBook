package com.example.phonebook;

import androidx.fragment.app.Fragment;

public class UserActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new UserFragment();
    }
}