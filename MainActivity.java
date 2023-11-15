
package com.example.memorygame;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {

    private ImageView[] buttons;
    private int[] images;
    private  int[] idOfImages = {11 , 12 , 13 , 14 , 15, 16, 17 , 18 , 21, 22, 23, 24, 25, 26 , 27 , 28};
    private TextView txt_player , txt_scorePlayer1 , txt_scorePlayer2 , txt_timer;
    private int backgroundCard, countScorePlayer1 = 0 , countScorePlayer2 = 0;
    private int card1 , card2 , firstClick , secondClick , turn = 1;
    int numOfCard = 1;
    private Button newGame;
    private CountDownTimer countDownTimer;
    private long maxTime = TimeUnit.MINUTES.toMillis(1);
    private boolean running = true;
    private MediaPlayer myMediaPlayer;


    private void prepareTheGame() {
        this.buttons = new ImageView[16];
        //row no.1
        this.buttons[0] = findViewById(R.id.first_first_Card);
        this.buttons[1] = findViewById(R.id.second_first_Card);
        this.buttons[2] = findViewById(R.id.third_first_Card);
        this.buttons[3] = findViewById(R.id.fourth_first_Card);
        //row no.2
        this.buttons[4] = findViewById(R.id.first_second_Card);
        this.buttons[5] = findViewById(R.id.second_second_Card);
        this.buttons[6] = findViewById(R.id.third_second_Card);
        this.buttons[7] = findViewById(R.id.fourth_second_Card);
        //row no.3
        this.buttons[8] = findViewById(R.id.first_third_Card);
        this.buttons[9] = findViewById(R.id.second_third_Card);
        this.buttons[10] = findViewById(R.id.third_third_Card);
        this.buttons[11] = findViewById(R.id.fourth_third_Card);
        //row no.4
        this.buttons[12] = findViewById(R.id.first_fourth_Card);
        this.buttons[13] = findViewById(R.id.second_fourth_Card);
        this.buttons[14] = findViewById(R.id.third_fourth_Card);
        this.buttons[15] = findViewById(R.id.fourth_fourth_Card);


        //images
        this.images = new int[8];
        this.images[0] = R.drawable.apple;
        this.images[1] = R.drawable.banana;
        this.images[2] = R.drawable.carrot;
        this.images[3] = R.drawable.cherry;
        this.images[4] = R.drawable.corn;
        this.images[5] = R.drawable.eggplant;
        this.images[6] = R.drawable.grapes;
        this.images[7] = R.drawable.peaches;

        backgroundCard = R.drawable.background_image_cards;

        txt_player = findViewById(R.id.txt_player);
        txt_scorePlayer1 = findViewById(R.id.txt_scorePlayer1);
        txt_scorePlayer2 = findViewById(R.id.txt_scorePlayer2);
        txt_timer = findViewById(R.id.txt_timer);
        myMediaPlayer = MediaPlayer.create(MainActivity.this , R.raw.alarm_clock);

        countDownTimer = new CountDownTimer(maxTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (running) {
                    String str = String.format(Locale.ENGLISH, "%02d : %02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

                    txt_timer.setText(str);
                } else {
                    countDownTimer.cancel();
                }
            }

            @Override
            public void onFinish() {
                txt_timer.setText("The time is up");

                myMediaPlayer.start();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        myMediaPlayer.pause();
                    }
                }, 3000);

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setMessage("The time is up!");
                alert.setCancelable(false);
                alert.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        MainActivity.this.recreate();
                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();

            }
        }.start();

    }


    private void changeThePicture(ImageView button, int location) {
            //check the location and change the picture of the card.
            if (idOfImages[location] == 11) {
                button.setImageResource(images[0]);
            }
            else if (idOfImages[location] == 12) {
                button.setImageResource(images[1]);
            }
            else if (idOfImages[location] == 13) {
                button.setImageResource(images[2]);
            }
            else if (idOfImages[location] == 14) {
                button.setImageResource(images[3]);
            }
            else if (idOfImages[location] == 15) {
                button.setImageResource(images[4]);
            }
            else if (idOfImages[location] == 16) {
                button.setImageResource(images[5]);
            }
            else if (idOfImages[location] == 17) {
                button.setImageResource(images[6]);
            }
            else if (idOfImages[location] == 18) {
                button.setImageResource(images[7]);
            }
            else if (idOfImages[location] == 21) {
                button.setImageResource(images[0]);
            }
            else if (idOfImages[location] == 22) {
                button.setImageResource(images[1]);
            }
            else if (idOfImages[location] == 23) {
                button.setImageResource(images[2]);
            }
            else if (idOfImages[location] == 24) {
                button.setImageResource(images[3]);
            }
            else if (idOfImages[location] == 25) {
                button.setImageResource(images[4]);
            }
            else if (idOfImages[location] == 26) {
                button.setImageResource(images[5]);
            }
            else if (idOfImages[location] == 27) {
                button.setImageResource(images[6]);
            }
            else if (idOfImages[location] == 28) {
                button.setImageResource(images[7]);
            }


            if (numOfCard == 1) {
                card1 = idOfImages[location];
                if (card1 > 20) {
                    card1 -= 10;
                }
                numOfCard = 2;
                firstClick = location;

                button.setEnabled(false);
            }
            else if (numOfCard == 2) {
                card2 = idOfImages[location];
                if (card2 > 20) {
                    card2 -= 10;
                }
                numOfCard = 1;
                secondClick = location;

                //Blocks the ability to click on the rest of the images.
                for (int i = 0; i < buttons.length; i ++){
                    buttons[i].setEnabled(false);
                }


                //Delay the disappearance of the pictures.
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //check if the cards are equals.
                        isMatched();
                    }
                }, 700);
            }

    }


    private void isMatched() {
        if (card1 == card2){

            for (int i = 0; i < buttons.length; i++){
                if (firstClick == i){
                    buttons[i].setVisibility(View.INVISIBLE);
                }
            }

            for (int i = 0; i < buttons.length; i++){
                if (secondClick == i){
                    buttons[i].setVisibility(View.INVISIBLE);
                    break;
                }
            }


            //add points to player
            if (turn == 1){
                countScorePlayer1++;
                txt_scorePlayer1.setText("" +countScorePlayer1);
            }
            else if (turn == 2){
                countScorePlayer2++;
                txt_scorePlayer2.setText("" + countScorePlayer2);
            }

        }

        else {
            for (int i = 0; i < buttons.length; i++){
                buttons[i].setImageResource(backgroundCard);
            }

            if (turn == 1) {
                turn = 2;
                txt_player.setText("Turn of: Player 2");
            }
            else if (turn == 2) {
                turn = 1;
                txt_player.setText("Turn of: Player 1");
            }
        }

        //Allows the ability to click on the rest of the images.
        for (int i = 0; i < buttons.length; i++){
            buttons[i].setEnabled(true);
        }

        gameOver();

    }


    private void step(ImageView button , int location) {
        changeThePicture(button , location);
    }

    private boolean invalidVisible() {
        return buttons[0].getVisibility() == View.INVISIBLE && buttons[1].getVisibility() == View.INVISIBLE &&
                buttons[2].getVisibility() == View.INVISIBLE && buttons[3].getVisibility() == View.INVISIBLE &&
                buttons[4].getVisibility() == View.INVISIBLE && buttons[5].getVisibility() == View.INVISIBLE &&
                buttons[6].getVisibility() == View.INVISIBLE && buttons[7].getVisibility() == View.INVISIBLE &&
                buttons[8].getVisibility() == View.INVISIBLE && buttons[9].getVisibility() == View.INVISIBLE &&
                buttons[10].getVisibility() == View.INVISIBLE && buttons[11].getVisibility() == View.INVISIBLE &&
                buttons[12].getVisibility() == View.INVISIBLE && buttons[13].getVisibility() == View.INVISIBLE &&
                buttons[14].getVisibility() == View.INVISIBLE && buttons[15].getVisibility() == View.INVISIBLE;

    }

    private void gameOver() {
        if (invalidVisible()){
            //check who's the winner - text of the name of the winner
            txt_timer.setVisibility(View.GONE);
            running = false;
            myMediaPlayer.pause();

            String strWin = "";
            if (countScorePlayer1 > countScorePlayer2){
                strWin = "Player 1";
            }
            else if (countScorePlayer2 > countScorePlayer1) {
                strWin = "Player 2";
            }
            else strWin = "TE-KO";

            //message box popup!!!
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setMessage("Game Over\n\nThe winner is: " + strWin + "!!!\n\nPlayer 1: " + countScorePlayer1 + " points.\nPlayer 2: " + countScorePlayer2 + " points.");
            alert.setCancelable(false);
            alert.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    MainActivity.this.recreate();
                }
            });
            alert.setNegativeButton("Ok, Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    exitApp();
                }
            });
            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initialization of the buttons.
        prepareTheGame();

        //shuffle
        Collections.shuffle(Arrays.asList(buttons));


        //on click
        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step(buttons[0] , 0);
            }

        });

        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step(buttons[1] , 1);
            }
        });

        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step(buttons[2] , 2);
            }
        });

        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step(buttons[3] , 3);
            }
        });

        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step(buttons[4] , 4);
            }
        });

        buttons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step(buttons[5] , 5);
            }
        });

        buttons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step(buttons[6] , 6);
            }
        });

        buttons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step(buttons[7] , 7);
            }
        });

        buttons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step(buttons[8] , 8);
            }
        });

        buttons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step(buttons[9] , 9);
            }
        });


        buttons[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step(buttons[10] , 10);
            }
        });

        buttons[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step(buttons[11] , 11);
            }
        });

        buttons[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step(buttons[12] , 12);
            }
        });

        buttons[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step(buttons[13] , 13);
            }
        });

        buttons[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step(buttons[14] , 14);
            }
        });

        buttons[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step(buttons[15] , 15);
            }
        });

        //new game button.
        newGame = findViewById(R.id.btn_newGame);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.recreate();
                return;
            }
        });

    }


    private void exitApp() {
        AlertDialog.Builder alertExit = new AlertDialog.Builder(MainActivity.this);
        alertExit.setMessage("Are you sure to exit from the application?");
        alertExit.setCancelable(false);
        alertExit.setPositiveButton("Yes, bye!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertExit.setNegativeButton("OOPS!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.recreate();
            }
        });

        alertExit.setCancelable(true);
        AlertDialog alertDialog = alertExit.create();
        alertDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.memory_game , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_music: {

                Toast.makeText(this, "The music is play now!", Toast.LENGTH_SHORT).show();
                return true;
            }

            case R.id.settings: {
                //Toast.makeText(this, "You clicked on the Settings item", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this , Settings.class);
                startActivity(intent);
                return true;
            }

            case R.id.timer: {
                if (txt_timer.getVisibility() == View.GONE){
                    Toast.makeText(this, "The timer is already turn off", Toast.LENGTH_SHORT).show();
                }
                else {
                    countDownTimer.cancel();
                    txt_timer.setVisibility(View.GONE);
                }
                return true;
            }

            case R.id.exit: {
                exitApp();
                return true;
            }

            default: {
                return super.onOptionsItemSelected(item);
            }

        }

    }


}