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
import com.projeto.teste.model.Vacina;

import java.util.List;

public class AdapterVacinas extends RecyclerView.Adapter<AdapterVacinas.MyViewHolder> {
    private List<Vacina> vacinas;
    private Context context;

    public AdapterVacinas(List<Vacina> vacinas, Context context) {
        this.vacinas = vacinas;
        this.context = context;
    }


    public List<Vacina> getVacinas() {
        return vacinas;
    }

    public void setVacinas(List<Vacina> vacinas) {
        this.vacinas = vacinas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate( R.layout.activity_adapter_vacinas, parent, false);
        return new AdapterVacinas.MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVacinas.MyViewHolder holder , int position) {
        if(vacinas!= null){
            Vacina vacina = vacinas.get(position);
            holder.nome.setText (vacina.getNomeVacina());
            holder.dataAplicacao.setText(vacina.getDataAplicacao());
        }
    }

    @Override
    public int getItemCount() {
        if(vacinas != null){
            return vacinas.size();
        }
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nome;
        TextView dataAplicacao;
        ImageView icone;

        public MyViewHolder(View itemView){
            super(itemView);

            nome = itemView.findViewById ( R.id.txtnomeVacina);
            dataAplicacao = itemView.findViewById ( R.id.txtDataAplicacao);
            icone = itemView.findViewById(R.id.imageVacina);
        }
    }
}
