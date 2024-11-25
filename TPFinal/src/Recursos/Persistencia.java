package Recursos;

import java.io.*;
import java.util.ArrayList;

public class Persistencia {
    public static <T> void salvarDados(String arquivo, ArrayList<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public static <T> ArrayList<T> carregarDados(String arquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado, inicializando lista vazia.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
