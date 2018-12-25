package com.SpadesScorecard;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class Scoreboard extends Activity {
    private static int p4tPos = 0;
    private static int p4bPos = 0;
    private static int p3tPos = 0;
    private static int p3bPos = 0;
    private static int p2tPos = 0;
    private static int p2bPos = 0;
    private static int p1tPos = 0;
    private static int p1bPos = 0;
    private Spinner playerFourBid;
    private Spinner playerFourTricks;
    private Spinner playerOneBid;
    private Spinner playerOneTricks;
    private Spinner playerThreeBid;
    private Spinner playerThreeTricks;
    private Spinner playerTwoBid;
    private Spinner playerTwoTricks;
    private int p1b;
    private int p1t;
    private int p2b;
    private int p2t;
    private int p3b;
    private int p3t;
    private int p4b;
    private int p4t;
    private TextView playerFourName;
    private TextView playerFourScore;
    private TextView playerOneName;
    private TextView playerOneScore;
    private TextView playerThreeName;
    private TextView playerThreeScore;
    private TextView playerTwoName;
    private TextView playerTwoScore;

    class FinishRoundBtn implements OnClickListener {

        FinishRoundBtn() {
        }

        public void onClick(View v) {
            try {
                Scoreboard.this.getBidsAndTricks();
                Menu.game.addRound(Scoreboard.this.p1b, Scoreboard.this.p1t, Scoreboard.this.p2b, Scoreboard.this.p2t, Scoreboard.this.p3b, Scoreboard.this.p3t, Scoreboard.this.p4b, Scoreboard.this.p4t);
                Scoreboard.this.checkForWin();
                Scoreboard.this.clearBoard();
            } catch (NoSelectionException e) {
                new Builder(Scoreboard.this).setMessage(R.string.NoSelection).setTitle(R.string.Error).setCancelable(true).setNeutralButton(android.R.string.ok, null).show();
            }  catch (NotEnoughTricksException e) {
                new Builder(Scoreboard.this).setMessage(R.string.NotEnoughTricks).setTitle(R.string.Error).setCancelable(true).setNeutralButton(android.R.string.ok, null).show();
            }
        }
    }

    class ScoreSheetBtn implements OnClickListener {
        ScoreSheetBtn() {
        }

        public void onClick(View v) {
            Scoreboard.this.startActivity(new Intent("com.SpadesScorecard.SCORESHEET"));
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSelections();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);
        this.playerOneName = (TextView) findViewById(R.id.playerOneText);
        this.playerTwoName = (TextView) findViewById(R.id.playerTwoText);
        this.playerThreeName = (TextView) findViewById(R.id.playerThreeText);
        this.playerFourName = (TextView) findViewById(R.id.playerFourText);
        this.playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        this.playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        this.playerThreeScore = (TextView) findViewById(R.id.playerThreeScore);
        this.playerFourScore = (TextView) findViewById(R.id.playerFourScore);
        playerOneBid = (Spinner) findViewById(R.id.playerOneBid);
        playerTwoBid = (Spinner) findViewById(R.id.playerTwoBid);
        playerThreeBid = (Spinner) findViewById(R.id.playerThreeBid);
        playerFourBid = (Spinner) findViewById(R.id.playerFourBid);
        playerOneTricks = (Spinner) findViewById(R.id.playerOneTricks);
        playerTwoTricks = (Spinner) findViewById(R.id.playerTwoTricks);
        playerThreeTricks = (Spinner) findViewById(R.id.playerThreeTricks);
        playerFourTricks = (Spinner) findViewById(R.id.playerFourTricks);
        ArrayAdapter<CharSequence> bidAdapter = ArrayAdapter.createFromResource(this, R.array.bid_array, R.layout.spinner);
        bidAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> trickAdapter = ArrayAdapter.createFromResource(this, R.array.trick_array, R.layout.spinner);
        trickAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerOneBid.setAdapter(bidAdapter);
        playerTwoBid.setAdapter(bidAdapter);
        playerThreeBid.setAdapter(bidAdapter);
        playerFourBid.setAdapter(bidAdapter);
        playerOneTricks.setAdapter(trickAdapter);
        playerTwoTricks.setAdapter(trickAdapter);
        playerThreeTricks.setAdapter(trickAdapter);
        playerFourTricks.setAdapter(trickAdapter);
        setSelections();
    }

    private void clearBoard() {
        playerOneBid.setSelection(0);
        playerTwoBid.setSelection(0);
        playerThreeBid.setSelection(0);
        playerFourBid.setSelection(0);
        playerOneTricks.setSelection(0);
        playerTwoTricks.setSelection(0);
        playerThreeTricks.setSelection(0);
        playerFourTricks.setSelection(0);
    }

    protected void onResume() {
        super.onResume();
        setSelections();
        setScreen();
        findViewById(R.id.finishRoundButton).setOnClickListener(new FinishRoundBtn());
        findViewById(R.id.scoreSheetButton).setOnClickListener(new ScoreSheetBtn());
    }

    protected void onStop() {
        super.onStop();
        getSelections();
    }

    protected void onDestroy() {
        super.onDestroy();
        getSelections();
    }

    private void checkForWin() {
        if (Menu.game.checkWin() == Game.noWinner) {
            setScreen();
        } else if (Menu.game.checkWin() == Game.teamOneWinner) {
            setScreen();
            new Builder(this).setMessage(R.string.team_one_wins).setTitle(R.string.winner).setCancelable(true).setNeutralButton(android.R.string.ok, null).show();
        } else if (Menu.game.checkWin() == Game.teamTwoWinner) {
            setScreen();
            new Builder(this).setMessage(R.string.team_two_wins).setTitle(R.string.winner).setCancelable(true).setNeutralButton(android.R.string.ok, null).show();
        } else if (Menu.game.checkWin() == Game.teamTieWinner) {
            setScreen();
            new Builder(this).setMessage(R.string.tie).setTitle(R.string.continue_playing).setCancelable(true).setNeutralButton(android.R.string.ok, null).show();
        }
    }

    private void getBidsAndTricks() throws NoSelectionException, NotEnoughTricksException {
        String bid = getResources().getString(R.string.bid);
        String tricks = getResources().getString(R.string.tricks);
        String blindNil = getResources().getString(R.string.blind_nil);

        if ( playerOneBid.getSelectedItem().equals(bid) ||
                playerTwoBid.getSelectedItem().equals(bid) ||
                playerThreeBid.getSelectedItem().equals(bid) ||
                playerFourBid.getSelectedItem().equals(bid) ||
                playerOneTricks.getSelectedItem().equals(tricks) ||
                playerTwoTricks.getSelectedItem().equals(tricks) ||
                playerThreeTricks.getSelectedItem().equals(tricks) ||
                playerFourTricks.getSelectedItem().equals(tricks)) {
            throw new NoSelectionException();
        }
        if (playerOneBid.getSelectedItem().equals(blindNil)) {
            this.p1b = Game.blindNilIdx;
        } else {
            this.p1b = Integer.parseInt((String) playerOneBid.getSelectedItem());
        }
        if (playerTwoBid.getSelectedItem().equals(blindNil)) {
            this.p2b = Game.blindNilIdx;
        } else {
            this.p2b = Integer.parseInt((String) playerTwoBid.getSelectedItem());
        }
        if (playerThreeBid.getSelectedItem().equals(blindNil)) {
            this.p3b = Game.blindNilIdx;
        } else {
            this.p3b = Integer.parseInt((String) playerThreeBid.getSelectedItem());
        }
        if ( playerFourBid.getSelectedItem().equals( blindNil ) ) {
            this.p4b = Game.blindNilIdx;
        } else {
            this.p4b = Integer.parseInt((String) playerFourBid.getSelectedItem());
        }
        this.p1t = Integer.parseInt((String) playerOneTricks.getSelectedItem());
        this.p2t = Integer.parseInt((String) playerTwoTricks.getSelectedItem());
        this.p3t = Integer.parseInt((String) playerThreeTricks.getSelectedItem());
        this.p4t = Integer.parseInt((String) playerFourTricks.getSelectedItem());

        if( ( this.p1t + this.p2t + this.p3t + this.p4t ) != 13 )
        {
            throw new NotEnoughTricksException();
        }
    }

    private void setScreen() {
        String team_score = getResources().getString(R.string.team_score);
        String team_bags = getResources().getString(R.string.team_bags);
        this.playerOneName.setText(String.format(" %s", Menu.game.getPlayerOneName()));
        this.playerTwoName.setText(String.format(" %s", Menu.game.getPlayerTwoName()));
        this.playerThreeName.setText(String.format(" %s", Menu.game.getPlayerThreeName()));
        this.playerFourName.setText(String.format(" %s", Menu.game.getPlayerFourName()));
        this.playerOneScore.setText(String.format(Locale.US, " %s %d\n %s %d", team_score, Menu.game.getTeamOneTotal(), team_bags, Menu.game.getTeamOneBags()));
        this.playerTwoScore.setText(String.format(Locale.US, " %s %d\n %s %d", team_score, Menu.game.getTeamTwoTotal(), team_bags, Menu.game.getTeamTwoBags()));
        this.playerThreeScore.setText(String.format(Locale.US, " %s %d\n %s %d", team_score, Menu.game.getTeamOneTotal(), team_bags, Menu.game.getTeamOneBags()));
        this.playerFourScore.setText(String.format(Locale.US, " %s %d\n %s %d", team_score, Menu.game.getTeamTwoTotal(), team_bags, Menu.game.getTeamTwoBags()));
    }

    private void getSelections() {
        p1bPos = playerOneBid.getSelectedItemPosition();
        p2bPos = playerTwoBid.getSelectedItemPosition();
        p3bPos = playerThreeBid.getSelectedItemPosition();
        p4bPos = playerFourBid.getSelectedItemPosition();
        p1tPos = playerOneTricks.getSelectedItemPosition();
        p2tPos = playerTwoTricks.getSelectedItemPosition();
        p3tPos = playerThreeTricks.getSelectedItemPosition();
        p4tPos = playerFourTricks.getSelectedItemPosition();
    }

    private void setSelections() {
        playerOneBid.setSelection(p1bPos);
        playerTwoBid.setSelection(p2bPos);
        playerThreeBid.setSelection(p3bPos);
        playerFourBid.setSelection(p4bPos);
        playerOneTricks.setSelection(p1tPos);
        playerTwoTricks.setSelection(p2tPos);
        playerThreeTricks.setSelection(p3tPos);
        playerFourTricks.setSelection(p4tPos);
    }
}
