package com.knowtest.limovievoxwatch.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.knowtest.limovievoxwatch.R;
import com.knowtest.limovievoxwatch.adapter.TelaIncialAdapter;
import com.knowtest.limovievoxwatch.helper.FireBaseConfiguration;
import com.knowtest.limovievoxwatch.helper.RecyclerItemClickListener;
import com.knowtest.limovievoxwatch.model.Movie;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class TelaInicialActivity extends AppCompatActivity {
    private RecyclerView recycleViewTelaInicial;
    private TelaIncialAdapter adapter;
    private List <Movie>  movies= new ArrayList();
    private SearchView searchView;
    private  FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.getSupportActionBar().hide();
        ArrayList<Movie> data = getIntent().getExtras().getParcelableArrayList("array");
        movies=data;
        setContentView(R.layout.activity_tela_inicial);
        searchView = findViewById(R.id.search_tela_inicial);
        recycleViewTelaInicial = findViewById(R.id.recicler_lista_file);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        final TelaIncialAdapter adapter = new TelaIncialAdapter(movies);
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

                        Intent intent = new Intent(getApplicationContext(), TelaDeApresentacaoActivity.class);
                        intent.putExtra("movie", (Serializable) movies.get(position));
                        startActivity(intent);


                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                })

        );


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });

    }

    public void sairTelaInicial(View v) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja Sair do Aplicativo?");
        builder.setCancelable(true);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    public void logOutTelaInicial(View v) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Fazer logou? precisará informar usuário e senha quando entrar.");
        builder.setCancelable(true);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseAuth auth = FireBaseConfiguration.getFirebaseAuth();
                auth.signOut();
                if (auth.getCurrentUser() == null){
                    Log.d("usuario", "jorge candeias do nascimento");
                }
                deleteCache(getApplicationContext());
                finish();
                finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }




}
