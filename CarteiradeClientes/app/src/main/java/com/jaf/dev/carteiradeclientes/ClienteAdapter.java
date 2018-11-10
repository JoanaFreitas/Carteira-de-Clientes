package com.jaf.dev.carteiradeclientes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaf.dev.carteiradeclientes.dominio.entidades.Cliente;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolderClientes> {
    @NonNull
    private List<Cliente>dados;

    public ClienteAdapter(List<Cliente>dados){
        this.dados = dados;
    }
    @Override
    public ClienteAdapter.ViewHolderClientes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha_clientes, parent,false);
        ViewHolderClientes holderClientes = new ViewHolderClientes(view);
        return holderClientes;
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteAdapter.ViewHolderClientes holder, int position) {
        if((dados!=null)&&(dados.size()>0)) {
            Cliente cliente = dados.get(position);
            holder.txtNome.setText(cliente.nome);
            holder.txtTelefone.setText(cliente.telefone);
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderClientes extends RecyclerView.ViewHolder{
        public TextView txtNome;
        public TextView txtTelefone;

        public ViewHolderClientes(@NonNull View itemView) {
            super(itemView);

            txtNome     = (TextView)itemView.findViewById(R.id.txtNome);
            txtTelefone = (TextView)itemView.findViewById(R.id.txtTelefone);
        }
    }
}
