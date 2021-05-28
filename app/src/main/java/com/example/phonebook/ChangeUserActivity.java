package com.example.phonebook;

import androidx.fragment.app.Fragment;

public class ChangeUserActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ChangeUserFragment();
    }
}
