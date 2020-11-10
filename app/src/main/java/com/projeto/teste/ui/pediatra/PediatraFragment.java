package com.projeto.teste.ui.pediatra;

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
import com.projeto.teste.activity.AdicionarPediatraActivity;
import com.projeto.teste.activity.EditarBebesAcivity;
import com.projeto.teste.adapter.AdapterPediatra;
import com.projeto.teste.helper.Firebase;
import com.projeto.teste.helper.RecyclerItemClickListener;
import com.projeto.teste.model.Pediatra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PediatraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PediatraFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1="param1";
    private static final String ARG_PARAM2="param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FloatingActionButton fabAddPediatra;
    private RecyclerView recyclerViewPediatra;
    private DatabaseReference refPediatraFirebase;
    private List<Pediatra> listPediatra = new ArrayList<> ( );
    private AdapterPediatra adapterPediatra;
    private AlertDialog dialog;

    public PediatraFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PediatraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PediatraFragment newInstance(String param1 , String param2) {
        PediatraFragment fragment=new PediatraFragment ( );
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
        View myView=inflater.inflate ( R.layout.fragment_pediatra , container , false );
        recyclerViewPediatra = myView.findViewById ( R.id.recyclerMeuPediatra );
        fabAddPediatra=myView.findViewById ( R.id.fabAddPediatra );

        //Evento de clique para adicionar bebe
        fabAddPediatra.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                startActivity ( new Intent ( getContext ( ) , AdicionarPediatraActivity.class ) );
            }
        } );

        //# Configura RecyclerView
        recyclerViewPediatra.setLayoutManager ( new LinearLayoutManager ( getContext ( ) ) );
        recyclerViewPediatra.setHasFixedSize ( true );
        adapterPediatra = new AdapterPediatra ( listPediatra , getContext ( ) );
        recyclerViewPediatra.setAdapter ( adapterPediatra );
        recyclerViewPediatra.addOnItemTouchListener (
                new RecyclerItemClickListener (
                        getContext ( ) ,
                        recyclerViewPediatra ,
                        new RecyclerItemClickListener.OnItemClickListener ( ) {
                            @Override
                            public void onItemClick(View view , int position) {
                                Pediatra pediatraSelectionado=listPediatra.get ( position );
                                Intent i=new Intent ( getContext ( ) , EditarBebesAcivity.class );
                                i.putExtra ( "pediatraSelecionado" , pediatraSelectionado);
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

    public void recuperarPediatraFirebase(){
        dialog = new SpotsDialog.Builder()
                .setContext(getContext())
                .setMessage("carregando pediatra")
                .setCancelable(false)
                .build();
        dialog.show();

        refPediatraFirebase = Firebase.getFirebase().child("atividades").child("pediatra")
                .child(Firebase.getIdUser());

        refPediatraFirebase.addValueEventListener(new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPediatra.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    listPediatra.add(ds.getValue( Pediatra.class));
                }

                Collections.reverse(listPediatra);
                adapterPediatra.setPediatras (listPediatra);
                adapterPediatra.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //onStart para sempre que iniciar o Fragment chamar o recuperarBebesFirebase
    @Override
    public void onStart() {
        super.onStart();
        recuperarPediatraFirebase ();
    }

}