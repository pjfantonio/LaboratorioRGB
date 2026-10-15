
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LaboratorioImagemRGB extends JFrame {

    private BufferedImage imagem;
    private final JLabel etiquetaImagem;
    private final JLabel etiquetaRGB;
    private final JLabel etiquetaHex;
    private final JPanel painelCor;

    public LaboratorioImagemRGB() {
        setTitle("Laboratório de Análise RGB");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton botaoAbrir = new JButton("Abrir fotografia");

        etiquetaImagem = new JLabel("Abre uma fotografia para começar.");
        etiquetaImagem.setHorizontalAlignment(SwingConstants.CENTER);

        etiquetaRGB = new JLabel("RGB: —");
        etiquetaHex = new JLabel("Hexadecimal: —");

        painelCor = new JPanel();
        painelCor.setPreferredSize(new Dimension(100, 50));
        painelCor.setBackground(Color.WHITE);
        painelCor.setBorder(
                BorderFactory.createLineBorder(Color.BLACK)
        );

        JPanel painelInformacao = new JPanel(new FlowLayout());
        painelInformacao.add(etiquetaRGB);
        painelInformacao.add(etiquetaHex);
        painelInformacao.add(painelCor);

        JPanel painelSuperior = new JPanel();
        painelSuperior.add(botaoAbrir);

        JScrollPane scroll = new JScrollPane(etiquetaImagem);

        add(painelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(painelInformacao, BorderLayout.SOUTH);

        botaoAbrir.addActionListener(e -> abrirImagem());

        etiquetaImagem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                analisarPixel(e.getX(), e.getY());
            }
        });

        setVisible(true);
    }

    private void abrirImagem() {
        JFileChooser selector = new JFileChooser();

        int resultado = selector.showOpenDialog(this);

        if (resultado != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File ficheiro = selector.getSelectedFile();

        try {
            imagem = ImageIO.read(ficheiro);

            if (imagem == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "O ficheiro seleccionado não é uma imagem suportada."
                );
                return;
            }

            ImageIcon icone = new ImageIcon(imagem);
            etiquetaImagem.setIcon(icone);
            etiquetaImagem.setText("");

            etiquetaRGB.setText("RGB: Clique na fotografia");
            etiquetaHex.setText("Hexadecimal: —");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível abrir a imagem: " + ex.getMessage()
            );
        }
    }

    private void analisarPixel(int x, int y) {
        if (imagem == null) {
            return;
        }

        // Converter as coordenadas do clique para as coordenadas
        // originais da imagem.
        Icon icone = etiquetaImagem.getIcon();

        if (icone == null) {
            return;
        }

        int larguraMostrada = icone.getIconWidth();
        int alturaMostrada = icone.getIconHeight();

        int inicioX = (etiquetaImagem.getWidth() - larguraMostrada) / 2;
        int inicioY = (etiquetaImagem.getHeight() - alturaMostrada) / 2;

        int pixelX = x - inicioX;
        int pixelY = y - inicioY;

        if (pixelX < 0 || pixelY < 0
                || pixelX >= imagem.getWidth()
                || pixelY >= imagem.getHeight()) {
            return;
        }

        int cor = imagem.getRGB(pixelX, pixelY);

        int vermelho = (cor >> 16) & 0xFF;
        int verde = (cor >> 8) & 0xFF;
        int azul = cor & 0xFF;

        Color corSelecionada = new Color(
                vermelho, verde, azul
        );

        etiquetaRGB.setText(
                "RGB: (" + vermelho + ", "
                        + verde + ", " + azul + ")"
        );

        etiquetaHex.setText(
                String.format(
                        "Hexadecimal: #%02X%02X%02X",
                        vermelho, verde, azul
                )
        );

        painelCor.setBackground(corSelecionada);

        setTitle(
                "Laboratório RGB — Píxel X: "
                        + pixelX + ", Y: " + pixelY
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LaboratorioImagemRGB::new);
    }
}
