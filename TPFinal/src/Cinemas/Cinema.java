package Cinemas;

import Excessoes.*;
import Recursos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Cinema {
    private static int proximoId = 1;
    private static List<Cinema> listaCinemas = new ArrayList<>();

    private int id;
    private String nome;
    private String endereco;
    private List<Sala> salas;

    public Cinema(String nome, String endereco) {
        this.id = proximoId++;
        this.nome = nome;
        this.endereco = endereco;
        this.salas = new ArrayList<>();
        listaCinemas.add(this);
    }

    @Override
    public String toString() {
        String resultado = "\nCinema \n" +
                "ID: " + id + '\n' +
                "Nome: " + nome + '\n' +
                "Endereço: " + endereco + '\n' +
                "Salas:\n" + '\n';

        if (salas.isEmpty()) {
            resultado += "  Nenhuma sala cadastrada\n";
        } else {
            for (Sala sala : salas) {
                resultado += "  " + sala.toString() + '\n';
            }
        }
        return resultado;
    }



    public void criarSala() throws nomeDuplicadoException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome da sala: ");
        String nomeSala = scanner.nextLine();

        for(Sala sala : this.salas){
            if(sala.getNome().equals(nomeSala)){
                throw new nomeDuplicadoException("Ja existe uma sala com esse nome!");
            }
        }

        System.out.print("Digite a capacidade da sala: ");
        int capacidadeSala = scanner.nextInt();

        Sala novaSala = new Sala(nomeSala, capacidadeSala, this);

        salas.add(novaSala);

        System.out.println("Sala criada com sucesso!");
        System.out.println(novaSala);
    }

    public static void listarCinemas() {
        if (listaCinemas.isEmpty()) {
            System.out.println("Nenhum cinema cadastrado.");
        } else {
            System.out.println("Lista de Cinemas:");
            for (Cinema cinema : listaCinemas) {
                System.out.println(cinema);
            }
        }
    }

    public void listarSalas() {
        if (salas.isEmpty()) {
            System.out.println("Nenhuma sala cadastrada.");
        } else {
            System.out.println("Lista de Salas:");
            for (Sala sala : salas) {
                System.out.println(sala);
            }
        }
    }

    public static List<Cinema> getListaCinemas() {return listaCinemas;}
    public List<Sala> getSalas() {return salas;}
    public Sala getSala(int id){return salas.get(id);}
    public int getId() {return id;}
    public String getNome() {return nome;}
    public String getEndereco() {return endereco;}
}
