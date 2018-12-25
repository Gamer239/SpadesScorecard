package com.SpadesScorecard;

class Round {
    private static final int doubleNilWinner = 200;
    private static final int nilWinner = 100;
    private int[] teamScore = {0,0};
    private int[] teamBags = {0,0};
    private int[] playerBid = {0,0,0,0};
    private int[] playerTricks = {0,0,0,0};
    private static final int underBidPenalty = 10;

    // Player Index
    private static final int pOneIdx = 0;
    private static final int pTwoIdx = 1;
    private static final int pThreeIdx = 2;
    private static final int pFourIdx = 3;

    Round(int playerOneBid, int playerOneTricks, int playerTwoBid, int playerTwoTricks, int playerThreeBid, int playerThreeTricks, int playerFourBid, int playerFourTricks) {
        this.playerBid[pOneIdx] = playerOneBid;
        this.playerBid[pTwoIdx] = playerTwoBid;
        this.playerBid[pThreeIdx] = playerThreeBid;
        this.playerBid[pFourIdx] = playerFourBid;
        this.playerTricks[pOneIdx] = playerOneTricks;
        this.playerTricks[pTwoIdx] = playerTwoTricks;
        this.playerTricks[pThreeIdx] = playerThreeTricks;
        this.playerTricks[pFourIdx] = playerFourTricks;
        calculateTeamScore(0, pOneIdx, pThreeIdx);
        calculateTeamScore(1, pTwoIdx, pFourIdx);
    }

    private void calculateTeamScore(int team, int playerA, int playerB) {
        int trueBidTotal = this.playerBid[playerA] + this.playerBid[playerB];
        int trickTotal = this.playerTricks[playerA] + this.playerTricks[playerB];
        this.teamBags[team] = 0;
        if (this.playerBid[playerA] == Game.blindNilIdx) {
            trueBidTotal++;
        }
        if (this.playerBid[playerB] == Game.blindNilIdx) {
            trueBidTotal++;
        }
        this.teamScore[team] = 0;
        if (trickTotal > trueBidTotal) {
            this.teamBags[team] = trickTotal - trueBidTotal;
        }
        if (this.playerBid[playerA] == Game.blindNilIdx) {
            if (this.playerTricks[playerA] == 0) {
                this.teamScore[team] += doubleNilWinner;
            } else {
                this.teamScore[team] -= doubleNilWinner;
            }
        }
        if (this.playerBid[playerA] == 0) {
            if (this.playerTricks[playerA] == 0) {
                this.teamScore[team] += nilWinner;
            } else {
                this.teamScore[team] -= nilWinner;
            }
        }
        if (this.playerBid[playerB] == Game.blindNilIdx) {
            if (this.playerTricks[playerB] == 0) {
                this.teamScore[team] += doubleNilWinner;
            } else {
                this.teamScore[team] -= doubleNilWinner;
            }
        }
        if (this.playerBid[playerB] == 0) {
            if (this.playerTricks[playerB] == 0) {
                this.teamScore[team] += nilWinner;
            } else {
                this.teamScore[team] -= nilWinner;
            }
        }
        if (this.teamBags[team] > 0) {
            this.teamScore[team] += trueBidTotal * 10;
            this.teamScore[team] += this.teamBags[team];
        } else if (this.teamBags[team] != 0) {
        } else {
            if (trickTotal < trueBidTotal) {
                this.teamScore[team] -= underBidPenalty * trueBidTotal;
            } else {
                this.teamScore[team] += trueBidTotal * 10;
            }
        }
    }

    int getTeamOneScore() {
        return this.teamScore[0];
    }

    int getTeamTwoScore() {
        return this.teamScore[1];
    }

    int getPlayerOneBid() {
        return this.playerBid[pOneIdx];
    }

    int getPlayerTwoBid() {
        return this.playerBid[pTwoIdx];
    }

    int getPlayerThreeBid() {
        return this.playerBid[pThreeIdx];
    }

    int getPlayerFourBid() {
        return this.playerBid[pFourIdx];
    }

    int getPlayerOneTricks() {
        return this.playerTricks[pOneIdx];
    }

    int getPlayerTwoTricks() {
        return this.playerTricks[pTwoIdx];
    }

    int getPlayerThreeTricks() {
        return this.playerTricks[pThreeIdx];
    }

    int getPlayerFourTricks() {
        return this.playerTricks[pFourIdx];
    }

    int getTeamOneBags() {
        return this.teamBags[0];
    }

    int getTeamTwoBags() {
        return this.teamBags[1];
    }

    void editPlayerOne(int playerOneBid, int playerOneTricks) {
        this.playerBid[pOneIdx] = playerOneBid;
        this.playerTricks[pOneIdx] = playerOneTricks;
        calculateTeamScore(0, pOneIdx, pThreeIdx);
    }

    void editPlayerTwo(int playerTwoBid, int playerTwoTricks) {
        this.playerBid[pTwoIdx] = playerTwoBid;
        this.playerTricks[pTwoIdx] = playerTwoTricks;
        calculateTeamScore(1, pTwoIdx, pFourIdx);
    }

    void editPlayerThree(int playerThreeBid, int playerThreeTricks) {
        this.playerBid[pThreeIdx] = playerThreeBid;
        this.playerTricks[pThreeIdx] = playerThreeTricks;
        calculateTeamScore(0, pOneIdx, pThreeIdx);
    }

    void editPlayerFour(int playerFourBid, int playerFourTricks) {
        this.playerBid[pFourIdx] = playerFourBid;
        this.playerTricks[pFourIdx] = playerFourTricks;
        calculateTeamScore(1, pTwoIdx, pFourIdx);
    }

    String displayPlayerOne() {
        if (this.playerBid[pOneIdx] == Game.blindNilIdx) {
            return "00/" + this.playerTricks[pOneIdx];
        }
        return this.playerBid[pOneIdx] + "/" + this.playerTricks[pOneIdx];
    }

    String displayPlayerTwo() {
        if (this.playerBid[pTwoIdx] == Game.blindNilIdx) {
            return "00/" + this.playerTricks[pTwoIdx];
        }
        return this.playerBid[pTwoIdx] + "/" + this.playerTricks[pTwoIdx];
    }

    String displayPlayerThree() {
        if (this.playerBid[pThreeIdx] == Game.blindNilIdx) {
            return "00/" + this.playerTricks[pThreeIdx];
        }
        return this.playerBid[pThreeIdx] + "/" + this.playerTricks[pThreeIdx];
    }

    String displayPlayerFour() {
        if (this.playerBid[pFourIdx] == Game.blindNilIdx) {
            return "00/" + this.playerTricks[pFourIdx];
        }
        return this.playerBid[pFourIdx] + "/" + this.playerTricks[pFourIdx];
    }
}
