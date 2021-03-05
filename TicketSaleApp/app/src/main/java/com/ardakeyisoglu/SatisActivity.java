package com.ardakeyisoglu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SatisActivity extends AppCompatActivity
{
    private List<String> nereden;
    private List<String> nereye;
    private Spinner s1,s2;
    private String n = "", ny = "" , a = "";
    private CalendarView cld;
    private RadioButton c1,c2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satis);

        veriTabani vt = new veriTabani(getApplicationContext());

        nereden = vt.Sp1Doldur();
        s1 = (Spinner) findViewById(R.id.spNereden);
        s2 = (Spinner) findViewById(R.id.spNereye);
        cld = (CalendarView) findViewById(R.id.clndrView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nereden);
        s1.setAdapter(adapter);
        nereye = new ArrayList<String>();
        nereye.add("-------------");

        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nereye);
        s2.setAdapter(adapter2);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                veriTabani vt = new veriTabani(getApplicationContext());
                String val =(String) adapterView.getItemAtPosition(i);
                n = val;
                nereye = vt.Sp2Doldur(val);
                ArrayAdapter a = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, nereye);
                s2.setAdapter(null);
                s2.setAdapter(a);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });


        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String val =(String) adapterView.getItemAtPosition(i);
                ny =val;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });


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

            }
        });
    }

    public void biletListele(View view)
    {


        c1 = (RadioButton) findViewById(R.id.rdTek);
        c2 = (RadioButton) findViewById(R.id.rdGD);




        if(c1.isChecked() == false && c2.isChecked() == false)
        {
            Toast.makeText(getApplicationContext(),"Lütfen seyahat seçeneğinizi işaretleyiniz.",Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(c1.isChecked() == true)
            {
                Intent intent = new Intent(SatisActivity.this,Satis2Activity.class);
                intent.putExtra("nereden1", n);
                intent.putExtra("nereye1", ny);
                intent.putExtra("tarih1", a);
                startActivity(intent);
            }
            else if(c2.isChecked() == true)
            {
                Intent intent = new Intent(SatisActivity.this,Satis3Activity.class);
                intent.putExtra("nereden2", n);
                intent.putExtra("nereye2", ny);
                intent.putExtra("tarih2", a);
                startActivity(intent);
            }
        }


    }


}
