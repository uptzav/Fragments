package com.example.fragmentos;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.security.PrivilegedAction;


public class SimpleFragment extends Fragment {

    private static final int YES = 0;
    private static final int NO = 1;
    private static  final  int NONE = 2;
    public  int mRadioButtonChoice = NONE;
    private  static final String CHOICE = "choice";

    OnFragmentInteractionListener mListener;

interface  OnFragmentInteractionListener{
    void onRadioButtonChoice(int choice);
}

public static SimpleFragment newInstance(int choice){
    SimpleFragment fragment = new SimpleFragment();
    Bundle arguments = new Bundle();
    arguments.putInt(CHOICE,choice);
    fragment.setArguments(arguments);
    return  fragment;
}

@Override
public void onAttach(Context context){
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
        mListener = (OnFragmentInteractionListener) context;
    }else{
        throw  new ClassCastException(context.toString()
        + getResources().getString(R.string.exception_message));
    }
}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View root = inflater.inflate(R.layout.fragment_simple, container, false);

        RadioGroup radioGroup = root.findViewById(R.id.radio_group);
        if(getArguments().containsKey(CHOICE)){
            mRadioButtonChoice = getArguments().getInt(CHOICE);
            if(mRadioButtonChoice != NONE){
                radioGroup
                        .check(radioGroup.getChildAt(mRadioButtonChoice).getId());
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                TextView tv = root.findViewById(R.id.fragment_header);



                switch (index){
                    case YES:
                        tv.setText(R.string.yes_message);
                        mListener.onRadioButtonChoice(YES);
                        break;
                    case NO:
                        tv.setText(R.string.no_message);
                        mListener.onRadioButtonChoice(NO);
                        break;
                    default:

                        mRadioButtonChoice = NONE;
                        mListener.onRadioButtonChoice(NONE);

                        break;

                }
            }
        });

       return root;
    }
}