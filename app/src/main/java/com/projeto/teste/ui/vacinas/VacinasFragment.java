package com.projeto.teste.ui.vacinas;

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
import com.projeto.teste.activity.AdicionarVacinasActivity;
import com.projeto.teste.activity.EditarVacinasActivity;
import com.projeto.teste.adapter.AdapterVacinas;
import com.projeto.teste.helper.Firebase;
import com.projeto.teste.helper.RecyclerItemClickListener;
import com.projeto.teste.model.Vacina;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VacinasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VacinasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1="param1";
    private static final String ARG_PARAM2="param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FloatingActionButton fabAddVacinas;
    private RecyclerView recyclerViewVacinas;
    private DatabaseReference refVacinasFirebase;
    private List<Vacina> listVacina = new ArrayList<> ();
    private AdapterVacinas adapterVacinas;
    private AlertDialog dialog;

    public VacinasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VacinasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VacinasFragment newInstance(String param1 , String param2) {
        VacinasFragment fragment = new VacinasFragment ( );
        Bundle args = new Bundle ( );
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
        //return inflater.inflate(R.layout.fragment_bebe, container, false);
        View myView = inflater.inflate( R.layout.fragment_vacinas,container,false );
        recyclerViewVacinas = myView.findViewById(R.id.recyclerMinhasVacinas);
        fabAddVacinas = myView.findViewById(R.id.fabAddVacinas);

//

        //Evento de clique para adicionar vacina
        fabAddVacinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),AdicionarVacinasActivity.class));
            }
        });

        //# Configura RecyclerView
        recyclerViewVacinas.setLayoutManager(new LinearLayoutManager (getContext()));
        recyclerViewVacinas.setHasFixedSize(true);
        adapterVacinas = new AdapterVacinas (listVacina, getContext());
        recyclerViewVacinas.setAdapter(adapterVacinas);
        recyclerViewVacinas.addOnItemTouchListener(
                new RecyclerItemClickListener (
                        getContext(),
                        recyclerViewVacinas,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Vacina vacinasSelectionado = listVacina.get(position);
                                Intent i = new Intent(getContext(), EditarVacinasActivity.class);
                                i.putExtra("vacinasSelecionado", vacinasSelectionado);
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

    public void recuperarVacinasFirebase(){
        dialog = new SpotsDialog.Builder()
                .setContext(getContext())
                .setMessage("carregando vacinas")
                .setCancelable(false)
                .build();
        dialog.show();

        refVacinasFirebase = Firebase.getFirebase().child("atividades").child("vacina")
                .child(Firebase.getIdUser());

        refVacinasFirebase.addValueEventListener(new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listVacina.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    listVacina.add(ds.getValue(Vacina.class));
                }

                Collections.reverse(listVacina);
                adapterVacinas.setVacinas (listVacina);//set a lista no AdapterBebes
                adapterVacinas.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //onStart para sempre que iniciar o Fragmente chamar o recuperarBebesFirebase
    @Override
    public void onStart() {
        super.onStart();
        recuperarVacinasFirebase();
    }
}