package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Text_Screen implements ActionListener {

	private JFrame frame;
	private JTextArea textArea;
	private JMenuItem new_option;
	private JMenuItem open_option;
	private JMenuItem save_option;
	private JMenuItem print_option;
	private JMenuItem close_option;
	private JMenuItem find_option;
	private JMenuItem replace_option;
	private JMenu views_menu;
	private JMenuItem font_option;
	private JMenuItem background_option;
	private JMenuItem textColor_option;
	private JMenu theme_menu;
	private JMenuItem light_option;
	private JMenuItem dark_option;
	private boolean check;
	private JMenu encryption_menu;
	private JMenu decryption_menu;
	private JMenuItem caesarCipherEncryption;
	private JMenuItem vigenereCipherEncryption;
	private JMenuItem caeserCipherDecryption;
	private String data;
	private JMenuItem vigenereCipherDecryption;
	
	Text_Screen()
	{
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Text Editor");
		check=false;
		
		Image icon = new ImageIcon(this.getClass().getResource("notebook.png")).getImage();
		frame.setIconImage(icon);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu file_menu = new JMenu("File");
		menuBar.add(file_menu);
		
		new_option = new JMenuItem("New");
		new_option.addActionListener(this);
		file_menu.add(new_option);
		
		open_option = new JMenuItem("Open");
		open_option.addActionListener(this);
		file_menu.add(open_option);
		
		save_option = new JMenuItem("Save");
		save_option.addActionListener(this);
		file_menu.add(save_option);
		
		print_option = new JMenuItem("Print");
		print_option.addActionListener(this);
		file_menu.add(print_option);
		
		close_option = new JMenuItem("Close");
		close_option.addActionListener(this);
		file_menu.add(close_option);
		
		JMenu edit_menu = new JMenu("Edit");
		menuBar.add(edit_menu);
		
		find_option = new JMenuItem("Find");
		edit_menu.add(find_option);
		find_option.addActionListener(this);
		
		replace_option = new JMenuItem("Find and Replace");
		edit_menu.add(replace_option);
		replace_option.addActionListener(this);
		
		views_menu = new JMenu("Views");
		menuBar.add(views_menu);
		
		font_option = new JMenuItem("Font");
		views_menu.add(font_option);
		font_option.addActionListener(this);
		
		background_option = new JMenuItem("Background Color");
		views_menu.add(background_option);
		background_option.addActionListener(this);
		
		textColor_option = new JMenuItem("Text Color");
		views_menu.add(textColor_option);
		textColor_option.addActionListener(this);
		
		theme_menu = new JMenu("Theme");
		menuBar.add(theme_menu);
		
		light_option = new JMenuItem("Light Theme");
		theme_menu.add(light_option);
		light_option.addActionListener(this);
		
		dark_option = new JMenuItem("Dark Theme");
		theme_menu.add(dark_option);
		dark_option.addActionListener(this);
		
		encryption_menu = new JMenu("Encryption");
		menuBar.add(encryption_menu);
		
		caesarCipherEncryption = new JMenuItem("Caeser Cipher");
		encryption_menu.add(caesarCipherEncryption);
		caesarCipherEncryption.addActionListener(this);
		
		vigenereCipherEncryption = new JMenuItem("Vigenere Cipher");
		encryption_menu.add(vigenereCipherEncryption);
		vigenereCipherEncryption.addActionListener(this);
		
		decryption_menu = new JMenu("Decryption");
		menuBar.add(decryption_menu);
		
		caeserCipherDecryption = new JMenuItem("Caeser Cipher");
		decryption_menu.add(caeserCipherDecryption);
		caeserCipherDecryption.addActionListener(this);
		
		vigenereCipherDecryption = new JMenuItem("Vigenere Cipher");
		decryption_menu.add(vigenereCipherDecryption);
		vigenereCipherDecryption.addActionListener(this);
		
		JMenu info_menu = new JMenu("Info");
		menuBar.add(info_menu);
		
		//Text Area
		textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman",Font.PLAIN,20));
		//If line is completed the text automatically goes to next Line
		textArea.setLineWrap(true);
		//If Next line is used than whole word goes to next line and not its part
		textArea.setWrapStyleWord(true);
		
		//For Scroll Bar
		JScrollPane scroll= new JScrollPane(textArea);
		
		frame.getContentPane().add(scroll);
		frame.setVisible(true);
	}
	
	private void saveFile()
	{
		JFileChooser save=new JFileChooser();
		save.setApproveButtonText("Save");
		int action = save.showOpenDialog(frame);
		//To check that whether a file is being saved or not
		if(action!=JFileChooser.APPROVE_OPTION)
		{
			return;
		}
		File file=new File(save.getSelectedFile()+".txt");

		try {
			BufferedWriter br=new BufferedWriter(new FileWriter(file));
			textArea.write(br);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frame,"Error saving file: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void findText()
	{
		String search=JOptionPane.showInputDialog(frame,"Enter Text To Search","Search",JOptionPane.PLAIN_MESSAGE);
		if(search!=null)
		{
			String str=textArea.getText();
			int start=str.indexOf(search);
			if(start!=-1)
			{
				textArea.setSelectionStart(start);
				textArea.setSelectionEnd(start+search.length());
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "Text not Found","Search",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
			JOptionPane.showMessageDialog(frame, "Invalid Input","Search",JOptionPane.ERROR_MESSAGE);
	}
	
	private void replaceText()
	{
		String search=JOptionPane.showInputDialog(frame,"Enter Text to Find","Search",JOptionPane.PLAIN_MESSAGE);
		String str=textArea.getText();
		int start =-1;
		start=str.indexOf(search);
		if(search!=null&&start>=0)
		{
			String replace=JOptionPane.showInputDialog(frame,"Enter text to be Replaced With","Replace",JOptionPane.PLAIN_MESSAGE);
			if(replace==search)
			{
				JOptionPane.showMessageDialog(frame, "Enter Text Is Same");
			}
			else
			{
				String newstr=str.replace(search,replace);
				textArea.setText(newstr);
			}
		}
		else
			JOptionPane.showMessageDialog(frame, "Element Not Found","Search",JOptionPane.ERROR_MESSAGE);
	}
	
	private void setFont()
	{
		Font curr=textArea.getFont();
		String[] fonts=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		String fontName =(String) JOptionPane.showInputDialog(frame,"Select a Font","Font",JOptionPane.PLAIN_MESSAGE,null,fonts,curr.getFontName());
		if(fontName!=null)
		{
			int fontStyle=curr.getStyle();
			int fontSize=curr.getSize();
			Font newFont= new Font(fontName,fontStyle,fontSize);
			textArea.setFont(newFont);
		}
		else
		{
			JOptionPane.showMessageDialog(frame, "Invalid Font","Search",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void setTextColor()
	{
		Color curr = textArea.getForeground();
		Color newColor=JColorChooser.showDialog(frame,"Select Text Color", curr);
		if(newColor!=null)
		{
			textArea.setForeground(newColor);
		}
		else
			JOptionPane.showMessageDialog(frame, "Invalid Color Selection",null,JOptionPane.ERROR_MESSAGE);
				
	}
	
	private void setBackgroundColor()
	{
		Color curr=textArea.getBackground();
		Color newColor=JColorChooser.showDialog(frame,"Select Background Color",curr);
		if(newColor!=null)
		{
			textArea.setBackground(newColor);
		}
		else
			JOptionPane.showMessageDialog(frame,"Invalid Color Selection","",JOptionPane.ERROR_MESSAGE);
	}
	
	private void setDarkTheme() {
		 textArea.setBackground(Color.BLACK);
		 textArea.setForeground(Color.WHITE);
		 }
		 
	private void setLightTheme() {
		 textArea.setBackground(Color.WHITE);
		 textArea.setForeground(Color.BLACK);
		 }
	
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource()==new_option)
		{
			textArea.setText("");
		}
		if(ae.getSource()==open_option)
		{
			JFileChooser chooser = new JFileChooser();
			//chooser.setAcceptAllFileFilterUsed(false);
			//Restriction is not showing all files at the moment
			String ar[]= {".txt"};
			FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", ar);
			chooser.addChoosableFileFilter(restrict);
			//To check that whether a file is being opened or not
			int action=chooser.showOpenDialog(frame);
			if(action!=JFileChooser.APPROVE_OPTION)
			{
				return;
			}
			else
			{
				File file =chooser.getSelectedFile();
				
				try {
					FileReader read=new FileReader(file);
					BufferedReader br = new BufferedReader(read);
					textArea.read(br, null);
					br.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(frame,"Error opening file: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if(ae.getSource()==save_option)
		{
			saveFile();
		}
		if(ae.getSource()==print_option)
		{
			try {
				textArea.print();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(frame,"Error printing file: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(ae.getSource()==close_option)
		{
			System.exit(0);
		}
		if(ae.getSource()==find_option)
		{
			findText();
			
		}
		if(ae.getSource()==replace_option)
		{
			replaceText();
		}
		if(ae.getSource()==font_option)
		{
			setFont();
		}
		if(ae.getSource()==textColor_option)
		{
			setTextColor();
		}
		if(ae.getSource()==background_option) 
		{
			setBackgroundColor();
		}
		if(ae.getSource()==light_option)
		{
			setLightTheme();
		}
		if(ae.getSource()==dark_option)
		{
			setDarkTheme();
		}
		
		//Encryption
		if(ae.getSource()==caesarCipherEncryption)
		{
			if(!check)
			{
				String plaintext=textArea.getText();
				String key=JOptionPane.showInputDialog(frame,"Enter Key","Caesar Cipher",JOptionPane.PLAIN_MESSAGE);
				if(key!=null)
				{
					Encrypting encryption=new Encrypting(plaintext,key);
					data=plaintext;
					String ciphertext=encryption.CaesarCipher();
					textArea.setText(ciphertext);
					check=true;
					Image iconencrypt = new ImageIcon(this.getClass().getResource("hacker.png")).getImage();
					frame.setIconImage(iconencrypt);
				}
			}
			else
				JOptionPane.showMessageDialog(frame, "Text is already Encrypted", "Warning", JOptionPane.ERROR_MESSAGE);
		}
		
		if(ae.getSource()==vigenereCipherEncryption)
		{
			if(!check)
			{
				String plaintext=textArea.getText();
				String key=JOptionPane.showInputDialog(frame,"Enter Key","Vigenere Cipher",JOptionPane.PLAIN_MESSAGE);
				if(key!=null)
				{
					Encrypting encryption=new Encrypting(plaintext,key);
					data=plaintext;
					String ciphertext=encryption.VigenereCipher();
					textArea.setText(ciphertext);
					check=true;
					Image iconencrypt = new ImageIcon(this.getClass().getResource("hacker.png")).getImage();
					frame.setIconImage(iconencrypt);
				}
			}
			else
				JOptionPane.showMessageDialog(frame, "Text is already Encrypted", "Warning", JOptionPane.ERROR_MESSAGE);
		}
		
		
		//Decryption
		if(ae.getSource()==caeserCipherDecryption)
		{
			if(check)
			{
				String ciphertext=textArea.getText();
				String key = JOptionPane.showInputDialog(frame,"Enter Key","Caeser Cipher",JOptionPane.PLAIN_MESSAGE);
				if(key!=null)
				{
					Decryption decryption= new Decryption(ciphertext,key);
					String plaintext=decryption.CaesarCipher();
					textArea.setText(plaintext);
					check=false;
					Image iconencrypt = new ImageIcon(this.getClass().getResource("notebook.png")).getImage();
					frame.setIconImage(iconencrypt);
					if(!plaintext.equals(data))
					{
						System.exit(0);
					}
				}
			}
			else
				JOptionPane.showMessageDialog(frame, "Text is already Decrypted", "Warning", JOptionPane.ERROR_MESSAGE);
		}
		if(ae.getSource()==vigenereCipherDecryption)
		{
			if(check)
			{
				String ciphertext=textArea.getText();
				String key = JOptionPane.showInputDialog(frame,"Enter Key","Vigenere Cipher",JOptionPane.PLAIN_MESSAGE);
				Decryption decryption= new Decryption(ciphertext,key);
				String plaintext=decryption.VigenereCipher();
				textArea.setText(plaintext);
				check=false;
				Image iconencrypt = new ImageIcon(this.getClass().getResource("notebook.png")).getImage();
				frame.setIconImage(iconencrypt);
				if(!plaintext.equals(data))
				{
					System.exit(0);
				}
				
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "Text is already Decrypted", "Warning", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public static void main(String[] args) {
		new Text_Screen();
	}


	

}
