package com.example.galerie_photo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int RETOUR_PRENDRE_PHOTO = 1;

    private ImageButton btnPrendrePhoto;
    private ImageButton btnSupprimerPhoto;
    private ImageButton btnGauche;
    private ImageButton btnDroite;

    ArrayList<File> listeImage;

    private ImageView imgAffichePhoto;
    private ImageView previewGauche;
    private ImageView previewDroite;

    private Bitmap image;
    private Uri imageUri;

    private int indiceImg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();
    }

    // Initialisation de l'activity
    private void initActivity() {
        listeImage = imageReader(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));

        // récupération des objets graphiques
        btnPrendrePhoto = (ImageButton)findViewById(R.id.btnPrendrePhoto);
        btnSupprimerPhoto = (ImageButton)findViewById(R.id.btnSupprimer);
        btnGauche       = (ImageButton)findViewById(R.id.btnGauche);
        btnDroite       = (ImageButton)findViewById(R.id.btnDroite);

        imgAffichePhoto = (ImageView)findViewById(R.id.imgAffichePhoto);
        previewDroite = (ImageView)findViewById(R.id.previewDroite);
        previewGauche = (ImageView)findViewById(R.id.previewGauche);

        // méthode qui va gérer les événements
        createOnClickBtnPrendrePhoto();
        createOnClickBtnSupprimerPhoto();
        changeOnClickBtnChangeImage();

        if (listeImage.size() > 0) {
            imgAffichePhoto.setImageURI(Uri.parse(listeImage.get(indiceImg).toString()));
            if (listeImage.size() > 1) {
                previewDroite.setImageURI(Uri.parse(listeImage.get(indiceImg + 1).toString()));
                previewGauche.setImageURI(Uri.parse(listeImage.get(listeImage.size() - 1).toString()));
            }
        }

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    ArrayList<File> imageReader(File root) {
        ArrayList<File> a = new ArrayList<>();

        File[] files = root.listFiles();
        for(int i = 0; i < files.length; i++){
            if (files[i].isDirectory()) {

            } else {
                if (files[i].getName().endsWith(".jpg")) {
                    a.add(files[i]);
                }
            }
        }
        return a;
    }

    private void createOnClickBtnPrendrePhoto() {
        btnPrendrePhoto.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                prendrePhoto();
            }
        });
    }

    private void createOnClickBtnSupprimerPhoto() {
        btnSupprimerPhoto.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                supprimerPhoto();
            }
        });
    }

    private void changeOnClickBtnChangeImage() {
        btnGauche.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listeImage.size() > 0) {
                            if (indiceImg > 0) {
                                indiceImg--;
                            } else {
                                indiceImg = listeImage.size() - 1;
                            }

                            imgAffichePhoto.setImageURI(Uri.parse(listeImage.get(indiceImg).toString()));
                            previewDroite.setImageResource(R.mipmap.ic_launcher);
                            previewGauche.setImageResource(R.mipmap.ic_launcher);
                            if (indiceImg + 1 == listeImage.size()) {
                                if (listeImage.size() > 1) {
                                    previewDroite.setImageURI(Uri.parse(listeImage.get(0).toString()));
                                    previewGauche.setImageURI(Uri.parse(listeImage.get(indiceImg - 1).toString()));
                                }
                            } else if (indiceImg == 0) {
                                if (listeImage.size() > 1) {
                                    previewDroite.setImageURI(Uri.parse(listeImage.get(indiceImg + 1).toString()));
                                    previewGauche.setImageURI(Uri.parse(listeImage.get(listeImage.size() - 1).toString()));
                                }
                            } else {
                                if (listeImage.size() > 1) {
                                    previewDroite.setImageURI(Uri.parse(listeImage.get(indiceImg + 1).toString()));
                                    previewGauche.setImageURI(Uri.parse(listeImage.get(indiceImg - 1).toString()));
                                }
                            }
                        }
                    }
                }
        );
        btnDroite.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listeImage.size() > 0) {
                            indiceImg++;
                            indiceImg = indiceImg % listeImage.size();

                            imgAffichePhoto.setImageURI(Uri.parse(listeImage.get(indiceImg).toString()));
                            previewDroite.setImageResource(R.mipmap.ic_launcher);
                            previewGauche.setImageResource(R.mipmap.ic_launcher);
                            if (indiceImg + 1 == listeImage.size()) {
                                if (listeImage.size() > 1) {
                                    previewDroite.setImageURI(Uri.parse(listeImage.get(0).toString()));
                                    previewGauche.setImageURI(Uri.parse(listeImage.get(indiceImg - 1).toString()));
                                }
                            } else if (indiceImg == 0) {
                                if (listeImage.size() > 1) {
                                    previewDroite.setImageURI(Uri.parse(listeImage.get(indiceImg + 1).toString()));
                                    previewGauche.setImageURI(Uri.parse(listeImage.get(listeImage.size() - 1).toString()));
                                }
                            } else {
                                if (listeImage.size() > 1) {
                                    previewDroite.setImageURI(Uri.parse(listeImage.get(indiceImg + 1).toString()));
                                    previewGauche.setImageURI(Uri.parse(listeImage.get(indiceImg - 1).toString()));
                                }
                            }
                        }
                    }
                }
        );
    }

    // accés à l'appareil photo puis elle mémorise la photo temporairement
    private void prendrePhoto() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Photo_" + timeStamp + ".jpg");
        imageUri = Uri.fromFile(file);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, RETOUR_PRENDRE_PHOTO);
    }

    private void supprimerPhoto() {
        if (listeImage.size() > 0) {
            listeImage.get(indiceImg).delete();
            listeImage.remove(indiceImg);
        }
        imgAffichePhoto.setImageResource(R.mipmap.ic_launcher);
        if (listeImage.size() > 0) {
            if (indiceImg - 1 < 0) {
                indiceImg = listeImage.size() - 1;
            } else {
                indiceImg--;
            }
            previewDroite.setImageResource(R.mipmap.ic_launcher);
            previewGauche.setImageResource(R.mipmap.ic_launcher);
            imgAffichePhoto.setImageURI(Uri.parse(listeImage.get(indiceImg).toString()));
            if (indiceImg + 1 == listeImage.size()) {
                if (listeImage.size() > 1) {
                    previewDroite.setImageURI(Uri.parse(listeImage.get(0).toString()));
                    previewGauche.setImageURI(Uri.parse(listeImage.get(indiceImg - 1).toString()));
                }
            } else if (indiceImg == 0) {
                if (listeImage.size() > 1) {
                    previewDroite.setImageURI(Uri.parse(listeImage.get(indiceImg + 1).toString()));
                    previewGauche.setImageURI(Uri.parse(listeImage.get(listeImage.size() - 1).toString()));
                }
            } else {
                if (listeImage.size() > 1) {
                    previewDroite.setImageURI(Uri.parse(listeImage.get(indiceImg + 1).toString()));
                    previewGauche.setImageURI(Uri.parse(listeImage.get(indiceImg - 1).toString()));
                }
            }
        }
    }

    // retour de l'appel de l'appereil photo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RETOUR_PRENDRE_PHOTO:
                if (requestCode == RETOUR_PRENDRE_PHOTO)
                    if (resultCode == Activity.RESULT_OK) {
                        try {
                            image = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                            System.out.println(imageUri);
                            System.out.println(getContentResolver());
                            System.out.println(image);
                            imgAffichePhoto.setImageBitmap(image);

                            listeImage = imageReader(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));

                            previewDroite.setImageResource(R.mipmap.ic_launcher);
                            previewGauche.setImageResource(R.mipmap.ic_launcher);
                            indiceImg = 0;
                            if (indiceImg + 1 == listeImage.size()) {
                                if (listeImage.size() > 1) {
                                    previewDroite.setImageURI(Uri.parse(listeImage.get(0).toString()));
                                    previewGauche.setImageURI(Uri.parse(listeImage.get(indiceImg - 1).toString()));
                                }
                            } else if (indiceImg == 0) {
                                if (listeImage.size() > 1) {
                                    previewDroite.setImageURI(Uri.parse(listeImage.get(indiceImg + 1).toString()));
                                    previewGauche.setImageURI(Uri.parse(listeImage.get(listeImage.size() - 1).toString()));
                                }
                            } else {
                                if (listeImage.size() > 1) {
                                    previewDroite.setImageURI(Uri.parse(listeImage.get(indiceImg + 1).toString()));
                                    previewGauche.setImageURI(Uri.parse(listeImage.get(indiceImg - 1).toString()));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
        }
    }
}
