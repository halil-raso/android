package com.halil.android.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.halil.android.utilities.StackHelper;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickNumberButton(View view) {
        TextView resultTextView = findViewById(R.id.result_text_view);
        String currentText = resultTextView.getText().toString();
        if(currentText.contains("= ")){
            currentText="";
        }
        if (currentText.endsWith(")")) {
            currentText = currentText + "x";
        }
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
        } else if (!currentText.endsWith(".")) {
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
            case R.id.buttonMod:
                replacement = "%";
                break;
            default:
                replacement = "";
                break;
        }
        if (currentText.length() > 0) {
            if (currentText.contains("= ")) {
                currentText = (currentText.split("= ")[1] + replacement);
                resultTextView.setText(currentText);
            } else {
                String lastOperator = getlastOperator();
                if (endsWithOperator()) {
                    resultTextView.setText(replaceLast(currentText, lastOperator, replacement));
                } else if (currentText.endsWith(lastOperator + ".")) {
                    resultTextView.setText(replaceLast(currentText, lastOperator + ".", replacement));
                } else if (currentText.endsWith(".")) {
                    resultTextView.setText(replaceLast(currentText, ".", replacement));
                } else if (!currentText.endsWith("(")) {
                    resultTextView.setText(currentText + replacement);
                }
            }
        }
    }

    public void onclickParenthesesButton(View view) {

        TextView resultTextView = findViewById(R.id.result_text_view);
        String currentText = resultTextView.getText().toString();
        if (endsWithOperator()) {
            currentText = currentText + "(";
        } else if (endsWithNumber()) {
            boolean openedFlag = existsOpenedParentheses();
            if (!openedFlag) {
                currentText = currentText + "x(";
            } else {
                currentText = currentText + ")";
            }
        } else if (currentText.endsWith("(")) {
            currentText = currentText + "(";
        } else if (currentText.endsWith(")")) {
            boolean openedFlag = existsOpenedParentheses();
            if (openedFlag) {
                currentText = currentText + ")";
            } else {
                currentText = currentText + "x(";
            }
        }
        resultTextView.setText(currentText);

    }

    private boolean existsOpenedParentheses() {
        TextView resultTextView = findViewById(R.id.result_text_view);
        String s = resultTextView.getText().toString();
        StackHelper stack = new StackHelper(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == "(".charAt(0)) {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ")".charAt(0)) {
                stack.pop();
            }
        }
        if (!stack.isEmpty()) {
            if ("(".charAt(0) == stack.peek()) {
                return true;
            }
        }
        return false;
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
            int lastIndexOfMod = currentText.lastIndexOf("%");
            int lastIndexOfOperator = Math.max(Math.max(Math.max(lastIndexOfAdd, lastIndexOfSub), Math.max(lastIndexOfMult, lastIndexOfDiv)), lastIndexOfMod);
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
            int lastIndexOfMod = currentText.lastIndexOf("%");
            int lastIndexOfOperator = Math.max(Math.max(Math.max(lastIndexOfAdd, lastIndexOfSub), Math.max(lastIndexOfMult, lastIndexOfDiv)), lastIndexOfMod);
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

    public void onClickequalButton(View view) {
        TextView resultTextView = findViewById(R.id.result_text_view);
        String currentText = resultTextView.getText().toString();
        if (currentText.contains("= ")) {
            currentText = (currentText.split("= ")[1]);
            resultTextView.setText(currentText);
        } else {
            boolean valid = checkForValidity();
            if (valid) {
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
                try {
                    currentText = currentText + "\n" + "= " + (engine.eval(currentText));
                } catch (Exception e) {
                    Toast.makeText(this, "Exception Raised", Toast.LENGTH_SHORT).show();
                }
            }
            resultTextView.setText(currentText);
        }
    }

    private boolean asInt() {
        TextView resultTextView = findViewById(R.id.result_text_view);
        String currentText = resultTextView.getText().toString();
        if (!currentText.contains(".")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkForValidity() {
        if (existsOpenedParentheses()) {
            return false;
        } else if (endsWithOperator()) {
            return false;
        } else {
            return true;
        }
    }

}