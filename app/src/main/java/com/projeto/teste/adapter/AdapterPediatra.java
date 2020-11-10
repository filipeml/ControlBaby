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
import com.projeto.teste.model.Pediatra;

import java.util.List;

public class AdapterPediatra extends RecyclerView.Adapter<AdapterPediatra.MyViewHolder> {
    private List<Pediatra> pediatras;
    private Context context;

    public AdapterPediatra(List<Pediatra> pediatras, Context context) {
        this.pediatras = pediatras;
        this.context = context;
    }

    public List<Pediatra> getPediatras() {
        return pediatras;
    }

    public void setPediatras(List<Pediatra> pediatras) {
        this.pediatras = pediatras;
    }

    @NonNull
    @Override
    public AdapterPediatra.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate( R.layout.activity_adapter_pediatra, parent, false);
        return new AdapterPediatra.MyViewHolder(item);  //adicionei o AdapterBebes.
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPediatra.MyViewHolder holder , int position) {

        if (pediatras != null) {  //adicionei esse if, é bom ter para só fazer se tiver conteudo
            Pediatra pediatra = pediatras.get ( position );

            holder.dataConsulta.setText ( pediatra.getDataConsulta ( ) );
            holder.horaConsulta.setText ( pediatra.getHoraConsulta ( ) );  //retirei espaço do "getDataNasc ()"
            holder.localConsulta.setText ( pediatra.getLocalConsulta ( ) );

        }
    }

    @Override
    public int getItemCount() {
        if(pediatras != null){  //adicionei if
            return pediatras.size();
        }
        return 0;
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView dataConsulta;
        TextView horaConsulta;
        TextView localConsulta;
        ImageView icone;

        public MyViewHolder(@NonNull View itemView) {
            super ( itemView );

            dataConsulta   = itemView.findViewById(R.id.editDataConsulta);
            horaConsulta    = itemView.findViewById(R.id.editHoraConsulta);
            localConsulta = itemView.findViewById (R.id.editLocalConsulta);
            icone    = itemView.findViewById(R.id.imageLogo);
        }
    }
}


