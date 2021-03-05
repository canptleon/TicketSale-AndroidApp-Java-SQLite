package com.ardakeyisoglu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class kullaniciGiris extends AppCompatActivity
{
    private EditText Gemail,Gsifre;
    public static String kullanici = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_giris);

        Gemail = (EditText) findViewById(R.id.txtGEMAIL);
        Gsifre = (EditText) findViewById(R.id.txtGSIFRE);
    }

    public void girisYap(View view)
    {
        veriTabani vt = new veriTabani(getApplicationContext());

        String Demail = Gemail.getText().toString();
        String Dsifre = Gsifre.getText().toString();

        Boolean e = vt.emailKontrol(Demail);

        if(Demail != null)
        {
           if(Dsifre != null)
           {
               if(e == true)
               {
                   Boolean s = vt.sifreKontrol(Demail, Dsifre);

                   if(s == true)
                   {
                       kullanici = Demail;
                       Intent intent = new Intent(getApplicationContext(),SatisActivity.class);
                       startActivity(intent);

                   }
                   else
                   {
                       Toast.makeText(getApplicationContext(), "Girilen Şifre yanlış.", Toast.LENGTH_SHORT).show();
                   }
               }
               else
               {
                   Toast.makeText(getApplicationContext(), "Girilen Email yanlış.", Toast.LENGTH_SHORT).show();
               }
           }
           else
           {
               Toast.makeText(getApplicationContext(), "Şifre boş bırakılamaz.", Toast.LENGTH_SHORT).show();
           }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Email boş bırakılamaz.", Toast.LENGTH_SHORT).show();
        }


    }


}
