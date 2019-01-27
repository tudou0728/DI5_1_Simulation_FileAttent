package com.views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.evenement.Evenement;
import com.model.Echeancier;
import com.model.Simulation;

public class SimulationView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimulationView frame = new SimulationView();
					Dimension dimension= Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation((int)(dimension.getWidth()/2- frame.getWidth()/2), (int)(dimension.getHeight()/2- frame.getHeight()/2));
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
	public SimulationView() {
		
		setTitle("Simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JComboBox<String> comboLoiArrivee = new JComboBox<String>();
		JComboBox<String> comboLoiService = new JComboBox<String>();
		JTextPane paramLoiArrivee = new JTextPane();
		paramLoiArrivee.setBounds(321, 86, 123, 27);
		contentPane.add(paramLoiArrivee);
		
		JTextPane paramLoiService = new JTextPane();
		paramLoiService.setBounds(321, 141, 123, 27);
		contentPane.add(paramLoiService);
		
		JTextPane paramDureeTotal = new JTextPane();
		paramDureeTotal.setBounds(321, 196, 123, 27);
		contentPane.add(paramDureeTotal);
		
		JTextPane paramNBServeur = new JTextPane();
		paramNBServeur.setBounds(108, 196, 123, 27);
		contentPane.add(paramNBServeur);
		
		JButton btnLancer = new JButton("Lancer");
		btnLancer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!paramLoiArrivee.getText().isEmpty())
				{
					if(!paramLoiService.getText().isEmpty())
					{
						if(!paramNBServeur.getText().isEmpty()) {
							if(!paramDureeTotal.getText().isEmpty()) {
							
								Echeancier.setNBServeur(Integer.parseInt(paramNBServeur.getText()));
								Evenement evt=new Evenement();
								
								//on r�cup�re le type de loi et le param�tre
								evt.setInterArrivee(Float.valueOf(paramLoiArrivee.getText()));
								evt.setLoiInterArrivee(comboLoiArrivee.getSelectedItem().toString());
								
								evt.setLoiDureeService(comboLoiService.getSelectedItem().toString());
								evt.setDureeService(Float.valueOf(paramLoiService.getText()));	
								//evt.setDureeService(Float.valueOf(paramLoiService.getText()));
								
								//lyy begin---
								Simulation sim=new Simulation(evt,"sim.csv",Integer.parseInt(paramDureeTotal.getText()));
								//lyy end---
								try {
									sim.simulate();
									//sim.simulate2();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}
					
				}
				
			}
		});
		btnLancer.setBounds(163, 243, 117, 29);
		contentPane.add(btnLancer);
		
		comboLoiArrivee.setModel(new DefaultComboBoxModel<String>(new String[] {"Loi exponentielle", "Loi normale","Loi uniforme", "Loi de poisson","Loi empirique"}));
		comboLoiArrivee.setBounds(108, 86, 123, 27);
		contentPane.add(comboLoiArrivee);
		
		JLabel lblParamtre = new JLabel("parametre:");
		lblParamtre.setBounds(249, 86, 67, 27);
		contentPane.add(lblParamtre);
		
	
		JLabel lblNewLabel = new JLabel("arrivee");
		lblNewLabel.setBounds(29, 90, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblService = new JLabel("service");
		lblService.setBounds(29, 145, 61, 16);
		contentPane.add(lblService);
		
		JLabel lblNBService = new JLabel("nbServeur");
		lblNBService.setBounds(29, 205, 61, 16);
		contentPane.add(lblNBService);
		
		//lyy begin---
//		comboLoiService.setModel(new DefaultComboBoxModel<String>(new String[] {"Loi exponentielle", "Loi normale","Loi uniforme", "Loi beta"}));
		comboLoiService.setModel(new DefaultComboBoxModel<String>(new String[] {"Loi exponentielle", "Loi normale","Loi uniforme", "Loi beta","Loi empirique"}));
		//lyy end---
		comboLoiService.setBounds(108, 141, 123, 27);
		contentPane.add(comboLoiService);
		
		JLabel label = new JLabel("parametre:");
		label.setBounds(249, 145, 67, 27);
		contentPane.add(label);	
		
		JLabel lblDureeTotal = new JLabel("Duree Total:");
		lblDureeTotal.setBounds(249, 200, 80, 27);
		contentPane.add(lblDureeTotal);
		
	}
}
