package com.projeto.teste.ui.remedio;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.projeto.teste.R;
import com.projeto.teste.activity.AdicionarRemedioActivity;
import com.projeto.teste.activity.EditarBebesAcivity;
import com.projeto.teste.adapter.AdapterRemedio;
import com.projeto.teste.helper.Firebase;
import com.projeto.teste.helper.RecyclerItemClickListener;
import com.projeto.teste.model.Remedio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RemedioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RemedioFragment extends Fragment {

    private static final String ARG_PARAM1="param1";
    private static final String ARG_PARAM2="param2";

    private String mParam1;
    private String mParam2;
    private FloatingActionButton fabAddRemedio;
    private RecyclerView recyclerViewRemedio;
    private DatabaseReference refRemedioFirebase;
    private List<Remedio> listRemedio = new ArrayList<> ( );
    private AdapterRemedio adapterRemedio;
    private AlertDialog dialog;


    public RemedioFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RemedioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RemedioFragment newInstance(String param1 , String param2) {
        RemedioFragment fragment = new RemedioFragment();
        Bundle args=new Bundle ( );
        args.putString ( ARG_PARAM1 , param1 );
        args.putString ( ARG_PARAM2 , param2 );
        fragment.setArguments ( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        if (getArguments ( ) != null) {
            mParam1=getArguments ( ).getString ( ARG_PARAM1 );
            mParam2=getArguments ( ).getString ( ARG_PARAM2 );
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        View myView=inflater.inflate ( R.layout.fragment_remedio , container , false );
        recyclerViewRemedio=myView.findViewById ( R.id.recyclerMeuRemedio);
        fabAddRemedio=myView.findViewById ( R.id.fabAddRemedio);

        //Evento de clique para adicionar bebe
        fabAddRemedio.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                startActivity ( new Intent ( getContext ( ) , AdicionarRemedioActivity.class ) );
            }
        } );

        //# Configura RecyclerView
        recyclerViewRemedio.setLayoutManager ( new LinearLayoutManager ( getContext ( ) ) );
        recyclerViewRemedio.setHasFixedSize ( true );
        adapterRemedio = new AdapterRemedio ( listRemedio , getContext ( ) );
        recyclerViewRemedio.setAdapter ( adapterRemedio );
        recyclerViewRemedio.addOnItemTouchListener (
                new RecyclerItemClickListener (
                        getContext ( ) ,
                        recyclerViewRemedio ,
                        new RecyclerItemClickListener.OnItemClickListener ( ) {
                            @Override
                            public void onItemClick(View view , int position) {
                                Remedio remedioSelectionado = listRemedio.get ( position );
                                Intent i=new Intent ( getContext ( ) , EditarBebesAcivity.class );
                                i.putExtra ( "remedioSelecionado" , remedioSelectionado );
                                startActivity ( i );
                            }

                            @Override
                            public void onLongItemClick(View view , int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent , View view , int position , long id) {

                            }
                        }
                )
        );

        return myView;
    }

    public void recuperarRemedioFirebase() {
        dialog = new SpotsDialog.Builder ( )
                .setContext ( getContext ( ) )
                .setMessage ( "carregando remedio" )
                .setCancelable ( false )
                .build ( );
        dialog.show ( );

        refRemedioFirebase=Firebase.getFirebase ( ).child ( "atividades" ).child ( "remedio" )
                .child ( Firebase.getIdUser ( ) );

        refRemedioFirebase.addValueEventListener ( new ValueEventListener ( ) {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listRemedio.clear ( );
                for (DataSnapshot ds : dataSnapshot.getChildren ( )) {
                    listRemedio.add ( ds.getValue (Remedio.class) );
                }

                Collections.reverse ( listRemedio );
                adapterRemedio.setRemedios ( listRemedio);
                adapterRemedio.notifyDataSetChanged ( );
                dialog.dismiss ( );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //onStart para sempre que iniciar o Fragment chamar o recuperarBebesFirebase
    @Override
    public void onStart() {
        super.onStart ( );
        recuperarRemedioFirebase();
    }
}


