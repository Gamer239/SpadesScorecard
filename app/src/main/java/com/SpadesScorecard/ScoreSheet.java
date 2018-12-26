package com.SpadesScorecard;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreSheet extends Activity {
    private Button editRoundButton;
    private TextView output;
    private Spinner roundNumber = null;

    class EditRoundBtn implements View.OnClickListener {

        EditRoundBtn() {
        }

        public void onClick(View v) {
            try {
                Menu.game.setRoundIndex(ScoreSheet.this.getRoundSelection() - 1);
                ScoreSheet.this.startActivity(new Intent("com.SpadesScorecard.EDITROUND"));
            } catch (Exception e) {
                new Builder(ScoreSheet.this).setMessage(R.string.InvalidInput).setTitle(R.string.Error).setCancelable(true).setNeutralButton(android.R.string.ok, null).show();
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoresheet);
        this.editRoundButton = findViewById(R.id.editRoundButton);
        this.roundNumber = findViewById(R.id.roundNumberSpinner);
        this.output = findViewById(R.id.output);
        ArrayList<String> rounds = new ArrayList<>();
        for( int i = 0; i < Menu.game.getRoundCount(); i++ ) {
            rounds.add(String.valueOf(i+1));
        }
        ArrayAdapter<String> roundAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rounds);
        roundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.roundNumber.setAdapter(roundAdapter);
    }

    protected void onResume() {
        super.onResume();
        try {
            String table = getScoreSheetText();
            this.output.setText(table);
        } catch (Exception e) {
            new Builder(this).setMessage(R.string.InvalidInput).setTitle(R.string.Error).setCancelable(true).setNeutralButton(android.R.string.ok, null).show();
        }
        this.editRoundButton.setOnClickListener(new EditRoundBtn());
    }

    private int getRoundSelection() throws InvalidInputException {
        int selection = Integer.parseInt((String) this.roundNumber.getSelectedItem());
        if (selection <= Menu.game.getRoundCount()) {
            return selection;
        }
        throw new InvalidInputException();
    }

    private String formatPlayerBidTrick(int Bid, int Tricks) {
        if(Bid == Game.blindNilIdx) {
            return getResources().getString(R.string.blind_nil) + "/" + Tricks;
        }
        return Bid + "/" + Tricks;
    }

    private String getScoreSheetText() {
        String line;
        String spacing = getResources().getString(R.string.blind_nil) + "/" + getResources().getString(R.string.high_bid_trick) + " "; //longest example score
        String output;

        output = String.format( "Round %-" + Math.max( Menu.game.getPlayerOneName().length() + 1, spacing.length() ) + "s", Menu.game.getPlayerOneName() );
        output = String.valueOf(output) + String.format( "%-" + Math.max( Menu.game.getPlayerTwoName().length() + 1, spacing.length() ) + "s", Menu.game.getPlayerTwoName() );
        output = String.valueOf(output) + String.format( "%-" + Math.max( Menu.game.getPlayerThreeName().length() + 1, spacing.length() ) + "s", Menu.game.getPlayerThreeName() );
        output = String.valueOf(output) + String.format( "%-" + Math.max( Menu.game.getPlayerFourName().length() + 1, spacing.length() ) + "s", Menu.game.getPlayerFourName() );
        output = String.valueOf(output) + "T1 Bags T1 Score T2 Bags T2 Score\n";

        for ( int i = 0; i < Menu.game.getRoundCount(); i++ ) {
            Round a = Menu.game.getRound(i);
            line = String.format("%-6s", String.valueOf(i+1));

            output = String.valueOf(output) + line;
            line = String.format("%-" + Math.max(Menu.game.getPlayerOneName().length() + 1, spacing.length()) + "s", formatPlayerBidTrick(a.getPlayerOneBid(), a.getPlayerOneTricks()));

            output = String.valueOf(output) + line;
            line = String.format("%-" + Math.max(Menu.game.getPlayerTwoName().length() + 1, spacing.length()) + "s", formatPlayerBidTrick(a.getPlayerTwoBid(), a.getPlayerTwoTricks()));

            output = String.valueOf(output) + line;
            line = String.format("%-" + Math.max(Menu.game.getPlayerThreeName().length() + 1, spacing.length()) + "s", formatPlayerBidTrick(a.getPlayerThreeBid(), a.getPlayerThreeTricks()));

            output = String.valueOf(output) + line;
            line = String.format("%-" + Math.max(Menu.game.getPlayerFourName().length() + 1, spacing.length()) + "s", formatPlayerBidTrick(a.getPlayerFourBid(),a.getPlayerFourTricks()));

            output = String.valueOf(output) + line;

            line = String.format("%-7.7s %-8.8s %-7.7s %-8.8s\n", a.getTeamOneBags(), a.getTeamOneScore(), a.getTeamTwoBags(), a.getTeamTwoScore());
            output = String.valueOf(output) + line;
        }
        return output;
    }
}
