/**
 *Richard Yang & Emily Wang
 *ICS4U - Mr A
 *Created 2:08:04 PM, Apr 29, 2016
 *gui.java
 *Minesweeper Assignment
 * 
 * ██████╗ ██╗     ███████╗ █████╗ ███████╗███████╗    ██╗   ██╗███████╗███████╗    ███████╗ ██████╗██╗     ██╗██████╗ ███████╗███████╗
 * ██╔══██╗██║     ██╔════╝██╔══██╗██╔════╝██╔════╝    ██║   ██║██╔════╝██╔════╝    ██╔════╝██╔════╝██║     ██║██╔══██╗██╔════╝██╔════╝
 * ██████╔╝██║     █████╗  ███████║███████╗█████╗      ██║   ██║███████╗█████╗      █████╗  ██║     ██║     ██║██████╔╝███████╗█████╗  
 * ██╔═══╝ ██║     ██╔══╝  ██╔══██║╚════██║██╔══╝      ██║   ██║╚════██║██╔══╝      ██╔══╝  ██║     ██║     ██║██╔═══╝ ╚════██║██╔══╝  
 * ██║     ███████╗███████╗██║  ██║███████║███████╗    ╚██████╔╝███████║███████╗    ███████╗╚██████╗███████╗██║██║     ███████║███████╗
 *   ╚═╝     ╚══════╝╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝     ╚═════╝ ╚══════╝╚══════╝    ╚══════╝ ╚═════╝╚══════╝╚═╝╚═╝╚══════╝╚══════╝
 *                                                                                                            
 *      ██╗ █████╗ ██╗   ██╗ █████╗ ███████╗███████╗     ██╗███████╗                                           
 *      ██║██╔══██╗██║   ██║██╔══██╗██╔════╝██╔════╝    ███║╚════██║                                           
 *      ██║███████║██║   ██║███████║███████╗█████╗      ╚██║    ██╔╝                                           
 * ██   ██║██╔══██║╚██╗ ██╔╝██╔══██║╚════██║██╔══╝       ██║   ██╔╝                                            
 * ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║███████║███████╗     ██║██╗██║                                             
 *  ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝╚══════╝╚══════╝     ╚═╝╚═╝╚═╝       
 * 
 * ████████╗██╗  ██╗ █████╗ ███╗   ██╗██╗  ██╗███████╗       ██╗ 
 * ╚══██╔══╝██║  ██║██╔══██╗████╗  ██║██║ ██╔╝██╔════╝    ██╗╚██╗
 *    ██║   ███████║███████║██╔██╗ ██║█████╔╝ ███████╗    ╚═╝ ██║
 *    ██║   ██╔══██║██╔══██║██║╚██╗██║██╔═██╗ ╚════██║    ██╗ ██║
 *    ██║   ██║  ██║██║  ██║██║ ╚████║██║  ██╗███████║    ╚═╝██╔╝
 *    ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝       ╚═╝ 
 * ps. give us 100%
 */
