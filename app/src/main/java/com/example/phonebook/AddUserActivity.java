package com.example.phonebook;

import androidx.fragment.app.Fragment;

public class AddUserActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new AddUserFragment();
    }
}