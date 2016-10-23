package challenge3;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Label;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JColorChooser;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class MainWindow {
	
	public class MyPanel extends JPanel
	{
		public void paint(Graphics g)
		{
			g.clearRect(0, 0, this.getWidth(), this.getHeight());
			g.translate(this.getWidth() / 2, this.getHeight() / 2);
			for(int i = 0; i < spirographs.size(); i++) {
				spirographs.elementAt(i).paint(g);
			}
			
		}
	}

	private JFrame frmSpirographGenerator;
	
	protected Vector<Spirograph> spirographs = new Vector<Spirograph>();
	private JSlider sliderFix;
	private JSlider sliderMov;
	private JSlider sliderOffset;
	private JComboBox<String> cbxSpiros;
	private MyPanel panel;
	private JButton btnDeleteSpirograph;
	protected boolean cbxEvents = true;
	private JColorChooser colorChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmSpirographGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		spirographs.addElement(new Spirograph());
		
		initialize();
		cbxSpiros.addItem("Spirograph 1");
		panel.repaint();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSpirographGenerator = new JFrame();
		frmSpirographGenerator.setTitle("Spirograph generator");
		frmSpirographGenerator.setBounds(100, 100, 1252, 912);
		frmSpirographGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSpirographGenerator.getContentPane().setLayout(null);
		
		sliderFix = new JSlider();
		sliderFix.setMaximum(300);
		sliderFix.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				spirographs.get(cbxSpiros.getSelectedIndex()).setFixedRad(sliderFix.getValue());
				panel.repaint();
			}
		});
		sliderFix.setPaintLabels(true);
		sliderFix.setMajorTickSpacing(20);
		sliderFix.setPaintTicks(true);
		sliderFix.setBounds(108, 750, 508, 36);
		frmSpirographGenerator.getContentPane().add(sliderFix);
		
		sliderMov = new JSlider();
		sliderMov.setMaximum(300);
		sliderMov.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spirographs.get(cbxSpiros.getSelectedIndex()).setMovRad(sliderMov.getValue());
				panel.repaint();
			}
		});
		sliderMov.setMajorTickSpacing(20);
		sliderMov.setPaintTicks(true);
		sliderMov.setPaintLabels(true);
		sliderMov.setBounds(108, 787, 508, 36);
		frmSpirographGenerator.getContentPane().add(sliderMov);
		
		sliderOffset = new JSlider();
		sliderOffset.setMaximum(300);
		sliderOffset.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spirographs.get(cbxSpiros.getSelectedIndex()).setOffset(sliderOffset.getValue());
				panel.repaint();
			}
		});
		sliderOffset.setPaintTicks(true);
		sliderOffset.setPaintLabels(true);
		sliderOffset.setMajorTickSpacing(20);
		sliderOffset.setBounds(108, 824, 508, 37);
		frmSpirographGenerator.getContentPane().add(sliderOffset);
		
		panel = new MyPanel();
		panel.setBounds(10, 11, 764, 719);
		panel.setBackground(Color.white);
		frmSpirographGenerator.getContentPane().add(panel);
		
		cbxSpiros = new JComboBox<String>();
		cbxSpiros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cbxEvents)
					return;
				sliderFix.setValue(spirographs.get(cbxSpiros.getSelectedIndex()).getFixedRad());
				sliderMov.setValue(spirographs.get(cbxSpiros.getSelectedIndex()).getMovRad());
				sliderOffset.setValue(spirographs.get(cbxSpiros.getSelectedIndex()).getOffset());
				colorChooser.setColor(spirographs.get(cbxSpiros.getSelectedIndex()).getColour());
			}
		});
		cbxSpiros.setBounds(626, 750, 148, 20);
		frmSpirographGenerator.getContentPane().add(cbxSpiros);
		
		JLabel lblFixedRadius = new JLabel("Fixed radius");
		lblFixedRadius.setBounds(10, 750, 88, 14);
		frmSpirographGenerator.getContentPane().add(lblFixedRadius);
		
		JLabel lblMovingRadius = new JLabel("Moving radius");
		lblMovingRadius.setBounds(10, 787, 88, 14);
		frmSpirographGenerator.getContentPane().add(lblMovingRadius);
		
		JLabel lblOffset = new JLabel("Offset");
		lblOffset.setBounds(10, 824, 46, 14);
		frmSpirographGenerator.getContentPane().add(lblOffset);
		
		JButton btnAddSpirograph = new JButton("Add spirograph");
		btnAddSpirograph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spirographs.addElement(new Spirograph());
				cbxSpiros.addItem("Spirograph " + String.valueOf(cbxSpiros.getItemCount() + 1));
				cbxSpiros.setSelectedIndex(cbxSpiros.getItemCount() - 1);
			}
		});
		btnAddSpirograph.setBounds(626, 781, 148, 23);
		frmSpirographGenerator.getContentPane().add(btnAddSpirograph);
		
		btnDeleteSpirograph = new JButton("Delete spirograph");
		btnDeleteSpirograph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(spirographs.size() <= 1)
					return;
				spirographs.remove(cbxSpiros.getSelectedIndex());
				cbxEvents = false;
				cbxSpiros.removeAllItems();
				for(int i = 0; i < spirographs.size(); i++) {
					cbxSpiros.addItem(new String("Spirograph " + String.valueOf(i + 1)));
				}
				cbxEvents = true;
				frmSpirographGenerator.repaint();
			}
		});
		btnDeleteSpirograph.setBounds(626, 807, 148, 23);
		frmSpirographGenerator.getContentPane().add(btnDeleteSpirograph);
		
		colorChooser = new JColorChooser();
		colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spirographs.get(cbxSpiros.getSelectedIndex()).setColour(colorChooser.getColor());
				frmSpirographGenerator.repaint();
			}
		});
		colorChooser.setBounds(789, 619, 437, 242);
		frmSpirographGenerator.getContentPane().add(colorChooser);
	}
}
