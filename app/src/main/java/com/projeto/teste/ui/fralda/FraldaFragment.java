package com.projeto.teste.ui.fralda;

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
import com.projeto.teste.activity.AdicionarFraldaActivity;
import com.projeto.teste.activity.EditarBebesAcivity;
import com.projeto.teste.adapter.AdapterFralda;
import com.projeto.teste.helper.Firebase;
import com.projeto.teste.helper.RecyclerItemClickListener;
import com.projeto.teste.model.Fralda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FraldaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FraldaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1="param1";
    private static final String ARG_PARAM2="param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FloatingActionButton fabAddFralda;
    private RecyclerView recyclerViewFralda;
    private DatabaseReference refFraldaFirebase;
    private List<Fralda> listFralda = new ArrayList<> ();
    private AdapterFralda adapterFralda;
    private AlertDialog dialog;

    public FraldaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FraldasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FraldaFragment newInstance(String param1 , String param2) {
        FraldaFragment fragment=new FraldaFragment ( );
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
        View myView = inflater.inflate( R.layout.fragment_fralda,container,false );
        recyclerViewFralda = myView.findViewById(R.id.recyclerMinhaFralda);
        fabAddFralda = myView.findViewById(R.id.fabAddFralda);

//

        //Evento de clique para adicionar bebe
        fabAddFralda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (getContext(), AdicionarFraldaActivity.class));
            }
        });

        //# Configura RecyclerView
        recyclerViewFralda.setLayoutManager(new LinearLayoutManager (getContext()));
        recyclerViewFralda.setHasFixedSize(true);
        adapterFralda = new AdapterFralda (listFralda, getContext());
        recyclerViewFralda.setAdapter(adapterFralda);
        recyclerViewFralda.addOnItemTouchListener(
                new RecyclerItemClickListener (
                        getContext(),
                        recyclerViewFralda,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Fralda fraldaSelectionado = listFralda.get(position);
                                Intent i = new Intent(getContext(), EditarBebesAcivity.class);
                                i.putExtra("fraldaSelecionado", fraldaSelectionado);
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

    public void recuperarFraldaFirebase(){
        dialog = new SpotsDialog.Builder()
                .setContext(getContext())
                .setMessage("carregando fraldas")
                .setCancelable(false)
                .build();
        dialog.show();

        refFraldaFirebase = Firebase.getFirebase().child("atividades").child("fralda")
                .child(Firebase.getIdUser());

        refFraldaFirebase.addValueEventListener(new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listFralda.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    listFralda.add(ds.getValue(Fralda.class));
                }

                Collections.reverse(listFralda);
                adapterFralda.setFraldas(listFralda);
                adapterFralda.notifyDataSetChanged();
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
        recuperarFraldaFirebase();
    }
}
