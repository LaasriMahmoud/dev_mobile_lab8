package com.example.projetws;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import com.example.projetws.beans.Etudiant;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AddEtudiant extends AppCompatActivity implements View.OnClickListener {

    private EditText nom, prenom;
    private Spinner ville;
    private RadioButton m, f;
    private Button add;
    private RequestQueue requestQueue;

    private static final String insertUrl =
            "http://10.0.2.2:8080/projet/ws/createEtudiant.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_etudiant);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        ville = findViewById(R.id.ville);
        m = findViewById(R.id.m);
        f = findViewById(R.id.f);
        add = findViewById(R.id.add);

        requestQueue = Volley.newRequestQueue(this);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add) {
            envoyerEtudiant();
        }
    }

    private void envoyerEtudiant() {

        StringRequest request = new StringRequest(
                Request.Method.POST,
                insertUrl,
                response -> {
                    Log.d("RESPONSE", response);

                    Type type = new TypeToken<Collection<Etudiant>>(){}.getType();
                    Collection<Etudiant> etudiants = new Gson().fromJson(response, type);

                    for (Etudiant e : etudiants) {
                        Log.d("ETUDIANT", e.toString());
                    }
                },
                error -> {
                    Log.e("VOLLEY", "Erreur Volley", error);

                    // Pour voir le vrai message retourné (PHP error, 404, etc.)
                    if (error.networkResponse != null && error.networkResponse.data != null) {
                        Log.e("VOLLEY", "Status: " + error.networkResponse.statusCode);
                        Log.e("VOLLEY", "Body: " + new String(error.networkResponse.data));
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                String sexe = m.isChecked() ? "homme" : "femme";

                Map<String, String> params = new HashMap<>();
                params.put("nom", nom.getText().toString().trim());
                params.put("prenom", prenom.getText().toString().trim());
                params.put("ville", ville.getSelectedItem().toString().trim());
                params.put("sexe", sexe);
                return params;
            }
        };

        requestQueue.add(request);
    }
}