package com.example.asd;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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

        // Comprueba si la receta tiene datos de imagen válidos
        if (receta.getImagen() != null && receta.getImagen().length > 0) {
            // Convierte el array de bytes en un Bitmap (si es una imagen en formato bitmap)
            Bitmap imagenBitmap = BitmapFactory.decodeByteArray(receta.getImagen(), 0, receta.getImagen().length);
            holder.imagenImageView.setImageBitmap(imagenBitmap);
        } else {
            // Establece una imagen predeterminada si no hay datos de imagen válidos
            holder.imagenImageView.setImageResource(R.drawable.encabezado); // Reemplaza con tu recurso de imagen predeterminada
        }

        // Puedes configurar más vistas aquí si es necesario
    }


    @Override
    public int getItemCount() {
        return listaRecetas.size();
    }

    public class RecetaViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagenImageView;
        public TextView nombreTextView;

        public TextView descripcionReceta;

        public RecetaViewHolder(View view) {
            super(view);
            imagenImageView = view.findViewById(R.id.itemsImagenReceta);
            nombreTextView = view.findViewById(R.id.itemTextImagen);
            descripcionReceta = view.findViewById(R.id.itemsDescriptionReceta);
        }
    }
}
