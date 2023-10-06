package com.example.mycrypto;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

public class NotificationPopUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_pop_up);

        // Set rounded corners for the window
        int cornerRadius = 30; // Adjust this value to control the corner roundness
        setRoundedCorners(cornerRadius);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        // Set enter animation
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top);

    }

    private void setRoundedCorners(int cornerRadius) {
        // Create a rounded rectangle shape drawable
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(cornerRadius);

        // Set the drawable as the background
        getWindow().setBackgroundDrawable(drawable);
    }

}