package com.example.ehi.calculatorsarefun.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.ehi.calculatorsarefun.R;
import com.example.ehi.calculatorsarefun.adapters.KeypadButtonAdapter;

public class KeypadFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keypad, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final GridView gridview = (GridView) this.getActivity().findViewById(R.id.keypad_gridview);
        gridview.setAdapter(new KeypadButtonAdapter(this.getActivity()));
    }
}
