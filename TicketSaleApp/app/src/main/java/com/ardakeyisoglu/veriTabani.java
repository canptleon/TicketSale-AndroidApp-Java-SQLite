package com.ardakeyisoglu;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Random;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class veriTabani extends SQLiteOpenHelper
{

    private static final String DATABASE_NAME = "BKT";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLO_KULLANICILAR = "kullanicilar";
    private static final String ROW_KID = "kid";
    private static final String ROW_KAD = "kad";
    private static final String ROW_KSOYAD = "ksoyad";
    private static final String ROW_KTEL = "ktel";
    private static final String ROW_KEMAIL = "kemail";
    private static final String ROW_KSIFRE = "ksifre";

    private static final String TABLO_UCAKLAR = "ucaklar";
    private static final String ROW_UID = "uid";
    private static final String ROW_UKOD = "ukod";
    private static final String ROW_UYOLCU = "uyolcu";

    private static final String TABLO_SEYAHATLER = "seyahatler";
    private static final String ROW_SID = "sid";
    private static final String ROW_SNEREDEN = "snereden";
    private static final String ROW_SNEREYE = "snereye";
    private static final String ROW_STARIH = "starih";
    private static final String ROW_SSAAT = "ssaat";
    private static final String ROW_SUCRET = "sucret";
    private static final String ROW_SKOD = "skod";

    private static final String TABLO_BILET = "biletler";
    private static final String ROW_BID = "bid";
    private static final String ROW_KKID = "kkid";
    private static final String ROW_DURUM = "durum";
    private static final String ROW_UUKOD = "uukod";



    public veriTabani(@Nullable Context context)
    {
        super(context,  DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + TABLO_KULLANICILAR + "("
                + ROW_KID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_KAD + " TEXT NOT NULL, "
                + ROW_KSOYAD + " TEXT NOT NULL, "
                + ROW_KTEL + " TEXT NOT NULL, "
                + ROW_KEMAIL + " TEXT NOT NULL, "
                + ROW_KSIFRE + " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLO_UCAKLAR + "("
                + ROW_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_UKOD + " TEXT NOT NULL, "
                + ROW_UYOLCU + " TEXT NOT NULL )");

        db.execSQL("CREATE TABLE " + TABLO_SEYAHATLER + "("
                + ROW_SID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_SNEREDEN + " TEXT NOT NULL, "
                + ROW_SNEREYE + " TEXT NOT NULL, "
                + ROW_STARIH + " TEXT NOT NULL, "
                + ROW_SSAAT + " TEXT NOT NULL, "
                + ROW_SUCRET + " TEXT NOT NULL, "
                + ROW_SKOD + " TEXT NOT NULL )");

        db.execSQL("CREATE TABLE " + TABLO_BILET + "("
                + ROW_BID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_KKID  + " TEXT NOT NULL, "
                + ROW_DURUM  + " TEXT NOT NULL, "
                + ROW_UUKOD + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_KULLANICILAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_UCAKLAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_SEYAHATLER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_BILET);
        onCreate(db);
    }

    public Boolean emailKontrol(String Demail)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Boolean v = false;

        try
        {
            String[] stunlar = {ROW_KEMAIL};
            Cursor cursor = db.query(TABLO_KULLANICILAR, stunlar,"kemail = ?",new String[]{Demail},null,null,null);
            if (cursor.getCount()>0)
            {
                v = true;

            }
        }
        catch (Exception e)
        {

        }
        db.close();

        return v;
    }

    public Boolean sifreKontrol(String Demail, String Dsifre)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Boolean t = false;

        try
        {
            String[] stunlar = {ROW_KEMAIL,ROW_KSIFRE};
            Cursor cursor = db.query(TABLO_KULLANICILAR, stunlar,"kemail = ? AND ksifre = ?",new String[]{Demail,Dsifre},null,null,null);
            if (cursor.getCount()>0)
            {
                t = true;

            }
        }
        catch (Exception e)
        {

        }
        db.close();

        return t;
    }


    public void KayitKaydet(String kad, String ksoyad, String ktel, String kemail , String ksifre)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues cv = new ContentValues();
            cv.put(ROW_KAD, kad);
            cv.put(ROW_KSOYAD, ksoyad);
            cv.put(ROW_KTEL, ktel);
            cv.put(ROW_KEMAIL, kemail);
            cv.put(ROW_KSIFRE, ksifre);
            db.insert(TABLO_KULLANICILAR, null,cv);
        }
        catch (Exception e)
        {

        }
        db.close();
    }

    public void SeyahatKaydet()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] Sehirler = {"İstanbul","İzmir","Adana","Ankara","Bursa","Kayseri","Erzurum","Diyarbakır","Muğla","Gaziantep","Konya","Çanakkale","Tekirdağ","Antalya","Kars","Mardin","Amasya","Tokat","Trabzon","Elazığ"};
        int yolcu=0;
        String Tarih;

        for (int ee = 2; ee <= 30; ee++)
        {
            if (ee <= 9)
            {
                Tarih = "0" + ee + ".01.2020";
            }
            else
            {
                Tarih = ee + ".01.2020";
            }

            for(int i=0; i< Sehirler.length; i++)
            {
                for (int k=0; k < Sehirler.length; k++)
                {
                    if (Sehirler[i] != Sehirler[k])
                    {
                        for (int j=0; j < 3; j++)
                        {
                            Random rnd = new Random();

                            String[] saat2 = new String[3];
                            String s ="";
                            String p ="";
                            int saat = rnd.nextInt(24);
                            int dakika = rnd.nextInt(11);
                            dakika = dakika * 5;

                            if(saat <= 9)
                            {
                                s = "0" + String.valueOf(saat);
                            }else
                            {
                                s = String.valueOf(saat);
                            }

                            if(dakika == 0 || dakika == 5)
                            {
                                p = "0" + String.valueOf(dakika);
                            }else
                            {
                                p = String.valueOf(dakika);
                            }

                            String tutan = s + ":" + p;

                            if(saat2[0] != tutan && saat2[1] != tutan && saat2[2] != tutan)
                            {
                                saat2[j] = tutan;
                            }

                            int ucret = 0;
                            ucret = (rnd.nextInt(41)+10)*10;

                            char a1 = Sehirler[i].charAt(0);
                            char a2 = Sehirler[k].charAt(0);
                            char a3 = saat2[j].charAt(0);
                            char a4 = saat2[j].charAt(1);
                            char a5 = saat2[j].charAt(3);
                            char a6 = saat2[j].charAt(4);

                            String kod = String.valueOf(a1) + String.valueOf(a2) + String.valueOf(a3) + String.valueOf(a4) + String.valueOf(a5) + String.valueOf(a6);


                            try
                            {
                                ContentValues cv = new ContentValues();
                                cv.put(ROW_SNEREDEN, Sehirler[i]);
                                cv.put(ROW_SNEREYE, Sehirler[k]);
                                cv.put(ROW_STARIH, Tarih);
                                cv.put(ROW_SSAAT, saat2[j]);
                                cv.put(ROW_SUCRET, ucret);
                                cv.put(ROW_SKOD, kod);
                                db.insert(TABLO_SEYAHATLER, null,cv);
                            }
                            catch (Exception e)
                            {

                            }

                            try
                            {
                                ContentValues ct = new ContentValues();
                                ct.put(ROW_UKOD, kod);
                                ct.put(ROW_UYOLCU, yolcu);
                                db.insert(TABLO_UCAKLAR, null,ct);
                            }
                            catch (Exception e)
                            {

                            }
                        }

                    }

                }
        }



        }

        db.close();

    }



    public List<String> Sp2Doldur(String nereden)
    {
        List<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        try
        {
            String[] stunlar = {ROW_SNEREYE};
            Cursor cursor = db.query(TABLO_SEYAHATLER, stunlar, "snereden = ?", new String[]{nereden}, "snereye", null, null);

            while (cursor.moveToNext())
            {
                veriler.add(cursor.getString(0));
            }
        }
        catch (Exception e)
        {

        }
        db.close();
        return veriler;
    }

    public List<String> Sp1Doldur()
    {
        List<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] stunlar = {ROW_SNEREDEN};
            Cursor cursor = db.query(TABLO_SEYAHATLER, stunlar, null, null, "snereden", null, null);
            while (cursor.moveToNext()) {
                veriler.add(cursor.getString(0));
            }
        }
        catch (Exception e)
        {

        }
        db.close();
        return veriler;
    }

    public List<String> SeyahatListele1(String nereden,String nereye,String tarih)
    {
        List<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();

        try
        {

            String[] stunlar = {ROW_SNEREDEN,ROW_SNEREYE,ROW_STARIH,ROW_SSAAT,ROW_SUCRET};
            Cursor cursor = db.query(TABLO_SEYAHATLER, stunlar,"snereden = ? AND snereye = ? AND starih = ?",new String[]{nereden,nereye,tarih},null,null,null);
            while (cursor.moveToNext())
            {
                veriler.add
                        (
                                cursor.getString(0)
                                + " - "
                                + cursor.getString(1)
                                + " - "
                                + cursor.getString(2)
                                + " - "
                                  + cursor.getString(3)
                                + " - "
                                + cursor.getString(4)
                        );
            }

        }
        catch (Exception e)
        {

        }
        db.close();
        return veriler;
    }




}
