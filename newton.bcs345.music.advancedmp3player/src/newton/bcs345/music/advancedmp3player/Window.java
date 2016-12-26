//********************************************************
// File: Window.java  
//
// Purpose: Creates a media player that plays
//			 a song based on information from a file.
//
// Written By: Linda Newton
//
// Date: 12/13/2013
//
//********************************************************


package newton.bcs345.music.advancedmp3player;

import jaco.mp3.player.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.lang.model.element.Element;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import SoundJLayer.SoundJLayer;
import newton.bcs345.music.advancedmp3player.*;
import newton.bcs345.music.songinfo1.*;
import newton.bsc345.music.playlist.*;



public class Window extends JFrame implements TreeSelectionListener,  ActionListener {
	private DefaultMutableTreeNode node;
	private DefaultMutableTreeNode team1;
	private DefaultMutableTreeNode team2;
	private DefaultMutableTreeNode team3;
	private JButton StartButton;
	private JButton StopButton;
	private JButton NextButton;
	private JButton PreviousButton;
	private JTree tree;
	private JTree Team;
	private JTree n;
	private JTree c;
	private JButton load;
	private JButton play;
	private JButton stop;
	private JButton resume;
	private JButton pause;
	private JMenuBar menuBar;
	private JMenu menu;
	private MP3Player mp3;
	private SoundJLayer m_SongPlaying;
	private JFileChooser chooser;
	private SongInfo Song = new SongInfo();
	private JTextField songName;
	private JTextField fileName1;
	private JTextField minutes;
	private JTextField seconds;
	private JLabel name1;
	private JLabel file;
	private JLabel mins;
	private JLabel secs;
	private playlist mainplaylist = new playlist();
	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JPanel mainPanel;
	private JPanel treePanel;
	private JPanel mainPanel2;
	private File file1;
	private String filename;
	private Scanner input;
	private DefaultMutableTreeNode[] nodeSongs;
	private int count=0;


	//****************************************************
	// Method: Window 
	//
	// Purpose: A constructor that gives values to all variables
	//  and creates Jframe.
	//
	// ****************************************************
	
