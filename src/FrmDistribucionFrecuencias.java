import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class FrmDistribucionFrecuencias extends JFrame {

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

        JComboBox cmbRespuesta = new JComboBox();
        cmbRespuesta.setBounds(110, 65, 100, 25);
        add(cmbRespuesta);

        String[] opciones = { "Excelente", "Buena", "Regular", "Mala" };
        cmbRespuesta.setModel(new DefaultComboBoxModel(opciones));

        JButton btnAgregar = new JButton(">>");
        btnAgregar.setBounds(10, 95, 100, 25);
        add(btnAgregar);

        JButton btnQuitar = new JButton("<<");
        btnQuitar.setBounds(10, 125, 100, 25);
        add(btnQuitar);

        JList lstRespuestas = new JList();
        lstRespuestas.setBounds(110, 95, 100, 100);
        add(lstRespuestas);

        JTable tblDistribucion = new JTable();
        JScrollPane spDistribucion = new JScrollPane(tblDistribucion);
        spDistribucion.setBounds(10, 200, 450, 100);
        add(spDistribucion);

        String[] encabezados = { "Variable", "Frecuencia absoluta (f)", "Frecuencia acumulada (F)",
                "Frecuencia relativa (fr)", "Frecuencia porcentual (%f)" };

        tblDistribucion.setModel(new DefaultTableModel(null, encabezados));
    }
}
