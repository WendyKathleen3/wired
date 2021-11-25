package com.hfad.productmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText stock_name, stockonhand, stockintransit, stock_price, reorder_quantity, reorder_amount;

    EditText stock_id;
    Button btninsert_stock;
    Button btnviews_stock;
    Button btnviewUpdate;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        stock_name =  findViewById(R.id.sn);
        stockonhand = findViewById(R.id.soh);
        stockintransit =  findViewById(R.id.sit);
        stock_price =  findViewById(R.id.pr);
        reorder_quantity =  findViewById(R.id.roq);
        reorder_amount =  findViewById(R.id.roa);

        btninsert_stock =  findViewById(R.id.insert_stock);
        btnviews_stock =  findViewById(R.id.views_stock);
	    btnviewUpdate = findViewById(R.id.update_stock);
	    stock_id = findViewById(R.id.sid);

        addStockData();
        viewAll();
    }


    public void addStockData(){
        btninsert_stock.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertStockData(stock_name.getText().toString(),
                        stockonhand.getText().toString(), stockintransit.getText().toString(),
                        stock_price.getText().toString(), reorder_quantity.getText().toString(),
                        reorder_amount.getText().toString());
                if(isInserted == true)
                    Toast.makeText(MainActivity.this, "record inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "record not inserted", Toast.LENGTH_LONG).show();
        }
    });

    }

    public void viewAll() {
        btnviews_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id: " + res.getString(0) + "\n");
                    buffer.append("Stock Name: " + res.getString(1) + "\n");
                    buffer.append("StockOnHand " + res.getString(2) + "\n");
                    buffer.append("StockInTransit: " + res.getString(3) + "\n");
                    buffer.append("Price: " + res.getString(4) + "\n");
                    buffer.append("Reorder_Quantity: " + res.getString(5) + "\n");
                    buffer.append("Reorder_Amount: " + res.getString(6) + "\n\n");
                }
                showMessage("Data",buffer.toString());
            }
        });
    }

        public void showMessage(String title, String Message){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(Message);
            builder.show();

        }
    }
