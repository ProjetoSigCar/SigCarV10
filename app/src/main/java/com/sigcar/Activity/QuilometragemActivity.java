package com.sigcar.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.sigcar.Classes.Kilometragem;
import com.sigcar.DAO.ConfiguracaoFirebase;
import com.sigcar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuilometragemActivity extends AppCompatActivity {

    ListView listViewArtists;
    Spinner spinnerVeiculos;
    Spinner spinnerKilometragem;
    DatabaseReference databaseArtists;
    HashMap<String,List<String>> veiculos = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quilometragem);

        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();

        listViewArtists = (ListView) findViewById(R.id.listViewArtists);
        spinnerVeiculos = (Spinner) findViewById(R.id.spinnerVeiculos);
        spinnerKilometragem =(Spinner)  findViewById(R.id.spinnerKilometragem);

        firebase.child("Veiculo").getParent().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                veiculos = new  HashMap<String,List<String>> ();

                List<String>  modelos = new ArrayList<>();

                List<String>  kms = new ArrayList<>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {

                    for(DataSnapshot veiculo : areaSnapshot.getChildren()){

                        modelos.add(veiculo.getKey());

                        for(DataSnapshot km : veiculo.getChildren()) {
                              kms.add( km.child("km").getValue().toString());
                        }

                        veiculos.put(veiculo.getKey(),kms);

                          kms = new ArrayList<>();

                    }


                }

                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(QuilometragemActivity.this, android.R.layout.simple_spinner_item, modelos);

                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerVeiculos.setAdapter(areasAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        spinnerVeiculos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

             String marca = (String) parent.getItemAtPosition(position);

                 ArrayAdapter<String>   adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, veiculos.get(marca));

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerKilometragem.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


  /*      listViewArtists.setOnItemLongClickListener((adapterView, view, i, l) -> {
            Artist artist = artistList.get(i);
            new AlertDialog.Builder(this)
                    .setTitle("Delete " + artist.getArtistName() + " "
                    )
                    .setMessage("Você deseja excluir o registro selecionado?")
                    .setPositiveButton("Delete", (dialogInterface, i1) -> {
                        databaseArtists.child(artist.getArtistId()).removeValue();

                        Toast.makeText(this, artist.getArtistName() + ""+" Excluido com Sucesso", Toast.LENGTH_LONG).show();


                    })
                    .setNegativeButton("Cancel", (dialogInterface, i12) -> {
                        dialogInterface.dismiss();
                    })
                    .create()
                    .show();
            return true;
        });
*/
    }

}