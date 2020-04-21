package com.example.exercisesql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class display_kontak extends AppCompatActivity {
    private dbKontak mydb;
    EditText EtNama;
    EditText EtTelpon;
    EditText EtEmail;
    EditText EtAlamat;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_kontak);
        EtNama = (EditText) findViewById(R.id.EtNama);
        EtTelpon = (EditText) findViewById(R.id.EtTelpon);
        EtEmail = (EditText) findViewById(R.id.EtEmail);
        EtAlamat = (EditText) findViewById(R.id.EtAlamat);

        mydb = new dbKontak(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();
                String nam =
                        rs.getString(rs.getColumnIndex(dbKontak.MHS_COLUMN_NAMA));
                String tel =
                        rs.getString(rs.getColumnIndex(dbKontak.MHS_COLUMN_NOMOR_TELEPON));
                String eml =
                        rs.getString(rs.getColumnIndex(dbKontak.MHS_COLUMN_EMAIL));
                String almt =
                        rs.getString(rs.getColumnIndex(dbKontak.MHS_COLUMN_ALAMAT));
                if (!rs.isClosed()) {
                    rs.close();
                }

                Button b = (Button) findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);
                EtNama.setText((CharSequence) nam);
                EtNama.setFocusable(false);
                EtNama.setClickable(false);
                EtTelpon.setText((CharSequence) tel);
                EtTelpon.setFocusable(false);
                EtTelpon.setClickable(false);
                EtEmail.setText((CharSequence) eml);
                EtEmail.setFocusable(false);
                EtEmail.setClickable(false);
                EtAlamat.setText((CharSequence) almt);
                EtAlamat.setFocusable(false);
                EtAlamat.setClickable(false);
            }
        }
    }

    public void run(View view) {
        if (EtNama.getText().toString().equals("") || EtTelpon.getText().toString().equals("") ||
                EtEmail.getText().toString().equals("") || EtAlamat.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Data Harus Lengkap", Toast.LENGTH_LONG).show();
        } else {
            mydb.insertContact(EtNama.getText().toString(), EtTelpon.getText().toString(),
                    EtEmail.getText().toString(),EtAlamat.getText().toString());
            Toast.makeText(getApplicationContext(),
                    "Insert data berhasil", Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);

        }
    }
}