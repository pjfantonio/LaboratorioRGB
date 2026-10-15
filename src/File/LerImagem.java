package File;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class LerImagem {
    public  static void main(String[] args) throws Exception{
        File ficheiro = new File("src/File/fotos/imagem.jpg");
        if(!ficheiro.exists()){
            System.out.println("Imagem não encontrada!");
        }

        BufferedImage imagem = ImageIO.read(ficheiro);
        if(imagem == null){
            System.out.println("O formato não suportado");
        }
        System.out.println("Imagem carregada!");
        System.out.println("Largura: " +imagem.getWidth());
        System.out.println("Largura: " +imagem.getHeight());

    }
}
