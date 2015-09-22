package com.mfarag.criminalintent;

import java.util.UUID;

/**
 * Created by muhammadfarag on 9/22/15.
 */
public class Crime {
    private final UUID mId;
    private String mTitle;

    public Crime() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
