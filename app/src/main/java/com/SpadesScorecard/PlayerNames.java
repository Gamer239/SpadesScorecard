package com.SpadesScorecard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PlayerNames extends Activity {
    private TextView playerFourText;
    private TextView playerOneText;
    private TextView playerThreeText;
    private TextView playerTwoText;

    class ConfirmBtn implements OnClickListener {
        ConfirmBtn() {
        }

        public void onClick(View v) {
            Menu.game.setPlayerOneName(PlayerNames.this.playerOneText.getText().toString());
            Menu.game.setPlayerTwoName(PlayerNames.this.playerTwoText.getText().toString());
            Menu.game.setPlayerThreeName(PlayerNames.this.playerThreeText.getText().toString());
            Menu.game.setPlayerFourName(PlayerNames.this.playerFourText.getText().toString());

            if( PlayerNames.this.playerOneText.getText().toString().equals("") )
            {
                Menu.game.setPlayerOneName(getResources().getString(R.string.player_1_name_hint));
            }

            if( PlayerNames.this.playerTwoText.getText().toString().equals("") )
            {
                Menu.game.setPlayerTwoName(getResources().getString(R.string.player_2_name_hint));
            }

            if( PlayerNames.this.playerThreeText.getText().toString().equals("") )
            {
                Menu.game.setPlayerThreeName(getResources().getString(R.string.player_3_name_hint));
            }

            if( PlayerNames.this.playerFourText.getText().toString().equals("") )
            {
                Menu.game.setPlayerFourName(getResources().getString(R.string.player_4_name_hint));
            }


            PlayerNames.this.startActivity(new Intent("com.SpadesScorecard.BEGINGAME"));
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playernames);
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        this.playerOneText = (TextView) findViewById(R.id.playerOneText);
        this.playerTwoText = (TextView) findViewById(R.id.playerTwoText);
        this.playerThreeText = (TextView) findViewById(R.id.playerThreeText);
        this.playerFourText = (TextView) findViewById(R.id.playerFourText);
        confirmButton.setOnClickListener(new ConfirmBtn());
    }
}
