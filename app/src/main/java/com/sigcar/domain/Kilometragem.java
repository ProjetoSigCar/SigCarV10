package com.sigcar.domain;

import java.util.List;

public abstract class Kilometragem {

        public String sobrenome;

        public String nome;

        public int idade;

        public String estado;

        public String email;

        public String km;

    @Override
    public String toString() {
        return "Kilometragem{" +
                "km='" + km + '\'' +
                '}';
    }
}
