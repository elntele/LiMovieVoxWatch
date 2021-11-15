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

import java.util.List;

public class TelaIncialAdapter extends RecyclerView.Adapter<TelaIncialAdapter.TelaInicialMyViewHolder> {

    private List <Movie> movies;

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
        holder.image.setImageResource(R.drawable.icone);

    }

    @Override
    public int getItemCount() {

        return this.movies.size();
    }

    public class TelaInicialMyViewHolder extends   RecyclerView.ViewHolder{
        private TextView name;
        private TextView overView;
        private ImageView image;

        public TelaInicialMyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.card_title);
            overView=itemView.findViewById(R.id.card_text);
            image=itemView.findViewById(R.id.card_image);

        }
    }

}
