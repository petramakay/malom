package malom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DataScreen {
	private JFrame data;
	
	public DataScreen() {
		initialize();
	}
	
	//elindít
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataScreen window = new DataScreen();
					window.data.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void initialize() {
		data = new JFrame("Malom");
		data.setResizable(false);
		data.setBounds(100, 100, 770, 100);
		data.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		data.setLocationRelativeTo(null);
		
		JPanel panelgomb = new JPanel();
		JButton start = new JButton("Play");
		start.setBounds(20, 20, 20, 20);
		panelgomb.add(start);
		data.add(panelgomb, BorderLayout.EAST);
		
		JButton exit = new JButton("Exit");
		exit.setBounds(20, 20, 20, 20);
		panelgomb.add(exit);
		
		JButton folytat = new JButton("Adatok betölt");
		folytat.setBounds(20, 20, 20, 20);
		panelgomb.add(folytat);
		
		JLabel elsofelirat = new JLabel("1. játékos");
		JPanel panelelso = new JPanel();
		JTextField elsojatekos = new JTextField("", 20);
		panelelso.add(elsofelirat);
		panelelso.add(elsojatekos);
		data.add(panelelso, BorderLayout.WEST);
		
		JLabel masodikfelirat = new JLabel("2. játékos");
		JPanel panelmasodik = new JPanel();
		JTextField masodikjatekos = new JTextField("", 20);
		panelmasodik.add(masodikfelirat);
		panelmasodik.add(masodikjatekos);
		data.add(panelmasodik, BorderLayout.CENTER);
		
		start.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String[] nevek = new String[2];
				nevek[0] = elsojatekos.getText();
				nevek[1] = masodikjatekos.getText();
				if(nevek[0].equals("") || nevek[1].equals("")) {
					JOptionPane.showMessageDialog(null, "Adj meg neveket!");
				}
				else {
					MainScreen.start(nevek);
					data.setVisible(false);
				}
			}
		});

		exit.addActionListener((event) -> System.exit(0));
		
		folytat.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				Malom.load();
				data.setVisible(false);
			}
		});
	}
	
	
	
}

