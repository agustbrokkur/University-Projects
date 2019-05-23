package is.ru.tictactoe;

import static spark.Spark.*;
import spark.QueryParamsMap;
public class GameWeb
{
	
	public static void main(String[] args) 
	{
		port(getHerokuPort());
		staticFiles.location("/public");
		Tictactoe game = new Tictactoe();
		game.InitializeGame();
		// Get request when player makes move on game board
		get("/api/game", (req, res) -> 
		{
            		QueryParamsMap map = req.queryMap();
            		try 
			{
                		Integer slot = map.get("slot").integerValue();
				char player = game.PlayerMove(slot);
				return player;
	
           		}
            		catch (Exception e)
			{
                		return "Error: " + e.getMessage();
            		}
        	});

                // Get request when players want to start new game
                get("/api/refresh", (req, res) ->
                {
                        try
                        {
				String ret = game.InitializeGame();
                                return ret;

                        }
                        catch (Exception e)
                        {
                                return "Error: " + e.getMessage();
                        }
                });		
      
    	}

	static int getHerokuPort() 
	{
		ProcessBuilder psb = new ProcessBuilder();
		if (psb.environment().get("PORT") != null) 
		{
	    		return Integer.parseInt(psb.environment().get("PORT"));
		}
		return 4567;
    	}
}
