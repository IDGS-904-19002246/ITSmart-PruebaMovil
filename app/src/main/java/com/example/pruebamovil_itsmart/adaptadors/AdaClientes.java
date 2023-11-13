package com.example.pruebamovil_itsmart.adaptadors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebamovil_itsmart.R;
import com.example.pruebamovil_itsmart.models.ClsClientes;

import java.util.List;

public class AdaClientes extends RecyclerView.Adapter<AdaClientes.Vista>{
    private Context C;
    private List<ClsClientes> Lista;
    public AdaClientes(Context C, List<ClsClientes> Lista) {
        this.C = C;
        this.Lista = Lista;
    }
//    -----------------------------------------------------------------------------------------
    @NonNull
    @Override
    public AdaClientes.Vista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(C).inflate(R.layout.item, parent, false);
        return new Vista(V);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaClientes.Vista holder, int position) {
        final ClsClientes item = Lista.get(position);
        holder.titulo.setText(item.getId_cliente() + item.getNombre());
        holder.subtitulo.setText(item.getEmail()+" - "+item.getTelefono());


    }

    @Override
    public int getItemCount() {return Lista.size();}
//    -----------------------------------------------------------------------------------------

    public class Vista extends RecyclerView.ViewHolder{
        TextView titulo;
        TextView subtitulo;
        public Vista(@NonNull View itemView) { super(itemView);
            titulo = itemView.findViewById(R.id.item_titulo);
            subtitulo = itemView.findViewById(R.id.item_subtitulo);
        }
    }
}
