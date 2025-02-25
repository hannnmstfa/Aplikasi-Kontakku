package com.example.utskontak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aplikasikontak.R;

public class LihatKontak extends AppCompatActivity {
    protected Cursor cursor; DataHelper dbHelper; Button bt2;
    TextView text1,text2,text3,text4,text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kontak);
        dbHelper = new DataHelper(this);
        text1 = (TextView) findViewById(R.id.textView1);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        text4 = (TextView) findViewById(R.id.textView4);
        text5 = (TextView) findViewById(R.id.textView5);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama =	'" + getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();

        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0); text1.setText(cursor.getString(0).toString()); text2.setText(cursor.getString(1).toString()); text3.setText(cursor.getString(2).toString()); text4.setText(cursor.getString(3).toString()); text5.setText(cursor.getString(4).toString());
        }
        bt2 = (Button) findViewById(R.id.button1);
        bt2.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
//TODO Auto-generated method stub
            finish();
        }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//Inflate the menu: this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void panggil(View view) {
        Intent panggil = new Intent(Intent. ACTION_DIAL);
        panggil.setData(Uri.parse("tel: " + text1.getText().toString()));
        startActivity(panggil);
    }

    public void pesan(View view) {
        Intent pesan = new Intent(Intent.ACTION_SENDTO);
        pesan.setData(Uri.parse("smsto: " + text1.getText().toString()));
        startActivity(pesan);

    }
}