package com.ardakeyisoglu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Satis2Activity extends AppCompatActivity
{
    ListView list2;
    TextView edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satis2);

        list2 = (ListView) findViewById(R.id.listelenen2);
        edit2 = (TextView) findViewById(R.id.txtview5);

        Intent intent = getIntent();
        String nereden = intent.getStringExtra("nereden1");
        String nereye = intent.getStringExtra("nereye1");
        String tarih = intent.getStringExtra("tarih1");


        Listele(nereden,nereye,tarih);

        list2.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                String item = list2.getItemAtPosition(position).toString();
                String[] itemBol = item.split(" - ");
                String deger;
                deger = itemBol[4];
                edit2.setText(deger);

            }
        });

    }

    public void Listele(String nereden,String nereye,String tarih)
    {
        veriTabani vt = new veriTabani(getApplicationContext());
        List<String> list = vt.SeyahatListele1(nereden,nereye,tarih);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,android.R.id.text1,list);
        list2.setAdapter(adapter2);
    }

    public void TekSatinAl(View view)
    {

        if(kullaniciGiris.kullanici != null)
        {
            Toast.makeText(getApplicationContext(), "Giriş başarılı.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent3 = new Intent(Satis2Activity.this,MainActivity.class);
            startActivity(intent3);
            Toast.makeText(getApplicationContext(), "Lütfen giriş yapınız.", Toast.LENGTH_SHORT).show();
        }

    }


    public void TekRezerveEt(View view)
    {

        if(kullaniciGiris.kullanici != null)
        {
            Toast.makeText(getApplicationContext(), "Giriş başarılı.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent3 = new Intent(Satis2Activity.this,MainActivity.class);
            startActivity(intent3);
            Toast.makeText(getApplicationContext(), "Lütfen giriş yapınız.", Toast.LENGTH_SHORT).show();
        }

    }
}
