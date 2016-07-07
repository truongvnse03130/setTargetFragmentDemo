package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class FragmentA extends Fragment implements View.OnClickListener {

    public static final String TAG = "TruongVN - FragmentA";
    public static final int REQUEST_CODE = 0;
    public static final int RESULT_CODE = 1;
    public static final String RESULT_KEY = "key";

    private EditText mNumberOne, mNumberTwo;
    private Button mButton;
    private TextView mResult;
    private int resultFromFragment  = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mNumberOne = (EditText) view.findViewById(R.id.number_one);
        mNumberTwo = (EditText) view.findViewById(R.id.number_two);
        mButton = (Button) view.findViewById(R.id.button);
        mResult = (TextView) view.findViewById(R.id.result);

        if (resultFromFragment != 0) {
            mResult.setText(String.valueOf(resultFromFragment));
        }

        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = new FragmentB();
        fragment.setTargetFragment(this, REQUEST_CODE);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.contain_frame, fragment, FragmentB.TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_CODE) {
                resultFromFragment = data.getIntExtra(RESULT_KEY, 0);
            }
        }
    }

    public int getNumberOne() {
        if (!mNumberOne.getText().toString().equals("")) {
            return Integer.valueOf(mNumberOne.getText().toString());
        } else return 0;
    }

    public int getNumberTwo() {
        if (!mNumberTwo.getText().toString().equals("")) {
            return Integer.valueOf(mNumberTwo.getText().toString());
        } else return 0;
    }
}
