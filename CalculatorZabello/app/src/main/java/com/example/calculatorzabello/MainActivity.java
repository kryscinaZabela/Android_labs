package com.example.calculatorzabello;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    EditText numberField;


    boolean stateError;
    boolean lastNumeric;
    boolean lastDot;
    boolean lastRightBracket;
    boolean lastPercent;
    int leftBracketCount;
    int rightBracketCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        numberField = (EditText) findViewById(R.id.numberField);
    }


    public void onNumberClick(View view) {
        Button button = (Button) view;
        if (stateError) {
            numberField.setText(button.getText());
            stateError = false;
        } else {

            numberField.append(button.getText());
            lastNumeric = true;
            lastRightBracket = false;
            lastDot = false;
            lastPercent= false;
        }
    }

    public void onOperationClick(View view) {

        Button button = (Button) view;
        if ((lastNumeric || lastPercent|| lastRightBracket )   && !stateError ) {
            numberField.append(button.getText());
            lastNumeric = false;
            lastDot = false;
            lastRightBracket = false;
        }
    }

    public void onDelClick(View view) {

        numberField.setText("");
        lastNumeric = false;
        stateError = false;
        lastDot = false;
        lastRightBracket = false;
        lastPercent =  false;
        leftBracketCount = 0;
        rightBracketCount = 0;
    }

    public void onDotClick(View view) {

        if (lastNumeric && !stateError ) {
            numberField.append(".");
            lastNumeric = false;
            lastDot = true;
            lastRightBracket = false;
        }
    }


    public void onEqualClick(View view) {

        if (leftBracketCount == rightBracketCount &&(lastNumeric||lastRightBracket || lastPercent) && !stateError) {
            String txt = numberField.getText().toString();
            txt.replaceAll("%", "/100");
            txt.replaceAll("ctg", "1/tan");

            Expression expression = new ExpressionBuilder(txt).build();
            try {
                double result = expression.evaluate();
                numberField.setText(Double.toString(result));

            } catch (Exception ex) {

                numberField.setText("Error");
                stateError = true;
            }
            lastNumeric = false;
            lastRightBracket = false;
            lastRightBracket = false;
        }
    }

    public void onLeftBracketClick(View view) {

        if (  !lastNumeric && !stateError && !lastDot) {
            Button button = (Button) view;
            numberField.append(button.getText());
            lastNumeric = false;
            lastDot = false;
            lastRightBracket = false;
            leftBracketCount++;
        }
    }
    public void onRightBracketClick(View view) {

        if (leftBracketCount>=rightBracketCount  && (lastNumeric || lastRightBracket) && !stateError && !lastDot) {
            Button button = (Button) view;
            numberField.append(button.getText());
            lastNumeric = false;
            lastDot = false;
            lastRightBracket = true;
            rightBracketCount++;

        }
    }

    public void onSinClick(View view) {

        if (!lastNumeric && !lastRightBracket && !stateError && !lastDot) {
            String str = numberField.getText().toString();
            numberField.setText(str+"sin(");
            lastNumeric = false;
            lastDot = false;
            lastRightBracket = false;
            leftBracketCount++;

        }
    }
    public void onCtgClick(View view) {

        if (!lastNumeric && !lastRightBracket && !stateError && !lastDot) {
            String str = numberField.getText().toString();
            numberField.setText(str+"ctg(");
            lastNumeric = false;
            lastDot = false;
            lastRightBracket = false;
            leftBracketCount++;

        }
    }
    public void onLogClick(View view) {

        if (!lastNumeric && !lastRightBracket && !stateError && !lastDot) {
            String str = numberField.getText().toString();
            numberField.setText(str+"log(");

            lastNumeric = false;
            lastDot = false;
            lastRightBracket = false;
            leftBracketCount++;

        }
    }
    public void onCosClick(View view) {

        if (!lastNumeric && !lastRightBracket && !stateError && !lastDot) {
            String str = numberField.getText().toString();
            numberField.setText(str+"cos(");
            lastNumeric = false;
            lastDot = false;
            lastRightBracket = false;
            leftBracketCount++;

        }
    }
    public void onTanClick(View view) {

        if (!lastNumeric && !lastRightBracket && !stateError && !lastDot) {
            String str = numberField.getText().toString();
            numberField.setText(str+"tan(");
            lastNumeric = false;
            lastDot = false;
            lastRightBracket = false;
            leftBracketCount++;

        }
    }
    public void onPercentClick(View view) {

        if ((lastNumeric || lastRightBracket)  && !lastDot) {//&& !stateError
            String str = numberField.getText().toString();
            numberField.setText(str+"%");
            lastNumeric = false;
            lastDot = false;
            lastRightBracket = false;
            lastPercent = true;

        }
    }
    public void onPowerClick(View view) {

        if ((lastNumeric || lastRightBracket) && !stateError && !lastDot) {
            String str = numberField.getText().toString();
            numberField.setText(str+"^");
            lastNumeric = false;
            lastDot = false;
            lastRightBracket = false;

        }
    }
}