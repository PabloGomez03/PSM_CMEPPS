/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psm;

public class GameEngine {
    private Player p1;
    private Player p2;
    private boolean isP1TurnToGuess; 
    
    private final int MAX_WINS = 3;

    public GameEngine() {
        this.p1 = new Player("Jugador 1");
        this.p2 = new Player("Jugador 2");
        this.isP1TurnToGuess = true; 
    }

    public Player getGuesser() {
        return isP1TurnToGuess ? p1 : p2;
    }

    public Player getChallenger() {
        return isP1TurnToGuess ? p2 : p1;
    }

    public void switchTurns() {
        this.isP1TurnToGuess = !this.isP1TurnToGuess;
    }

    public void registerGuesserWin() {
        getGuesser().addWin();
    }

    public void registerChallengerWin() {
        getChallenger().addWin();
    }

    public boolean isSeriesOver() {
        return p1.getWins() >= MAX_WINS || p2.getWins() >= MAX_WINS;
    }

    public Player getSeriesWinner() {
        if (p1.getWins() >= MAX_WINS) return p1;
        if (p2.getWins() >= MAX_WINS) return p2;
        return null;
    }
    
    public String getScoreText() {
        return p1.getName() + ": " + p1.getWins() + "  -  " + 
               p2.getName() + ": " + p2.getWins();
    }
}