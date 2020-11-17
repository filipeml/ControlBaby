package com.projeto.teste.ui.crescimento;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

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
import com.projeto.teste.activity.AdicionarCrescimentoActivity;
import com.projeto.teste.activity.EditarBebesAcivity;
import com.projeto.teste.adapter.AdapterCrescimento;
import com.projeto.teste.helper.Firebase;
import com.projeto.teste.helper.RecyclerItemClickListener;
import com.projeto.teste.model.Bebe;
import com.projeto.teste.model.Crescimento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CrescimentoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrescimentoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1="param1";
    private static final String ARG_PARAM2="param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FloatingActionButton fabAddCrescimento;
    private RecyclerView recyclerViewCrescimento;
    private DatabaseReference refCrescimentoFirebase;
    private List<Crescimento> listCrescimento=new ArrayList<> ( );
    private AdapterCrescimento adapterCrescimento;
    private List<Bebe> listBebes = new ArrayList<>();
    private List<String> listNomeBebes = new ArrayList<>();
    private HashMap<String, String> posicoesBebes = new HashMap<>();
    private Spinner spinnerCrescimentoBebe;
    private int index;
    private AlertDialog dialog;

    public CrescimentoFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CrescimentoFragment newInstance(String param1 , String param2) {
        CrescimentoFragment fragment=new CrescimentoFragment ( );
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
        // Inflate the layout for this fragment
        View myView=inflater.inflate ( R.layout.fragment_crescimento , container , false );
        recyclerViewCrescimento=myView.findViewById ( R.id.recyclerMeuCrescimento );
        fabAddCrescimento=myView.findViewById ( R.id.fabAddCrescimento );

        //Evento de clique para adicionar bebe
        fabAddCrescimento.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                startActivity ( new Intent ( getContext ( ) , AdicionarCrescimentoActivity.class ) );
            }
        } );

        //# Configura RecyclerView
        recyclerViewCrescimento.setLayoutManager ( new LinearLayoutManager ( getContext ( ) ) );
        recyclerViewCrescimento.setHasFixedSize ( true );
        adapterCrescimento=new AdapterCrescimento ( listCrescimento , getContext ( ) );
        recyclerViewCrescimento.setAdapter ( adapterCrescimento );
        recyclerViewCrescimento.addOnItemTouchListener (
                new RecyclerItemClickListener (
                        getContext ( ) ,
                        recyclerViewCrescimento ,
                        new RecyclerItemClickListener.OnItemClickListener ( ) {
                            @Override
                            public void onItemClick(View view , int position) {
                                Crescimento crescimentoSelectionado=listCrescimento.get ( position );
                                Intent i=new Intent ( getContext ( ) , EditarBebesAcivity.class );
                                i.putExtra ( "crescimentoSelecionado" , crescimentoSelectionado );
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

    public void recuperarCrescimentoFirebase(){
        dialog = new SpotsDialog.Builder()
                .setContext(getContext())
                .setMessage("carregando crescimento")
                .setCancelable(false)
                .build();
        dialog.show();

        refCrescimentoFirebase = Firebase.getFirebase().child("atividades").child("crescimento")
                .child(Firebase.getIdUser());

        refCrescimentoFirebase.addValueEventListener(new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listCrescimento.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    listCrescimento.add(ds.getValue(Crescimento.class));
                }

                Collections.reverse ( listCrescimento );
                adapterCrescimento.setCrescimento ( listCrescimento );
                adapterCrescimento.notifyDataSetChanged ();
                dialog.dismiss ();
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
        recuperarCrescimentoFirebase ();
    }
}