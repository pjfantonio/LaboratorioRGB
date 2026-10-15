package File;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class VisaoComputacional {
    public static void main(String[] args) {

      try {
          File ficheiro = new File("src/FIle/fotos/imagem2.jpg");

          BufferedImage imagem = ImageIO.read(ficheiro);
          if(imagem == null){
              System.out.println("O formato da imagem não suportado!");
              return;
          }
          int largura = imagem.getWidth();
          int altura = imagem.getHeight();

          int cor = imagem.getRGB(0, 0);
          int vermelho = (cor >> 16) & 0xFF;
          int verde = (cor >> 8) & 0xFF;
          int azul = cor & 0xFF;

          System.out.println("Anãlise de Imagem!");
          System.out.println("------------------");
          System.out.println("Largura: " +largura + " pixels");
          System.out.println("Altura: " +altura + " pixels");
          System.out.println("Total de pixel  s: " + (long) largura * altura +" pixels");
          System.out.println("Cor: " +cor);
          System.out.println("Vermelho: " +vermelho);
          System.out.println("Verde: " +verde);
          System.out.println("Azul: " +azul);

      } catch (Exception e) {
          System.out.println("Erro ao Ler a Imagem! " +e.getMessage());
      }
    }
}