package Vista;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import Dao.Daoprovedor;
import Modelo.Provedor;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class vProvedor extends JFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtmodelo;
	private JTextField txtmarca;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnBorrar;
	Daoprovedor dao=new Daoprovedor();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblProvedor;
	ArrayList<Provedor> lista = new ArrayList<Provedor>();
	int fila=-1;
	Provedor Provedor;
	private JLabel lblNewLabel_2;
	private JTextField txtprecio;
	private JTextField txtcantidad;
	private JButton btnPdf;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vProvedor frame = new vProvedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblid.setText("");
		txtmodelo.setText("");
		txtmarca.setText("");
	}
	

	public vProvedor() {
		setLocationRelativeTo(null);
		setTitle("CRUDProvedor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(20, 26, 33, 23);
		contentPane.add(lblNewLabel);
		
		lblid = new JLabel("1");
		lblid.setHorizontalAlignment(SwingConstants.LEFT);
		lblid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblid.setBounds(106, 26, 86, 23);
		contentPane.add(lblid);
		
		JLabel lblNewLabel_1 = new JLabel("Precio");
		lblNewLabel_1.setBounds(227, 26, 60, 23);
		contentPane.add(lblNewLabel_1);
		
		txtmodelo = new JTextField();
		txtmodelo.setBounds(106, 95, 86, 20);
		contentPane.add(txtmodelo);
		txtmodelo.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Modelo");
		lblNewLabel_1_1.setBounds(20, 94, 86, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Marca");
		lblNewLabel_1_2.setBounds(10, 60, 86, 23);
		contentPane.add(lblNewLabel_1_2);
		
		txtmarca = new JTextField();
		txtmarca.setColumns(10);
		txtmarca.setBounds(106, 63, 86, 20);
		contentPane.add(txtmarca);
		
		btnAgregar = new JButton("Agregar");
		
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtmodelo.getText().equals("")||txtmarca.getText().equals("")||txtprecio.getText().equals("")||txtcantidad.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Provedor user=new Provedor();
					user.setMarca(txtmarca.getText());
					user.setModelo(txtmodelo.getText());
					user.setPrecio(Double.parseDouble(txtprecio.getText()));
					user.setCantidad(Integer.parseInt(txtcantidad.getText()));
					
					if (dao.insertarprovedor(user)) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se agrego correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Error");
				}
				
			}
		});
		btnAgregar.setBounds(20, 141, 89, 23);
		contentPane.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int opcion =JOptionPane.showConfirmDialog(null , "Estas seguro de eliminar");
					if(opcion==0) {
					if (dao.eliminarprovedor(lista.get(fila).getIdprovedor())) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se elimino correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Error");
				}
				
			}
		});
		btnEliminar.setBounds(122, 141, 89, 23);
		contentPane.add(btnEliminar);
		
		btnBorrar = new JButton("borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				
			}
		});
		btnBorrar.setBounds(227, 141, 89, 23);
		contentPane.add(btnBorrar);
		
		btnEditar = new JButton("editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtmodelo.getText().equals("")||txtmarca.getText().equals("")||txtprecio.getText().equals("")||txtcantidad.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Provedor.setMarca(txtmarca.getText());
					Provedor.setModelo(txtmodelo.getText());
					Provedor.setPrecio(Double.parseDouble(txtprecio.getText()));
					Provedor.setCantidad(Integer.parseInt(txtcantidad.getText()));
					if (dao.editarprovedor(Provedor)) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se edito correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}catch (Exception e2) {
					
				}
				
			}
		});
		btnEditar.setBounds(326, 141, 89, 23);
		contentPane.add(btnEditar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 513, 238);
		contentPane.add(scrollPane);
		
		tblProvedor = new JTable();
		tblProvedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblProvedor.getSelectedRow();
				Provedor=lista.get(fila);
				lblid.setText(""+lista.get(fila).getIdprovedor());
				txtmarca.setText(""+Provedor.getMarca());
				txtmodelo.setText(""+Provedor.getModelo());
				txtprecio.setText(""+Provedor.getPrecio());
				txtcantidad.setText(""+Provedor.getCantidad());
				
			}
		});
		tblProvedor.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblProvedor);
		
		modelo.addColumn("ID");
		modelo.addColumn("Marca");
		modelo.addColumn("Modelo");
		modelo.addColumn("Precio");
		modelo.addColumn("Cantidad");
		tblProvedor.setModel(modelo);
		refrescarTabla();
		
		lblNewLabel_2 = new JLabel("cantidad");
		lblNewLabel_2.setBounds(227, 82, 60, 23);
		contentPane.add(lblNewLabel_2);
		
		txtprecio = new JTextField();
		txtprecio.setBounds(297, 27, 86, 20);
		contentPane.add(txtprecio);
		txtprecio.setColumns(10);
		
		txtcantidad = new JTextField();
		txtcantidad.setColumns(10);
		txtcantidad.setBounds(297, 83, 86, 20);
		contentPane.add(txtcantidad);
		
		btnPdf = new JButton("PDF");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				FileOutputStream archivo;
				File file = new File("C:/Users/sdeba/git/cruddelmartes/crudprovedor/src/PDF/provedor.pdf");
				archivo = new FileOutputStream(file);
				Document doc=new Document();
				PdfWriter.getInstance(doc, archivo);
				doc.open();
				Image img Image.getInstance("C:/Users/sdeba/git/cruddelmartes/crudprovedor/src/Img/s14.webp");  
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (DocumentException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPdf.setBounds(425, 141, 89, 23);
		contentPane.add(btnPdf);
		refrescarTabla();
	}
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchprovedors();
		for(Provedor u: lista) {
			Object o[]=new Object [5];
			o[0]=u.getIdprovedor();
			o[1]=u.getMarca();
			o[2]=u.getModelo();
			o[3]=u.getPrecio();
			o[4]=u.getCantidad();
			modelo.addRow(o);
		}
		tblProvedor.setModel(modelo);
	}
}