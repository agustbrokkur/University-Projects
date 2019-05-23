package is.ru.tictactoe;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
public class TictactoeTest
{
        // Test game board init
        @Test
        public void TestBoardInit()
        {
                Tictactoe t = new Tictactoe();
                t.InitializeBoard();

                for (int i = 0; i < t.SIZE; i++)
                {
                        for (int j = 0; j < t.SIZE; j++)
                        {
                                assertEquals(t.STATUS_EMPTY, t.gameBoard[i][j]);
                        }
                }
        }
	
	// Test status of current player when game is initialized
	@Test
	public void TestGameInit()
	{
		Tictactoe t = new Tictactoe();
		t.InitializeGame();
		assertEquals(t.PLAYER_X, t.currPlayer);
	}
	
	// Test player move on game board
	@Test
	public void TestPlayerXMoveToThirdSlot()
	{
		Tictactoe t = new Tictactoe();
		t.InitializeGame();
		t.PlayerMove(3);			// Player_X moves to slot (0,2)			
		assertEquals(t.PLAYER_X, t.gameBoard[0][2]);
	}

	// Test another player move on game board
        @Test
        public void TestPlayerOMoveToNinthSlot()
        {
                Tictactoe t = new Tictactoe();
                t.InitializeGame();
                t.PlayerMove(3);                        // Player_X moves to slot (0,2)
		t.PlayerMove(9);			// PLAYER_O moves to slot (2,2)
                assertEquals(t.PLAYER_O, t.gameBoard[2][2]);
        }

        // Test another player move on game board
        @Test
        public void TestPlayerXMoveToFifthSlot()
        {
                Tictactoe t = new Tictactoe();
                t.InitializeGame();
                t.PlayerMove(5);                        // Player_X moves to slot (1,1)
                assertEquals(t.PLAYER_X, t.gameBoard[1][1]);
        }

        // Test invalid player move
        @Test
        public void TestInvalidPlayerMove()
        {
                Tictactoe t = new Tictactoe();
                t.InitializeGame();
                t.PlayerMove(13);                       // Invalid move
		for (int i = 0; i < t.SIZE; i++)
		{
			for (int j = 0; j < t.SIZE; j++)
			{
				assertEquals(t.STATUS_EMPTY, t.gameBoard[i][j]); 
			}
		}
                
        }
	
	// Test player move if game is in status DRAW
        @Test
        public void TestPlayerMoveWhenDraw()
        {
                Tictactoe t = new Tictactoe();
                t.InitializeGame();
                					// Create when game ends in a draw
		t.PlayerMove(2);
		t.PlayerMove(1);
                t.PlayerMove(5);
                t.PlayerMove(3);
                t.PlayerMove(6);
                t.PlayerMove(4);
                t.PlayerMove(7);
                t.PlayerMove(8);
		t.PlayerMove(9);         
                assertEquals(true, t.IsDraw());		// Game ended in a draw, should return true
        }
	
        // Test draw function when game is still in playing mode
        @Test
        public void TestPlayerMoveWhenNotDraw()
        {
                Tictactoe t = new Tictactoe();
                t.InitializeGame();
                                                        // Create a game that is not in draw status
                t.PlayerMove(2);
                t.PlayerMove(1);
 		assertEquals(false, t.IsDraw());	               
        }
	
        // Test won function when player x has won the game 
        @Test
        public void TestPlayerXHasWon()
        {
                Tictactoe t = new Tictactoe();
                t.InitializeGame();
                                                        // Create a game that player x wins
                t.PlayerMove(1);
                t.PlayerMove(2);
                t.PlayerMove(4);
                t.PlayerMove(3);
		String player = t.PlayerMove(7);	// PlayerMove returns player who did the move and swaps players
                assertEquals(true, t.GameWon(player, 7));
        }

        // Test won function when player O has won the game 
        @Test
        public void TestPlayerOHasWon()
        {
                Tictactoe t = new Tictactoe();
                t.InitializeGame();
                                                        // Create a game that player O wins
                t.PlayerMove(1);
                t.PlayerMove(5);
                t.PlayerMove(2);
                t.PlayerMove(3);
                t.PlayerMove(4);
                String player = t.PlayerMove(7);        // PlayerMove returns player who did the move and swaps players
                assertEquals(true, t.GameWon(player, 7));
        }
	
        // Test won function when the game has not been won
        @Test
        public void TestGameNotWon()
        {
                Tictactoe t = new Tictactoe();
                t.InitializeGame();
                                                        // Create a game that player x wins
                t.PlayerMove(1);
                t.PlayerMove(2);
                t.PlayerMove(3);
                String player = t.PlayerMove(4);
                assertEquals(false, t.GameWon(player, 7));
        }

        // Test change player function, player X to Player O
        @Test
        public void TestChangePlayerXToO()
        {
                Tictactoe t = new Tictactoe();
                t.InitializeGame();

                t.PlayerMove(1);
                assertEquals(t.PLAYER_O, t.currPlayer);
        }

        // Test change player function, player O to Player X
        @Test
        public void TestChangePlayerOToX()
        {
                Tictactoe t = new Tictactoe();
                t.InitializeGame();

                t.PlayerMove(1);
                t.PlayerMove(2);
                assertEquals(t.PLAYER_X, t.currPlayer);
        }

        // Test when slot is not available for playerX
        @Test
        public void TestSlotIsNotAvailableForPlayerX()
        {
                Tictactoe t = new Tictactoe();
                t.InitializeGame(); 
                
                t.PlayerMove(1);
                t.PlayerMove(2);
                t.PlayerMove(2);                        //unvalid and not available
                assertEquals(t.PLAYER_O, t.gameBoard[0][1]);
                assertEquals(t.PLAYER_X, t.currPlayer);

        }

        // Test when slot is not available for playerY
        @Test
        public void TestSlotIsNotAvailableForPlayerO()
        {
                Tictactoe t = new Tictactoe();
                t.InitializeGame();
                
                t.PlayerMove(1);
                t.PlayerMove(2);
                t.PlayerMove(3); 
                t.PlayerMove(3);                       //unvalid and not available
                assertEquals(t.PLAYER_X, t.gameBoard[0][2]);
                assertEquals(t.PLAYER_O, t.currPlayer);

        }

        //Test when player picks a slot he has already picked
        @Test
        public void TestIfPlayerCanPickHisOwnSlot()
        {
                Tictactoe t = new Tictactoe();
                t.InitializeGame();
                
                t.PlayerMove(1);
                t.PlayerMove(2);
                t.PlayerMove(3); 
                t.PlayerMove(2);                        //pick his own slot, unvalid
                assertEquals(t.PLAYER_O, t.gameBoard[0][1]);
                assertEquals(t.PLAYER_O, t.currPlayer);

        }

}
