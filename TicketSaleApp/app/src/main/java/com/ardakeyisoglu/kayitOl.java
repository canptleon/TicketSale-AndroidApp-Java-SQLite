package com.ardakeyisoglu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class kayitOl extends AppCompatActivity
{
    private EditText ad,soyad,tel,email,sifre;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);

        ad = (EditText) findViewById(R.id.txtAD);
        soyad = (EditText) findViewById(R.id.txtSOYAD);
        tel = (EditText) findViewById(R.id.txtTELEFON);
        email = (EditText) findViewById(R.id.txtEMAIL);
        sifre = (EditText) findViewById(R.id.txtSIFRE);
    }


    public void kaydet(View view)
    {
        char[] alfabe = {'a','A','b','B','c','C','d','D','e','E','f','F','g','G','h','H','ı','I','i','İ','j','J','k','K','l','L','m','M','n','N','o','O','ö','Ö','p','P','r','R','s','S','ş','Ş','t','T','u','U','ü','Ü','v','V','y','Y','z','Z'};
        char[] sayılar = {'0','1','2','3','4','5','6','7','8','9'};
        boolean harf=false,sayı=false;
        int sayac=0;

        String kad = ad.getText().toString();
        String ksoyad = soyad.getText().toString();
        String ktel = tel.getText().toString();
        String kemail = email.getText().toString();
        String ksifre = sifre.getText().toString();


        if (ksifre.length() >= 8)
        {
            for (int i = 0; i < ksifre.length(); i++)
            {
                for (int k = 0; k < alfabe.length; k++)
                {
                    if (ksifre.charAt(i) == alfabe[k])
                    {
                        harf = true;
                        break;
                    }
                }

                for (int j = 0; j < sayılar.length; j++)
                {
                    if (ksifre.charAt(i) == sayılar[j])
                    {
                        sayı = true;
                        break;
                    }
                }

            }

            if (harf==true && sayı==true)
            {
                veriTabani vt = new veriTabani(getApplicationContext());
                vt.KayitKaydet(kad, ksoyad, ktel, kemail, ksifre);

                Intent intent = new Intent(getApplicationContext(),kullaniciGiris.class);
                startActivity(intent);
            }
            else if (harf == false)
            {
                Toast.makeText(getApplicationContext(), "Şifrenizde harf bulunmalıdır.", Toast.LENGTH_SHORT).show();
            }
            else if (sayı == false)
            {
                Toast.makeText(getApplicationContext(), "Şifrenizde sayı bulunmalıdır.", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Şifreniz 8 karakter veya daha uzun olmadılır.", Toast.LENGTH_SHORT).show();
        }


    }

}
