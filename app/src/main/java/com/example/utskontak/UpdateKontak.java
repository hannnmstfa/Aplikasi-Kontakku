package com.example.utskontak;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplikasikontak.R;

public class UpdateKontak extends AppCompatActivity {
    protected Cursor cursor; DataHelper dbHelper; Button bt1,bt2;
    EditText text1,text2,text3,text4,text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_update_kontak); dbHelper = new DataHelper(this);
        text1=(EditText) findViewById(R.id.editText1); text2=(EditText) findViewById(R.id.editText2); text3=(EditText) findViewById(R.id.editText3); text4=(EditText) findViewById(R.id.editText4); text5=(EditText) findViewById(R.id.editText5);  SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" + getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) { cursor.moveToPosition(0); text1.setText(cursor.getString(0).toString()); text2.setText(cursor.getString(1).toString()); text3.setText(cursor.getString(2).toString()); text4.setText(cursor.getString(3).toString()); text5.setText(cursor.getString(4).toString());
        }
        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);
//daftarkan even onClick pada btnSimpan
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View arg0) {
            SQLiteDatabase db = dbHelper.getWritableDatabase(); db.execSQL("update biodata set nama='" + text2.getText().toString() + "', tgl='" + text3.getText().toString() + "', jk='" + text4.getText().toString() + "', alamat='" + text5.getText().toString() + "' where no='" + text1.getText().toString() + "'");
            Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
            MainActivity.ma.RefreshList(); finish();
        }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View view) { finish();
        }
        });
    }
    public boolean onCreateOptionMenu(Menu menu) {

//Inflate the menu: this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
