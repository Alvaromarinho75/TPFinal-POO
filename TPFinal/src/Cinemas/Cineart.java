package Cinemas;

public class Cineart extends Cinema {
    private static Cineart cineart;

    private Cineart(String nome, String endereco) {
        super(nome, endereco);
    }

    public static Cineart getCineart() {
        if (cineart == null) {
        cineart = new Cineart("Cineart", "Rua um, 103");
        }
        return cineart;
    }
}
