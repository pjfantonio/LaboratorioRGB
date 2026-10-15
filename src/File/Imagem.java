package File;

import java.io.File;

public class Imagem {
    public static void main(String[] args) {

        File ficheiro = new File("fotos/imagem.jpg");
        //ficheiro.exists();

        long totalSpace = ficheiro.getTotalSpace();
        System.out.println(totalSpace);
    }
}
