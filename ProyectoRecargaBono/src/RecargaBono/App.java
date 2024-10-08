package RecargaBono;

import com.toedter.calendar.JMonthChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class App extends JFrame {

    private JLabel labelInstruccion;
    private JLabel labelCantidad;
    private JMonthChooser monthChooser;
    private JButton btnRecargar;
    private JTextField txtCantidad;  // Campo de texto para la cantidad a recargar

    // Arreglo con los nombres de los meses en español
    private String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    // Panel personalizado para mostrar la imagen de fondo
    class FondoPanel extends JPanel {
        private BufferedImage imagen;

        public FondoPanel() {
            try {
                // Cargar la imagen de fondo desde el paquete Imgs
                imagen = ImageIO.read(getClass().getResource("/Imgs/foto_fondo.jpg"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dibujar la imagen escalada para que se ajuste al tamaño del panel
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public App() {
        // Configuración del JFrame
        setTitle("Recarga de Abono de Transporte");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centrar la ventana

        // Crear el panel de fondo
        FondoPanel fondoPanel = new FondoPanel();
        fondoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));  // Usar FlowLayout para centrar componentes

        // Crear los componentes
        labelInstruccion = new JLabel("Seleccione el mes para recargar su abono:");
        monthChooser = new JMonthChooser();
        labelCantidad = new JLabel("Cantidad en euros:");
        txtCantidad = new JTextField(10);  // Campo de texto para que el usuario introduzca la cantidad
        btnRecargar = new JButton("Recargar Abono");

        // Establecer colores personalizados
        Color textColor = new Color(70, 70, 70);   // Texto oscuro

        // Establecer colores de fondo y texto
        labelInstruccion.setForeground(textColor); // Color de texto de la instrucción
        labelCantidad.setForeground(textColor);    // Color de texto de la cantidad
        txtCantidad.setBackground(Color.WHITE);    // Fondo blanco para la caja de texto
        btnRecargar.setBackground(new Color(100, 149, 237)); // Color azul para el botón
        btnRecargar.setForeground(Color.WHITE);    // Texto blanco en el botón

        // Agregar componentes al panel de fondo
        fondoPanel.add(labelInstruccion);
        fondoPanel.add(monthChooser);
        fondoPanel.add(labelCantidad);  // Etiqueta para la cantidad
        fondoPanel.add(txtCantidad);  // Campo de texto para la cantidad
        fondoPanel.add(btnRecargar);

        // Agregar el panel de fondo al JFrame
        getContentPane().add(fondoPanel);

        // Ajustar el tamaño de la ventana según la imagen
        setSize(370, 423); // Ajusta el tamaño según sea necesario
        setVisible(true); // Hacer visible la ventana

        // Añadir funcionalidad al botón
        btnRecargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el índice del mes seleccionado
                int mesSeleccionado = monthChooser.getMonth();

                // Obtener la cantidad introducida por el usuario
                String cantidad = txtCantidad.getText();

                // Validar si la cantidad es numérica
                if (cantidad.isEmpty() || !cantidad.matches("\\d+(\\.\\d{1,2})?")) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduzca una cantidad válida en euros.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Mostrar un cuadro de diálogo confirmando la recarga
                    JOptionPane.showMessageDialog(null,
                            "Su abono de transporte ha sido recargado para el mes de: " + meses[mesSeleccionado]
                                    + "\nCantidad recargada: " + cantidad + " euros",
                            "Recarga Confirmada", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        // Crear la ventana de recarga de abono y hacerla visible
        App ventana = new App();
        ventana.setVisible(true);
    }
}
