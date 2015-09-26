package com.mfarag.criminalintent;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by muhammadfarag on 9/23/15.
 */
public class CrimeListActivity extends SingleFragmentActivity{
    @NonNull
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
