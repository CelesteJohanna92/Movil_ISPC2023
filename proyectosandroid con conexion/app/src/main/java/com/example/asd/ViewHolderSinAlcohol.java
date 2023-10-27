package com.example.asd;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderSinAlcohol extends RecyclerView.ViewHolder {
    View sView;

    private ViewHolderSinAlcohol.ClickListener sClickListener;

    public interface ClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolderSinAlcohol.ClickListener slickListener) {
        sClickListener = slickListener;
    }

    public ViewHolderSinAlcohol(@NonNull View itemView) {
        super(itemView);
        sView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sClickListener.onItemClick(view, getBindingAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                sClickListener.onItemLongClick(view, getBindingAdapterPosition());
                return true;
            }
        });
    }

    public void SeteoSinAlcohol(Context context, String nombre, String ingredientes, byte[] imagenData) {
        ImageView itemsImagenSinAlcohol;
        TextView itemNombreSinAlcohol;
        TextView itemDescripcionSinAlcohol;


        itemsImagenSinAlcohol = sView.findViewById(R.id.itemsImagenSinAlcohol);
        itemNombreSinAlcohol = sView.findViewById(R.id.itemNombreSinAlcohol);
        itemDescripcionSinAlcohol = sView.findViewById(R.id.itemDescripcionSinAlcohol);


        itemNombreSinAlcohol.setText(nombre);
        itemDescripcionSinAlcohol.setText(ingredientes);

        try {
            // Crear un Bitmap a partir de los datos de la imagen
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagenData, 0, imagenData.length);

            // Establecer la imagen en el ImageView
            itemsImagenSinAlcohol.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
