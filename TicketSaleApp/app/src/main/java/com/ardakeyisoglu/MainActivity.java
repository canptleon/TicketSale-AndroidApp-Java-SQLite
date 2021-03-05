package com.ardakeyisoglu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeToGirisYap(View view)
    {
        Intent intent = new Intent(MainActivity.this,kullaniciGiris.class);
        startActivity(intent);
    }

    public void changeToKayit(View view)
    {
        Intent intent = new Intent(MainActivity.this,kayitOl.class);
        startActivity(intent);
    }

    public void uyeolmadandevam(View view)
    {
        Intent intent = new Intent(MainActivity.this,SatisActivity.class);
        startActivity(intent);
    }
}
