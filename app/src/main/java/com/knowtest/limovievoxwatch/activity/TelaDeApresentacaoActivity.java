package com.knowtest.limovievoxwatch.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.knowtest.limovievoxwatch.R;
import com.knowtest.limovievoxwatch.helper.ClearCache;
import com.knowtest.limovievoxwatch.helper.FireBaseConfiguration;
import com.knowtest.limovievoxwatch.model.Movie;
import com.squareup.picasso.Picasso;

public class TelaDeApresentacaoActivity extends AppCompatActivity {
    private TextView title;
    private TextView overView;
    private ImageView exiteButton;
    private ImageView backButton;
    private ImageView poster;
    private final String baseUrl="https://image.tmdb.org/t/p/";
    private  final String tamanho ="w300";
    private ImageView botaoSair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_de_apresentacao);
        this.getSupportActionBar().hide();
        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        title = findViewById(R.id.titulo_tela_de_apresetacao);
        overView = findViewById(R.id.texto_descricao_tela_de_apresentacao);
        backButton= findViewById(R.id.voltar_tela_de_apresentacao);
        exiteButton= findViewById(R.id.sair_tela_de_apersentacao);
        poster= findViewById(R.id.foto_tela_de_apresentacao);
        Picasso.get().load(baseUrl+tamanho+movie.getPoster_path()).
                placeholder(R.drawable.icone).error(R.drawable.icone).into(poster);
        title.setText(movie.getOriginal_title());
        overView.setText(movie.getOverview());
        botaoSair=findViewById(R.id.exit_button_tela_principal);



    }

    public void backButton(View view){
        finish();

    }



    public void sairTelaDeApresentacao(View v) {
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


    public void logOutTelaApresentacao(View v) {
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
                ClearCache.deleteCache(getApplicationContext());
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


}
