package com.klerzjeh.xo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0=X 1=O
    int activePlayer =0;
    int xCount;

    boolean gameActive = true;

    //already played is 1
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] win = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void moveIn(View view)
    {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        if(gameState[tappedCounter]==2 && gameActive)
        {

            counter.setTranslationY(-1000f);


            gameState[tappedCounter]=activePlayer;


            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.x);
                xCount++;
                activePlayer = 1;

            }
            else
            {
                counter.setImageResource(R.drawable.o);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).setDuration(300);

            for(int[] winPos : win)
            {
                if(gameState[winPos[0]]==gameState[winPos[1]]&&gameState[winPos[1]]==gameState[winPos[2]]&&
                        gameState[winPos[0]]!=2) {

                    gameActive = false;

                    String pobjeda = "X";

                    if (gameState[winPos[0]] == 1) {
                        pobjeda = "O";
                    }

                    String finWinner = pobjeda + " je pobijedio!";
                    TextView winnerMsg = (TextView) findViewById(R.id.winner);
                    winnerMsg.setText(finWinner);

                    LinearLayout cloak = (LinearLayout) findViewById(R.id.playAgainLayout);
                    cloak.setVisibility(View.VISIBLE);


                }
                else if(!(gameState[winPos[0]]==gameState[winPos[1]]&&gameState[winPos[1]]==gameState[winPos[2]]&&
                        gameState[winPos[0]]!=2)&&xCount==5)
                {
                        gameActive=false;
                        TextView winnerMsg = (TextView) findViewById(R.id.winner);
                        winnerMsg.setText("Nema pobjednika!");

                        LinearLayout cloak = (LinearLayout) findViewById(R.id.playAgainLayout);
                        cloak.setVisibility(View.VISIBLE);

                }



            }
        }


    }

    public void playAgain(View view){

        gameActive=true;
        xCount=0;
        LinearLayout cloak = (LinearLayout)findViewById(R.id.playAgainLayout);
        cloak.setVisibility(View.INVISIBLE);



        activePlayer=0;

        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;

        }

        GridLayout griddy = (GridLayout)findViewById(R.id.gridy);

        for(int i=0; i<griddy.getChildCount();i++)
        {
            ((ImageView)griddy.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
