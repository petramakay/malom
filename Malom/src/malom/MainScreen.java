package malom;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.*;

public class MainScreen {
	public static Malom malom;
	private JFrame frmMalom;
	public static final List<StoneType> stones = Algoritmusok.getStones();
	public static boolean hasPlayerTakenTheStep = false;
	public static boolean haveSelected = false;
	public static StoneType selected;


	//elindít
	public static void start(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen(args[0], args[1]);
					window.frmMalom.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void start(PlayerType playerOne, PlayerType playerTwo) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen(playerOne, playerTwo);
					window.frmMalom.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainScreen(String p1, String p2) {
		malom = new Malom();
		malom.playerOne.setName(p1); 
		malom.playerTwo.setName(p2);

		initialize();
	}
	
	public MainScreen(PlayerType playerOne, PlayerType playerTwo) {
		malom = new Malom(playerOne, playerTwo);
		
		initialize();
	}

	private void initialize() {
		frmMalom = new JFrame();
		frmMalom.setResizable(false);
		frmMalom.setTitle("Malomjáték");
		frmMalom.setBounds(100, 100, 650, 440);
		frmMalom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMalom.setLocationRelativeTo(null);

		ImagePanel imgPanel = new ImagePanel(new ImageIcon(this.getClass()
				.getResource("/background.png")).getImage());
		imgPanel.setBorder(null);

		frmMalom.getContentPane().add(imgPanel);

		final JLabel lblJtkos = new JLabel("1. játékos: "
				+ malom.playerOne.getName());
		lblJtkos.setForeground(Color.RED);
		lblJtkos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJtkos.setBounds(380, 11, 179, 21);
		imgPanel.add(lblJtkos);

		final JLabel lblJtkos_step = new JLabel("1. játékos: "
				+ malom.playerOne.getName() + " - lép!");
		lblJtkos_step.setForeground(Color.RED);
		lblJtkos_step.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJtkos_step.setBounds(380, 11, 179, 21);
		lblJtkos_step.setVisible(false);
		imgPanel.add(lblJtkos_step);

		final JLabel lblJtkos_1 = new JLabel("2. játékos: "
				+ malom.playerTwo.getName());
		lblJtkos_1.setForeground(Color.BLUE);
		lblJtkos_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJtkos_1.setBounds(380, 117, 179, 14);
		imgPanel.add(lblJtkos_1);

		final JLabel lblJtkos_step_1 = new JLabel("2. játékos: "
				+ malom.playerTwo.getName() + " - lép!");
		lblJtkos_step_1.setForeground(Color.BLUE);
		lblJtkos_step_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJtkos_step_1.setBounds(380, 117, 179, 14);
		lblJtkos_step_1.setVisible(false);
		imgPanel.add(lblJtkos_step_1);

		JButton btnNextButton = new JButton("Következő");
		btnNextButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				malom.roundCounter++;
				hasPlayerTakenTheStep = false;
				haveSelected = false;
				if (malom.roundCounter % 2 == 1) {
					lblJtkos.setVisible(false);
					lblJtkos_step.setVisible(true);
					lblJtkos_1.setVisible(true);
					lblJtkos_step_1.setVisible(false);
					frmMalom.repaint();
				} else {
					lblJtkos.setVisible(true);
					lblJtkos_step.setVisible(false);
					lblJtkos_1.setVisible(false);
					lblJtkos_step_1.setVisible(true);
					frmMalom.repaint();
				}
			}
		});
		btnNextButton.setBounds(405, 217, 129, 23);
		imgPanel.add(btnNextButton);

		JButton exit = new JButton("Exit");
		exit.setBounds(405, 250, 129, 23);
		imgPanel.add(exit);
		exit.addActionListener((event) -> System.exit(0));
		
		JButton mentes = new JButton("Mentés");
		mentes.setBounds(405, 283, 129, 23);
		imgPanel.add(mentes);
		
		mentes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				malom.save();
			}
		});
		


		final JLabel lblKvekSzma_1 = new JLabel("Kövek száma: "
				+ Malom.playerOne.getStones());
		lblKvekSzma_1.setBounds(380, 32, 112, 14);
		imgPanel.add(lblKvekSzma_1);

		final JLabel lblKvekATablan_1 = new JLabel("Kövek a táblán: "
				+ Malom.playerOne.getOnBoardStones());
		lblKvekATablan_1.setBounds(380, 57, 112, 14);
		imgPanel.add(lblKvekATablan_1);

		final JLabel lblKvekSzma_2 = new JLabel("Kövek száma: "
				+ Malom.playerTwo.getStones());
		lblKvekSzma_2.setBounds(380, 142, 101, 14);
		imgPanel.add(lblKvekSzma_2);

		final JLabel lblKvekATablan_2 = new JLabel("Kövek a táblán: "
				+ Malom.playerTwo.getOnBoardStones());
		lblKvekATablan_2.setBounds(380, 167, 112, 14);
		imgPanel.add(lblKvekATablan_2);

		final JLabel lblMostUgorhatsz_1 = new JLabel("Most ugorhatsz!");
		lblMostUgorhatsz_1.setBounds(380, 82, 112, 14);
		if (Malom.playerOne.canJump())
			lblMostUgorhatsz_1.setVisible(true);
		else
			lblMostUgorhatsz_1.setVisible(false);
		imgPanel.add(lblMostUgorhatsz_1);

		final JLabel lblMostUgorhatsz_2 = new JLabel("Most ugorhatsz!");
		lblMostUgorhatsz_2.setBounds(380, 192, 101, 14);
		if (Malom.playerTwo.canJump())
			lblMostUgorhatsz_2.setVisible(true);
		else
			lblMostUgorhatsz_2.setVisible(false);
		imgPanel.add(lblMostUgorhatsz_2);


		final JLabel lblLevehetszKovet_1 = new JLabel("Levehetsz egy követ");
		lblLevehetszKovet_1.setBounds(380, 82, 124, 14);
		lblLevehetszKovet_1.setVisible(false);
		imgPanel.add(lblLevehetszKovet_1);

		final JLabel lblLevehetszKovet_2 = new JLabel("Levehetsz egy követ");
		lblLevehetszKovet_2.setBounds(380, 192, 124, 14);
		lblLevehetszKovet_2.setVisible(false);
		imgPanel.add(lblLevehetszKovet_2);

		for (StoneType s : stones) {
			imgPanel.add(s.getLabel());
		}


		frmMalom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					if (!hasPlayerTakenTheStep) {
						if (new MalomOperator(malom.t).canRemove(
								malom.roundCounter, malom.playerOne,
								malom.playerTwo, malom)) {
							hasPlayerTakenTheStep = Algoritmusok
									.removeStone(arg0);

							{
								malom.previousMalmok = malom.malmok;
								malom.malmok = new MalomOperator(malom.t)
										.getMalmok();
							}

							lblKvekSzma_1.setText("Kövek száma: "
									+ malom.playerOne.getStones());
							lblKvekATablan_1.setText("Kövek a táblán: "
									+ malom.playerOne.getOnBoardStones());
							lblKvekSzma_2.setText("Kövek száma: "
									+ malom.playerTwo.getStones());
							lblKvekATablan_2.setText("Kövek a táblán: "
									+ malom.playerTwo.getOnBoardStones());

							if (malom.roundCounter % 2 == 1)
								lblLevehetszKovet_1.setVisible(false);
							else
								lblLevehetszKovet_2.setVisible(false);

							frmMalom.repaint();
						} else {
							if (new MalomOperator(malom.t).canJump(
									malom.roundCounter, malom.playerOne,
									malom.playerTwo)) {
								if (malom.roundCounter % 2 == 1) 
									lblMostUgorhatsz_1.setVisible(true);
								else
									lblMostUgorhatsz_2.setVisible(true);
								
								hasPlayerTakenTheStep = Algoritmusok
										.jumpStone(arg0);
								frmMalom.repaint();

								

								if (hasPlayerTakenTheStep) {
									{
										malom.previousMalmok = malom.malmok;
										malom.malmok = new MalomOperator(malom.t)
												.getMalmok();
									}
									
									if (new MalomOperator(malom.t).canRemove(
											malom.roundCounter,
											malom.playerOne, malom.playerTwo,
											malom)) {
										if (malom.roundCounter % 2 == 1)
											lblLevehetszKovet_1
													.setVisible(true);
										else
											lblLevehetszKovet_2
													.setVisible(true);
										frmMalom.repaint();

										hasPlayerTakenTheStep = false;
									}
								}
								
								if(new MalomOperator(malom.t).isGameOver(malom)) {
									JOptionPane.showMessageDialog(null, "Vége a játéknak!");
									frmMalom.setVisible(false);
									DataScreen.start();
								}
							}

							if (new MalomOperator(malom.t).canMove(
									malom.roundCounter, malom.playerOne,
									malom.playerTwo)) {
								hasPlayerTakenTheStep = Algoritmusok
										.moveStone(arg0);
								frmMalom.repaint();

								

								if (hasPlayerTakenTheStep) {
									{
										malom.previousMalmok = malom.malmok;
										malom.malmok = new MalomOperator(malom.t)
												.getMalmok();
									}

									
									if (new MalomOperator(malom.t).canRemove(
											malom.roundCounter,
											malom.playerOne, malom.playerTwo,
											malom)) {

										if (malom.roundCounter % 2 == 1)
											lblLevehetszKovet_1
													.setVisible(true);
										else
											lblLevehetszKovet_2
													.setVisible(true);
										frmMalom.repaint();

										hasPlayerTakenTheStep = false;
									}
								}
								
								if(new MalomOperator(malom.t).isGameOver(malom)) {
									JOptionPane.showMessageDialog(null, "Vége a játéknak!");
								}
								
							} else if(malom.playerOne.getStones() != malom.playerOne.getOnBoardStones()
									&& malom.playerTwo.getStones() != malom.playerTwo.getOnBoardStones()){
								JOptionPane
										.showMessageDialog(frmMalom,
												"Még nem raktál le minden követ - Jobb gomb");
							}
						}
					} else {
						JOptionPane.showMessageDialog(frmMalom,
								"Már léptél, nyomd meg a 'következő' gombot!");
					}

				}

				if (arg0.getButton() == MouseEvent.BUTTON3) {
					if (!hasPlayerTakenTheStep) {
						if (new MalomOperator(malom.t).canPut(
								malom.roundCounter, malom.playerOne,
								malom.playerTwo)) {
							Algoritmusok.putStone(arg0);
							
							{
								malom.previousMalmok = malom.malmok;
								malom.malmok = new MalomOperator(malom.t)
										.getMalmok();
							}
							
							hasPlayerTakenTheStep = true;
							
							if (new MalomOperator(malom.t).canRemove(
									malom.roundCounter, malom.playerOne,
									malom.playerTwo, malom)) {
								if (malom.roundCounter % 2 == 1)
									lblLevehetszKovet_1.setVisible(true);
								else
									lblLevehetszKovet_2.setVisible(true);
								frmMalom.repaint();

								hasPlayerTakenTheStep = false;
							}
						} else {
							JOptionPane.showMessageDialog(frmMalom,
									"Már minden követ lehejeztél");
						}
					} else {
						JOptionPane.showMessageDialog(frmMalom,
								"Már léptél, nyomd meg a 'következő' gombot!");
					}

					lblKvekSzma_1.setText("Kövek száma: "
							+ malom.playerOne.getStones());
					lblKvekATablan_1.setText("Kövek a táblán: "
							+ malom.playerOne.getOnBoardStones());
					lblKvekSzma_2.setText("Kövek száma: "
							+ malom.playerTwo.getStones());
					lblKvekATablan_2.setText("Kövek a táblán: "
							+ malom.playerTwo.getOnBoardStones());
					frmMalom.repaint();
				}
			}
		});
		

		frmMalom.setVisible(true);
	}

}