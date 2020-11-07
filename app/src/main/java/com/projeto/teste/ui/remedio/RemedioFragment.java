package com.projeto.teste.ui.remedio;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.projeto.teste.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RemedioFragment extends Fragment {


    public RemedioFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate ( R.layout.fragment_remedio , container , false );
    }

}
