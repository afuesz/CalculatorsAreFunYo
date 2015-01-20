package com.example.ehi.calculatorsarefun.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ehi.calculatorsarefun.R;
import com.example.ehi.calculatorsarefun.activities.CalculatorActivity;

/**
 * Created by Amelia Fuesz on 1/16/15.
 */
public class KeypadButtonAdapter extends BaseAdapter {

    private Context mContext;
    private CalculatorActivity calculatorActivity;

    private static final String DARK_BLUE = "#98ADED";
    private static final String LIGHT_BLUE = "#A8FFF9";
    private static final String RED = "#FCB6B6";
    private static final String YELLOW = "#FFFCDB";
    private static final String GREEN = "#C8FAC5";

    private static final String[] keyboardArray = new String[] {
            "7", "8", "9", "<",
            "4", "5", "6", "+",
            "1", "2", "3", "-",
            "0", "?", "CL", "="
    };

    public KeypadButtonAdapter(Context context) {
        mContext = context;
        calculatorActivity = (CalculatorActivity) context;
    }

    public int getCount() {
        return keyboardArray.length;
    }

    public Object getItem(int position) {
        return keyboardArray[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        Button keyButton;
        if (convertView == null) {
            keyButton = new Button(mContext);
            keyButton.setTextAppearance(mContext, R.style.keypad_button_text);
        } else {
            keyButton = (Button) convertView;
        }
        keyButton.setText(keyboardArray[position]);

        // Just coloring them for fun. :)
        colorKeyButtons(keyButton, position);

        keyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                calculatorActivity.onKeySelected(((TextView) view).getText().toString());
            }
        });

        return keyButton;
    }

    private void colorKeyButtons(Button keyButton, final int position) {
        switch (position) {
            case 3:
                keyButton.setBackgroundResource(R.drawable.keypad_blue_button);
                keyButton.setTextColor(Color.parseColor(DARK_BLUE));
                break;
            case 7:
                keyButton.setBackgroundResource(R.drawable.keypad_light_blue_button);
                keyButton.setTextColor(Color.parseColor(LIGHT_BLUE));
                break;
            case 11:
                keyButton.setBackgroundResource(R.drawable.keypad_red_button);
                keyButton.setTextColor(Color.parseColor(RED));
                break;
            case 14:
                keyButton.setBackgroundResource(R.drawable.keypad_green_button);
                keyButton.setTextColor(Color.parseColor(GREEN));
                break;
            case 15:
                keyButton.setBackgroundResource(R.drawable.keypad_yellow_button);
                keyButton.setTextColor(Color.parseColor(YELLOW));
                break;
            default:
                keyButton.setBackgroundResource(R.drawable.keypad_button);
                break;
        }
    }

}