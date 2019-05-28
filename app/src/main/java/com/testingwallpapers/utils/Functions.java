package com.testingwallpapers.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.testingwallpapers.R;

public class Functions {
    public static void changeFragment(FragmentActivity fragmentActivity, Fragment fragment){
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main,fragment)
                .commit();
    }
}
