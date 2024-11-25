package Cinemas;

public class Cinemark extends Cinema {
    private static Cinemark cinemark;

    private Cinemark(String nome, String endereco) {
        super(nome, endereco);
    }

    public static Cinemark getCinemark() {
        if (cinemark == null) {
            cinemark = new Cinemark("Cinemark", "Rua Moeda, 86");
        }
        return cinemark;
    }
}
