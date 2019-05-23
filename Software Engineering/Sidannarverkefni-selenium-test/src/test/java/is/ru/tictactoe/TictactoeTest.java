package is.ru.tictactoe;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

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
}
