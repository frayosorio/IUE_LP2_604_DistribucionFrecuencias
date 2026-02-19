import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class FrmDistribucionFrecuencias extends JFrame {
    private JComboBox cmbRespuesta;
    private JList lstRespuestas;
    private String[] opciones = { "Excelente", "Buena", "Regular", "Mala" };
    private JTable tblDistribucion;
    String[] encabezados = { "Variable", "Frecuencia absoluta (f)", "Frecuencia acumulada (F)",
            "Frecuencia relativa (fr)", "Frecuencia porcentual (%f)" };

    // metodo constructor
    public FrmDistribucionFrecuencias() {
        setTitle("Tabla de Distribución de Frecuencias");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTituloPregunta = new JLabel("Pregunta:");
        lblTituloPregunta.setBounds(10, 10, 100, 25);
        add(lblTituloPregunta);

        JTextArea txtPregunta = new JTextArea(
                "¿Cómo considera la calidad de la señal de internet que entra al barrio?");
        txtPregunta.setBounds(110, 10, 350, 50);
        txtPregunta.setEditable(false);
        txtPregunta.setLineWrap(true);
        add(txtPregunta);

        JLabel lblTituloRespuesta = new JLabel("Respuesta:");
        lblTituloRespuesta.setBounds(10, 65, 100, 25);
        add(lblTituloRespuesta);

        cmbRespuesta = new JComboBox();
        cmbRespuesta.setBounds(110, 65, 100, 25);
        add(cmbRespuesta);

        cmbRespuesta.setModel(new DefaultComboBoxModel(opciones));

        JButton btnAgregar = new JButton(">>");
        btnAgregar.setBounds(10, 95, 100, 25);
        add(btnAgregar);

        JButton btnQuitar = new JButton("<<");
        btnQuitar.setBounds(10, 125, 100, 25);
        add(btnQuitar);

        lstRespuestas = new JList();
        JScrollPane spRespuestas = new JScrollPane(lstRespuestas);
        spRespuestas.setBounds(110, 95, 100, 100);
        add(spRespuestas);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(10, 200, 100, 25);
        add(btnCalcular);

        tblDistribucion = new JTable();
        JScrollPane spDistribucion = new JScrollPane(tblDistribucion);
        spDistribucion.setBounds(10, 240, 450, 100);
        add(spDistribucion);

        tblDistribucion.setModel(new DefaultTableModel(null, encabezados));

        // Eventos
        btnAgregar.addActionListener(e -> {
            agregarRespuesta();
        });
        btnQuitar.addActionListener(e -> {
            quitarRespuesta();
        });
        btnCalcular.addActionListener(e -> {
            calcularDistribucion();
        });
    }

    private String[] respuestas = new String[1000];
    private int totalRespuestas = -1;

    private void agregarRespuesta() {
        totalRespuestas++;
        respuestas[totalRespuestas] = cmbRespuesta.getSelectedItem().toString();
        mostrarRespuestas();
    }

    private void mostrarRespuestas() {
        String[] respuestasAMostrar = new String[totalRespuestas + 1];
        // recorrer todas las respuestas agregadas
        for (int i = 0; i <= totalRespuestas; i++) {
            respuestasAMostrar[i] = respuestas[i];
        }
        lstRespuestas.setListData(respuestasAMostrar);
    }

    private void quitarRespuesta() {
        if (lstRespuestas.getSelectedIndex() >= 0) {
            for (int i = lstRespuestas.getSelectedIndex(); i < totalRespuestas; i++) {
                respuestas[i] = respuestas[i + 1];
            }
            totalRespuestas--;
            mostrarRespuestas();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
        }
    }

    private void calcularDistribucion() {
        // calcular la tabla de frecuencias
        double[][] tablaFrecuencias = new double[opciones.length][4];
        // ***** Calculo de la frecuencia absoluta *****
        // recorrer todas las respuestas agregadas
        for (int i = 0; i <= totalRespuestas; i++) {
            // recorrer todas las opciones
            for (int j = 0; j < opciones.length; j++) {
                if (respuestas[i].equals(opciones[j])) {
                    tablaFrecuencias[j][0]++;
                    break;
                }
            }
        }
        // Mostrar y terminar de calcular la tabla de frecuencias
        String[][] tablaFrecuenciasMostrar = new String[opciones.length][5];
        for (int i = 0; i < opciones.length; i++) {
            // ***** calcular la frecuencia acumulada *****
            if (i == 0) {
                tablaFrecuencias[i][1] = tablaFrecuencias[i][0];
            } else {
                tablaFrecuencias[i][1] = tablaFrecuencias[i][0] + tablaFrecuencias[i - 1][1];
            }

            // ***** calcular la frecuencia relativa *****
            tablaFrecuencias[i][2] = tablaFrecuencias[i][0] / (totalRespuestas + 1);

            // ***** calcular la frecuencia porcentual *****
            tablaFrecuencias[i][3] = tablaFrecuencias[i][2] * 100;

            // asignar a matriz de resultados
            tablaFrecuenciasMostrar[i][0] = opciones[i];
            tablaFrecuenciasMostrar[i][1] = String.valueOf(tablaFrecuencias[i][0]);
            tablaFrecuenciasMostrar[i][2] = String.valueOf(tablaFrecuencias[i][1]);
            tablaFrecuenciasMostrar[i][3] = String.valueOf(tablaFrecuencias[i][2]);
            tablaFrecuenciasMostrar[i][4] = String.valueOf(tablaFrecuencias[i][3]);
        }
        DefaultTableModel dtm = new DefaultTableModel(tablaFrecuenciasMostrar, encabezados);
        tblDistribucion.setModel(dtm);

    }
}
