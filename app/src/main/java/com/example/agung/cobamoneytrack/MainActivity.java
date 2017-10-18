package com.example.agung.cobamoneytrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Button addButton,tarikButton;
    private DatabaseReference myRef;
    private EditText txtSaldo,txtTarik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        addButton = (Button) findViewById(R.id.BtnSaldo);
        tarikButton = (Button) findViewById(R.id.BtnTarik);
        txtSaldo = (EditText) findViewById(R.id.TxtSaldo);
        txtTarik = (EditText) findViewById(R.id.TxtTarik);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String isi = dataSnapshot.getValue(String.class);
                txtSaldo.setText(isi);

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String value = txtSaldo.getText().toString();
                        myRef.setValue(value);
                        Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
                    }
                });

                tarikButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tarik = txtTarik.getText().toString();

                        int tTarik = Integer.parseInt(isi) - Integer.parseInt(tarik);
                        String ttTarik = "" + tTarik;
                        myRef.setValue(ttTarik);
                        Toast.makeText(MainActivity.this, tarik, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