	public Window(){
		

	setTitle("BCS 345 – Advanced MP3 Player - Linda Newton");
	setSize(650, 550);
	setDefaultCloseOperation(EXIT_ON_CLOSE);

	// ****************************************************
	//
	// Set all menu to new and set Items in menu
	//
	// ****************************************************

	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu();
	menuItem1 = new JMenuItem("Load PlayList");
	menuItem2 = new JMenuItem("Load SongInfo");
	menu = new JMenu("File");
	

	// ****************************************************
	//
	// Set panels to new and create layout
	//
	// ****************************************************
	
	JPanel Main = new JPanel();
	Main.setLayout(new BoxLayout(Main, BoxLayout.PAGE_AXIS));
	
	JPanel Buttons = new JPanel();
	Buttons.setLayout(new FlowLayout());
	
	JPanel Songname = new JPanel();
	Songname.setLayout(new BoxLayout(Songname, BoxLayout.X_AXIS));
	
	JPanel fileName = new JPanel();
	fileName.setLayout(new BoxLayout(fileName, BoxLayout.X_AXIS));
	
	JPanel Minutes = new JPanel();
	Minutes.setLayout(new BoxLayout(Minutes, BoxLayout.X_AXIS));
	
	JPanel Seconds = new JPanel();
	Seconds.setLayout(new BoxLayout(Seconds, BoxLayout.X_AXIS));
	
	
	JPanel mainPanel = new JPanel();
	mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	
	
	JPanel mainPanel2 = new JPanel();

	JPanel p = new JPanel();
	p.setLayout(new GridLayout(2,2,20,20));
	p.setPreferredSize(new Dimension(600, 100));
	JPanel p2 = new JPanel();
	// ****************************************************
	//
	// Set Buttons to new so they can be used and gives them names
	//
	// ****************************************************
	
	StartButton = new JButton("Start PlayList");
	StopButton = new JButton("Stop PlayList");
	NextButton = new JButton("Next Song in PlayList");
	PreviousButton = new JButton("Previous Song in PlayList");
	
	load = new JButton("Load");
	play = new JButton("Play");
	stop = new JButton("Stop");
	resume = new JButton("Resume");
	pause = new JButton("Pause");
	
	//a tab has been created
	JTabbedPane tab1 = new JTabbedPane();
	
	// ****************************************************
	//
	// Set a tree called playlise with nodes
	//
	// ****************************************************
	
	node = new DefaultMutableTreeNode("PlayList");
	//tree = new JTree(node);
	
	// makes tree listen to events	
	tree.addTreeSelectionListener(this);
	
	// ****************************************************
	//
	// Set Text Fields and Labels to be used
	//
	// ****************************************************
	
	songName = new JTextField(50);
	fileName1 = new JTextField(50);
	minutes = new JTextField(50);
	seconds = new JTextField(50);
	
	name1 = new JLabel("Name     ");
	file = new JLabel("File          ");
	mins = new JLabel("Minutes  ");
	secs = new JLabel("Seconds ");
	
	// ****************************************************
	//
	// Sets background colors
	//
	// ****************************************************
	
	Buttons.setBackground(Color.red);
	p2.setBackground(Color.black);
	mainPanel.setBackground(Color.black);
	p.setBackground(Color.blue);
	Main.setBackground(Color.orange);
	
	// ****************************************************
	//
	// added information to window
	//
	// ****************************************************
	
	//adds buttons to panel p
	p.add(StartButton);
	p.add(StopButton);
	p.add(NextButton);
	p.add(PreviousButton);
	
	// add p and tree to mainpanel
	mainPanel.add(p);
	mainPanel.add(tree);
	
	//mainPanel is added to window
	add(mainPanel);
	
	// added to buttons panel
	Buttons.add(load);
	Buttons.add(play);
	Buttons.add(stop);
	Buttons.add(resume);
	Buttons.add(pause);
	
	// adds to panel p2
	p2.add(Songname);
	p2.add(fileName);
	p2.add(Minutes);		
	p2.add(Seconds);
	
	
	Songname.add(name1);		
	Songname.add(songName);

	fileName.add(file);
	fileName.add(fileName1);

	Minutes.add(mins);
	Minutes.add(minutes);

	Seconds.add(secs);
	Seconds.add(seconds);

	
	Main.add(Buttons);
	Main.add(p2);
	
	// adds to menu items to menu
	menu.add(menuItem1);
	menu.add(menuItem2);
	//menu bar has bee added to window
	menuBar.add(menu);
	setJMenuBar(menuBar);
	//main panel add
	add(Main);
	
	
	//two tabs added to tab1
	tab1.addTab("PlayList", mainPanel);
	tab1.addTab("SongInfo", Main);
	
	

	add(tab1);
	
	// ****************************************************
	//
	// Action Listeners for buttons to listen to actions
	//
	// ****************************************************
	
	load.addActionListener(this);
	play.addActionListener(this);
	stop.addActionListener(this);
	pause.addActionListener(this);
	resume.addActionListener(this);
	StartButton.addActionListener(this);
	StopButton.addActionListener(this);
	NextButton.addActionListener(this);
	PreviousButton.addActionListener(this);
	menu.addActionListener(this);
	menuItem2.addActionListener(this);
	menuItem1.addActionListener(this);

	p2.setPreferredSize(new Dimension(800, 500));
	p2.setMaximumSize(p2.getPreferredSize()); 
	p2.setMinimumSize(p2.getPreferredSize());
	
	setResizable(false);
	}

	
@Override
public void valueChanged(TreeSelectionEvent e) {

	
}


@Override
public void actionPerformed(ActionEvent e) {
	
	
	// ****************************************************
	//
	// loads SongInfo from file gets all information and
	// add information to appropriate text fields
	//
	// ****************************************************	
	
	if (menuItem2 == e.getSource()){

		chooser = new JFileChooser();
		
		int newValue = chooser.showOpenDialog(Window.this);
		
		if(newValue == JFileChooser.APPROVE_OPTION){
			
			// stops player from playing song more then once at a time		
			if(mp3 != null){
				mp3.stop();
			}		
		
			
			try {
				Scanner input;
				input = new Scanner(new FileReader(chooser.getSelectedFile().getName()));
				
				Song.Read(input);
			//information changed to string	
			songName.setText(Song.getSongName());
			fileName1.setText(Song.getFilename());
			minutes.setText(Integer.toString(Song.getLength().getMinutes()));
			seconds.setText(Integer.toString(Song.getLength().getSeconds()));
				
			mp3 = new MP3Player(new File(Song.getFilename()));
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
							
				
			}			
		
	}
	

	// ****************************************************
	//
	// loads PlayList from file gets all information and
	// adds song name to appropriate node fields
	//
	// ****************************************************	
		
	if (menuItem1 == e.getSource()){
			
		//remove nodes when playlist is reloaded
		node.removeAllChildren(); 
		
		chooser = new JFileChooser();

		int returnVal = chooser.showOpenDialog(Window.this);
		
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
					

				file1 = chooser.getSelectedFile();
				
				filename = file1.getName();
				
				if(mp3 != null){
					mp3.stop();
				}
							

			
			try {
				
				input = new Scanner(new FileReader(filename));

				mainplaylist = new playlist();
				mainplaylist.Read(input);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		// adds all Song names from the file into the tree					
		nodeSongs = new DefaultMutableTreeNode[mainplaylist.getSongCount()];
		
		 for(int i =0 ; i < nodeSongs.length; i++ ){
			 nodeSongs[i] = new DefaultMutableTreeNode(mainplaylist.GetSong(i).toString());
				node.add(nodeSongs[i]);
			 
			}	
			
	}

		

	// ****************************************************
	//
	// Start Button:
	//  
	// Purpose: Gets the file name and plays mp3
	//
	// ****************************************************	
	

	if(StartButton == e.getSource()){

		if(mp3 != null){
			mp3.stop();
		}

		mp3 = new MP3Player(new File(mainplaylist.GetSong(0).getFilename()));
		mp3.play();
	}
	
	// ****************************************************
	//
	// Stop Button:
	//  
	// Purpose: stops mp3
	//
	// ****************************************************	
		
	if(StopButton == e.getSource()){
		mp3.stop();
	}

	// ****************************************************
	//
	// Next Button:
	//  
	// Purpose: Gets the next song in file and plays it
	//
	// ****************************************************	
		
	if(NextButton == e.getSource()){
		mp3.stop();
		
		count++;
		if(count >= mainplaylist.getSongCount()){
			count = 0;
		}
		
		
	mp3 = new MP3Player(new File(mainplaylist.GetSong(count).getFilename()));
		
		mp3.play();

	
	
	}
	
	// ****************************************************
	//
	// Previous Button:
	//  
	// Purpose: Gets previous song in file and plays it
	//
	// ****************************************************	
	
	
	if(PreviousButton == e.getSource()){
		
		mp3.stop();
		
		if(count <= 0){
			count = mainplaylist.getSongCount();
			
							
		}
		count = count - 1;
		
		
		mp3 = new MP3Player(new File(mainplaylist.GetSong(count).getFilename()));
		mp3.play();
		return;
	
	}
	
	
	// ****************************************************
	//
	// loads SongInfo from file gets all information and
	// add information to appropriate text fields
	//
	// ****************************************************		

	if (load == e.getSource()){

		chooser = new JFileChooser();
		
		int newValue = chooser.showOpenDialog(Window.this);
		
		if(newValue == JFileChooser.APPROVE_OPTION){
			
			if(mp3 != null){
				mp3.stop();
			}
			
			try {
				Scanner input;
				input = new Scanner(new FileReader(chooser.getSelectedFile().getName()));
				
				Song.Read(input);
				
			songName.setText(Song.getSongName());
			fileName1.setText(Song.getFilename());
			minutes.setText(Integer.toString(Song.getLength().getMinutes()));
			seconds.setText(Integer.toString(Song.getLength().getSeconds()));
				
				mp3 = new MP3Player(new File(Song.getFilename()));
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
							
				
			}			
		
	}
		
	
	//****************************************************
	//
	// Purpose: plays the mp3 corresponding to the filename text field.
	//
	//****************************************************

	
		if (play== e.getSource()){
			
		//	mp3 = new MP3Player(new File("papercut.mp3"));
			
			mp3.play();
			
		}
	
	//****************************************************
	//
	// Purpose: Stops the song that is currently playing.
	//
	//****************************************************

	if (stop ==e.getSource()){
	
			mp3.stop();
	}
	//****************************************************
	//
	// Purpose: pauses the song that is currently playing.
	//
	//****************************************************
	
	if (pause == e.getSource()){
		mp3.pause();
	
	}
	//****************************************************
	//
	// Purpose: when song is paused resume to where song left off
	//
	//****************************************************
	if (resume == e.getSource()){
		if (mp3.isPaused())
			mp3.play();
	}
	
}

}





