package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtCodigo;
	JComboBox cboCategorias;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
				limpiatexto();
			}
		});
		btnRegistrar.setBounds(322, 10, 89, 23);
		contentPane.add(btnRegistrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 10, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Id. Producto :");
		lblNewLabel.setBounds(10, 13, 102, 14);
		contentPane.add(lblNewLabel);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 59, 86, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 63, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblNewLabel_1 = new JLabel("Nom. Producto :");
		lblNewLabel_1.setBounds(10, 35, 102, 14);
		contentPane.add(lblNewLabel_1);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 32, 86, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblNewLabel_1_1 = new JLabel("Stock");
		lblNewLabel_1_1.setBounds(10, 94, 102, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 91, 86, 20);
		contentPane.add(txtStock);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Precio Producto :");
		lblNewLabel_1_1_1.setBounds(10, 125, 102, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 122, 86, 20);
		contentPane.add(txtPrecio);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				actualizar();
				limpiatexto();
			}
		});
		btnActualizar.setBounds(322, 43, 89, 23);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
				limpiatexto();
			}
		});
		btnEliminar.setBounds(322, 76, 89, 23);
		contentPane.add(btnEliminar);
		
		llenacombo();
	}
	
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
	EntityManager em = fabrica.createEntityManager();
	
	void limpiatexto() {
		txtCodigo.setText(null);
		txtDescripcion.setText(null);
		cboCategorias.setSelectedIndex(0);
		txtStock.setText(null);
		txtPrecio.setText(null);
	}
	void llenacombo() {

		String sql = "Select c from Categoria c";
		List<Categoria> lstCategorias = em.createQuery(sql,Categoria.class).getResultList();
		for (Categoria c : lstCategorias) {
			 cboCategorias.addItem(c.getIdcategoria());
		}
	}

	void listado() {
		
	}
	
	void registrar() {
			Producto p = new Producto();
			p.setIdprod(txtCodigo.getText());
			p.setDescripcion(txtDescripcion.getText());
			p.setIdcategoria((Integer)cboCategorias.getSelectedItem());
			p.setStock(Integer.parseInt(txtStock.getText()));
			p.setPrecio(Double.parseDouble(txtPrecio.getText()));
			p.setEstado(1);
			em.getTransaction().begin();
			em.persist(p); // persist es el metodo para registrar
			em.getTransaction().commit();
			System.out.println("registro ok");
			
	}
	
	void actualizar() {
		// TODO Auto-generated method stub
		Producto p = new Producto();
		p.setIdprod(txtCodigo.getText());
		p.setDescripcion(txtDescripcion.getText());
		p.setIdcategoria((Integer)cboCategorias.getSelectedItem());
		p.setStock(Integer.parseInt(txtStock.getText()));
		p.setPrecio(Double.parseDouble(txtPrecio.getText()));
		p.setEstado(1);
		em.getTransaction().begin();
		em.merge(p); // persist es el metodo para registrar
		em.getTransaction().commit();
		System.out.println("actualizacion ok");
		
	}
	
	void eliminar() {
		
		Producto p = em.find(Producto.class, txtCodigo.getText());
		em.getTransaction().begin();

		em.remove(p);

		em.getTransaction().commit();

		System.out.println("Registro eliminado correctatmente");

		
	}
	

}
