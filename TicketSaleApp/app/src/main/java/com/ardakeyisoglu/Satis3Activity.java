package com.ardakeyisoglu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Satis3Activity extends AppCompatActivity
{

    ListView listt1;
    ListView listt2;
    TextView txtview99;
    TextView txtview999;
    CalendarView cld;
    private String a ="";
    private String deger3="0";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satis3);

        listt1 = (ListView) findViewById(R.id.lst11);
        listt2 = (ListView) findViewById(R.id.lst22);
        cld = (CalendarView) findViewById(R.id.clndrview2);
        txtview99 = (TextView) findViewById(R.id.txtview9);
        txtview999 = (TextView) findViewById(R.id.txtview99);

        Intent intent = getIntent();
        String nereden = intent.getStringExtra("nereden2");
        String nereye = intent.getStringExtra("nereye2");
        String tarih = intent.getStringExtra("tarih2");


        Listele(nereden,nereye,tarih);

        cld.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2)
            {
                a = "";
                if (i2 <= 9) a += "0" + String.valueOf(i2); else a += String.valueOf(i2);
                a +=".";
                if (i1 <= 9) a += "0" + String.valueOf(i1 + 1); else  a+= String.valueOf(i1 +1);
                a+= "." + String.valueOf(i);

                Intent intent = getIntent();
                String nereden = intent.getStringExtra("nereden2");
                String nereye = intent.getStringExtra("nereye2");
                Listele2(nereden,nereye,a);
            }
        });

        listt1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {


               String item = listt1.getItemAtPosition(position).toString();
               String[] itemBol = item.split(" - ");
               String deger1;
               deger1 = itemBol[4];

               txtview99.setText(deger1);

            }
        });


        listt2.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                String item = listt2.getItemAtPosition(position).toString();
                String[] itemBol = item.split(" - ");
                String deger2;
                deger2 = itemBol[4];

                txtview999.setText(deger2);

            }
        });


    }

    public void Listele(String nereden,String nereye,String tarih)
    {
        veriTabani vt = new veriTabani(getApplicationContext());
        List<String> list = vt.SeyahatListele1(nereden,nereye,tarih);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,android.R.id.text1,list);
        listt1.setAdapter(adapter3);
    }

    public void Listele2(String nereden,String nereye,String tarih)
    {
        veriTabani vt = new veriTabani(getApplicationContext());
        List<String> list = vt.SeyahatListele1(nereye,nereden,tarih);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,android.R.id.text1,list);
        listt2.setAdapter(adapter4);
    }


    public void GidisDönüsSatinAl(View view)
    {

        if(kullaniciGiris.kullanici != null)
        {
            Toast.makeText(getApplicationContext(), "Giriş başarılı.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent3 = new Intent(Satis3Activity.this,MainActivity.class);
            startActivity(intent3);
            Toast.makeText(getApplicationContext(), "Lütfen giriş yapınız.", Toast.LENGTH_SHORT).show();
        }

    }
}
