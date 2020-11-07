package com.projeto.teste.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projeto.teste.R;
import com.projeto.teste.model.Bebe;

import java.util.List;

public class AdapterBebes extends RecyclerView.Adapter<AdapterBebes.MyViewHolder> {
    private List<Bebe> bebes;
    private Context context;

    public AdapterBebes(List<Bebe> bebes, Context context) {
        this.bebes = bebes;
        this.context = context;
    }

    //adicionei getter e setter
    public List<Bebe> getBebes() {
        return bebes;
    }

    public void setBebes(List<Bebe> bebes) {
        this.bebes = bebes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_bebes, parent, false);
        return new AdapterBebes.MyViewHolder(item);  //adicionei o AdapterBebes.
    }

    @Override
    public int getItemCount() {
        if(bebes != null){  //adicionei if
            return bebes.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBebes.MyViewHolder holder, int position) { //adicionei o AdapterBebes.
        if (bebes != null) {  //adicionei esse if, é bom ter para só fazer se tiver conteudo
            Bebe bebe = bebes.get(position);
            holder.nome.setText(bebe.getNomeBebe());
            holder.dataNasc.setText(bebe.getDataNasc());  //retirei espaço do "getDataNasc ()"
            String sexo = bebe.getSexo();

            if(sexo.equals("Menino")){
                holder.icone.setImageResource(R.drawable.bebe);
            }else{
                holder.icone.setImageResource(R.drawable.bebe);
            }
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nome;
        TextView dataNasc;
        ImageView icone;

        public MyViewHolder(View itemView){
            super(itemView);

            nome    = itemView.findViewById(R.id.txtnomeBebe);
            dataNasc    = itemView.findViewById(R.id.txtDataNasc);
            icone    = itemView.findViewById(R.id.imageBebe);
        }
    }
}

