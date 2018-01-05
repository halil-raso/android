package com.halil.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        if (!currentText.contains(".")) {
            resultTextView.setText(currentText + ".");
        }
    }

    public void onClickAddButton(View view) {
        TextView resultTextView = findViewById(R.id.result_text_view);
        String currentText = resultTextView.getText().toString();
        if (currentText.length() > 1) {
            if (!currentText.endsWith("+")) {
                if (currentText.endsWith("+.")) {
                    resultTextView.setText(currentText.replace("+.", "+"));
                    currentText = resultTextView.getText().toString();
                    if (currentText.endsWith(".")) {
                        resultTextView.setText(currentText.replace(".", "+"));
                    } else {
                        resultTextView.setText(currentText + "+");
                    }
                } else if (currentText.endsWith(".")) {
                    resultTextView.setText(currentText.replace(".", "+"));
                } else {
                    resultTextView.setText(currentText + "+");
                }
            } else if (currentText.endsWith(".")) {
                resultTextView.setText(currentText.replace(".", "+"));
            }
        }
    }


}