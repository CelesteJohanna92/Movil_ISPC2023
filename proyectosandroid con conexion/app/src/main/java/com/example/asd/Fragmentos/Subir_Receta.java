package com.example.asd.Fragmentos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.example.asd.R;
import com.example.asd.daoUsuario;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Subir_Receta extends Fragment {

    private EditText edtNombre, edtIngredientes, edtInstrucciones;
    private ImageButton btnImagen;
    private String imagenPath;
    private Button SubirRecetas;
    private int idCategoria;
    private int idUsuario;
    private Spinner spinnerCategoria;

    // ActivityResultLauncher para la galería
    private final ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == getActivity().RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        Uri uri = data.getData();
                        imagenPath = getPathFromUri(uri);
                        btnImagen.setImageURI(uri);
                    }
                }
            }
    );

    private String getPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String filePath = cursor.getString(column_index);
            cursor.close();
            return filePath;
        } else {
            return uri.getPath();
        }
    }

    public Subir_Receta() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subir__receta, container, false);

        edtNombre = view.findViewById(R.id.NombreReceta);
        edtIngredientes = view.findViewById(R.id.Ingrediente);
        edtInstrucciones = view.findViewById(R.id.Descripcion);
        btnImagen = view.findViewById(R.id.imagen);
        SubirRecetas = view.findViewById(R.id.SubirRecetas);



        // Inicializa el Spinner
        spinnerCategoria = view.findViewById(R.id.spinnerCategoria);

        // Crea un ArrayAdapter con las opciones
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.opciones_categoria, android.R.layout.simple_spinner_item);
        // Especifica el diseño del dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Aplica el adapter al Spinner
        spinnerCategoria.setAdapter(adapter);

        // Manejo de clic en el botón de imagen para seleccionar de la galería
        btnImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la galería para seleccionar una imagen
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                galleryLauncher.launch(intent);
            }
        });

        SubirRecetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = edtNombre.getText().toString();
                String ingredientes = edtIngredientes.getText().toString();
                String instrucciones = edtInstrucciones.getText().toString();
                // Verifica si se ha seleccionado una imagen
                if (imagenPath == null || imagenPath.isEmpty()) {
                    Toast.makeText(getActivity(), "Seleccione una imagen", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convierte la URI de la imagen a bytes
                byte[] imagenBytes = convertirImagenBytes(Uri.parse(imagenPath));
                agregarReceta(nombre, ingredientes, instrucciones, imagenBytes);
                // Limpiar los campos después de subir la receta con éxito
                limpiarCampos();
            }
            private void limpiarCampos() {
                edtNombre.getText().clear();
                edtIngredientes.getText().clear();
                edtInstrucciones.getText().clear();
                btnImagen.setImageURI(null);
                spinnerCategoria.setSelection(0);
            }
        });

        return view;
    }

    private void agregarReceta(String nombre, String ingredientes, String instrucciones, byte[] imagenBytes) {
        if (nombre.isEmpty()) {
            Toast.makeText(getActivity(), "El nombre de la receta no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crea un nuevo objeto de ContentValues
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("ingredientes", ingredientes);
        values.put("instrucciones", instrucciones);
        values.put("imagen", imagenBytes);

        daoUsuario dao = new daoUsuario(getActivity());

        try {
            // Llama al método insertarReceta de la clase daoUsuario
            dao.insertarReceta(idCategoria, idUsuario, nombre, ingredientes, instrucciones, imagenBytes);

            Toast.makeText(getActivity(), "La receta ha sido subida con éxito", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error al subir la receta", Toast.LENGTH_SHORT).show();
        }
    }

    // FUNCION PARA CONVERTIR URI A BYTE
    private byte[] convertirImagenBytes(Uri uri) {
        try {
            InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
