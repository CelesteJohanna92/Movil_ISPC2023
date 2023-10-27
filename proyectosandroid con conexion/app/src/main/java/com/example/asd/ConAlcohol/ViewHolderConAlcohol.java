package com.example.asd.ConAlcohol;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asd.R;

public class ViewHolderConAlcohol extends RecyclerView.ViewHolder {

    View cView;

    private ClickListener cClickListener;

    public interface ClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ClickListener clickListener) {
        cClickListener = clickListener;
    }

    public ViewHolderConAlcohol(@NonNull View itemView) {
        super(itemView);
        cView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cClickListener.onItemClick(view, getBindingAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                cClickListener.onItemLongClick(view, getBindingAdapterPosition());
                return true;
            }
        });
    }

    public void SeteoConAlcohol(Context context, String nombre, String ingredientes, byte[] imagenData) {
        ImageView itemsImagenReceta;
        TextView itemTextImagen;
        TextView itemDescripcionReceta;


        itemsImagenReceta = cView.findViewById(R.id.itemsImagenReceta);
        itemTextImagen = cView.findViewById(R.id.itemTextImagen);
        itemDescripcionReceta = cView.findViewById(R.id.itenDescripcionReceta);


        itemTextImagen.setText(nombre);
        itemDescripcionReceta.setText(ingredientes);

        try {
            // Crear un Bitmap a partir de los datos de la imagen
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagenData, 0, imagenData.length);

            // Establecer la imagen en el ImageView
            itemsImagenReceta.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}

