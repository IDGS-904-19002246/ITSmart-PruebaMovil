package com.example.pruebamovil_itsmart.adaptadors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebamovil_itsmart.FormActivity;
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
//    private OnClientesClickListener onClientesClickListener;
//    public interface OnClientesClickListener { void onClientesClick(ClsClientes Cli);}
//
//    public void setOnPetClickListener(OnClientesClickListener onClientesClickListener) {
//        this.onClientesClickListener = onClientesClickListener;
//    }
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
        holder.titulo.setText("# "+String.valueOf(item.getId_cliente())+" " + item.getNombre());
        holder.subtitulo.setText(item.getEmail()+" - "+item.getTelefono());

        holder.btn_borrar.setOnClickListener(v ->
                Toast.makeText(C, "My id is: "+item.getId_cliente(), Toast.LENGTH_SHORT).show()
        );
        holder.btn_editar.setOnClickListener(v -> {
            Intent intent = new Intent(C, FormActivity.class);
            intent.putExtra(FormActivity.CLI_KEY, item);
            C.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {return Lista.size();}
//    -----------------------------------------------------------------------------------------

    public class Vista extends RecyclerView.ViewHolder{
        TextView titulo, subtitulo;
        Button btn_editar, btn_borrar;
        public Vista(@NonNull View itemView) { super(itemView);
            titulo = itemView.findViewById(R.id.item_titulo);
            subtitulo = itemView.findViewById(R.id.item_subtitulo);

            btn_editar = itemView.findViewById(R.id.btn_editar);
            btn_borrar = itemView.findViewById(R.id.btn_borrar);
        }
    }
}
