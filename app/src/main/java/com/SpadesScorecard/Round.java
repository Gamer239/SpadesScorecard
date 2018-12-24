package com.SpadesScorecard;

class Round {
    private static final int doubleNilWinner = 200;
    private static final int nilWinner = 100;
    private int[] teamScore = {0,0};
    private int[] teamBags = {0,0};
    private int[] playerBid = {0,0,0,0};
    private int[] playerTricks = {0,0,0,0};
    private static final int underBidPenalty = 10;

    Round(int playerOneBid, int playerOneTricks, int playerTwoBid, int playerTwoTricks, int playerThreeBid, int playerThreeTricks, int playerFourBid, int playerFourTricks) {
        this.playerBid[0] = playerOneBid;
        this.playerBid[1] = playerTwoBid;
        this.playerBid[2] = playerThreeBid;
        this.playerBid[3] = playerFourBid;
        this.playerTricks[0] = playerOneTricks;
        this.playerTricks[1] = playerTwoTricks;
        this.playerTricks[2] = playerThreeTricks;
        this.playerTricks[3] = playerFourTricks;
        calculateTeamOneScore();
        calculateTeamTwoScore();
    }

    private void calculateTeamOneScore() {
        int trueBidTotal = this.playerBid[0] + this.playerBid[2];
        int trickTotal = this.playerTricks[0] + this.playerTricks[2];
        this.teamBags[0] = 0;
        if (this.playerBid[0] == Game.blindNilIdx) {
            trueBidTotal++;
        }
        if (this.playerBid[2] == Game.blindNilIdx) {
            trueBidTotal++;
        }
        this.teamScore[0] = 0;
        if (trickTotal > trueBidTotal) {
            this.teamBags[0] = trickTotal - trueBidTotal;
        }
        if (this.playerBid[0] == Game.blindNilIdx) {
            if (this.playerTricks[0] == 0) {
                this.teamScore[0] += doubleNilWinner;
            } else {
                this.teamScore[0] -= doubleNilWinner;
            }
        }
        if (this.playerBid[0] == 0) {
            if (this.playerTricks[0] == 0) {
                this.teamScore[0] += nilWinner;
            } else {
                this.teamScore[0] -= nilWinner;
            }
        }
        if (this.playerBid[2] == Game.blindNilIdx) {
            if (this.playerTricks[2] == 0) {
                this.teamScore[0] += doubleNilWinner;
            } else {
                this.teamScore[0] -= doubleNilWinner;
            }
        }
        if (this.playerBid[2] == 0) {
            if (this.playerTricks[2] == 0) {
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
        int trueBidTotal = this.playerBid[1] + this.playerBid[3];
        int trickTotal = this.playerTricks[1] + this.playerTricks[3];
        this.teamBags[1] = 0;
        if (this.playerBid[1] == Game.blindNilIdx) {
            trueBidTotal++;
        }
        if (this.playerBid[3] == Game.blindNilIdx) {
            trueBidTotal++;
        }
        this.teamScore[1] = 0;
        if (trickTotal > trueBidTotal) {
            this.teamBags[1] = trickTotal - trueBidTotal;
        }
        if (this.playerBid[1] == Game.blindNilIdx) {
            if (this.playerTricks[1] == 0) {
                this.teamScore[1] += doubleNilWinner;
            } else {
                this.teamScore[1] -= doubleNilWinner;
            }
        }
        if (this.playerBid[1] == 0) {
            if (this.playerTricks[1] == 0) {
                this.teamScore[1] += nilWinner;
            } else {
                this.teamScore[1] -= nilWinner;
            }
        }
        if (this.playerBid[3] == Game.blindNilIdx) {
            if (this.playerTricks[3] == 0) {
                this.teamScore[1] += doubleNilWinner;
            } else {
                this.teamScore[1] -= doubleNilWinner;
            }
        }
        if (this.playerBid[3] == 0) {
            if (this.playerTricks[3] == 0) {
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
        return this.playerBid[0];
    }

    int getPlayerTwoBid() {
        return this.playerBid[1];
    }

    int getPlayerThreeBid() {
        return this.playerBid[2];
    }

    int getPlayerFourBid() {
        return this.playerBid[3];
    }

    int getPlayerOneTricks() {
        return this.playerTricks[0];
    }

    int getPlayerTwoTricks() {
        return this.playerTricks[1];
    }

    int getPlayerThreeTricks() {
        return this.playerTricks[2];
    }

    int getPlayerFourTricks() {
        return this.playerTricks[3];
    }

    int getTeamOneBags() {
        return this.teamBags[0];
    }

    int getTeamTwoBags() {
        return this.teamBags[1];
    }

    void editPlayerOne(int playerOneBid, int playerOneTricks) {
        this.playerBid[0] = playerOneBid;
        this.playerTricks[0] = playerOneTricks;
        calculateTeamOneScore();
    }

    void editPlayerTwo(int playerTwoBid, int playerTwoTricks) {
        this.playerBid[1] = playerTwoBid;
        this.playerTricks[1] = playerTwoTricks;
        calculateTeamTwoScore();
    }

    void editPlayerThree(int playerThreeBid, int playerThreeTricks) {
        this.playerBid[2] = playerThreeBid;
        this.playerTricks[2] = playerThreeTricks;
        calculateTeamOneScore();
    }

    void editPlayerFour(int playerFourBid, int playerFourTricks) {
        this.playerBid[3] = playerFourBid;
        this.playerTricks[3] = playerFourTricks;
        calculateTeamTwoScore();
    }

    String displayPlayerOne() {
        if (this.playerBid[0] == Game.blindNilIdx) {
            return "00/" + this.playerTricks[0];
        }
        return this.playerBid[0] + "/" + this.playerTricks[0];
    }

    String displayPlayerTwo() {
        if (this.playerBid[1] == Game.blindNilIdx) {
            return "00/" + this.playerTricks[1];
        }
        return this.playerBid[1] + "/" + this.playerTricks[1];
    }

    String displayPlayerThree() {
        if (this.playerBid[2] == Game.blindNilIdx) {
            return "00/" + this.playerTricks[2];
        }
        return this.playerBid[2] + "/" + this.playerTricks[2];
    }

    String displayPlayerFour() {
        if (this.playerBid[3] == Game.blindNilIdx) {
            return "00/" + this.playerTricks[3];
        }
        return this.playerBid[3] + "/" + this.playerTricks[3];
    }
}
