package com.knowtest.limovievoxwatch.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knowtest.limovievoxwatch.R;
import com.knowtest.limovievoxwatch.activity.MainActivity;
import com.knowtest.limovievoxwatch.model.ListMovies;
import com.knowtest.limovievoxwatch.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TelaIncialAdapter extends RecyclerView.Adapter<TelaIncialAdapter.TelaInicialMyViewHolder> {

    private List <Movie> movies;
    private final String baseUrl="https://image.tmdb.org/t/p/";
    private  final String tamanho ="w45";

    public TelaIncialAdapter(List<Movie> movies) {
       this.movies=movies;
    }

    @NonNull
    @Override
    public TelaIncialAdapter.TelaInicialMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ItemLista = LayoutInflater.
                from(parent.getContext()).inflate(R.layout.tela_principal_card_view,
                parent, false);
        return new TelaInicialMyViewHolder(ItemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull TelaIncialAdapter.TelaInicialMyViewHolder holder, int position) {

        String textoCompleto=movies.get(position).getOverview();
        String substring="";
        try {
            substring=textoCompleto.substring(0,50);
        }catch (Exception e){
            substring=textoCompleto.substring(0,textoCompleto.length());
        }
        String mess = "Clique para continuar lendo";
        substring= substring+"..."+"\n"+mess ;
        holder.overView.setText(substring);
        holder.name.setText(movies.get(position).getOriginal_title());

        Picasso.get().load(baseUrl+tamanho+movies.get(position).getPoster_path()).
                placeholder(R.drawable.icone).error(R.drawable.icone).into(holder.image);
        holder.id.setText(movies.get(position).getId());


    }

    @Override
    public int getItemCount() {

        return this.movies.size();
    }

    public class TelaInicialMyViewHolder extends   RecyclerView.ViewHolder{
        private TextView name;
        private TextView overView;
        private ImageView image;
        private TextView id;

        public TelaInicialMyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.card_title);
            overView=itemView.findViewById(R.id.card_text);
            image=itemView.findViewById(R.id.card_image);
            id = itemView.findViewById(R.id.id);

        }
    }

}
