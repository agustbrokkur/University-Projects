package is.ru.tictactoe;

import java.util.Scanner;
public class Tictactoe
{
        // Constants for slot status in game
        public static final int STATUS_EMPTY = '0';
	public static final char PLAYER_X = 'X';
	public static final char PLAYER_O = 'O';

        // Constants for game status
        public static final int XWON = 0;
        public static final int OWON = 1;
        public static final int DRAW = 2;
	

        // Tictactoe game entities
        public static final int SIZE = 3;				// Size of game board rows and columns
        public static char gameBoard[][] = new char[SIZE][SIZE];  	// Represents the board of the game
	public static char currPlayer;     	
   
	public static void InitializeBoard()
	{
		for (int i = 0; i < SIZE; i++)
		{
			for (int j = 0; j < SIZE; j++)
			{
				gameBoard[i][j] = STATUS_EMPTY;
			}
		}
	}
	
	// Initializes game bord and sets first player as player X	
	public static String InitializeGame()
	{
		InitializeBoard();
		currPlayer = PLAYER_X;
		return "refresh";					// Return value used to restart game in UI
	}
	
	// Inserts player move into board and returns which player was making the move
	public static char PlayerMove(int slot)
	{
		if (ValidateSlot(slot))					// Insert if slot is valid
		{
			int[] indicies = To2D(slot);			// Fetch 2D value of slot
			int row = indicies[0];
			int col = indicies[1];
			if (gameBoard[row][col] == STATUS_EMPTY)
			{
				gameBoard[row][col] = currPlayer;
				char player = ChangePlayer();		// Change current player and return what player did this move
				return player;				// Return value of current player to display in UI
							
			}		
		}
		return ' '; 						// Return space if move wasn't valid
	}
	
	// Slots are from 1 to 9, validate slot
	private static boolean ValidateSlot(int slot)
	{
		return ((slot >= 1) && (slot <= 9));
	}

	// Maps slot from 1D to 2D where 1 = (0,0), 2 = (0,1), 3 = (0,2), 4 = (1,0) and so on
	private static int[] To2D(int slot)
	{
		int[] indicies = new int [2];				// 0 is row and 1 is col
		switch (slot)
		{
			case 1:
				indicies[0] = 0;
				indicies[1] = 0;
				break;
                        case 2:
                                indicies[0] = 0;
                                indicies[1] = 1;
                                break;
                        case 3:
                                indicies[0] = 0;
                                indicies[1] = 2;
                                break;
                        case 4:
                                indicies[0] = 1;
                                indicies[1] = 0;
                                break;
                        case 5:
                                indicies[0] = 1;
                                indicies[1] = 1;
                                break;
                        case 6:
                                indicies[0] = 1;
                                indicies[1] = 2;
                                break;
                        case 7:
                                indicies[0] = 2;
                                indicies[1] = 0;
                                break;
                        case 8:
                                indicies[0] = 2;
                                indicies[1] = 1;
                                break;
                        case 9:
                                indicies[0] = 2;
                                indicies[1] = 2;
                                break;
		}
		return indicies;
	}
	
	// Changes current player, returns who was player before function call
	private static char ChangePlayer()
	{
		if (currPlayer == PLAYER_X)
		{
			currPlayer = PLAYER_O;
			return PLAYER_X;
		}
		else
		{
			currPlayer = PLAYER_X;
			return PLAYER_O;
		}
	}


	public static char test()
	{
		return 'X';
	}	
	
}
