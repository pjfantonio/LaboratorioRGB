
import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class LaboratorioRGB extends JFrame {

    private final JSlider sliderVermelho;
    private final JSlider sliderVerde;
    private final JSlider sliderAzul;

    private final JPanel painelCor;
    private final JLabel etiquetaRGB;
    private final JLabel etiquetaHexadecimal;

    public LaboratorioRGB() {
        setTitle("Laboratório RGB - Processamento de Imagem");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Valores iniciais dos canais RGB
        sliderVermelho = criarSlider(51);
        sliderVerde = criarSlider(102);
        sliderAzul = criarSlider(204);

        // Painel que apresenta a cor em tempo real
        painelCor = new JPanel();
        painelCor.setPreferredSize(new Dimension(300, 180));
        painelCor.setBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 2)
        );

        etiquetaRGB = new JLabel("", SwingConstants.CENTER);
        etiquetaHexadecimal = new JLabel("", SwingConstants.CENTER);

        etiquetaRGB.setFont(new Font("Arial", Font.BOLD, 18));
        etiquetaHexadecimal.setFont(new Font("Monospaced", Font.BOLD, 20));

        // Organização dos componentes
        JPanel painelControlos = new JPanel(new GridLayout(3, 1, 5, 5));
        painelControlos.setBorder(
                BorderFactory.createTitledBorder("Controlos RGB")
        );

        painelControlos.add(criarControlo(
                "Vermelho (R)", sliderVermelho, new Color(220, 40, 40)
        ));

        painelControlos.add(criarControlo(
                "Verde (G)", sliderVerde, new Color(30, 150, 50)
        ));

        painelControlos.add(criarControlo(
                "Azul (B)", sliderAzul, new Color(30, 100, 220)
        ));

        JPanel painelInformacao = new JPanel(new GridLayout(2, 1, 5, 5));
        painelInformacao.add(etiquetaRGB);
        painelInformacao.add(etiquetaHexadecimal);

        JPanel painelCentral = new JPanel(new BorderLayout(10, 10));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        painelCentral.add(painelCor, BorderLayout.CENTER);
        painelCentral.add(painelInformacao, BorderLayout.SOUTH);

        add(painelControlos, BorderLayout.NORTH);
        add(painelCentral, BorderLayout.CENTER);

        // Actualizar a cor sempre que um slider mudar
        ChangeListener actualizador = evento -> actualizarCor();

        sliderVermelho.addChangeListener(actualizador);
        sliderVerde.addChangeListener(actualizador);
        sliderAzul.addChangeListener(actualizador);

        actualizarCor();
        setVisible(true);
    }

    private JSlider criarSlider(int valorInicial) {
        JSlider slider = new JSlider(0, 255, valorInicial);
        slider.setMajorTickSpacing(51);
        slider.setMinorTickSpacing(17);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    private JPanel criarControlo(
            String nome, JSlider slider, Color corTitulo) {

        JPanel painel = new JPanel(new BorderLayout(5, 5));
        JLabel etiqueta = new JLabel(nome);

        etiqueta.setForeground(corTitulo);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 14));

        painel.add(etiqueta, BorderLayout.NORTH);
        painel.add(slider, BorderLayout.CENTER);

        return painel;
    }

    private void actualizarCor() {
        int vermelho = sliderVermelho.getValue();
        int verde = sliderVerde.getValue();
        int azul = sliderAzul.getValue();

        // Construir a cor a partir dos três canais
        Color cor = new Color(vermelho, verde, azul);

        // Mostrar a cor e os seus valores
        painelCor.setBackground(cor);

        etiquetaRGB.setText(
                "RGB: (" + vermelho + ", " + verde + ", " + azul + ")"
        );

        etiquetaHexadecimal.setText(
                String.format("#%02X%02X%02X", vermelho, verde, azul)
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LaboratorioRGB::new);
    }
}
