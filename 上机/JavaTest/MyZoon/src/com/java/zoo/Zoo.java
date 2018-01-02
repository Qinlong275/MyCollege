package com.java.zoo;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class Zoo extends JFrame {
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private JTextArea textFieldup;

	public Zoo() {
		Container contextPanel = getContentPane();
		// this.setSize(100, 100);

		button1 = new Button();
		button2 = new Button();
		button3 = new Button();
		button4 = new Button();
		Button button5 = new Button();
		Button button6 = new Button();
		textFieldup = new JTextArea();
		textFieldup.setFont(new Font("华文彩云", Font.BOLD, 20));
		textFieldup.setText("Hello 这是我们的动物园，里面有\n各种各样的动物，请随意参观\n");
		
		button1.setLabel("Bird");
		button2.setLabel("Fish");
		button3.setLabel("mammal");
		button4.setLabel("insect");
		button1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldup.setText(MyZoo.birdArea.showAllAnimals());
				setTitle(MyZoo.showMyZoo());

			}
		});

		button2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldup.setText(MyZoo.fishArea.showAllAnimals());

			}
		});

		button3.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldup.setText(MyZoo.mammaliaArea.showAllAnimals());

			}
		});

		button4.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldup.setText(MyZoo.insectArea.showAllAnimals());

			}
		});
		// button1.setSize(1600, 1600);
		// button2.setSize(600,600);
		Panel panel1 = new Panel();
		Panel panel2 = new Panel();
		Panel panel3 = new Panel();
		Panel panel4 = new Panel();
		panel1.setBackground(Color.blue);
		panel2.setBackground(Color.yellow);
		panel3.setBackground(Color.pink);
		panel4.setBackground(Color.green);
		// button1.setSize(300, 300);
		// panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		// panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		// panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		// panel4.setLayout(new FlowLayout(FlowLayout.CENTER));
		// panel1.setLayout(new BorderLayout());
		// panel3.setLayout(new FlowLayout());
		panel1.add(button1);
		panel3.add(button3);
		// panel1.setBounds(0, 0, 800, 800);
		// panel1.setSize(800, 800);
		// button3.setBounds(0, 100, 80, 80);
		// button1.setLocation(20, 20);
		setTitle("zoo");
		// Border border =
		// BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		setBounds(300, 250, 620, 620);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		BorderLayout borderLayout = new BorderLayout(5, 5);
		contextPanel.setLayout(borderLayout);
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.EAST);
		add(panel3, BorderLayout.SOUTH);
		add(panel4, BorderLayout.WEST);
		add(textFieldup, BorderLayout.CENTER);
		// Button button5 =new Button();
		// Button button6 = new Button();
		panel1.setPreferredSize(new Dimension(100, 60));
		panel2.setPreferredSize(new Dimension(100, 60));
		panel3.setPreferredSize(new Dimension(100, 60));
		panel4.setPreferredSize(new Dimension(100, 60));
		button1.setPreferredSize(new Dimension(50, 50));
		button2.setPreferredSize(new Dimension(50, 50));
		button3.setPreferredSize(new Dimension(60, 60));
		button4.setPreferredSize(new Dimension(50, 50));
		// button5.setPreferredSize(new Dimension(50,50));
		// button6.setPreferredSize(new Dimension(50,50));
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();
		JPanel panel7 = new JPanel();
		JPanel panel8 = new JPanel();
		JPanel panel9 = new JPanel();
		JPanel panel10 = new JPanel();
		panel5.setPreferredSize(new Dimension(50, 50));
		panel6.setPreferredSize(new Dimension(50, 50));
		panel7.setPreferredSize(new Dimension(50, 50));
		panel8.setPreferredSize(new Dimension(50, 50));
		panel9.setPreferredSize(new Dimension(50, 50));
		panel10.setPreferredSize(new Dimension(50, 50));

		panel2.add(panel5);
		panel2.add(panel6);
		panel2.add(panel7);
		panel2.add(button2);
		panel4.add(panel8);
		panel4.add(panel9);
		panel4.add(panel10);
		panel4.add(button4);
		// panel2.add(button5);
		// panel2.add(button6);
		// panel2.add(button4);
		panel5.setOpaque(false);
		panel6.setOpaque(false);
		panel7.setOpaque(false);
		panel8.setOpaque(false);
		panel9.setOpaque(false);
		panel10.setOpaque(false);
		// panel7.setBackground(Color.BLACK);
		// int centerx = panel3.getHeight()/2;
		// int centery = panel3.getWidth()/2;
		// button3.setLocation(centerx, centery);
		// getContentPane().add("North", panel1); //灏嗘寜閽坊鍔犲埌绐楀彛涓�
		// getContentPane().add("South",panel2);
		//
		// getContentPane().add("East", new JButton("East"));
		//
		// getContentPane().add("West", new JButton("West"));
		//
		// getContentPane().add("Center",new JButton("Center"));
		// add(textFieldup);
		textFieldup.setBackground(Color.white);
		// this.pack();
		// button2.hide();
		// button4.hide();
		// button5.hide();
		// button6.hide();
	}
}
