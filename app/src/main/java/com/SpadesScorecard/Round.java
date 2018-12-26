package com.SpadesScorecard;

class Round {
    private static final int doubleNilWinner = 200;
    private static final int nilWinner = 100;
    private final int[] teamScore = {0,0};
    private final int[] teamBags = {0,0};
    private final int[] playerBid = {0,0,0,0};
    private final int[] playerTricks = {0,0,0,0};
    private static final int underBidPenalty = 10;

    Round(int playerOneBid, int playerOneTricks, int playerTwoBid, int playerTwoTricks, int playerThreeBid, int playerThreeTricks, int playerFourBid, int playerFourTricks) {
        this.playerBid[Game.pOneIdx] = playerOneBid;
        this.playerBid[Game.pTwoIdx] = playerTwoBid;
        this.playerBid[Game.pThreeIdx] = playerThreeBid;
        this.playerBid[Game.pFourIdx] = playerFourBid;
        this.playerTricks[Game.pOneIdx] = playerOneTricks;
        this.playerTricks[Game.pTwoIdx] = playerTwoTricks;
        this.playerTricks[Game.pThreeIdx] = playerThreeTricks;
        this.playerTricks[Game.pFourIdx] = playerFourTricks;
        calculateTeamScore(0, Game.pOneIdx, Game.pThreeIdx);
        calculateTeamScore(1, Game.pTwoIdx, Game.pFourIdx);
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
        return this.playerBid[Game.pOneIdx];
    }

    int getPlayerTwoBid() {
        return this.playerBid[Game.pTwoIdx];
    }

    int getPlayerThreeBid() {
        return this.playerBid[Game.pThreeIdx];
    }

    int getPlayerFourBid() {
        return this.playerBid[Game.pFourIdx];
    }

    int getPlayerOneTricks() {
        return this.playerTricks[Game.pOneIdx];
    }

    int getPlayerTwoTricks() {
        return this.playerTricks[Game.pTwoIdx];
    }

    int getPlayerThreeTricks() {
        return this.playerTricks[Game.pThreeIdx];
    }

    int getPlayerFourTricks() {
        return this.playerTricks[Game.pFourIdx];
    }

    int getTeamOneBags() {
        return this.teamBags[0];
    }

    int getTeamTwoBags() {
        return this.teamBags[1];
    }

    void editPlayerOne(int playerOneBid, int playerOneTricks) {
        this.playerBid[Game.pOneIdx] = playerOneBid;
        this.playerTricks[Game.pOneIdx] = playerOneTricks;
        calculateTeamScore(0, Game.pOneIdx, Game.pThreeIdx);
    }

    void editPlayerTwo(int playerTwoBid, int playerTwoTricks) {
        this.playerBid[Game.pTwoIdx] = playerTwoBid;
        this.playerTricks[Game.pTwoIdx] = playerTwoTricks;
        calculateTeamScore(1, Game.pTwoIdx, Game.pFourIdx);
    }

    void editPlayerThree(int playerThreeBid, int playerThreeTricks) {
        this.playerBid[Game.pThreeIdx] = playerThreeBid;
        this.playerTricks[Game.pThreeIdx] = playerThreeTricks;
        calculateTeamScore(0, Game.pOneIdx, Game.pThreeIdx);
    }

    void editPlayerFour(int playerFourBid, int playerFourTricks) {
        this.playerBid[Game.pFourIdx] = playerFourBid;
        this.playerTricks[Game.pFourIdx] = playerFourTricks;
        calculateTeamScore(1, Game.pTwoIdx, Game.pFourIdx);
    }

}
