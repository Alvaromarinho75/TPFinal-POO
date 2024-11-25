package Recursos;

import java.io.Serializable;

public class Ingresso implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int contadorId = 1;

    private int id;
    private Filme filme;
    private double preco;
    private String portador;

    public Ingresso(Filme filme, double preco, String nome) {
        this.id = contadorId++;
        this.filme = filme;
        this.preco = preco;
        this.portador = nome;
    }

    @Override
    public String toString() {
        return "Ingresso{" +
                "Dono: " + portador + '\n' +
                "Filme: " + filme.getTitulo() + '\n' +
                "Preco: RS" + preco +
                '}';
    }

    public int getId() { return id; }
    public Filme getFilme() { return filme; }
    public double getPreco() { return preco; }
}
