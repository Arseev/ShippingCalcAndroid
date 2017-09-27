package com.schoolwork.shippingcalc;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {
    //DATA MODEL FOR SHIP ITEM
    private ShipItem shipItem;

    //VIEW OBJECTS FOR LAYOUT UI REFERENCE
    private EditText weightET;
    private TextView baseCostTV;
    private TextView addedCostTV;
    private TextView totalCostTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TASK 1: SET ACTIVITY CONTENT
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TASK 2: CREATE A DATA MODEL FOR STORING AN ITEM TO BE SHIPPED
        shipItem = new ShipItem();

        //TASK 3: ESTABLISH THE REFERNCES TO INPUT WEIGHT ELEMENT
        weightET = (EditText) findViewById(R.id.inputWeight);

        //TASK 4: ESTABLISH THE REFERENCES TO OUTPUT ELEMENTS
        baseCostTV = (TextView) findViewById(R.id.baseCostValue);
        addedCostTV = (TextView) findViewById(R.id.addedCostValue);
        totalCostTV = (TextView) findViewById(R.id.totalCostValue);

        //TASK 5: REGISTER THE LISTENER EVENT FOR WEIGHT INPUT
        weightET.addTextChangedListener(weightTextWatcher);
    }


    private TextWatcher weightTextWatcher = new TextWatcher() {
        //THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE
        //THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED

        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {
                shipItem.setWeight((int) Double.parseDouble((s.toString())));
            } catch (NumberFormatException e) {
                shipItem.setWeight(0);
            }
            displayShipping();
        }

        public void afterTextChanged(Editable s) {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
    };

    private void displayShipping() {
        //DISPLAY THE BASE COST, ADDED COST, AND TOTAL COST
        baseCostTV.setText("$" + String.format("%.02f", shipItem.getBaseCost()));
        addedCostTV.setText("$" + String.format("%.02f", shipItem.getmAddedCost()));
        totalCostTV.setText(("$" + String.format("%.02f", shipItem.getmTotalCost())));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //HANDLE ACTION BAR ITEM CLICKS HERE. THE ACTION BASE WILL AUTOMATICALLY
        // HANDLE CLICKS ON THE HOME/UP BUTTON , SO LONG A YOU SPECIFY A PARENT ACTIVITY
        // IN ANDROIDMANIFST.xml
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}