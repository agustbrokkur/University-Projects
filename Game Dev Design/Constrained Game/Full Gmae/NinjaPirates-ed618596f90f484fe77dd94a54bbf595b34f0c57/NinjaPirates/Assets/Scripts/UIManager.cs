﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class UIManager : MonoBehaviour {

    //Singleton
    private static UIManager instance = null;
    public static UIManager Instance { get { return instance; } }

    private void Awake()
    {
        if (instance != null && instance != this)
        {
            Destroy(this.gameObject);
        }
        else
        {
            instance = this;
        }
    }

    //reference variables for various UI elements.
    public UnityEngine.UI.Text gameTimer, p1Score, p2Score, p3Score, p4Score, winnerText, countDownText;
    public GameObject gameOverScreen, pauseScreen;


    //a function that resets the ui that needs resetting.
    public void ResetUI()
    {
        p1Score.text = "0";
        p2Score.text = "0";
        p3Score.text = "0";
        p4Score.text = "0";

        gameOverScreen.SetActive(false);
        pauseScreen.SetActive(false);
    }

    //setter function for the timer text.
    public void SetTimer(string text)
    {
        gameTimer.text = text;
    }


    //a function that shows the gameOver screen and displays who won the match.
    public void GameOver()
    {
        gameOverScreen.SetActive(true);
        int highest = 0, score = 0;
        for(int i = 0; i < Game.Instance.Player.Count; i++)
        {
            if(score < Game.Instance.Player[i].Score)
            {
                highest = i;
                score = Game.Instance.Player[i].Score;
            }
        } 
        if(highest == 0 && score == 0)
        {
            //todo fix for more than 2 players
            winnerText.text = "Tie!";
        }
        else
        {
            winnerText.text = "Player " + (highest + 1) + " Wins!";
        }
    }


    //a function that updates score ui with player scores.
    public void UpdateScore()
    {
        p1Score.text = Game.Instance.Player[0].Score.ToString();
        p2Score.text = Game.Instance.Player[1].Score.ToString();
        if(Game.Instance.Player.Count > 2)
        {
            p3Score.text = Game.Instance.Player[2].Score.ToString();
        }
        if (Game.Instance.Player.Count > 3)
        {
            p4Score.text = Game.Instance.Player[3].Score.ToString();
        }

    }
}
