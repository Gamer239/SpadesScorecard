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
        calculateTeamOneScore();
        calculateTeamTwoScore();
    }

    private void calculateTeamOneScore() {
        int trueBidTotal = this.playerBid[pOneIdx] + this.playerBid[pThreeIdx];
        int trickTotal = this.playerTricks[pOneIdx] + this.playerTricks[pThreeIdx];
        this.teamBags[0] = 0;
        if (this.playerBid[pOneIdx] == Game.blindNilIdx) {
            trueBidTotal++;
        }
        if (this.playerBid[pThreeIdx] == Game.blindNilIdx) {
            trueBidTotal++;
        }
        this.teamScore[0] = 0;
        if (trickTotal > trueBidTotal) {
            this.teamBags[0] = trickTotal - trueBidTotal;
        }
        if (this.playerBid[pOneIdx] == Game.blindNilIdx) {
            if (this.playerTricks[pOneIdx] == 0) {
                this.teamScore[0] += doubleNilWinner;
            } else {
                this.teamScore[0] -= doubleNilWinner;
            }
        }
        if (this.playerBid[pOneIdx] == 0) {
            if (this.playerTricks[pOneIdx] == 0) {
                this.teamScore[0] += nilWinner;
            } else {
                this.teamScore[0] -= nilWinner;
            }
        }
        if (this.playerBid[pThreeIdx] == Game.blindNilIdx) {
            if (this.playerTricks[pThreeIdx] == 0) {
                this.teamScore[0] += doubleNilWinner;
            } else {
                this.teamScore[0] -= doubleNilWinner;
            }
        }
        if (this.playerBid[pThreeIdx] == 0) {
            if (this.playerTricks[pThreeIdx] == 0) {
                this.teamScore[0] += nilWinner;
            } else {
                this.teamScore[0] -= nilWinner;
            }
        }
        if (this.teamBags[0] > 0) {
            this.teamScore[0] += trueBidTotal * 10;
            this.teamScore[0] += this.teamBags[0];
        } else if (this.teamBags[0] != 0) {
        } else {
            if (trickTotal < trueBidTotal) {
                this.teamScore[0] -= underBidPenalty * trueBidTotal;
            } else {
                this.teamScore[0] += trueBidTotal * 10;
            }
        }
    }

    private void calculateTeamTwoScore() {
        int trueBidTotal = this.playerBid[pTwoIdx] + this.playerBid[pFourIdx];
        int trickTotal = this.playerTricks[pTwoIdx] + this.playerTricks[pFourIdx];
        this.teamBags[1] = 0;
        if (this.playerBid[pTwoIdx] == Game.blindNilIdx) {
            trueBidTotal++;
        }
        if (this.playerBid[pFourIdx] == Game.blindNilIdx) {
            trueBidTotal++;
        }
        this.teamScore[1] = 0;
        if (trickTotal > trueBidTotal) {
            this.teamBags[1] = trickTotal - trueBidTotal;
        }
        if (this.playerBid[pTwoIdx] == Game.blindNilIdx) {
            if (this.playerTricks[pTwoIdx] == 0) {
                this.teamScore[1] += doubleNilWinner;
            } else {
                this.teamScore[1] -= doubleNilWinner;
            }
        }
        if (this.playerBid[pTwoIdx] == 0) {
            if (this.playerTricks[pTwoIdx] == 0) {
                this.teamScore[1] += nilWinner;
            } else {
                this.teamScore[1] -= nilWinner;
            }
        }
        if (this.playerBid[pFourIdx] == Game.blindNilIdx) {
            if (this.playerTricks[pFourIdx] == 0) {
                this.teamScore[1] += doubleNilWinner;
            } else {
                this.teamScore[1] -= doubleNilWinner;
            }
        }
        if (this.playerBid[pFourIdx] == 0) {
            if (this.playerTricks[pFourIdx] == 0) {
                this.teamScore[1] += nilWinner;
            } else {
                this.teamScore[1] -= nilWinner;
            }
        }
        if (this.teamBags[1] > 0) {
            this.teamScore[1] += trueBidTotal * 10;
            this.teamScore[1] += this.teamBags[1];
        } else if (this.teamBags[1] != 0) {
        } else {
            if (trickTotal < trueBidTotal) {
                this.teamScore[1] -= underBidPenalty * trueBidTotal;
            } else {
                this.teamScore[1] += trueBidTotal * 10;
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
        calculateTeamOneScore();
    }

    void editPlayerTwo(int playerTwoBid, int playerTwoTricks) {
        this.playerBid[pTwoIdx] = playerTwoBid;
        this.playerTricks[pTwoIdx] = playerTwoTricks;
        calculateTeamTwoScore();
    }

    void editPlayerThree(int playerThreeBid, int playerThreeTricks) {
        this.playerBid[pThreeIdx] = playerThreeBid;
        this.playerTricks[pThreeIdx] = playerThreeTricks;
        calculateTeamOneScore();
    }

    void editPlayerFour(int playerFourBid, int playerFourTricks) {
        this.playerBid[pFourIdx] = playerFourBid;
        this.playerTricks[pFourIdx] = playerFourTricks;
        calculateTeamTwoScore();
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
