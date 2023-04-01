package com.example.lb_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    HiveListHelper helper;
    public ArrayList<Sales> data;
    Button btninsert2,btnSearch2,btnUpdate2,btnDelete,btnClear,btnSendrecipt;
    EditText id,transid,date2,descrip2,amt2,price2,total2,coment2;
    ImageButton shistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        HiveDB_Helper hiveDB_helper=new HiveDB_Helper(MainActivity3.this);
        helper=new HiveListHelper(getApplicationContext(),"LBDB.db",null,1);


        btninsert2=(Button) findViewById(R.id.insert2);
        btnSearch2=(Button) findViewById(R.id.search2);
        btnUpdate2=(Button) findViewById(R.id.update2);
        btnClear=(Button) findViewById(R.id.clear2);
        btnSendrecipt=(Button) findViewById(R.id.send2);
        btnDelete=(Button)findViewById(R.id.delete1);

        shistory=(ImageButton)findViewById(R.id.shistory);

        id=(EditText) findViewById(R.id.id2);
        transid=(EditText) findViewById(R.id.transid);
        date2=(EditText) findViewById(R.id.date2);
        descrip2=(EditText) findViewById(R.id.descrip2);
        amt2=(EditText) findViewById(R.id.amt2);
        price2=(EditText) findViewById(R.id.price2);
        total2=(EditText) findViewById(R.id.total2);
        coment2=(EditText) findViewById(R.id.coment);


    }
    private void MainMenu() {
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void HiveRecords() {
        try {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }

    private void Sales() {
        try {
            Intent intent = new Intent(this, MainActivity3.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void Expenditure() {
        try {
            Intent intent = new Intent(this, MainActivity4.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void ExpenditureHistory() {
        try {
            Intent intent = new Intent(this, MainActivity8.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void SalesResume() {
        try {
            Intent intent = new Intent(this, MainActivity5.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void PlanEvent() {
        try {
            Intent intent=new Intent(this,MainActivity6.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
}