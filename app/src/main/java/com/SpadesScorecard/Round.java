package com.SpadesScorecard;

class Round {
    private static final int doubleNilWinner = 200;
    private static final int nilWinner = 100;
    private int playerFourBid;
    private int playerFourTricks;
    private int playerOneBid;
    private int playerOneTricks;
    private int playerThreeBid;
    private int playerThreeTricks;
    private int playerTwoBid;
    private int playerTwoTricks;
    private int[] teamScore = {0,0};
    private int[] teamBags = {0,0};
    private static final int underBidPenalty = 10;

    Round(int playerOneBid, int playerOneTricks, int playerTwoBid, int playerTwoTricks, int playerThreeBid, int playerThreeTricks, int playerFourBid, int playerFourTricks) {
        this.playerOneBid = playerOneBid;
        this.playerTwoBid = playerTwoBid;
        this.playerThreeBid = playerThreeBid;
        this.playerFourBid = playerFourBid;
        this.playerOneTricks = playerOneTricks;
        this.playerTwoTricks = playerTwoTricks;
        this.playerThreeTricks = playerThreeTricks;
        this.playerFourTricks = playerFourTricks;
        calculateTeamOneScore();
        calculateTeamTwoScore();
    }

    private void calculateTeamOneScore() {
        int trueBidTotal = this.playerOneBid + this.playerThreeBid;
        int trickTotal = this.playerOneTricks + this.playerThreeTricks;
        this.teamBags[0] = 0;
        if (this.playerOneBid == Game.blindNilIdx) {
            trueBidTotal++;
        }
        if (this.playerThreeBid == Game.blindNilIdx) {
            trueBidTotal++;
        }
        this.teamScore[0] = 0;
        if (trickTotal > trueBidTotal) {
            this.teamBags[0] = trickTotal - trueBidTotal;
        }
        if (this.playerOneBid == Game.blindNilIdx) {
            if (this.playerOneTricks == 0) {
                this.teamScore[0] += doubleNilWinner;
            } else {
                this.teamScore[0] -= doubleNilWinner;
            }
        }
        if (this.playerOneBid == 0) {
            if (this.playerOneTricks == 0) {
                this.teamScore[0] += nilWinner;
            } else {
                this.teamScore[0] -= nilWinner;
            }
        }
        if (this.playerThreeBid == Game.blindNilIdx) {
            if (this.playerThreeTricks == 0) {
                this.teamScore[0] += doubleNilWinner;
            } else {
                this.teamScore[0] -= doubleNilWinner;
            }
        }
        if (this.playerThreeBid == 0) {
            if (this.playerThreeTricks == 0) {
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
        int trueBidTotal = this.playerTwoBid + this.playerFourBid;
        int trickTotal = this.playerTwoTricks + this.playerFourTricks;
        this.teamBags[1] = 0;
        if (this.playerTwoBid == Game.blindNilIdx) {
            trueBidTotal++;
        }
        if (this.playerFourBid == Game.blindNilIdx) {
            trueBidTotal++;
        }
        this.teamScore[1] = 0;
        if (trickTotal > trueBidTotal) {
            this.teamBags[1] = trickTotal - trueBidTotal;
        }
        if (this.playerTwoBid == Game.blindNilIdx) {
            if (this.playerTwoTricks == 0) {
                this.teamScore[1] += doubleNilWinner;
            } else {
                this.teamScore[1] -= doubleNilWinner;
            }
        }
        if (this.playerTwoBid == 0) {
            if (this.playerTwoTricks == 0) {
                this.teamScore[1] += nilWinner;
            } else {
                this.teamScore[1] -= nilWinner;
            }
        }
        if (this.playerFourBid == Game.blindNilIdx) {
            if (this.playerFourTricks == 0) {
                this.teamScore[1] += doubleNilWinner;
            } else {
                this.teamScore[1] -= doubleNilWinner;
            }
        }
        if (this.playerFourBid == 0) {
            if (this.playerFourTricks == 0) {
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
        return this.playerOneBid;
    }

    int getPlayerTwoBid() {
        return this.playerTwoBid;
    }

    int getPlayerThreeBid() {
        return this.playerThreeBid;
    }

    int getPlayerFourBid() {
        return this.playerFourBid;
    }

    int getPlayerOneTricks() {
        return this.playerOneTricks;
    }

    int getPlayerTwoTricks() {
        return this.playerTwoTricks;
    }

    int getPlayerThreeTricks() {
        return this.playerThreeTricks;
    }

    int getPlayerFourTricks() {
        return this.playerFourTricks;
    }

    int getTeamOneBags() {
        return this.teamBags[0];
    }

    int getTeamTwoBags() {
        return this.teamBags[1];
    }

    void editPlayerOne(int playerOneBid, int playerOneTricks) {
        this.playerOneBid = playerOneBid;
        this.playerOneTricks = playerOneTricks;
        calculateTeamOneScore();
    }

    void editPlayerTwo(int playerTwoBid, int playerTwoTricks) {
        this.playerTwoBid = playerTwoBid;
        this.playerTwoTricks = playerTwoTricks;
        calculateTeamTwoScore();
    }

    void editPlayerThree(int playerThreeBid, int playerThreeTricks) {
        this.playerThreeBid = playerThreeBid;
        this.playerThreeTricks = playerThreeTricks;
        calculateTeamOneScore();
    }

    void editPlayerFour(int playerFourBid, int playerFourTricks) {
        this.playerFourBid = playerFourBid;
        this.playerFourTricks = playerFourTricks;
        calculateTeamTwoScore();
    }

    String displayPlayerOne() {
        if (this.playerOneBid == Game.blindNilIdx) {
            return "00/" + this.playerOneTricks;
        }
        return this.playerOneBid + "/" + this.playerOneTricks;
    }

    String displayPlayerTwo() {
        if (this.playerTwoBid == Game.blindNilIdx) {
            return "00/" + this.playerTwoTricks;
        }
        return this.playerTwoBid + "/" + this.playerTwoTricks;
    }

    String displayPlayerThree() {
        if (this.playerThreeBid == Game.blindNilIdx) {
            return "00/" + this.playerThreeTricks;
        }
        return this.playerThreeBid + "/" + this.playerThreeTricks;
    }

    String displayPlayerFour() {
        if (this.playerFourBid == Game.blindNilIdx) {
            return "00/" + this.playerFourTricks;
        }
        return this.playerFourBid + "/" + this.playerFourTricks;
    }
}
