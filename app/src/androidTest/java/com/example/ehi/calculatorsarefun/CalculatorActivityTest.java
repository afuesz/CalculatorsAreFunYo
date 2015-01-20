package com.example.ehi.calculatorsarefun;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import com.example.ehi.calculatorsarefun.activities.CalculatorActivity;
import com.example.ehi.calculatorsarefun.fragments.DisplayFragment;

/**
 * Created by Amelia Fuesz on 1/20/15.
 */
public class CalculatorActivityTest extends ActivityInstrumentationTestCase2<CalculatorActivity> {
    private CalculatorActivity mActivity;
    private DisplayFragment displayFragment;

    public CalculatorActivityTest() {
        super(CalculatorActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        displayFragment = (DisplayFragment) startFragment(new DisplayFragment());
    }

    private Fragment startFragment(Fragment fragment) {
        FragmentTransaction transaction = mActivity.getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_display, fragment, "tag");
        transaction.commit();
        getInstrumentation().waitForIdleSync();
        displayFragment = (DisplayFragment) mActivity.getFragmentManager().findFragmentByTag("tag");
        return displayFragment;
    }

    public void testRemoveError() {
        String displayText = "ERROR1";
        displayText = displayFragment.removeError(displayText);
        assertEquals("0", displayText);
    }


    public void testCheckIfEquation() {
        // Yes it is an equation
        displayFragment.displayText = "1+";
        String newKey = "+";
        displayFragment.checkIfEquation(newKey);
        assertEquals("1+", displayFragment.displayText);

        // No it is not an equation
        displayFragment.displayText = "1";
        newKey = "-";
        displayFragment.checkIfEquation(newKey);
        assertEquals("1-", displayFragment.displayText);
    }


    public void testUpdateDisplayText() {
        // If zero
        displayFragment.displayText = "0";
        displayFragment.updateDisplayText("1", true);
        assertEquals("1", displayFragment.displayText);

        // If under limit
        displayFragment.displayText = "12+";
        displayFragment.updateDisplayText("3", true);
        assertEquals("12+3", displayFragment.displayText);

        // If over limit
        displayFragment.displayText = "12345678901234";
        displayFragment.updateDisplayText("1", false);
        assertEquals("12345678901234", displayFragment.displayText);
    }


    public void testCalculate() {
        // If not real equation
        String answer = "";
        displayFragment.displayText = "112+";
        answer = displayFragment.calculate();
        assertEquals("112", answer);

        // If real equation (happy)
        displayFragment.displayText = "112+3";
        answer = displayFragment.calculate();
        assertEquals("115", answer);

        // If real equation (too big)
        displayFragment.displayText = "112+1231231231231231";
        answer = displayFragment.calculate();
        assertEquals("ERROR", answer);
    }


    public void testDoSubtraction() {
        displayFragment.displayText = "2-1";
        displayFragment.doSubtraction();
        assertEquals("1", displayFragment.displayText);
    }

    public void testDoAddition() {
        displayFragment.displayText = "2+1";
        displayFragment.doAddition();
        assertEquals("3", displayFragment.displayText);
    }
}
