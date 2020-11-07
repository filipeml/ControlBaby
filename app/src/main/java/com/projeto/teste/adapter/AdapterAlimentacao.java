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
import com.projeto.teste.model.Alimentacao;


import java.util.List;

public class AdapterAlimentacao extends RecyclerView.Adapter<AdapterAlimentacao.MyViewHolder> {
    private List<Alimentacao> alimentos;
    private Context context;

    public AdapterAlimentacao(List<Alimentacao> alimentacao, Context context) {
        this.alimentos = alimentos;
        this.context = context;
    }

    public List<Alimentacao> getAlimentacao() {
        return alimentos;
    }

    public void setAlimentacao(List<Alimentacao> alimentos) {
        this.alimentos=alimentos;
    }

    @NonNull
    @Override
    public AdapterAlimentacao.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate( R.layout.activity_adapter_alimentacao, parent, false);
        return new AdapterAlimentacao.MyViewHolder(item);  //adicionei o AdapterBebes.
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAlimentacao.MyViewHolder holder , int position) {
        if (alimentos != null) {  //adicionei esse if, é bom ter para só fazer se tiver conteudo
            Alimentacao alimentacao = alimentos.get(position);

            holder.dataAlimentacao.setText ( alimentacao.getDataAlimentacao () );
            holder.horaAlimentacao.setText(alimentacao.getHoraAlimentacao ());  //retirei espaço do "getDataNasc ()"
            String aliment = alimentacao.getAlimento ();

            if(aliment.equals("Materno")){
                holder.icone.setImageResource(R.drawable.mamadeira);
            }else{
                holder.icone.setImageResource(R.drawable.adocao);
            }
        }

    }

    @Override
    public int getItemCount() {
        if(alimentos != null){  //adicionei if
            return alimentos.size();
        }
        return 0;
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView dataAlimentacao;
        TextView horaAlimentacao;
        ImageView icone;
        public MyViewHolder(@NonNull View itemView) {
            super ( itemView );

            dataAlimentacao   = itemView.findViewById(R.id.editDataAlimentacao);
            horaAlimentacao    = itemView.findViewById(R.id.editHoraAlimentacao);
            icone    = itemView.findViewById(R.id.imageLogo);
        }
    }
}
