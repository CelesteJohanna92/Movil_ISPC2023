package com.example.asd.Fragmentos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.asd.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Contacto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Contacto extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Contacto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Contacto.
     */
    // TODO: Rename and change types and number of parameters
    public static Contacto newInstance(String param1, String param2) {
        Contacto fragment = new Contacto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacto, container, false);

        LottieAnimationView instagramAnimation = view.findViewById(R.id.instagramAnimation);
        LottieAnimationView facebookAnimation = view.findViewById(R.id.facebookAnimation);
        LottieAnimationView correoAnimation = view.findViewById(R.id.correoAnimation);
        instagramAnimation.setOnClickListener(v -> openInstagramPage());
        facebookAnimation.setOnClickListener(v -> openFacebookPage());
        correoAnimation.setOnClickListener(v -> openCorreoPage());

        return view;
    }

    private void openInstagramPage() {
        String instagramUsername = "Coctelis";
        String url = "https://www.instagram.com/" + instagramUsername;
        Uri uri = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void openFacebookPage() {
        String facebookUsername = "Coctelis";
        String url = "https://www.facebook.com/" + facebookUsername;
        Uri uri = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void openCorreoPage() {
        String correoUsername = "Coctelis@gmail.com";
        String url = "https://www.google.com/intl/es-419/gmail/about/" + correoUsername;
        Uri uri = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}