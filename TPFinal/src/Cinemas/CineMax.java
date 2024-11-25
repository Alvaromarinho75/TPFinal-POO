package Cinemas;

public class CineMax extends Cinema {
    private static CineMax cineMax;

    private CineMax(String nome, String endereco) {
        super(nome, endereco);
    }

    public static CineMax getCineMax() {
        if (cineMax == null) {
            cineMax = new CineMax("CineMax", "Rua Brasil, 36");
        }
        return cineMax;
    }
}
