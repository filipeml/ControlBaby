package com.projeto.teste.ui.alimentacao;

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
import com.projeto.teste.activity.AdicionarAlimentacaoActivity;
import com.projeto.teste.activity.EditarBebesAcivity;
import com.projeto.teste.adapter.AdapterAlimentacao;
import com.projeto.teste.helper.Firebase;
import com.projeto.teste.helper.RecyclerItemClickListener;
import com.projeto.teste.model.Alimentacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlimentacaoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlimentacaoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1="param1";
    private static final String ARG_PARAM2="param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FloatingActionButton fabAddAlimentacao;
    private RecyclerView recyclerViewAlimentacao;
    private DatabaseReference refAlimentacaoFirebase;
    private List<Alimentacao> listAlimentacao=new ArrayList<> ( );
    private AdapterAlimentacao adapterAlimentacao;
    private AlertDialog dialog;

    public AlimentacaoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlimentacaoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlimentacaoFragment newInstance(String param1 , String param2) {
        AlimentacaoFragment fragment=new AlimentacaoFragment ( );
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
        View myView=inflater.inflate ( R.layout.fragment_alimentacao , container , false );
        recyclerViewAlimentacao=myView.findViewById ( R.id.recyclerMinhaAlimentacao );
        fabAddAlimentacao=myView.findViewById ( R.id.fabAddAlimentacao );

        //Evento de clique para adicionar bebe
        fabAddAlimentacao.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                startActivity ( new Intent ( getContext ( ) , AdicionarAlimentacaoActivity.class ) );
            }
        } );

        //# Configura RecyclerView
        recyclerViewAlimentacao.setLayoutManager(new LinearLayoutManager (getContext()));
        recyclerViewAlimentacao.setHasFixedSize(true);
        adapterAlimentacao = new AdapterAlimentacao (listAlimentacao, getContext());
        recyclerViewAlimentacao.setAdapter(adapterAlimentacao);
        recyclerViewAlimentacao.addOnItemTouchListener(
                new RecyclerItemClickListener (
                        getContext(),
                        recyclerViewAlimentacao,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Alimentacao alimentacaoSelectionado = listAlimentacao.get(position);
                                Intent i = new Intent(getContext(), EditarBebesAcivity.class);
                                i.putExtra("alimentacaoSelecionado", alimentacaoSelectionado);
                                startActivity(i);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        return myView;
    }

    public void recuperarAlimentacaoFirebase(){
        dialog = new SpotsDialog.Builder()
                .setContext(getContext())
                .setMessage("carregando alimentacao")
                .setCancelable(false)
                .build();
        dialog.show();

        refAlimentacaoFirebase = Firebase.getFirebase().child("atividades").child("alimentacao")
                .child(Firebase.getIdUser());

        refAlimentacaoFirebase.addValueEventListener(new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listAlimentacao.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    listAlimentacao.add(ds.getValue(Alimentacao.class));
                }

                Collections.reverse(listAlimentacao);
                adapterAlimentacao.setAlimentacao (listAlimentacao);
                adapterAlimentacao.notifyDataSetChanged();
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
        recuperarAlimentacaoFirebase ();
    }
}