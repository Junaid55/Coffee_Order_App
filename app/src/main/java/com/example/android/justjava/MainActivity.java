package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity
{
    int quantity=2;
    String name;
    String priceMessage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Increment(View view)
    {
        if(quantity==100)
        {
            Toast.makeText(this,"not more than 100 coffe",Toast.LENGTH_SHORT).show();
            return;

        }
        quantity = quantity + 1;
        display(quantity);
    }
    public void Decrement(View view)
    {
        if(quantity==1)
        {
            Toast.makeText(this,"not less than 1 coffe",Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            quantity = quantity - 1;
            display(quantity);
        }
    }
    public void submitOrder(View view)
    {
        EditText textfield=(EditText)findViewById(R.id.name_field);
        name=textfield.getText().toString();
        CheckBox checkBox=(CheckBox)findViewById(R.id.whipped_cream);
        boolean hasWhippedCream=checkBox.isChecked();
        CheckBox chocoCheckBox=(CheckBox)findViewById(R.id.chocolate_cream);
        boolean hasChocolate=chocoCheckBox.isChecked();
        int price=calculatePrice(hasChocolate,hasWhippedCream);

        priceMessage=orderSummary(price,hasWhippedCream,hasChocolate,name);
        displayMessage(priceMessage);
    }
    public void startGmail(View view)
    {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "java order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayMessage(priceMessage);
    }
    private int calculatePrice(boolean addChocolate,boolean addWhippedCream) {
        int price=0;
        if (addChocolate == false && addWhippedCream == false) {
            price = quantity * 5;
        } else if (addChocolate == true && addWhippedCream == false) {
            price = (quantity * 5) + (quantity*2);
        } else if (addChocolate == false && addWhippedCream == true) {
            price = (quantity * 5) + (quantity*1);
        } else
        {
            price=(quantity*5)+(quantity*3);
        }
        return price;
    }


    private String orderSummary(int price,boolean addWhippedCream,boolean addChocolate,String name)
    {
        String a="Name:"+name;
        a=a+"\nAdd Whipped Cream:"+addWhippedCream;
        a=a+"\nAdd Chocolate:"+addChocolate;
        a=a+"\nQuantity:"+quantity;
        a=a+"\nTotal:$"+price;
        a=a+"\nThank you!";
        return a;
    }
    private void displayMessage(String a)
    {
        TextView msg=null;
        msg=(TextView)findViewById(R.id.order_summary_text_view);
        msg.setText(a);

    }
    private void display(int number)
    {
        TextView quantityTextView = null;
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}

