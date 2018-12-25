package com.SpadesScorecard;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class EditRound extends Activity {
    private Button confirmButton;
    private int p1b;
    private int p1t;
    private int p2b;
    private int p2t;
    private int p3b;
    private int p3t;
    private int p4b;
    private int p4t;
    private Spinner playerFourBid;
    private Spinner playerFourTricks;
    private Spinner playerOneBid;
    private Spinner playerOneTricks;
    private Spinner playerThreeBid;
    private Spinner playerThreeTricks;
    private Spinner playerTwoBid;
    private Spinner playerTwoTricks;
    
    class FinishRoundEditBtn implements OnClickListener {

        FinishRoundEditBtn() {
        }

        public void onClick(View v) {
            try {
                EditRound.this.getBidsAndTricks();
                Menu.game.editRound(Menu.game.getRoundIndex(), EditRound.this.p1b, EditRound.this.p2b, EditRound.this.p3b, EditRound.this.p4b, EditRound.this.p1t, EditRound.this.p2t, EditRound.this.p3t, EditRound.this.p4t);
                EditRound.this.finish();
            } catch (NoSelectionException e) {
                new Builder(EditRound.this).setMessage(R.string.NoSelection).setTitle(R.string.Error).setCancelable(true).setNeutralButton(android.R.string.ok, null).show();
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editround);
        setText();
        this.confirmButton = findViewById(R.id.finishEditButton);
        setSpinners();
    }

    private void setSpinners() {
        ArrayAdapter<CharSequence> bidAdapter = ArrayAdapter.createFromResource(this, R.array.bid_array, R.layout.spinner);
        bidAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> trickAdapter = ArrayAdapter.createFromResource(this, R.array.trick_array, R.layout.spinner);
        trickAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Round round = Menu.game.getCurrentRound();
        this.playerOneBid = findViewById(R.id.playerOneBid);
        this.playerTwoBid = findViewById(R.id.playerTwoBid);
        this.playerThreeBid = findViewById(R.id.playerThreeBid);
        this.playerFourBid = findViewById(R.id.playerFourBid);
        this.playerOneTricks = findViewById(R.id.playerOneTricks);
        this.playerTwoTricks = findViewById(R.id.playerTwoTricks);
        this.playerThreeTricks = findViewById(R.id.playerThreeTricks);
        this.playerFourTricks = findViewById(R.id.playerFourTricks);
        this.playerOneBid.setAdapter(bidAdapter);
        this.playerTwoBid.setAdapter(bidAdapter);
        this.playerThreeBid.setAdapter(bidAdapter);
        this.playerFourBid.setAdapter(bidAdapter);
        this.playerOneTricks.setAdapter(trickAdapter);
        this.playerTwoTricks.setAdapter(trickAdapter);
        this.playerThreeTricks.setAdapter(trickAdapter);
        this.playerFourTricks.setAdapter(trickAdapter);
        this.playerOneBid.setSelection(round.getPlayerOneBid() + 2);
        this.playerTwoBid.setSelection(round.getPlayerTwoBid() + 2);
        this.playerThreeBid.setSelection(round.getPlayerThreeBid() + 2);
        this.playerFourBid.setSelection(round.getPlayerFourBid() + 2);
        this.playerOneTricks.setSelection(round.getPlayerOneTricks() + 1);
        this.playerTwoTricks.setSelection(round.getPlayerTwoTricks() + 1);
        this.playerThreeTricks.setSelection(round.getPlayerThreeTricks() + 1);
        this.playerFourTricks.setSelection(round.getPlayerFourTricks() + 1);
    }

    private void setText() {
        TextView playerOneName = findViewById(R.id.playerOneText);
        TextView playerTwoName = findViewById(R.id.playerTwoText);
        TextView playerThreeName = findViewById(R.id.playerThreeText);
        TextView playerFourName = findViewById(R.id.playerFourText);
        playerOneName.setText(String.format(" %s", Menu.game.getPlayerOneName()));
        playerTwoName.setText(String.format(" %s", Menu.game.getPlayerTwoName()));
        playerThreeName.setText(String.format(" %s", Menu.game.getPlayerThreeName()));
        playerFourName.setText(String.format(" %s", Menu.game.getPlayerFourName()));
    }

    protected void onResume() {
        this.confirmButton.setOnClickListener(new FinishRoundEditBtn());
        super.onResume();
    }

    private void getBidsAndTricks() throws NoSelectionException {
        String bid = getResources().getString(R.string.bid);
        String tricks = getResources().getString(R.string.tricks);
        String blindNil = getResources().getString(R.string.blind_nil);

        if (this.playerOneBid.getSelectedItem().equals(bid) ||
                this.playerTwoBid.getSelectedItem().equals(bid) ||
                this.playerThreeBid.getSelectedItem().equals(bid) ||
                this.playerFourBid.getSelectedItem().equals(bid) ||
                this.playerOneTricks.getSelectedItem().equals(tricks) ||
                this.playerTwoTricks.getSelectedItem().equals(tricks) ||
                this.playerThreeTricks.getSelectedItem().equals(tricks) ||
                this.playerFourTricks.getSelectedItem().equals(tricks)) {
            throw new NoSelectionException();
        }
        if (this.playerOneBid.getSelectedItem().equals(blindNil)) {
            this.p1b = Game.blindNilIdx;
        } else {
            this.p1b = Integer.parseInt((String) this.playerOneBid.getSelectedItem());
        }
        if (this.playerTwoBid.getSelectedItem().equals(blindNil)) {
            this.p2b = Game.blindNilIdx;
        } else {
            this.p2b = Integer.parseInt((String) this.playerTwoBid.getSelectedItem());
        }
        if (this.playerThreeBid.getSelectedItem().equals(blindNil)) {
            this.p3b = Game.blindNilIdx;
        } else {
            this.p3b = Integer.parseInt((String) this.playerThreeBid.getSelectedItem());
        }
        if (this.playerFourBid.getSelectedItem().equals(blindNil)) {
            this.p4b = Game.blindNilIdx;
        } else {
            this.p4b = Integer.parseInt((String) this.playerFourBid.getSelectedItem());
        }
        this.p1t = Integer.parseInt((String) this.playerOneTricks.getSelectedItem());
        this.p2t = Integer.parseInt((String) this.playerTwoTricks.getSelectedItem());
        this.p3t = Integer.parseInt((String) this.playerThreeTricks.getSelectedItem());
        this.p4t = Integer.parseInt((String) this.playerFourTricks.getSelectedItem());
    }
}
