package controller;

import view.Inicio;

public class Main {
    public static void main(String[] args) {
        Inicio vista = new Inicio();
        new Controller(vista);
    }
}
