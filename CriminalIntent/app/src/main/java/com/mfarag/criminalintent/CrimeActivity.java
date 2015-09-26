package com.mfarag.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {
    public static final String EXTRA_CRIME_ID = "com.mfarag.criminalintent.crime_id";

    public static Intent newIntent(Context context, UUID id) {
        return new Intent(context, CrimeActivity.class).putExtra(EXTRA_CRIME_ID, id);
    }

    @Override
    @NonNull
    protected Fragment createFragment() {
        return new CrimeFragment();
    }

}
