package com.example.medifind2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab_main, fab_one, fab_two, fab_three;
    Float translationYaxis = 100f;
    Boolean menuOpen = false;

    OvershootInterpolator interpolator = new OvershootInterpolator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShowMenu();
    }

    private void ShowMenu() {
        fab_main = findViewById(R.id.fab_main);
        fab_one = findViewById(R.id.fab_one);
        fab_two = findViewById(R.id.fab_two);
        fab_three = findViewById(R.id.fab_three);

        fab_one.setAlpha(0f);
        fab_two.setAlpha(0f);
        fab_three.setAlpha(0f);

        fab_one.setTranslationY(translationYaxis);
        fab_two.setTranslationY(translationYaxis);
        fab_three.setTranslationY(translationYaxis);

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuOpen) {
                    CloseMenu();
                } else {
                    OpenMenu();
                }
            }
        });

        fab_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Camera", Toast.LENGTH_SHORT).show();
            }
        });
        fab_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Gallery", Toast.LENGTH_SHORT).show();
            }
        });
        fab_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void OpenMenu() {
        menuOpen = ! menuOpen;
        fab_main.setImageResource(R.drawable.ic_close);
        fab_one.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab_two.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab_three.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();

    }
    private void CloseMenu() {
        menuOpen = ! menuOpen;
        fab_main.setImageResource(R.drawable.ic_add);
        fab_one.animate().translationY(translationYaxis).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab_two.animate().translationY(translationYaxis).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab_three.animate().translationY(translationYaxis).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
    }

}

