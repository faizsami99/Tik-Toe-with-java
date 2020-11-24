
import java.awt.BorderLayout;
import java.awt.event.*;
// import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
// import java.awt.ActionEvent;
import java.awt.GridLayout;
import javax.swing.*;
import java.time.LocalTime;


class Game extends JFrame  implements ActionListener
{
	private JLabel heading, time, east, west;
	LocalTime t1;
	private Font font = new Font("", Font.BOLD, 36);
	
	JButton[] btns = new JButton[9];

	JPanel mainPanel;

	// game logic variables

	int gameChances[] = {2,2,2,2,2,2,2,2,2};
	int activePlayer=0;

	int winner[][] = 
	{
		{0,1,2},
		{3,4,5},
		{6,7,8},
		{0,3,6},
		{1,4,7},
		{2,5,8},
		{0,4,8},
		{2,4,6}
	};

	int winpl = 2;

	char[] players={'X', '0'};

	boolean gameOver = false;

	public Game()
	{

		setTitle("TikToe Game");
		setLocation(150,50);

		this.createGUI();
		
		setVisible(true);
		setResizable(false);

		ImageIcon icon = new ImageIcon("./Image/icon.png");
		setIconImage(icon.getImage());
		setSize(550,550);
		
		this.getContentPane().setBackground(Color.decode("#0377fc"));
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void createGUI()
	{
		//Border Layout;

		super.setLayout(new BorderLayout());

		// Heading of the Game;
		heading = new JLabel("Let's Play!");
		heading.setIcon(new ImageIcon("./Image/Ga.png"));
		heading.setFont(font);
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		// heading.setVerticalAlignment(SwingConstants.CENTER);
		heading.setForeground(Color.WHITE);
		super.add(heading, BorderLayout.NORTH);
		// heading.setHorizontalTextPosition(SwingConstants.CENTER);
		// heading.setVerticalTextPosition(SwingConstants.BOTTOM);

		east = new JLabel("  ");
		super.add(east, BorderLayout.EAST);

		west = new JLabel("  ");
		super.add(west, BorderLayout.WEST);

		t1 = LocalTime.now();
		time = new JLabel(t1.getHour() + ":" + t1.getMinute() + ":" + t1.getSecond());
		Timer t = new Timer(1000, ae ->
		{
			t1 = LocalTime.now();
			time.setText(t1.getHour() + ":" + t1.getMinute() + ":" + t1.getSecond());
		});
		t.start();
		time.setFont(font);
		time.setForeground(Color.WHITE);
		time.setHorizontalAlignment(JLabel.CENTER);
		super.add(time, BorderLayout.SOUTH);

		// Panel Section
		mainPanel = new JPanel();

		mainPanel.setLayout(new GridLayout(3,3));

		for(int i=1; i<=9; i++)
		{
			JButton btn = new JButton();
			// btn.setIcon(new ImageIcon("./Image/o.png"));
			// btn.setFont(font);
			btn.setBackground(Color.decode("#03cffc"));
			mainPanel.add(btn);
			btns[i-1] = btn;
			btn.addActionListener(this);
			btn.setName(String.valueOf(i-1));
		}

		this.add(mainPanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		JButton currentButton = (JButton) ae.getSource();
		String nameStr = currentButton.getName();
		int name = Integer.parseInt(nameStr.trim());

		if(gameOver == true)
		{
			JOptionPane.showMessageDialog(this, "Game Over");
			return;
		}

		if(gameChances[name]==2)
		{
			if(activePlayer==1)
			{
				currentButton.setIcon(new ImageIcon("./Image/o.png"));
				gameChances[name] = activePlayer;
				activePlayer = 0;
				
			}
			else
			{
				currentButton.setIcon(new ImageIcon("./Image/cancel.png"));
				gameChances[name] = activePlayer;
				activePlayer = 1;
				
			}

			for(int[] temp: winner)
			{
				if((gameChances[temp[0]]==gameChances[temp[1]])&&(gameChances[temp[1]]==gameChances[temp[2]]) && (gameChances[temp[2]]!=2))
				{
					winpl = gameChances[temp[0]];
					gameOver = true;
					JOptionPane.showMessageDialog(null, "Player "+players[winpl]+" won" );
					int i = JOptionPane.showConfirmDialog(this, "do you want to play more ??");
					System.out.println(i);
					if (i==0)
					{
						super.setVisible(false);
						new Game();
						
					}
					else if(i==1)
					{
						System.exit(3234);
					}
					else
					{

					}
					
				}
			}

			//..want to start again
			// Draw logic
			int counter = 0;
			for (int i: gameChances)
			{
				if (i==2)
				{
					counter++;
					break;
				}
			}

			if(counter==0 && gameOver==false)
			{
				JOptionPane.showMessageDialog(null, "Match is draw");
				int p = JOptionPane.showConfirmDialog(this, "Play more?..");
				if (p==0)
				{
					this.setVisible(false);
					new Game();
				}
				else if (p==1)
				{
					System.exit(3321);
				}
				else
				{
					
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Dekh Be bhara hua hun mei");
		}
	}
}