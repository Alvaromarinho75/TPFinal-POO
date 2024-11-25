package Cinemas;

public class Cinepolis extends Cinema {
    private static Cinepolis cinepolis;

    private Cinepolis(String nome, String endereco) {
        super(nome, endereco);
    }

    public static Cinepolis getCinepolis() {
        if (cinepolis == null) {
            cinepolis = new Cinepolis("Cinepolis", "Rua Perfume, 203");
        }
        return cinepolis;
    }
}
