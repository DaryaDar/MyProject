package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button mBtn0;
    Button mBtn1;
    Button mBtn2;
    Button mBtn3;
    Button mBtn4;
    Button mBtn5;
    Button mBtn6;
    Button mBtn7;
    Button mBtn8;
    Button mBtn9;

    TextView mDisplay;

    Button mBackSpace;
    Button mClear;
    Button mComma;
    Button mSing;

    Button mPlus;
    Button mMinus;
    Button mDiv;
    Button mMul;
    Button mResult;

    float mValue = 0;
    String mOperator = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn0 = findViewById(R.id.btn0);
        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);
        mBtn3 = findViewById(R.id.btn3);
        mBtn4 = findViewById(R.id.btn4);
        mBtn5 = findViewById(R.id.btn5);
        mBtn6 = findViewById(R.id.btn6);
        mBtn7 = findViewById(R.id.btn7);
        mBtn8 = findViewById(R.id.btn8);
        mBtn9 = findViewById(R.id.btn9);

        mDisplay = findViewById(R.id.Disp);

        mBackSpace = findViewById(R.id.btnB);
        mClear = findViewById(R.id.btnC);
        mComma = findViewById(R.id.btnZ);

        mSing = findViewById(R.id.btnPM);

        mPlus = findViewById(R.id.btnP);
        mMul = findViewById(R.id.btnU);
        mMinus = findViewById(R.id.btnM);
        mDiv = findViewById(R.id.btnD);

        mResult = findViewById(R.id.btnR);
/**
 Кнопки с числами
 **/
        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }

            public void onNumberClick(View button) {
                String number = ((Button) button).getText().toString();
                String display = mDisplay.getText().toString();

                if (display.equals("0"))
                    display = number;
                else
                    display += number;

                mDisplay.setText(display);
            }
        };
        mBtn0.setOnClickListener(numberListener);
        mBtn1.setOnClickListener(numberListener);
        mBtn2.setOnClickListener(numberListener);
        mBtn3.setOnClickListener(numberListener);
        mBtn4.setOnClickListener(numberListener);
        mBtn5.setOnClickListener(numberListener);
        mBtn6.setOnClickListener(numberListener);
        mBtn7.setOnClickListener(numberListener);
        mBtn8.setOnClickListener(numberListener);
        mBtn9.setOnClickListener(numberListener);

    /**
     Кнопки с операторами
     **/
        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorClick(v);
            }

            public void onOperatorClick(View button) {
                String operator = ((Button) button).getText().toString();
                mOperator = operator;

                String display = mDisplay.getText().toString();
                mValue = Float.parseFloat(display);

                mDisplay.setText("0");
            }
        };

        mPlus.setOnClickListener(operatorListener);
        mMinus.setOnClickListener(operatorListener);
        mMul.setOnClickListener(operatorListener);
        mDiv.setOnClickListener(operatorListener);

    /**
     Кнопка равно + обработка операторов
     **/
        View.OnClickListener resultListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResultListener(v);
            }

            public void onResultListener(View button) {

                String display = mDisplay.getText().toString();
                float value = Float.parseFloat(display);

                float  result = value;

                boolean i = true;

                switch (mOperator)
                {
                    case "+":
                    {
                        result = value + mValue;
                        break;
                    }
                    case "-":
                    {
                        result = value - mValue;
                        break;
                    }
                    case "*":
                    {
                        result = value * mValue;
                        break;
                    }
                    case "/":
                    {
                        if (value == 0) {
                            i = true;
                        } else {
                            i = false;
                            result = mValue / value;
                        }
                        break;
                    }
                }
                DecimalFormat format = new DecimalFormat("0.######");
                format.setRoundingMode(RoundingMode.DOWN);
                String resultText = format.format(result);

                mDisplay.setText(resultText);

                mValue = result;
                mOperator = "";
            }
        };

        mResult.setOnClickListener(resultListener);

    /**
     Кнопка плюс/минус
     **/
        View.OnClickListener plusMinusListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlusMinusListener(v);
            }
            public void onPlusMinusListener(View button) {
                String display = mDisplay.getText().toString();
                float value = Float.parseFloat(display);
                value = value*-1;

                DecimalFormat format = new DecimalFormat("0.######");
                format.setRoundingMode(RoundingMode.DOWN);
                String resultText = format.format(value);

                mDisplay.setText(String.valueOf(resultText));
            }
        };

        mSing.setOnClickListener(plusMinusListener);

    /**
     Кнопка очистки
     **/
        View.OnClickListener clearListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearListener(v);
            }
            public void onClearListener(View button) {
                mValue = 0;
                mOperator = "";
                mDisplay.setText("0");
            }
        };

        mClear.setOnClickListener(clearListener);

    /**
     Кнопка назад
     **/
        View.OnClickListener backListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackListener(v);
            }
            public void onBackListener(View button) {
                String field = mDisplay.getText().toString();
                if (field.length() >1 ) {
                    field = field.substring(0, field.length() - 1);
                    mDisplay.setText(field);
                }
                else if (field.length() <=1 ) {
                    mDisplay.setText("0");
                }
            }
        };

        mBackSpace.setOnClickListener(backListener);

    /**
     Кнопка с точкой
     **/
        View.OnClickListener commaListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommaListener(v);
            }
            public void onCommaListener(View button) {
                String field = mDisplay.getText().toString();
                String display = field + ".";
                mDisplay.setText(display);
            }
        };

        mComma.setOnClickListener(commaListener);
    }
}