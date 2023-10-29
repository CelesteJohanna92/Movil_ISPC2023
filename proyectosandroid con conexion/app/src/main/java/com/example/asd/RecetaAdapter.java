package com.example.asd;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder> {

    private List<Receta> listaRecetas;
    private Context context;

    public RecetaAdapter(List<Receta> listaRecetas, Context context) {
        this.listaRecetas = listaRecetas;
        this.context = context;
    }

    @Override
    public RecetaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receta, parent, false);
        return new RecetaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecetaViewHolder holder, int position) {
        Receta receta = listaRecetas.get(position);
        holder.nombreTextView.setText(receta.getNombre());
        holder.ingredientesView.setText(receta.getIngredientes());
        holder.instruccionesView.setText(receta.getInstrucciones());

        String imagenURL = receta.getImagenURL();

        Log.d("RecetaAdapter", "URL de imagen: " + imagenURL);
        loadImage(imagenURL, holder.imagenImageView);
    }
    public void loadImage(String imagenURL, ImageView imageView) {
        if (imagenURL != null && !imagenURL.isEmpty()) {
            Uri uri = Uri.parse(imagenURL);
            if (uri != null && uri.isAbsolute()) {
                Picasso.get()
                        .load(imagenURL)
                        .placeholder(R.drawable.encabezado)
                        .error(R.drawable.margarita)
                        .fit()
                        .into(imageView);
            } else {
                // URL de la imagen no válida
                imageView.setImageResource(R.drawable.margarita);
            }
        } else {
            // URL de la imagen vacía o nula
            imageView.setImageResource(R.drawable.margarita);
        }
    }

    @Override
    public int getItemCount() {
        return listaRecetas.size();
    }

    public class RecetaViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagenImageView;
        public TextView nombreTextView, ingredientesView, instruccionesView;

        public RecetaViewHolder(View view) {
            super(view);
            imagenImageView = view.findViewById(R.id.itemsImagenReceta);
            nombreTextView = view.findViewById(R.id.itemTextImagen);
            ingredientesView = view.findViewById(R.id.itemIngredientesReceta);
            instruccionesView = view.findViewById(R.id.itemInstruccionesReceta);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, EditarReceta.class);
                    intent.putExtra("ID", listaRecetas.get(getBindingAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
    public void setRecetas(List<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }
}
