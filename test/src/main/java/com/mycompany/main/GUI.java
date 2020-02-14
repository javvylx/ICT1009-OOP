package com.mycompany.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.control.ComboBox;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.Border;

public class GUI {
    JFrame f;
    JButton b1,searchButton,searchButton1,searchButton2;
    ButtonHandler hnd;
    JTextField search,search1;
    BarChartSample barChart;
    GUI()
    {
        initGUI();
    }
    private void initGUI()
    {
        f=new JFrame("ICT 1009 Project"); // Title of GUI
        f.setLayout(null);
        
        //Search Bar
        search=new JTextField("Search Here");
        search.setLocation(20, 20);// Location of Search Bar
        search.setSize(600, 40);
        f.add(search);
        
        // Search Button Icon
        searchButton=new JButton("search",new ImageIcon("search.png"));
        searchButton.setLocation(630, 20);
        searchButton.setSize(45, 40);
        searchButton.setBackground(new Color(245,242,208));
        searchButton.setBorder(null);
        searchButton.setFocusable(false);
        f.add(searchButton);
        
        // Dropdown Menu for Filter By
        JMenuBar br=new JMenuBar();
        JMenu menu=new JMenu("Filter By >");
        menu.setFont(new Font("Arial", Font.BOLD, 20));
        menu.setForeground(new Color(81, 122, 236));
        JMenu m1=new JMenu("Country");
        JMenu m2=new JMenu("Language");
        JMenu m3=new JMenu("Aurther");
        m1.add(new JMenuItem("Australia"));
        m1.add(new JMenuItem("England"));
        m1.add(new JMenuItem("Pakistan"));
        m2.add(new JMenuItem("English"));
        m2.add(new JMenuItem("Chinese"));
        m2.add(new JMenuItem("Tamil"));
        m3.add(new JMenuItem("CNN"));
        m3.add(new JMenuItem("BBC"));
        m3.add(new JMenuItem("ABC News"));
        br.add(menu);
        br.setSize(110, 40);
        br.setLocation(20, 70);
        br.setBackground(new Color(245,242,208));
        br.setBorder(null);
        menu.add(m1);
        menu.add(m2);
        menu.add(m3);
        f.add(br);
        
        searchButton2=new JButton("refresh",new ImageIcon("refresh.png"));
        searchButton2.setLocation(1125, 60);
        searchButton2.setSize(40, 40);
        searchButton2.setBackground(new Color(245,242,208));
        searchButton2.setBorder(null);
        searchButton2.setFocusable(false);
        f.add(searchButton2);
        
        //here its an empty panel for Article
        JPanel article=new JPanel();
        article.setLayout(null);
        article.setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
        //ImageIcon imageIcon = new ImageIcon(new ImageIcon("Inserturimagehere").getImage().getScaledInstance(310, 430, 0)); Set image location
        
        JLabel articl=new JLabel("Articles");
        articl.setLocation(100, 20);
        articl.setSize(300, 40);
        articl.setForeground(new Color(81, 122, 236));
        articl.setFont(new Font("Arial", Font.BOLD, 30));
        article.add(articl);
        
        JLabel label=new JLabel();
        label.setLocation(30, 70);
        label.setSize(310, 420);
        label.setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
        article.add(label);

        article.setSize(700, 550);
        article.setBackground(Color.WHITE);
        article.setLocation(20, 125);
        f.add(article);
        
        //Company Stock symbol search bar
        search1=new JTextField("Search Here");
        search1.setLocation(770, 20);
        search1.setSize(300, 40);
        f.add(search1);
        
        //Refresh button
        searchButton1=new JButton("refresh",new ImageIcon("search.png"));
        searchButton1.setLocation(1085, 20);
        searchButton1.setSize(40, 40);
        searchButton1.setBackground(new Color(245,242,208));
        searchButton1.setBorder(null);
        searchButton1.setFocusable(false);
        f.add(searchButton1);
        
        //Bar Chart Button
        b1=new JButton("Click here for Bar Chart");
        b1.setSize(395, 150);
        b1.setLocation(770,100);
        b1.setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
        b1.setBackground(new Color(245,242,208));
        b1.setFont(new Font("Arial", Font.PLAIN, 30));
        f.add(b1);
        hnd=new ButtonHandler(this);
        b1.addActionListener(hnd);
        searchButton.addActionListener(hnd);
        searchButton1.addActionListener(hnd);
        
        //Twiter Label Placeholder
        JLabel twitter=new JLabel("Twitter");
        twitter.setLocation(945, 260);
        twitter.setSize(300, 40);
        twitter.setForeground(new Color(81, 122, 236));
        twitter.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(twitter);
        
        //Window for twitter
        JPanel twite=new JPanel();
        twite.setLocation(770,300);
        twite.setSize(400, 375);
        twite.setBackground(Color.WHITE);
        twite.setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
        f.add(twite);
        
        f.setSize(1220, 750); //GUI Screen Size
        f.getContentPane().setBackground(new Color(245,242,208)); //Background Color
        f.setVisible(true);
        f.setResizable(false); // Fixed Panel for GUI, means dont allow user to resize screen
        int x=(Toolkit.getDefaultToolkit().getScreenSize().width/2)-(f.getSize().width/2),y=(Toolkit.getDefaultToolkit().getScreenSize().height/2)-(f.getSize().height/2);
        f.setLocation(x,y);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
