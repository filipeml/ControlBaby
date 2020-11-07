package com.projeto.teste.ui.bebe;

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
import com.projeto.teste.activity.AdicionarBebesActivity;
import com.projeto.teste.activity.EditarBebesAcivity;
import com.projeto.teste.adapter.AdapterBebes;
import com.projeto.teste.helper.Firebase;
import com.projeto.teste.helper.RecyclerItemClickListener;
import com.projeto.teste.model.Bebe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dmax.dialog.SpotsDialog;

//import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BebeFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class BebeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FloatingActionButton fabAddBebes;
    private RecyclerView recyclerViewBebes;
    private DatabaseReference refBebesFirebase;
    private List<Bebe> listBebes = new ArrayList<> ();
    private AdapterBebes adapterBebes;
    private AlertDialog dialog;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BebeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BebeFragment newInstance(String param1, String param2) {
        BebeFragment fragment = new BebeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BebeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_bebe, container, false);
        View myView = inflater.inflate( R.layout.fragment_bebe,container,false );
        recyclerViewBebes = myView.findViewById(R.id.recyclerMeusBebes);
        fabAddBebes = myView.findViewById(R.id.fabAddBebes);

//

        //Evento de clique para adicionar bebe
        fabAddBebes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AdicionarBebesActivity.class));
            }
        });

        //# Configura RecyclerView
        recyclerViewBebes.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewBebes.setHasFixedSize(true);
        adapterBebes = new AdapterBebes(listBebes, getContext());
        recyclerViewBebes.setAdapter(adapterBebes);
        recyclerViewBebes.addOnItemTouchListener(
                new RecyclerItemClickListener (
                        getContext(),
                        recyclerViewBebes,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Bebe bebeSelectionado = listBebes.get(position);
                                Intent i = new Intent(getContext(), EditarBebesAcivity.class);
                                i.putExtra("bebeSelecionado", bebeSelectionado);
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

    public void recuperarBebesFirebase(){
        dialog = new SpotsDialog.Builder()
                .setContext(getContext())
                .setMessage("carregando bebes")
                .setCancelable(false)
                .build();
        dialog.show();

        refBebesFirebase = Firebase.getFirebase().child("atividades").child("bebe")
                .child(Firebase.getIdUser());

        refBebesFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listBebes.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    listBebes.add(ds.getValue(Bebe.class));
                }

                Collections.reverse(listBebes);
                adapterBebes.setBebes(listBebes);
                adapterBebes.notifyDataSetChanged();
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
        recuperarBebesFirebase();
    }
}



