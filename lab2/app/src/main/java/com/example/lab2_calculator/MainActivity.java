package com.example.lab2_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String tag = "mainActivity";
    EditText txtValA;
    EditText txtValB;
    EditText txtResult;
    Button btnAdd;
    Button btnSub;
    Button btnMul;
    Button btnDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(tag, "onCreate ajillaa");
        setContentView(R.layout.activity_main);
        btnAdd = this.findViewById(R.id.btAdd);
        btnAdd.setOnClickListener(this);
        btnSub = this.findViewById(R.id.btSub);
        btnSub.setOnClickListener(this);
        btnMul = this.findViewById(R.id.btMulti);
        btnMul.setOnClickListener(this);
        btnDiv = this.findViewById(R.id.btDivide);
        btnDiv.setOnClickListener(this);
        txtValA = this.findViewById(R.id.etValueA);
        txtValB = this.findViewById(R.id.etValueB);
        txtResult = this.findViewById(R.id.etResult);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(tag, "onStart ajillaa");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(tag, "onStop ajillaa");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(tag, "onDestroy ajillaa");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(tag, "onPause ajillaa");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(tag, "onResume ajillaa");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(tag, "onRestart ajillaa");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) throws NullPointerException{
        if(view.getId() == R.id.btAdd) {
            Integer result = Integer.parseInt(txtValA.getText().toString()) + Integer.parseInt(txtValB.getText().toString());
            txtResult.setText(result.toString());
            Log.i(tag, txtValA.getText() + " utgiig " + txtValB.getText() + " deer nemj " + txtResult.getText() + " garlaa");
        }
        else if(view.getId() == R.id.btSub) {
            Integer result = Integer.parseInt(txtValA.getText().toString()) - Integer.parseInt(txtValB.getText().toString());
            txtResult.setText(result.toString());
            Log.i(tag, txtValA.getText() + " utgiig " + txtValB.getText() + " utgaas hasaj " + txtResult.getText() + " garlaa");
        }
        else if(view.getId() == R.id.btMulti) {
            Integer result = Integer.parseInt(txtValA.getText().toString()) * Integer.parseInt(txtValB.getText().toString());
            txtResult.setText(result.toString());
            Log.i(tag, txtValA.getText() + " utgiig " + txtValB.getText() + " utgaar urjuulj " + txtResult.getText() + " garlaa");
        }
        else if(view.getId() == R.id.btDivide) {
            double result = Integer.parseInt(txtValA.getText().toString()) / Double.parseDouble(txtValB.getText().toString());
            txtResult.setText(Double.toString(result));
            Log.i(tag, txtValA.getText() + " utgiig " + txtValB.getText() + " utgaar huwaaj " + txtResult.getText() + " garlaa");
        }
    }
}