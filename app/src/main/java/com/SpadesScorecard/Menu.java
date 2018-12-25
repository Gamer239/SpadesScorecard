package com.SpadesScorecard;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menu extends Activity {
    public static Game game = null;

    class NewGameBtn implements OnClickListener {

        NewGameBtn() {
        }

        public void onClick(View v) {
            if (Menu.game != null) {
                new Builder(Menu.this).setIcon(R.drawable.icon).setTitle(R.string.are_you_sure).setMessage(R.string.erase_current_game).setPositiveButton(R.string.continue_btn, null).setNegativeButton(R.string.cancel_btn, null).show();
                return;
            }
            Menu.game = new Game();
            Menu.this.startActivity(new Intent("com.SpadesScorecard.SETNAMES"));
        }
    }

    class ContinueGameBtn implements OnClickListener {

        ContinueGameBtn() {
        }

        public void onClick(View v) {
            if (Menu.game == null) {
                new Builder(Menu.this).setMessage(R.string.no_game_found).setTitle(R.string.Error).setCancelable(true).setNeutralButton(android.R.string.ok, null).show();
            } else {
                Menu.this.startActivity(new Intent("com.SpadesScorecard.SETNAMES"));
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Button continueGameButton = (Button) findViewById(R.id.continueGameButton);
        findViewById(R.id.startGameButton).setOnClickListener(new NewGameBtn());
        continueGameButton.setOnClickListener(new ContinueGameBtn());
    }
}
