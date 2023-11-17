package com.example.pruebamovil_itsmart.adaptadors;

import android.app.AlertDialog;
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
import com.example.pruebamovil_itsmart.MainActivity;
import com.example.pruebamovil_itsmart.R;
import com.example.pruebamovil_itsmart.api.api_inter;
import com.example.pruebamovil_itsmart.api.retro;
import com.example.pruebamovil_itsmart.models.ClsClientes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdaClientes extends RecyclerView.Adapter<AdaClientes.Vista>{
    private Context C;
    private List<ClsClientes> Lista;
    api_inter api;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;
    public AdaClientes(Context C, List<ClsClientes> Lista) {
        this.C = C;
        this.Lista = Lista;
        api = retro.getClient().create(api_inter.class);
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
        int p = position;
        holder.titulo.setText("# "+String.valueOf(item.getId_cliente())+" " + item.getNombre());
        holder.subtitulo.setText(item.getEmail()+" - "+item.getTelefono());

        holder.btn_borrar.setOnClickListener(v -> {
                    alertDialogBuilder = new AlertDialog.Builder(C);
                    final View V = LayoutInflater.from(C).inflate(R.layout.my_alert, null);

                    Button DEL = V.findViewById(R.id.btn_borrar);
                    Button CAN = V.findViewById(R.id.btn_cancelar);

                    CAN.setOnClickListener(v1 -> {
                        alertDialog.dismiss();
                    });
                    DEL.setOnClickListener(v12 -> {
                        Toast.makeText(C, "Se elimino a: "+item.getNombre().toString() , Toast.LENGTH_SHORT).show();

                        Call<String> call = api.BORRAR(item.getId_cliente());
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {}
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {}
                        });
                        holder.itemView.setVisibility(View.GONE);
                        Lista.remove(p);
                        notifyItemRemoved(p);
                        alertDialog.dismiss();
                    });
                    alertDialogBuilder.setView(V);
                    alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
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
