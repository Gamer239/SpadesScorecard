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
    private int teamOneBags = 0;
    private int teamOneScore = 0;
    private int teamTwoBags = 0;
    private int teamTwoScore = 0;
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
        this.teamOneBags = 0;
        if (this.playerOneBid == Game.blindNilIdx) {
            trueBidTotal++;
        }
        if (this.playerThreeBid == Game.blindNilIdx) {
            trueBidTotal++;
        }
        this.teamOneScore = 0;
        if (trickTotal > trueBidTotal) {
            this.teamOneBags = trickTotal - trueBidTotal;
        }
        if (this.playerOneBid == Game.blindNilIdx) {
            if (this.playerOneTricks == 0) {
                this.teamOneScore += doubleNilWinner;
            } else {
                this.teamOneScore -= doubleNilWinner;
            }
        }
        if (this.playerOneBid == 0) {
            if (this.playerOneTricks == 0) {
                this.teamOneScore += nilWinner;
            } else {
                this.teamOneScore -= nilWinner;
            }
        }
        if (this.playerThreeBid == Game.blindNilIdx) {
            if (this.playerThreeTricks == 0) {
                this.teamOneScore += doubleNilWinner;
            } else {
                this.teamOneScore -= doubleNilWinner;
            }
        }
        if (this.playerThreeBid == 0) {
            if (this.playerThreeTricks == 0) {
                this.teamOneScore += nilWinner;
            } else {
                this.teamOneScore -= nilWinner;
            }
        }
        if (this.teamOneBags > 0) {
            this.teamOneScore += trueBidTotal * 10;
            this.teamOneScore += this.teamOneBags;
        } else if (this.teamOneBags != 0) {
        } else {
            if (trickTotal < trueBidTotal) {
                this.teamOneScore -= underBidPenalty * trueBidTotal;
            } else {
                this.teamOneScore += trueBidTotal * 10;
            }
        }
    }

    private void calculateTeamTwoScore() {
        int trueBidTotal = this.playerTwoBid + this.playerFourBid;
        int trickTotal = this.playerTwoTricks + this.playerFourTricks;
        this.teamTwoBags = 0;
        if (this.playerTwoBid == Game.blindNilIdx) {
            trueBidTotal++;
        }
        if (this.playerFourBid == Game.blindNilIdx) {
            trueBidTotal++;
        }
        this.teamTwoScore = 0;
        if (trickTotal > trueBidTotal) {
            this.teamTwoBags = trickTotal - trueBidTotal;
        }
        if (this.playerTwoBid == Game.blindNilIdx) {
            if (this.playerTwoTricks == 0) {
                this.teamTwoScore += doubleNilWinner;
            } else {
                this.teamTwoScore -= doubleNilWinner;
            }
        }
        if (this.playerTwoBid == 0) {
            if (this.playerTwoTricks == 0) {
                this.teamTwoScore += nilWinner;
            } else {
                this.teamTwoScore -= nilWinner;
            }
        }
        if (this.playerFourBid == Game.blindNilIdx) {
            if (this.playerFourTricks == 0) {
                this.teamTwoScore += doubleNilWinner;
            } else {
                this.teamTwoScore -= doubleNilWinner;
            }
        }
        if (this.playerFourBid == 0) {
            if (this.playerFourTricks == 0) {
                this.teamTwoScore += nilWinner;
            } else {
                this.teamTwoScore -= nilWinner;
            }
        }
        if (this.teamTwoBags > 0) {
            this.teamTwoScore += trueBidTotal * 10;
            this.teamTwoScore += this.teamTwoBags;
        } else if (this.teamTwoBags != 0) {
        } else {
            if (trickTotal < trueBidTotal) {
                this.teamTwoScore -= underBidPenalty * trueBidTotal;
            } else {
                this.teamTwoScore += trueBidTotal * 10;
            }
        }
    }

    int getTeamOneScore() {
        return this.teamOneScore;
    }

    int getTeamTwoScore() {
        return this.teamTwoScore;
    }

    public int getPlayerOneBid() {
        return this.playerOneBid;
    }

    public int getPlayerTwoBid() {
        return this.playerTwoBid;
    }

    public int getPlayerThreeBid() {
        return this.playerThreeBid;
    }

    public int getPlayerFourBid() {
        return this.playerFourBid;
    }

    public int getPlayerOneTricks() {
        return this.playerOneTricks;
    }

    public int getPlayerTwoTricks() {
        return this.playerTwoTricks;
    }

    public int getPlayerThreeTricks() {
        return this.playerThreeTricks;
    }

    public int getPlayerFourTricks() {
        return this.playerFourTricks;
    }

    int getTeamOneBags() {
        return this.teamOneBags;
    }

    int getTeamTwoBags() {
        return this.teamTwoBags;
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
