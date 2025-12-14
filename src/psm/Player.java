/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psm;

public class Player {

    private String name;
    private int wins;

    public Player(String name) {
        this.name = name;
        this.wins = 0;
    }

    public void addWin() { //incrementWins
        this.wins++;
    }

    public int getWins() {
        return wins;
    }

    public String getName() {
        return name;
    }

    public void resetScore() {
        this.wins = 0;
    }
}
