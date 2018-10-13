package com.example.poonj.converter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;
import java.util.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private TextView decimal;
    private TextView binary;
    private TextView hex;
    private EditText decField;
    private EditText binField;
    private EditText hexField;
    private Button button;
    private Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        decField = (EditText)findViewById(R.id.decField);
        binField = (EditText)findViewById(R.id.binField);
        hexField = (EditText)findViewById(R.id.hexField);
        decField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                if (!(android.text.TextUtils.isDigitsOnly(s))) {
                    Toast.makeText(getApplicationContext(), "Error: Please enter a numerical digit", Toast.LENGTH_SHORT).show();
                        decField.setText(decField.getText().toString().substring(0, decField.getText().length() - 1));
                        decField.setSelection(decField.getText().toString().length());

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                if (!(android.text.TextUtils.isDigitsOnly(s))) {
                    Toast.makeText(getApplicationContext(), "Error: Please enter 0 or 1", Toast.LENGTH_LONG).show();
                    binField.setText(binField.getText().toString().substring(0, binField.getText().length() - 1));
                    binField.setSelection(binField.getText().toString().length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        hexField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                if (!str.matches("^[0-9A-F]+$")) {
                    Toast.makeText(getApplicationContext(), "Error: Please enter 0-9 or A-F", Toast.LENGTH_SHORT).show();
                    if(hexField.getText().toString().length() > 0) {
                        hexField.setText(hexField.getText().toString().substring(0, hexField.getText().length() - 1));
                        hexField.setSelection(hexField.getText().toString().length());

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        button = (Button)findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Button button = (Button) v;
                if(binField.getText().length() == 0 && hexField.getText().length() == 0) {
                    String input = decField.getText().toString();
                    binField.setText(decimalToBinary(input));
                    hexField.setText(decimalToHex(input));
                }
                else if (decField.getText().length() == 0 && hexField.getText().length() == 0) {
                    String input = binField.getText().toString();
                    decField.setText(binaryToDecimal(input));
                    hexField.setText(binaryToHex(input));
                }
                else if (decField.getText().length() == 0 && binField.getText().length() == 0) {
                    String input = hexField.getText().toString();
                    decField.setText(hexToDecimal(input));
                    binField.setText(hexToBinary(input));
                }
            }
        });
        button2 = (Button)findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                decField.getText().clear();
                binField.getText().clear();
                hexField.getText().clear();
            }
        });


    }


    private String binaryToDecimal(String binary) {
        int bin = Integer.parseInt(binary,2);
        return Integer.toString(bin);
    }

    private String binaryToHex(String binary) {
        int decimal = Integer.parseInt(binary,2);
        return Integer.toString(decimal,16).toUpperCase();
    }

    private String decimalToBinary(String dec) {
        return Integer.toBinaryString(Integer.parseInt(dec));
    }

    private String decimalToHex(String dec) {
        return Integer.toHexString(Integer.parseInt(dec)).toUpperCase();
    }

    private String hexToDecimal(String hex) {
        return Integer.toString(Integer.parseInt(hex,16));
    }

    private String hexToBinary(String hex) {
        int dec = Integer.parseInt(hex,16);
        return Integer.toBinaryString(dec);
    }

}