package minesweeper;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class gui extends JFrame implements ActionListener {

	// ARRAYS AND VARIABLES//
	int WID = 9, LEN = 9, NUM = 10;
	JButton[][] buttonArray = new JButton[WID][LEN];
	int[][] grid = new int[WID][LEN];
	int[][] status = new int[WID][LEN];
	int score_num = NUM;

	int gameState = 0; // 0 - NEW GAME || 1 - IN GAME || 2 - WIN || 3 - WIN

	// FONT FOR TIMER AND SCORE//
	Font sevenSeg;

	// TIMER VARIABLES//
	Timer timer;
	int TIME;

	// READ OR WRITE - WHICH IS IT?//
	boolean read;

	// IMAGES USED ON BUTTONS//
	ImageIcon EMPTY = new ImageIcon("empty.png");
	ImageIcon ZERO = new ImageIcon("0.png");
	ImageIcon ONE = new ImageIcon("1.png");
	ImageIcon TWO = new ImageIcon("2.png");
	ImageIcon THREE = new ImageIcon("3.png");
	ImageIcon FOUR = new ImageIcon("4.png");
	ImageIcon FIVE = new ImageIcon("5.png");
	ImageIcon SIX = new ImageIcon("6.png");
	ImageIcon SEVEN = new ImageIcon("7.png");
	ImageIcon EIGHT = new ImageIcon("8.png");
	ImageIcon MINE = new ImageIcon("9.png");
	ImageIcon FLAG = new ImageIcon("x.png");
	ImageIcon NOTMINE = new ImageIcon("z.png");
	ImageIcon MINEPRESSED = new ImageIcon("y.png");
	ImageIcon smile = new ImageIcon("smile.png");
	ImageIcon ded = new ImageIcon("deddd.png");
	ImageIcon win = new ImageIcon("winner.png");
	ImageIcon ooo = new ImageIcon("ooooo.png");
	ImageIcon ps = new ImageIcon("psmile.png");

	// FRAME//
	JFrame frame = new JFrame();// used for msg pop up

	// JPANELS//
	JPanel setting = new JPanel();
	JPanel top = new JPanel();
	JPanel bottom = new JPanel();
	JPanel mine = new JPanel();
	JPanel customize = new JPanel();
	JPanel File = new JPanel();
	JPanel gridPanel = new JPanel();

	// JLABELS//
	JLabel time = new JLabel(""); // timer
	JLabel score = new JLabel(""); // label for score tracker
	JLabel wids = new JLabel("Number of rows: ");
	JLabel lens = new JLabel("Number of columns: ");
	JLabel min = new JLabel("Number of mines: ");
	JLabel name = new JLabel("Please enter file name: ");

	// JTEXTFIELDS//
	JTextField rows = new JTextField(10);
	JTextField columns = new JTextField(10);
	JTextField mines = new JTextField(10);
	JTextField filename = new JTextField(15);

	// JBUTTONS//
	JButton ok = new JButton("OK");
	JButton save = new JButton("Save");
	JButton open = new JButton("Open");
	JButton reset = new JButton();
	JButton beg = new JButton("Beginner");
	JButton med = new JButton("Intermediate");
	JButton ex = new JButton("Expert");
	JButton set = new JButton("Settings");
	JButton cus = new JButton("Customize");
	JButton instruction = new JButton("How to play");
	JButton fileok = new JButton("OK");
	JButton backButton = new JButton("Back");

	// CONSTRUCTOR//
	public gui() {

		// SET UP FRAME//
		setSize(LEN * 40, WID * 40 + 100);
		setTitle("Minesweeper");
		setLayout(new BorderLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// ACTIONLISTENERS FOR BUTTONS//
		set.addActionListener(this);
		reset.addMouseListener(resetMListener);
		beg.addActionListener(this);
		med.addActionListener(this);
		ex.addActionListener(this);
		cus.addActionListener(this);
		instruction.addActionListener(this);
		save.addActionListener(this);
		open.addActionListener(this);
		ok.addActionListener(this);
		fileok.addActionListener(this);
	    backButton.addActionListener(this);
	    
		// ADD TOP AND BOTTOM PANELS//
		addTop();
		addBottom();

		gridPanel.setLayout(new GridLayout(WID, LEN)); // LAYOUT FOR 2D BUTTON ARRAY

		// INITIALIZE AND ADD MOUSELISTENER TO ALL BUTTONS//
		for (int i = 0; i < WID; i++) {
			for (int j = 0; j < LEN; j++) {
				buttonArray[i][j] = new JButton();
				buttonArray[i][j].addMouseListener(mouselistener);
				gridPanel.add(buttonArray[i][j]);
			}
		}

		// ADD PANEL ONTO FRAME//
		add(gridPanel, BorderLayout.CENTER);

		// SET ICON TO BUTTONS DEPENDING ON GAME STATE//
		draw(0, 0);
	}

	// ADD TOP PANEL//
	public void addTop() {
		top.setLayout(new BorderLayout()); // SET LAYOUT FOR 3 ELEMENTS
		score.setText(scoreText(score_num)); // SET LABEL TO NUMBER OF MINES
		time.setHorizontalAlignment(SwingConstants.RIGHT); // MOVE NUMBERS TO RIGHT SIDE OF ROW

		try {
			InputStream myStream = new BufferedInputStream(new FileInputStream(
					"7SEGBOLD.ttf")); // OPEN FONT LAYOUT FROM FILE
			Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
			sevenSeg = ttfBase.deriveFont(Font.PLAIN, 45); // GET AND SET FONT
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Font not loaded."); // IF FILE NOT FOUND
		}

		score.setFont(sevenSeg);
		score.setForeground(Color.RED);
		score.setBackground(Color.black);
		score.setOpaque(true); // SET UP SCORE LABEL
		time.setFont(sevenSeg);
		time.setForeground(Color.RED);
		time.setBackground(Color.black);
		time.setOpaque(true);
		time.setText(timeText(TIME)); // SET UP TIME LABEL
		reset.setIcon(smile);
		reset.setBorderPainted(false);
		reset.setContentAreaFilled(false);
		reset.setFocusPainted(false);
		reset.setOpaque(false); // SET UP RESET BUTTON

		// ADD ALL 3 ELEMENTS ONTO TOP PANEL//
		top.add(score, BorderLayout.WEST);
		top.add(reset, BorderLayout.CENTER);
		top.add(time, BorderLayout.EAST);

		add(top, BorderLayout.NORTH); // ADD TOP PANEL TO FRAME
	}

	// ADD BOTTOM PANEL//
	public void addBottom() {
		bottom.setLayout(new GridLayout(1, 2)); // SET LAYOUT FOR 2 ELEMENTS
		bottom.add(set);
		bottom.add(instruction); // ADD TWO SETTING AND INSTRUCTION BUTTONS
		add(bottom, BorderLayout.SOUTH); // ADD BOTTOM PANEL TO FRAME
	}

	// MOUSE LISTENER//
	MouseListener mouselistener = new MouseAdapter() {
		public void mousePressed(MouseEvent mouseEvent) {
			reset.setIcon(ooo); // WHEN MOUSE IS PRESSED SET TO O FACE

			// IF USER LEFT OR MIDDLE CLICKS//
			for (int i = 0; i < WID; i++) {
				for (int j = 0; j < LEN; j++) { // FIND THE BUTTON PRESSED{
					if (mouseEvent.getSource() == buttonArray[i][j]) {
						int modifiers = mouseEvent.getModifiers();
						if (((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK)
								|| ((modifiers & InputEvent.BUTTON2_MASK) == InputEvent.BUTTON2_MASK)) {
							if (status[i][j] == 0) {
								status[i][j] = 3;
							}
							if (status[i][j] == 1) {
								for (int a = i - 1; a <= i + 1; a++) {
									for (int b = j - 1; b <= j + 1; b++) {
										if (a >= 0 && b >= 0 && a < WID
												&& b < LEN && status[a][b] == 0) {
											status[a][b] = 3;
										}
									}
								}
							}
						}

						draw(i, j);
					}
				}
			}
		};

		// WHEN USER RELEASES MOUSE//
		public void mouseReleased(MouseEvent mouseEvent) {
			reset.setIcon(smile); // SET ICON BACK TO SMILE
			for (int i = 0; i < WID; i++) {
				for (int j = 0; j < LEN; j++) {
					if (status[i][j] == 3) {
						status[i][j] = 0;
					}
				}
			}
			for (int i = 0; i < WID; i++) {
				for (int j = 0; j < LEN; j++) {
					if (mouseEvent.getSource() == buttonArray[i][j]) {
						int modifiers = mouseEvent.getModifiers();
						if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK
								&& status[i][j] == 0) {
							if (gameState == 0) {
								startBoard(i, j);
								gameState = 1;

								time.setText(timeText(0));

								TIME = 0;
								timer = new Timer(1000, new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										TIME += 1;
										time.setText(timeText(TIME));
									}
								});
								timer.start();
							}
							status[i][j] = 1;
							if (grid[i][j] == 9) {
								loseGame();
							} else if (grid[i][j] == 0) {
								clearZeros(i, j);
								// checkWin();
							} else {
								// checkWin();
							}
						} else if ((((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) || ((modifiers & InputEvent.BUTTON2_MASK) == InputEvent.BUTTON2_MASK))
								&& status[i][j] == 1) {
							int count = 0;

							for (int a = i - 1; a <= i + 1; a++) {
								for (int b = j - 1; b <= j + 1; b++) {
									if (a >= 0 && b >= 0 && a < WID && b < LEN) {
										if (status[a][b] == 2)
											count++;
									}
								}
							}
							if (count == grid[i][j]) {
								for (int a = i - 1; a <= i + 1; a++) {
									for (int b = j - 1; b <= j + 1; b++) {
										if (a >= 0 && b >= 0 && a < WID
												&& b < LEN && status[a][b] != 2) {
											status[a][b] = 1;

											if (grid[a][b] == 0)
												clearZeros(a, b);
											else if (grid[a][b] == 9)
												loseGame();
										}
									}
								}
							}
						}

						else if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK
								&& status[i][j] == 0) {
							status[i][j] = 2;
							score_num--;
							score.setText(scoreText(score_num));
							// checkWin();
						} else if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK
								&& status[i][j] == 2) {
							status[i][j] = 0;
							score_num++;
							score.setText(scoreText(score_num));
						}
						checkWin();
						draw(i, j);
					}
				}
			}
		};
	};

	MouseListener resetMListener = new MouseAdapter() {
		public void mousePressed(MouseEvent mouseEvent) {
			reset.setIcon(ps); //when user presses on reset, set to pressed smile
			top.validate();
			top.repaint();
		}

		public void mouseReleased(MouseEvent mouseEvent) {
			reset.setIcon(smile); // set reset back to happy face
			emptyBoard(); // generate another random grid
			status = new int[WID][LEN]; // initialize clicks to 0

			// IF TIMER IS RUNNING, STOP//
			if (timer != null) {
				timer.stop();
			}

			// RESET TIMER//
			time.setText(scoreText(0));
		}
	};

	// ACTIONLISTENERS//
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Settings")) { // IF USER CLICKS SETTINGS{
			remove(top);
			remove(gridPanel);
			remove(bottom); // remove everything on frame

			setting = new JPanel();
			setting.setLayout(new BorderLayout()); // SET LAYOUT
			JPanel size = new JPanel();
			size.setLayout(new GridLayout(2, 2));
			size.add(beg);
			size.add(med);
			size.add(ex);
			size.add(cus);
			setting.add(size, BorderLayout.CENTER);  //add 4 buttons

			JPanel load = new JPanel();
			load.setLayout(new GridLayout(1, 2));
			load.add(save);
			load.add(open);
			setting.add(load, BorderLayout.NORTH);

			setting.add(backButton, BorderLayout.SOUTH);

			add(setting); // add panel onto frame

			refresh();
		} else if (command.equals("Beginner")) {
			WID = 9;
			LEN = 9;
			NUM = 10; // set numbers
			setSize(LEN * 40, WID * 40 + 100);
			remove(setting); // remove setting panel

			addTop(); // ADD TOP BAR//
			emptyBoard(); // GENERATE ANOTHER RANDOM BOARD//
			addBottom(); // ADD BOTTOM BAR
			refresh();
		} else if (command.equals("Intermediate")) {
			WID = 16;
			LEN = 16;
			NUM = 40; // set numbers
			setSize(LEN * 40 - 23, WID * 40 + 100);
			remove(setting); // remove setting panel

			addTop();
			emptyBoard();
			addBottom();
			refresh(); // add all game components back onto frame
		} else if (command.equals("Expert")) {
			WID = 16;
			LEN = 30;
			NUM = 99;// set numbers
			setSize(LEN * 40 - 23, WID * 40 + 100);
			remove(setting); // remove setting panel

			addTop();
			emptyBoard();
			addBottom();
			refresh(); // add all game components back onto frame
		} else if (event.getSource().equals(cus)) { // IF USER CLICKS CUSTOMIZE
			remove(setting);
			addCustomize();
			refresh(); // REMOVE SETTING PANEL AND ADD CUSTOMIZE BUTTON
		} else if (event.getSource().equals(ok)) { // IF USER PRESSES OKAY ON CUSTOMIZE PANEL
			String a, b, c; // DECLARE 3 STRING VARIABLES TO GET TEXT FROM TEXTFIELDS
			if (!(rows.getText().equals(""))) {
				a = rows.getText();
				LEN = Integer.parseInt(a);
			} // IF USER ENETERED A NUMBER STORE IT INTO LEN
			if (!(columns.getText().equals(""))) {
				b = columns.getText();
				WID = Integer.parseInt(b);
			} // STORE INTO WID

			boolean notManyMines = true;
			if (!(mines.getText().equals(""))) {
				c = mines.getText();
				if ((LEN * WID) <= Integer.valueOf(c)) { 
					JOptionPane.showMessageDialog(frame, "Too many mines.");
					notManyMines = false;
				}
				NUM = Integer.parseInt(c);
			} // STORE INTO NUM

			if (notManyMines) {
				setSize(LEN * 40, WID * 40 + 100);

				remove(customize);
				addTop();
				emptyBoard();
				addBottom();
				refresh(); // ADD ALL PANELS BACK ONTO FRAME WITH NEW DIMENSIONS
			}

		} else if (event.getSource().equals(save)
				|| event.getSource().equals(open)) {
			if (event.getSource().equals(save)) {
				read = false;
			} else {
				read = true;
			} //check if the button is pressed in save or open
			remove(setting); // REMOVE EXISTING SETTING PANEL
			addFile(); // ADD FILE PANEL
			refresh();
		} else if (command.equals("How to play")) { // INSTRUCTION MESSAGE DIALOG
			JOptionPane
					.showMessageDialog(
							frame,
							"                                       BASIC RULES FOR MINESWEEPER"
									+ "\n1) Left click a closed square to open it, right click to mark possible mines"
									+ "\n2) If a mine is uncovered, the game ends and the player loses"
									+ "\n3) Uncovering a square with no mine will display the number of neighbouring mines"
									+ "\n4) Each number has a unique icon with a unique color, and 0 is blank (empty icon)"
									+ "\n5) Uncovering a empty square will reveal all touching empty squares"
									+ "\n    The surrounding 8 squares of every empty square will also be revealed"
									+ "\n6) Left or middle click on an exposed number square after marking all"
									+ "\n    the mines surrounding it will quick-clear the square and reveal all"
									+ "\n    the squares around it");  
		} else if (command.equals("OK")) {
			String TFfilename = filename.getText();
			try {
				if (read) {
					loadBoard(TFfilename);
				} else {
					saveBoard(TFfilename); //use methods to perform tasks
				}

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (command.equals("Back")) {
			remove(setting);
			addTop();
			add(gridPanel);
			addBottom();
			refresh(); // add all game components back onto frame
		}
	}

	// PANEL FOR SAVE AND OPEN FILE//
	public void addFile() {
		File.setLayout(new FlowLayout()); // SET LAYOUT
		File.add(name); // ADD PROMPT LABEL
		File.add(filename); // ADD TEXTFIELD
		File.add(fileok); // ADD OK BUTTON
		add(File, BorderLayout.CENTER); // ADD FILE PANEL ONTO FRAME//
	}

	// CUSTOMIZE PANEL//
	public void addCustomize() {
		customize = new JPanel();
		customize.setLayout(new BorderLayout()); // SET LAYOUT FOR 4 BY 2 GRID

		JPanel inputAndLabels = new JPanel();
		inputAndLabels.setLayout(new GridLayout(3, 2));

		// ADD GUI COMPONENTS//
		inputAndLabels.add(wids);
		inputAndLabels.add(columns);
		inputAndLabels.add(lens);
		inputAndLabels.add(rows);
		inputAndLabels.add(min);
		inputAndLabels.add(mines);
		customize.add(ok, BorderLayout.SOUTH);
		customize.add(inputAndLabels, BorderLayout.CENTER);

		// ADD PANEL ONTO FRAME//
		add(customize, BorderLayout.CENTER);

		// REFRESH//
		customize.validate();
		customize.repaint();
	}

	public void loadBoard(String nameOfFile) throws NumberFormatException,
			IOException { // given nameOfFile
		nameOfFile = "saves/" + nameOfFile + ".txt"; //open file from the folder saves
		File f = new File(nameOfFile); 
		if (!(f.exists() && !f.isDirectory())) {
			JOptionPane.showMessageDialog(frame, "Error: File Not Found!"); //if file does not exist
		} else {
			FileInputStream fs = new FileInputStream(nameOfFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			TIME = Integer.valueOf(br.readLine());
			score_num = Integer.valueOf(br.readLine());
			WID = Integer.valueOf(br.readLine());
			LEN = Integer.valueOf(br.readLine());
			grid = new int[WID][LEN];
			status = new int[WID][LEN];
			for (int i = 0; i < WID; i++) {
				for (int j = 0; j < LEN; j++) {
					grid[i][j] = Integer.valueOf(br.readLine());
				}
			}
			for (int i = 0; i < WID; i++) {
				for (int j = 0; j < LEN; j++) {
					status[i][j] = Integer.valueOf(br.readLine());
				}
			} //read all values in the file
			br.close(); //close buffer reader
			buttonArray = new JButton[WID][LEN]; //reinitialize 
			gridPanel.removeAll(); 
			remove(gridPanel);
			remove(File); //remove all components from frame
			gridPanel.setLayout(new GridLayout(WID, LEN)); //set layout to new dimensions
			for (int i = 0; i < WID; i++) {
				for (int j = 0; j < LEN; j++) {
					buttonArray[i][j] = new JButton();
					buttonArray[i][j].addMouseListener(mouselistener);
					gridPanel.add(buttonArray[i][j]);
				}
			}
			addTop();
			add(gridPanel);
			addBottom(); //add panels 
			setSize(LEN * 40, WID * 40 + 100);
			gameState = 1;
			score.setText(scoreText(score_num));
			timer = new Timer(1000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TIME += 1;
					time.setText(timeText(TIME)); //start timer from the saved time
				}
			});
			timer.start();
			draw(0, 0); //set icons again
		}
	}

	public void saveBoard(String nameOfFile) throws FileNotFoundException,
			UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("saves/"+nameOfFile + ".txt",
				"UTF-8"); //save file into the folder saves
		writer.println(TIME);
		writer.println(score_num);
		writer.println(WID);
		writer.println(LEN);  //print neede components
		for (int i = 0; i < WID; i++) {
			for (int j = 0; j < LEN; j++) {
				writer.println(grid[i][j]); //print the value of each button
			}
		}
		for (int i = 0; i < WID; i++) {
			for (int j = 0; j < LEN; j++) {
				writer.println(status[i][j]); //print the status of each button
			}
		}
		writer.close(); //close print writier
		remove(File);  //remove file panel
		addTop();  
		add(gridPanel);
		addBottom();  //add back game components
		draw(0, 0); //set icons again
	}

	// SET ICONS//
	public void draw(int x, int y) {
		for (int i = 0; i < WID; i++) {
			for (int j = 0; j < LEN; j++) {

				if (status[i][j] == 0) {
					buttonArray[i][j].setIcon(EMPTY); // if unclicked
				} else if (status[i][j] == 2) {
					buttonArray[i][j].setIcon(FLAG); // if flaged
				} else if (status[i][j] == 3) {
					buttonArray[i][j].setIcon(ZERO); // if it is 0
				} else {
					buttonArray[i][j].setIcon(new ImageIcon(String
							.valueOf(grid[i][j]) + ".png")); // set to number icon
				}

				if (gameState == 2) { // win
					if (status[i][j] != 2 && grid[i][j] == 9) {
						buttonArray[i][j].setIcon(FLAG);
					} // flag all mines that are unflagged
				} else if (gameState == 3) { // lose
					if (grid[i][j] == 9 && status[i][j] == 0) {
						buttonArray[i][j].setIcon(MINE); // reveal all mine
					}
					if (grid[x][y] == 9) {
						buttonArray[x][y].setIcon(MINEPRESSED); // mine with red background
					} else {
						for (int k = x - 1; k <= x + 1; k++) {
							for (int l = y - 1; l <= y + 1; l++) {
								if (k >= 0 && k < WID && l >= 0 && l < LEN) {
									if (grid[k][l] == 9 && status[k][l] != 2) {
										buttonArray[k][l].setIcon(MINEPRESSED); // pressed neighbours
									}
								}
							}
						}
					}

					if (status[i][j] == 2 && grid[i][j] != 9) {
						buttonArray[i][j].setIcon(NOTMINE);
					}

				}
			}

		}
		refresh();
	}

	// EMPTY BOARD WHEN USER PRESSES RESET//
	public void emptyBoard() {

		// INITIALIZE ARRAYS//
		grid = new int[WID][LEN];
		status = new int[WID][LEN];
		buttonArray = new JButton[WID][LEN];

		// REMOVE ALL EXISTING BUTTONS ON GRIDPANEL//
		gridPanel.removeAll();

		// REMOVE THE PANEL FROM FRAME
		remove(gridPanel);

		// SET NEW LAYOUT FOR GRIDPANEL WITH NEW DIMENSIONS//
		gridPanel.setLayout(new GridLayout(WID, LEN));

		// REINITIALIZE ALL BUTTONS AND READD MOUSELISTENERS//
		for (int i = 0; i < WID; i++) {
			for (int j = 0; j < LEN; j++) {
				buttonArray[i][j] = new JButton();
				buttonArray[i][j].addMouseListener(mouselistener);
				gridPanel.add(buttonArray[i][j]);
			}
		}

		// ADD PANEL BACK ONTO FRAME//
		add(gridPanel);

		// CHANGE GAME STATE TO NEW GAME//
		gameState = 0;

		// CHANGE SCORE_NUM TO NUMBER OF MINES//
		score_num = NUM;
		score.setText(scoreText(score_num));

		// SET ICON//
		draw(0, 0);
	}

	// METHOD TO START THE BOARD AFTER PRESSING AT (X,Y)
	public void startBoard(int x, int y) {

		// INITIALIZE ARRAYS//
		grid = new int[WID][LEN];
		status = new int[WID][LEN];

		// GENERATE RANDOM MINES//
		for (int numLeft = NUM; numLeft > 0;) { // generate NUM mines
			int ranX = (int) (Math.random() * WID); // random x position
			int ranY = (int) (Math.random() * LEN); // random y position

			if ((ranX == x && ranY == y) || grid[ranX][ranY] == 9) { // ensure the position is not a mine
				continue; // if it is, regenerate
			}

			grid[ranX][ranY] = 9; // set the location to a mine
			numLeft--; // decrease number of mines by 1
		}

		// generating neighbor values
		for (int i = 0; i < WID; i++) { // go thorugh every square
			for (int j = 0; j < LEN; j++) {

				if (grid[i][j] != 9) { // if its not a mine
					int value = 0; // start a counter

					for (int k = -1; k <= 1; k++) { // go through 8 neighbors
						for (int ll = -1; ll <= 1; ll++) {

							if (k + i >= 0 && j + ll >= 0 && k + i < WID
									&& j + ll < LEN) { // if in bound
								if (grid[i + k][j + ll] == 9) { // and is a mine
									value++; // increment counter
								}
							}
						}
					}

					grid[i][j] = value; // set value into grid
				}
			}
		}
		if ((LEN * WID) * 2 / 3 <= NUM) { // number might be buggy

		} else if (grid[x][y] != 0) { // if the start position is not 0 and and 0 is
			startBoard(x, y); // redo
		}

	}

	// RECURSIVE FUNCTION FOR CLEARING ZEROS//
	public void clearZeros(int x, int y) {
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) { // 8 WAY FLOOD FILL
				if (i >= 0 && j >= 0 && i < WID && j < LEN && status[i][j] == 0) {
					status[i][j] = 1; // SET CLICK STATUS TO 1
					if (grid[i][j] == 0) {
						clearZeros(i, j); // IF IT IS ANOTHER 0 USE RECURSION TO FURTHER CLEAR
					}
				}
			}
		}
	}

	// CHECK IF USER WON OR NOT//
	public void checkWin() {
		boolean won = true; // INITALIZE BOOLEAN TO TRUE
		for (int x = 0; x < WID; x++) {
			for (int y = 0; y < LEN; y++) {
				if (grid[x][y] != 9 && status[x][y] == 0) {
					won = false; // IF ANY BUTTON IS NOT MINE AND NOT CLICKED, WON IS FALSE
				}
			}
		}

		// IF WON IS TRUE//
		if (won) {
			reset.setIcon(win); // SET RESET BUTTON TO SUNGLASSES FACE
			score_num = 0;
			score.setText(scoreText(score_num)); // SET REMAINING MINES SCORE TO 0

			for (int x = 0; x < WID; x++) {
				for (int y = 0; y < LEN; y++)
					buttonArray[x][y].removeMouseListener(mouselistener); // DISABLE ALL BUTTONS
			}

			// SET GAME STATE TO 2//
			gameState = 2;

			// STOP TIMER//
			timer.stop();
		}
	}

	// AFTER USER CLICKS A MINE//
	public void loseGame() {
		for (int x = 0; x < WID; x++) {
			for (int y = 0; y < LEN; y++) {
				buttonArray[x][y].removeMouseListener(mouselistener); // DISABLE BUTTONS AFTER LOSE
			}
		}

		gameState = 3; // SET GAME STATE TO 3

		reset.setIcon(ded); // SET RESET ICON TO DEAD FACE

		timer.stop(); // STOP TIMER
	}

	// FORMAT SCORE LABEL WITH CORRECT DIGITS//
	public String scoreText(int score) {
		if (score == 0) {
			return "000";
		} else if (score < 10) {
			return "00" + String.valueOf(score_num);
		} else if (score < 100) {
			return "0" + String.valueOf(score_num);
		} else {
			return String.valueOf(score_num);
		}
	}

	// FORMAT TIME LABEL WITH CORRECT DIGITS//
	public String timeText(int time) {
		if (time == 0) {
			return "000";
		} else if (time < 10) {
			return "00" + String.valueOf(time);
		} else if (time < 100) {
			return "0" + String.valueOf(time);
		} else {
			return String.valueOf(time);
		}
	}

	// REFRESH AFTER CLEARING AND CHANGING FRAME//
	public void refresh() {
		validate();
		repaint();
	}

	public static void main(String[] args) {
		gui g = new gui();
	}
}
