package com.example.patearn.mykeypad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    EditText editText;
    String textTemp;
    int textLength;
    char lastChar;
    TextView textView;
    TextView textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        editText.addTextChangedListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        alter();
    }

    @Override
    public void afterTextChanged(Editable editable) {
        textView2.setText(editText.getText().toString());
    }

    public void alter() {
        textTemp = editText.getText().toString();
        textLength = textTemp.length();
        int position = 0;
        if (textLength != 0) {
            position = textLength - 1;
            if (position != -1) {
                lastChar = editText.getText().toString().charAt(position);
            }
        }
        textView.setText(String.valueOf(lastChar));

        if(lastChar == '잇') {
            textTemp = textTemp.replace("잇", "있");
            editText.setText(textTemp);
            editText.setSelection(editText.length());
        }

        if(lastChar == '줫') {
            textTemp = textTemp.replace("줫", "줬");
            editText.setText(textTemp);
            editText.setSelection(editText.length());
        }


        if(lastChar == '낫') {
            textTemp = textTemp.replace("낫", "났");
            editText.setText(textTemp);
            editText.setSelection(editText.length());
        }

        if(lastChar == '혓') {
            textTemp = textTemp.replace("혓", "혔");
            editText.setText(textTemp);
            editText.setSelection(editText.length());
        }

        if(lastChar == '햇') {
            textTemp = textTemp.replace("햇", "했");
            editText.setText(textTemp);
            editText.setSelection(editText.length());
        }

        if(lastChar == '엇') {
            textTemp = textTemp.replace("엇", "었");
            editText.setText(textTemp);
            editText.setSelection(editText.length());
        }

        if(lastChar == '랏') {
            textTemp = textTemp.replace("랏", "랐");
            editText.setText(textTemp);
            editText.setSelection(editText.length());
        }
        if(lastChar == '겟') {
            textTemp = textTemp.replace("겟", "겠");
            editText.setText(textTemp);
            editText.setSelection(editText.length());
        }

        if(lastChar == '냇') {
            textTemp = textTemp.replace("냇", "냈");
            editText.setText(textTemp);
            editText.setSelection(editText.length());
        }

        if(lastChar == '졋') {
            textTemp = textTemp.replace("졋", "졌");
            editText.setText(textTemp);
            editText.setSelection(editText.length());
        }

        if(lastChar == '앗') {
            textTemp = textTemp.replace("앗", "았");
            editText.setText(textTemp);
            editText.setSelection(editText.length());
        }
    }
}
