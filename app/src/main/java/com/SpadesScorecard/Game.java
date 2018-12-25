package com.SpadesScorecard;

import java.util.ArrayList;

class Game {

    private String playerFourName;
    private String playerOneName;
    private String playerThreeName;
    private String playerTwoName;
    private int roundIndex;
    private final ArrayList<Round> roundList = new ArrayList<>();
    private int teamOneBags = 0;
    private int teamOneTotal = 0;
    private int teamTwoBags = 0;
    private int teamTwoTotal = 0;

    //Static Game Information
    private final int bagPenalty = 100;
    private final int bagsAllowed = 10;
    private final static int winningScore = 500;
    final static int blindNilIdx = -1;

    //Static Game State Winners
    final static int noWinner = 0;
    final static int teamOneWinner = 1;
    final static int teamTwoWinner = 2;
    final static int teamTieWinner = 3;

    // Player Index
    static final int pOneIdx = 0;
    static final int pTwoIdx = 1;
    static final int pThreeIdx = 2;
    static final int pFourIdx = 3;

    void addRound(int playerOneBid, int playerOneTricks, int playerTwoBid, int playerTwoTricks, int playerThreeBid, int playerThreeTricks, int playerFourBid, int playerFourTricks) {
        this.roundList.add(new Round(playerOneBid, playerOneTricks, playerTwoBid, playerTwoTricks, playerThreeBid, playerThreeTricks, playerFourBid, playerFourTricks));
    }

    private void calculateTeamOneScore() {
        int total = 0;
        int totalBags = 0;
        for (Round a : this.roundList) {
            total += a.getTeamOneScore();
            totalBags += a.getTeamOneBags();
        }
        this.teamOneBags = totalBags;
        while (totalBags >= this.bagsAllowed) {
            total -= this.bagPenalty;
            totalBags -= this.bagsAllowed;
        }
        this.teamOneTotal = total;
    }

    private void calculateTeamTwoScore() {
        int total = 0;
        int totalBags = 0;
        for (Round a : this.roundList) {
            total += a.getTeamTwoScore();
            totalBags += a.getTeamTwoBags();
        }
        this.teamTwoBags = totalBags;
        while (totalBags >= this.bagsAllowed) {
            total -= this.bagPenalty;
            totalBags -= this.bagsAllowed;
        }
        this.teamTwoTotal = total;
    }

    int checkWin() {
        calculateTeamOneScore();
        calculateTeamTwoScore();
        if (this.teamOneTotal < winningScore && this.teamTwoTotal < winningScore) {
            return noWinner;
        }
        if (this.teamOneTotal == this.teamTwoTotal) {
            return teamTieWinner;
        }
        if (this.teamOneTotal > this.teamTwoTotal) {
            return teamOneWinner;
        }
        return teamTwoWinner;
    }

    void setPlayerOneName(String name) {
        this.playerOneName = name;
    }

    void setPlayerTwoName(String name) {
        this.playerTwoName = name;
    }

    void setPlayerThreeName(String name) {
        this.playerThreeName = name;
    }

    void setPlayerFourName(String name) {
        this.playerFourName = name;
    }

    String getPlayerOneName() {
        return this.playerOneName;
    }

    String getPlayerTwoName() {
        return this.playerTwoName;
    }

    String getPlayerThreeName() {
        return this.playerThreeName;
    }

    String getPlayerFourName() {
        return this.playerFourName;
    }

    int getTeamOneTotal() {
        calculateTeamOneScore();
        return this.teamOneTotal;
    }

    int getTeamTwoTotal() {
        calculateTeamTwoScore();
        return this.teamTwoTotal;
    }

    int getTeamOneBags() {
        return this.teamOneBags;
    }

    int getTeamTwoBags() {
        return this.teamTwoBags;
    }

    void setRoundIndex(int index) {
        this.roundIndex = index;
    }

    int getRoundIndex() {
        return this.roundIndex;
    }

    void editRound(int index, int p1b, int p2b, int p3b, int p4b, int p1t, int p2t, int p3t, int p4t) {
        Round round = this.roundList.get(index);
        round.editPlayerOne(p1b, p1t);
        round.editPlayerTwo(p2b, p2t);
        round.editPlayerThree(p3b, p3t);
        round.editPlayerFour(p4b, p4t);
        calculateTeamOneScore();
        calculateTeamTwoScore();
    }

    Round getCurrentRound() {
        return this.roundList.get(this.roundIndex);
    }

    Round getRound(int selectedRound) {
        return this.roundList.get(selectedRound);
    }

    int getRoundCount() {
        return this.roundList.size();
    }
}
