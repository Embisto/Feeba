package com.feeba.server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.CardLayout;

import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;


public class ServerGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread serverThread;
	private JPanel contentPane;
	private JLabel ipadress;
	private JButton startStopServer;
	private JLabel serverState;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI frame = new ServerGUI();
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
	public ServerGUI() {
		setTitle("Feeba Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0x17748F));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		startStopServer = new JButton("Server Starten");
		startStopServer.setForeground(Color.DARK_GRAY);
		startStopServer.setFont(new Font("Helvetica", Font.PLAIN, 13));
		startStopServer.setBackground(Color.WHITE);
		startStopServer.setFocusable(false);
		startStopServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (ServerController.isStarted()) {

					ServerController.stopServer();
					ipadress.setText("");
					startStopServer.setText("Server starten");
					serverThread.interrupt();
					serverState.setForeground(Color.DARK_GRAY);
					serverState.setText("Angehalten");
				}

				else {

					serverState.setText("Starte...");

					serverThread = new Thread(new Runnable() {
						
						public void run() {

							ServerController.startServer();


						}
					});
					
					ipadress.setText(ServerController.getIp()+":8080");
					startStopServer.setText("Server stoppen");
					serverThread.start();
					serverState.setText("Aktiv");
					serverState.setForeground(new Color(0, 128, 0));

				}

			}

		});
		
		startStopServer.setBounds(25, 62, 261, 42);
		startStopServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.setLayout(null);
		contentPane.add(startStopServer);

		JPanel statuspanel = new JPanel();
		statuspanel.setBackground(Color.WHITE);
		statuspanel.setBorder(null);
		statuspanel.setBounds(25, 188, 261, 52);
		contentPane.add(statuspanel);
		statuspanel.setLayout(new CardLayout(0, 0));
		
		serverState = new JLabel("Angehalten");
		serverState.setForeground(Color.DARK_GRAY);
		serverState.setBackground(Color.WHITE);
		serverState.setFont(new Font("Helvetica Neue", Font.PLAIN, 28));
		serverState.setHorizontalAlignment(SwingConstants.CENTER);
		statuspanel.add(serverState, "name_1377779724699494000");

		JPanel ippanel = new JPanel();
		ippanel.setBackground(Color.WHITE);
		ippanel.setBorder(null);
		ippanel.setBounds(328, 188, 394, 52);
		contentPane.add(ippanel);
		ippanel.setLayout(new CardLayout(0, 0));

		ipadress = new JLabel("");
		ipadress.setForeground(Color.DARK_GRAY);
		ipadress.setBackground(Color.WHITE);
		ipadress.setHorizontalAlignment(SwingConstants.CENTER);
		ippanel.add(ipadress, "name_1377695306787995000");
		ipadress.setFont(new Font("Helvetica", Font.PLAIN, 28));

		JLabel lblIpAdresseZum = new JLabel("IP Adresse zum Verbinden:");
		lblIpAdresseZum.setForeground(Color.WHITE);
		lblIpAdresseZum.setBounds(328, 160, 167, 16);
		contentPane.add(lblIpAdresseZum);
		
				JLabel lblAktuellerServerStatus = new JLabel("Server Status");
				lblAktuellerServerStatus.setForeground(Color.WHITE);
				lblAktuellerServerStatus.setBounds(25, 160, 142, 16);
				contentPane.add(lblAktuellerServerStatus);
				
				JLabel label = new JLabel("");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setIcon(new ImageIcon(ServerGUI.class.getResource("/images/logo.png")));
				label.setBounds(328, 39, 394, 88);
				contentPane.add(label);
	}
}