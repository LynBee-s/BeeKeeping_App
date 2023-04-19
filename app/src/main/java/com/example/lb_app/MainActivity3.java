package com.example.lb_app;


import static com.example.lb_app.Structure_BBDD.TABLE2;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;



public class MainActivity3 extends AppCompatActivity {
    HiveListHelper helper;
    public ArrayList<Sales> data;
    Button btninsert2,btnSearch2,btnUpdate2,btnClear,btnSendrecipt,btnPay;
    EditText id,transid,date2,descrip2,amt2,price2,total2,coment2;
    String url="https://www.paypal.com/mep/dashboard";

    private float TotalI;
    private float AmtI;
    private float PriceI;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sales, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.tomainmenu5:
                MainMenu();
                return true;
            case R.id.revsaleshist:
                SalesHist();
                return true;
            case R.id.reviewprod:
                GotoProducts();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//Sales
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
        btnPay=(Button) findViewById(R.id.pay);

        id=(EditText) findViewById(R.id.id2);
        transid=(EditText) findViewById(R.id.transid);
        date2=(EditText) findViewById(R.id.date2);
        descrip2=(EditText) findViewById(R.id.descrip2);
        amt2=(EditText) findViewById(R.id.amt2);
        price2=(EditText) findViewById(R.id.price2);
        total2=(EditText) findViewById(R.id.total2);
        coment2=(EditText) findViewById(R.id.coment);

        btnPay=(Button)findViewById(R.id.pay);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    CustomTabsIntent customTabsIntent = builder.build();
                    customTabsIntent.launchUrl(MainActivity3.this, Uri.parse(url));
                }catch (Exception e){
                    Toast.makeText(getApplication(),"Error",Toast.LENGTH_LONG).show();
            }
                }
        });

        btninsert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Calendar calendar=Calendar.getInstance();
                    String DateNow= MonthDay.now().toString()+"-"+calendar.get(Calendar.YEAR);

                    PriceI= Float.parseFloat(price2.getText().toString());
                    AmtI=Float.parseFloat(amt2.getText().toString());
                    TotalI=PriceI*AmtI;

                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(Structure_BBDD.COLUMNA2, transid.getText().toString());
                    values.put(Structure_BBDD.COLUMNA3, DateNow);
                    values.put(Structure_BBDD.COLUMNA4, descrip2.getText().toString());
                    values.put(Structure_BBDD.COLUMNA5, amt2.getText().toString());
                    values.put(Structure_BBDD.COLUMNA6, price2.getText().toString());
                    values.put(Structure_BBDD.COLUMNA7, TotalI);
                    values.put(Structure_BBDD.COLUMNA8, coment2.getText().toString());

                    long newRowId = db.insert(TABLE2, null, values);
                    Toast.makeText(getApplicationContext(), "The register was saved with ID: " + newRowId, Toast.LENGTH_LONG).show();

                    //Clear text from fields
                    id.setText("");
                    transid.setText("");
                    date2.setText("");
                    descrip2.setText("");
                    amt2.setText("");
                    price2.setText("");
                    total2.setText("");
                    coment2.setText("");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSearch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getReadableDatabase();
                String[] projection = {
                        Structure_BBDD.COLUMNA2,
                        Structure_BBDD.COLUMNA3,
                        Structure_BBDD.COLUMNA4,
                        Structure_BBDD.COLUMNA5,
                        Structure_BBDD.COLUMNA6,
                        Structure_BBDD.COLUMNA7,
                        Structure_BBDD.COLUMNA8
                };

                String selection = Structure_BBDD.COLUMNAID + " = ?";
                String[] selectionArgs = {id.getText().toString()};
                try {
                    Cursor cursor = db.query(
                            TABLE2,   // The table to query
                            projection,             // The array of columns to return (pass null to get all)
                            selection,              // The columns for the WHERE clause
                            selectionArgs,          // The values for the WHERE clause
                            null,                   // don't group the rows
                            null,                   // don't filter by row groups
                            null               // The sort order
                    );
                    cursor.moveToFirst();
                    transid.setText("");
                    date2.setText("");
                    descrip2.setText("");
                    amt2.setText("");
                    price2.setText("");
                    total2.setText("");
                    coment2.setText("");

                    transid.setText(cursor.getString(0));
                    date2.setText(cursor.getString(1));
                    descrip2.setText(cursor.getString(2));
                    amt2.setText(cursor.getString(3));
                    price2.setText(cursor.getString(4));
                    total2.setText(cursor.getString(5));
                    coment2.setText(cursor.getString(6));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR: Could not find the requested register. ", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnUpdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SQLiteDatabase db = helper.getReadableDatabase();
// New value for one column
                ContentValues values = new ContentValues();
                PriceI= Float.parseFloat(price2.getText().toString());
                AmtI=Float.parseFloat(amt2.getText().toString());
                TotalI=PriceI*AmtI;

                values.put(Structure_BBDD.COLUMNAID, id.getText().toString());
                values.put(Structure_BBDD.COLUMNA2, transid.getText().toString());
                values.put(Structure_BBDD.COLUMNA3, date2.getText().toString());
                values.put(Structure_BBDD.COLUMNA4, descrip2.getText().toString());
                values.put(Structure_BBDD.COLUMNA5, amt2.getText().toString());
                values.put(Structure_BBDD.COLUMNA6, price2.getText().toString());
                values.put(Structure_BBDD.COLUMNA7, TotalI);
                values.put(Structure_BBDD.COLUMNA8,coment2.getText().toString());
                String selection = Structure_BBDD.COLUMNAID + " LIKE ?";
                String[] selectionArgs = {id.getText().toString()};

                int count = db.update(
                        TABLE2,
                        values,
                        selection,
                        selectionArgs);
                Toast.makeText(getApplicationContext(), "Register " +id.getText()+ " has been successfully updated.", Toast.LENGTH_LONG).show();
            }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "ERROR:Please insert ID and try again.", Toast.LENGTH_LONG).show();
                }
                }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    id.setText("");
                    transid.setText("");
                    date2.setText("");
                    descrip2.setText("");
                    amt2.setText("");
                    price2.setText("");
                    total2.setText("");
                    coment2.setText("");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSendrecipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar  calendar=Calendar.getInstance();
                try{
                    PriceI= Float.parseFloat(price2.getText().toString());
                    AmtI=Float.parseFloat(amt2.getText().toString());
                    TotalI=PriceI*AmtI;
                    transid.getText();
                    date2.getText();
                    descrip2.getText();
                    amt2.getText();
                    price2.getText();
                    total2.getText();

                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.setType("*/*");
                    intent.putExtra(Intent.EXTRA_SUBJECT,"Transaction Notification from LynBee´s");
                    intent.putExtra(Intent.EXTRA_TEXT,"Date: "+date2.getText()+" " +
                            "\n Receipt number: "+transid.getText()+" " +
                            "\n Description: "+descrip2.getText()+" " +
                            "\n Amount: "+amt2.getText()+" " +
                            "\n Price: "+price2.getText()+
                            "\n Total: "+TotalI+" "+
                            "\n \n  Thank you for your purchase!");
                    if(intent.resolveActivity(getPackageManager()) !=null){
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void MainMenu() {
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void SalesHist() {
        try {
            Intent intent = new Intent(MainActivity3.this, MainActivity5.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void GotoProducts() {
        try {
            Intent intent = new Intent(MainActivity3.this, ScrollingActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }

}