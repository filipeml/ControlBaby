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
import com.projeto.teste.model.Remedio;

import java.util.List;

public class AdapterRemedio extends RecyclerView.Adapter<AdapterRemedio.MyViewHolder> {
    private List<Remedio> remedios;
    private Context context;

    public AdapterRemedio(List<Remedio> remedios, Context context) {
        this.remedios = remedios;
        this.context = context;
    }

    public List<Remedio> getRemedios() {
        return remedios;
    }

    public void setRemedios(List<Remedio> remedios) {
        this.remedios = remedios;
    }

    @NonNull
    @Override
    public AdapterRemedio.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate( R.layout.activity_adapter_remedio, parent, false);
        return new AdapterRemedio.MyViewHolder(item);  //adicionei o AdapterBebes.
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRemedio.MyViewHolder holder , int position) {

        if (remedios != null) {  //adicionei esse if, é bom ter para só fazer se tiver conteudo
            Remedio remedio = remedios.get ( position );

            holder.nomeRemedio.setText (remedio.getNomeRemedio( ));
            holder.dosagemRemedio.setText (remedio.getDosagemRemedio( ));  //retirei espaço do "getDataNasc ()"
            holder.dataRemedio.setText (remedio.getDataRemedio());
            holder.horaRemedio.setText (remedio.getHoraRemedio());

        }
    }

    @Override
    public int getItemCount() {
        if(remedios != null){  //adicionei if
            return remedios.size();
        }
        return 0;
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView nomeRemedio;
        TextView dosagemRemedio;
        TextView dataRemedio;
        TextView horaRemedio;
        ImageView icone;

        public MyViewHolder(@NonNull View itemView) {
            super ( itemView );

            nomeRemedio   = itemView.findViewById(R.id.editNomeRemedio);
            dosagemRemedio = itemView.findViewById (R.id.editDosagemRemedio);
            dataRemedio   = itemView.findViewById(R.id.editDataRemedio);
            horaRemedio   = itemView.findViewById(R.id.editHoraRemedio);
            icone    = itemView.findViewById(R.id.imageLogo);
        }
    }
}



