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
import com.projeto.teste.model.Fralda;

import java.util.List;

public class AdapterFralda extends RecyclerView.Adapter<AdapterFralda.MyViewHolder> {
    private List<Fralda> fraldas;
    private Context context;

    public AdapterFralda(List<Fralda> fraldas, Context context) {
        this.fraldas = fraldas;
        this.context = context;
    }

    //adicionei getter e setter
    public List<Fralda> getFraldas() {
        return fraldas;
    }

    public void setFraldas(List<Fralda> fraldas) {
        this.fraldas = fraldas;
    }

    @NonNull
    @Override
    public AdapterFralda.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate( R.layout.activity_adapter_fralda, parent, false);
        return new AdapterFralda.MyViewHolder(item);  //adicionei o AdapterBebes.
    }

    @Override
    public int getItemCount() {
        if(fraldas != null){  //adicionei if
            return fraldas.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFralda.MyViewHolder holder, int position) { //adicionei o AdapterBebes.
        if (fraldas != null) {  //adicionei esse if, é bom ter para só fazer se tiver conteudo
            Fralda fralda = fraldas.get(position);
            holder.dataTroca.setText(fralda.getDataTroca());
            holder.horaTroca.setText(fralda.getHoraTroca());  //retirei espaço do "getDataNasc ()"
            String condicaoFralda = fralda.getCondicaoFralda ();

            if(condicaoFralda.equals("Xixi")){
                holder.icone.setImageResource(R.drawable.fralda);
            }else if(condicaoFralda.equals ( "Coco" )){
                holder.icone.setImageResource(R.drawable.fralda);
            }else
                holder.icone.setImageResource ( R.drawable.fralda );
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView dataTroca;
        TextView horaTroca;
        ImageView icone;

        public MyViewHolder(View itemView){
            super(itemView);

            dataTroca    = itemView.findViewById(R.id.editDataTroca);
            horaTroca    = itemView.findViewById(R.id.editHoraTroca);
            icone    = itemView.findViewById(R.id.imageLogo);
        }
    }
}
