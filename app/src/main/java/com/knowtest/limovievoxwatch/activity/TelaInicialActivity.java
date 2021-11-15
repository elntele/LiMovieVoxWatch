package com.knowtest.limovievoxwatch.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.knowtest.limovievoxwatch.R;
import com.knowtest.limovievoxwatch.adapter.TelaIncialAdapter;
import com.knowtest.limovievoxwatch.helper.RecyclerItemClickListener;
import com.knowtest.limovievoxwatch.model.Movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class TelaInicialActivity extends AppCompatActivity {
    private RecyclerView recycleViewTelaInicial;
    private TelaIncialAdapter adapter;
    private List <Movie>  movies= new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.getSupportActionBar().hide();
        ArrayList<Movie> data = getIntent().getExtras().getParcelableArrayList("array");
        movies=data;

        setContentView(R.layout.activity_tela_inicial);
        recycleViewTelaInicial = findViewById(R.id.recicler_lista_file);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        TelaIncialAdapter adapter = new TelaIncialAdapter(movies);
        recycleViewTelaInicial.setLayoutManager(layoutManager);
        recycleViewTelaInicial.setAdapter(adapter);
        this.adapter=adapter;

        recycleViewTelaInicial.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recycleViewTelaInicial, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), TelaDeApresentacaoActivity.class);
                        intent.putExtra("movie", (Serializable) movies.get(position));
                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {



                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                })

        );





    }

    public void sairTelaInicial(View v) {
        //Cria o gerador do AlertDialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define a mensagem
        builder.setMessage("Deseja Sair do Aplicativo?");
        builder.setCancelable(true);
        //define um botão pra sair
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                finishAffinity();
                System.exit(0);
            }
        });
        //define um botão pra cancelar.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        //cria o AlertDialog
        AlertDialog alertDialog = builder.create();
        //Exibe
        alertDialog.show();

    }


}
