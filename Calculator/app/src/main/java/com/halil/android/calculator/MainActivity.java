package com.halil.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClickNumberButton(View view) {
        TextView resultTextView = findViewById(R.id.result_text_view);
        String currentText = resultTextView.getText().toString();
        switch (view.getId()) {
            case R.id.button_num0:
                resultTextView.setText(currentText + "0");
                break;
            case R.id.button_num1:
                resultTextView.setText(currentText + "1");
                break;
            case R.id.button_num2:
                resultTextView.setText(currentText + "2");
                break;
            case R.id.button_num3:
                resultTextView.setText(currentText + "3");
                break;
            case R.id.button_num4:
                resultTextView.setText(currentText + "4");
                break;
            case R.id.button_num5:
                resultTextView.setText(currentText + "5");
                break;
            case R.id.button_num6:
                resultTextView.setText(currentText + "6");
                break;
            case R.id.button_num7:
                resultTextView.setText(currentText + "7");
                break;
            case R.id.button_num8:
                resultTextView.setText(currentText + "8");
                break;
            case R.id.button_num9:
                resultTextView.setText(currentText + "9");
                break;
            default:
                break;
        }
    }

    public void onClickBSButton(View view) {
        TextView resultTextView = findViewById(R.id.result_text_view);
        String currentText = resultTextView.getText().toString();
        if (currentText.length() >= 1)
            resultTextView.setText(currentText.substring(0, currentText.length() - 1));
    }

    public void onClickACButton(View view) {
        TextView resultTextView = findViewById(R.id.result_text_view);
        resultTextView.setText("");
    }

    public void onClickFPButton(View view) {
        TextView resultTextView = findViewById(R.id.result_text_view);
        String currentText = resultTextView.getText().toString();
        if (endsWithNumber()) {
            String lastOperand = getLastOperand();
            if (!lastOperand.contains("."))
                resultTextView.setText(currentText + ".");
        } else if (!currentText.endsWith(".")){
            resultTextView.setText(currentText + ".");
        }
    }

    public void onClickOperatorButton(View view) {
        TextView resultTextView = findViewById(R.id.result_text_view);
        String currentText = resultTextView.getText().toString();
        String replacement = "";
        switch (view.getId()) {
            case R.id.buttonAdd:
                replacement = "+";
                break;
            case R.id.buttonSub:
                replacement = "-";
                break;
            case R.id.buttonDiv:
                replacement = "/";
                break;
            case R.id.buttonMult:
                replacement = "*";
                break;
            default:
                replacement = "";
                break;
        }
        if (currentText.length() > 1) {
            String lastOperator = getlastOperator();
            if (endsWithOperator()) {
                resultTextView.setText(replaceLast(currentText, lastOperator, replacement));
            } else if (currentText.endsWith(lastOperator + ".")) {
                resultTextView.setText(replaceLast(currentText, lastOperator + ".", replacement));
            } else if (currentText.endsWith(".")) {
                resultTextView.setText(replaceLast(currentText, ".", replacement));
            } else {
                resultTextView.setText(currentText + replacement);
            }

        }
    }


    private boolean endsWithNumber() {

        TextView resultTextView = findViewById(R.id.result_text_view);
        String currentText = resultTextView.getText().toString();
        return (currentText.endsWith("0") || currentText.endsWith("1") || (currentText.endsWith("2")) ||
                (currentText.endsWith("3")) || currentText.endsWith("4") || currentText.endsWith("5") ||
                (currentText.endsWith("6")) || (currentText.endsWith("7")) || currentText.endsWith("8") ||
                currentText.endsWith("9"));

    }


    private boolean endsWithOperator() {
        TextView resultTextView = findViewById(R.id.result_text_view);
        String currentText = resultTextView.getText().toString();
        return (currentText.endsWith("+") || currentText.endsWith("-") || (currentText.endsWith("/")) || (currentText.endsWith("*")));

    }

    private String getlastOperator() {
        TextView resultTextView = findViewById(R.id.result_text_view);
        String currentText = resultTextView.getText().toString();
        String s = "";
        if (currentText.contains("*") || currentText.contains("/") || currentText.contains("+") || currentText.contains("-")) {
            int lastIndexOfAdd = currentText.lastIndexOf("+");
            int lastIndexOfSub = currentText.lastIndexOf("-");
            int lastIndexOfDiv = currentText.lastIndexOf("/");
            int lastIndexOfMult = currentText.lastIndexOf("*");
            int lastIndexOfOperator = Math.max(Math.max(lastIndexOfAdd, lastIndexOfSub), Math.max(lastIndexOfMult, lastIndexOfDiv));
            s = "" + currentText.charAt(lastIndexOfOperator);
        }
        return s;
    }


    private int getlastOperatorIndex() {
        TextView resultTextView = findViewById(R.id.result_text_view);
        String currentText = resultTextView.getText().toString();

        if (currentText.contains("*") || currentText.contains("/") || currentText.contains("+") || currentText.contains("-")) {
            int lastIndexOfAdd = currentText.lastIndexOf("+");
            int lastIndexOfSub = currentText.lastIndexOf("-");
            int lastIndexOfDiv = currentText.lastIndexOf("/");
            int lastIndexOfMult = currentText.lastIndexOf("*");
            int lastIndexOfOperator = Math.max(Math.max(lastIndexOfAdd, lastIndexOfSub), Math.max(lastIndexOfMult, lastIndexOfDiv));
            return lastIndexOfOperator;
        }
        return -1;
    }


    public String replaceLast(String string, String toReplace, String replacement) {
        int pos = string.lastIndexOf(toReplace);
        if (pos > -1) {
            return string.substring(0, pos)
                    + replacement
                    + string.substring(pos + toReplace.length(), string.length());
        } else {
            return string;
        }
    }

    private String getLastOperand() {
        TextView resultTextView = findViewById(R.id.result_text_view);
        String currentText = resultTextView.getText().toString();
        if (!endsWithOperator())
            return currentText.substring(getlastOperatorIndex() + 1);
        else
            return "";
    }

}