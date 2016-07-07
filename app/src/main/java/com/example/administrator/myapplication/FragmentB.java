package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;


public class FragmentB extends Fragment implements View.OnClickListener {

    public static final String TAG = "TruongVN - FragmentB";

    private Fragment target;
    private FragmentA fragmentA;
    private Button btnAdd, btnMinus, btnMulti, btnDiv, btnValue;
    private int numberOne, numberTwo;

    private int result = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        target = getTargetFragment();
        if (target instanceof FragmentA) {
            fragmentA = ((FragmentA) target);
        }


        //hide keyboard, don't care
        hideKeyboard(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        btnAdd = (Button) view.findViewById(R.id.buttonAdd);
        btnMinus = (Button) view.findViewById(R.id.buttonMinus);
        btnMulti = (Button) view.findViewById(R.id.buttonMulti);
        btnDiv = (Button) view.findViewById(R.id.buttonDiv);
        btnValue = (Button) view.findViewById(R.id.buttonValues);

        numberOne = fragmentA.getNumberOne();
        numberTwo = fragmentA.getNumberTwo();

        btnValue.setText(numberOne + " -- vs -- " + numberTwo);

        btnAdd.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMulti.setOnClickListener(this);
        btnDiv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonAdd:
                result = numberOne + numberTwo;
                break;

            case R.id.buttonMinus:
                result = numberOne - numberTwo;
                break;

            case R.id.buttonMulti:
                result = numberOne * numberTwo;
                break;

            case R.id.buttonDiv:
                if (numberOne != 0 && numberTwo != 0) {
                    result = numberOne / numberTwo;
                }
                break;

            default:
                break;
        }

        Intent intent = new Intent();
        intent.putExtra(FragmentA.RESULT_KEY, result);
        fragmentA.onActivityResult(FragmentA.REQUEST_CODE, FragmentA.RESULT_CODE, intent);

        getFragmentManager().popBackStack();
    }

    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}
