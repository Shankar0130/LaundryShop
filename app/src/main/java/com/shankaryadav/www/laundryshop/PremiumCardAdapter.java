package com.shankaryadav.www.laundryshop;

import androidx.cardview.widget.CardView;

public interface PremiumCardAdapter {
    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int pos);

    int getCount();
}
