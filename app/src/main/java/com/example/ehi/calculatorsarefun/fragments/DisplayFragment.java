package com.example.ehi.calculatorsarefun.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ehi.calculatorsarefun.R;

public class DisplayFragment extends Fragment {

    private static final String ZERO = "0";
    private static final String CLEAR = "CL";
    private static final String EQUALS = "=";
    private static final String ADDITION = "+";
    private static final String SUBTRACTION = "-";
    private static final String BACK = "<";
    private static final String SURPRISE = "?";
    private static final String ERROR = "ERROR";
    private static final String MAXIMUM_DIGIT_ERROR = "Sorry - you are only allowed to enter up to 14 digits.";
    private static final String DISPLAY_TEXT = "DisplayText";

    public TextView displayTextView;
    public String displayText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, null);
        displayTextView = (TextView) view.findViewById(R.id.display_text_view);
        displayText = "";

        if (savedInstanceState != null) {
            displayText = (String) savedInstanceState.getSerializable(DISPLAY_TEXT);
        }

        if (!displayText.isEmpty()) {
            displayTextView.setText(displayText);
        } else {
            displayTextView.setText(ZERO);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(DISPLAY_TEXT, displayText);
    }

    public void updateDisplay(String key) {
        displayText = removeError(displayTextView.getText().toString());
        boolean isUnderDigitLimit = displayText.length() <= 14;

        switch (key) {
            case CLEAR:
                displayText = ZERO;
                displayTextView.setText(displayText);
                break;
            case EQUALS:
                displayTextView.setText(calculate());
                break;
            case BACK:
                backButtonPressed();
                break;
            case ADDITION:
                checkIfEquation(key);
                break;
            case SUBTRACTION:
                checkIfEquation(key);
                break;
            case SURPRISE:
                createSurpriseToast();
                break;
            default:
                updateDisplayText(key, isUnderDigitLimit);
                break;
        }
    }

    private void backButtonPressed() {
        if ((!displayText.equalsIgnoreCase(ZERO)) && (displayText.length() > 1)) {
            displayText = displayText.substring(0, displayText.length() - 1);
            displayTextView.setText(displayText);
        } else {
            displayText = ZERO;
            displayTextView.setText(displayText);
        }
    }

    private void createSurpriseToast() {
        ImageView BmoImageView = new ImageView(getActivity());
        BmoImageView.setImageResource(R.drawable.bmo2);
        Toast toast = new Toast(getActivity());
        toast.setView(BmoImageView);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public void updateDisplayText(String key, boolean isUnderDigitLimit) {
        if (displayText.equalsIgnoreCase(ZERO)) {
            displayText = key;
            displayTextView.setText(displayText);
        } else if (isUnderDigitLimit) {
            displayText = displayText + key;
            displayTextView.setText(displayText);
        } else {
            Toast.makeText(getActivity(), MAXIMUM_DIGIT_ERROR, Toast.LENGTH_SHORT).show();
        }
    }

    public String removeError(String displayText) {
        if (displayText.contains(ERROR)) {
            displayText = ZERO;
        }
        return displayText;
    }

    public void checkIfEquation(String newKey) {
        boolean isEquation = (displayText.contains(ADDITION) || displayText.contains(SUBTRACTION));
        if (!isEquation) {
            displayText = displayText + newKey;
            displayTextView.setText(displayText);
        }
    }

    public String calculate() {
        boolean isEquation = (displayText.contains(ADDITION) || displayText.contains(SUBTRACTION));
        if (isEquation) {
            if (TextUtils.isDigitsOnly(displayText.substring(displayText.length() - 1))) {
                try {
                    if (displayText.contains(SUBTRACTION)) {
                        doSubtraction();
                    }
                    if (displayText.contains(ADDITION)) {
                        doAddition();
                    }
                } catch (NumberFormatException e) {
                    displayText = ERROR;
                }
            } else {
                displayText = displayText.substring(0, (displayText.length() - 1));
            }
        }
        return displayText;
    }

    public void doSubtraction() {
        Integer firstNumber = Integer.parseInt(displayText.substring(0, displayText.indexOf(SUBTRACTION)));
        Integer secondNumber = Integer.parseInt(displayText.substring(displayText.lastIndexOf(SUBTRACTION) + 1));
        Integer answer = (firstNumber - secondNumber);
        displayText = answer.toString();
    }

    public void doAddition() {
        Integer firstNumber = Integer.parseInt(displayText.substring(0, displayText.indexOf(ADDITION)));
        Integer secondNumber = Integer.parseInt(displayText.substring(displayText.lastIndexOf(ADDITION) + 1));
        Integer answer = (firstNumber + secondNumber);
        displayText = answer.toString();
    }

}
