package com.jaf.dev.carteiradeclientes;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jaf.dev.carteiradeclientes.database.DadosOpenHelper;
import com.jaf.dev.carteiradeclientes.dominio.entidades.Cliente;
import com.jaf.dev.carteiradeclientes.dominio.repositorio.ClienteRepositorio;

import java.util.List;

public class ActMain extends AppCompatActivity {

    private RecyclerView lstDados;
    private FloatingActionButton fab;
    private ConstraintLayout  layoutContentMain;


    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;
    private ClienteRepositorio clienteRepositorio;
    private ClienteAdapter clienteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        lstDados = (RecyclerView) findViewById(R.id.lstDados);

        layoutContentMain = (ConstraintLayout) findViewById(R.id.layoutContentMain);

        criarConexao();
        //erro deve estar aqui
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstDados.setLayoutManager(linearLayoutManager);
        clienteRepositorio = new ClienteRepositorio(conexao);
        List<Cliente>dados = clienteRepositorio.buscarTodos();
        clienteAdapter = new ClienteAdapter(dados);
        lstDados.setAdapter(clienteAdapter);
        //


        }
        private void criarConexao(){
            try{
                dadosOpenHelper = new DadosOpenHelper(this);
                conexao = dadosOpenHelper.getWritableDatabase();

                Snackbar.make(layoutContentMain,R.string.message_conexao_criada_com_sucesso, Snackbar.LENGTH_SHORT )
                        .setAction(R.string.action_ok, null).show();

            }catch(SQLException ex){
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle(R.string.title_erro);
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton(R.string.action_ok, null);
                dlg.show();

            }

        }

    public void cadastrar(View view){
        Intent it = new Intent(ActMain.this, ActCadCliente.class);
        startActivity(it);
    }

}
//aula 15