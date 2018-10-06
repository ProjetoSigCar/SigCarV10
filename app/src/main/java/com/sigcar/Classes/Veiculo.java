package com.sigcar.Classes;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.sigcar.DAO.ConfiguracaoFirebase;

import java.util.List;

public class Veiculo {



  private String nome;

    private List<Kilometragem> kilometragemList;

    public void Loading(String Veiculo) {
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        nome = Veiculo;
        firebase.child( "Veiculo" ).child( Veiculo ).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Veiculo objAuxVeiculo  = dataSnapshot.getValue(Veiculo.class);
                kilometragemList = objAuxVeiculo.kilometragemList;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Kilometragem> getKilometragemList() {
        return kilometragemList;
    }

    public void setKilometragemList(List<Kilometragem> kilometragemList) {
        this.kilometragemList = kilometragemList;
    }
}
