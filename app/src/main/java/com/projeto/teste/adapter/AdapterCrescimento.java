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
import com.projeto.teste.model.Crescimento;

import java.util.List;

public class AdapterCrescimento extends RecyclerView.Adapter<AdapterCrescimento.MyViewHolder> {
    private List<Crescimento> crescimentos;
    private Context context;

    public AdapterCrescimento(List<Crescimento> crescimento, Context context) {
        this.crescimentos = crescimentos;
        this.context = context;
    }

    public List<Crescimento> getCrescimento() {
        return crescimentos;
    }

    public void setCrescimento(List<Crescimento> crescimentos) {
        this.crescimentos = crescimentos;
    }

    @NonNull
    @Override
    public AdapterCrescimento.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate( R.layout.activity_adapter_crescimento, parent, false);
        return new AdapterCrescimento.MyViewHolder(item);  //adicionei o AdapterBebes.
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCrescimento.MyViewHolder holder , int position) {

        if (crescimentos != null) {  //adicionei esse if, é bom ter para só fazer se tiver conteudo
            Crescimento crescimento = crescimentos.get ( position );

            holder.dataMedicao.setText ( crescimento.getDataMedicao ( ) );
            holder.pesoBebe.setText ( crescimento.getPesoBebe ( ) );  //retirei espaço do "getDataNasc ()"
            holder.tamanhoBebe.setText ( crescimento.getTamanhoBebe ( ) );

        }
    }

    @Override
    public int getItemCount() {
        if(crescimentos != null){  //adicionei if
            return crescimentos.size();
        }
        return 0;
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView dataMedicao;
        TextView pesoBebe;
        TextView tamanhoBebe;
        ImageView icone;

        public MyViewHolder(@NonNull View itemView) {
            super ( itemView );

            dataMedicao   = itemView.findViewById(R.id.editDataMedicao);
            pesoBebe    = itemView.findViewById(R.id.editPeso);
            tamanhoBebe = itemView.findViewById (R.id.editTamanho);
            icone    = itemView.findViewById(R.id.imageLogo);
        }
    }
}
