package com.example.rebecca.aadproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

/*
* Author: Luis Parcon - N0499977
* Pairs game activity, this activity will be the game screen used to play
* the pairs games, which will be a 4x4 grid
* http://opengameart.org/content/playing-cards-vector-png - deck of cards download link origin - for frontside
*b
*/


public class PairsGame extends AppCompatActivity implements View.OnClickListener
{
    //No magic numbers used here
    static final int correct =  5;
    static final int gameRows = 4;
    static final int gameColumns = 4;
    static final int cardsInPlay = (gameColumns * gameRows)/2; //need half of the numbers as we need two of each card image
    private int cardsInGrid = gameRows * gameColumns; //the number of cards in the 4x4 game space

    //Stores the selections made by the user to compare
    protected MemoryGameCard firstSelection;
    protected MemoryGameCard secondSelection;


    private MemoryGameCard[] cardsInGame;
    private int[] cardGraphics; //takes an ID from the drawable folder
    private int[] cardLocations; //The locations of the cards in the 4x4 grid

    protected int score = 0;
    private boolean isBusy = false; //checks if the app is already doing something

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pairs_game);

        Button exitBtn = (Button)findViewById(R.id.pairsExit);

        exitBtn.setOnClickListener(new View.OnClickListener() //navigate back to the main screen
        {
            @Override
            public void onClick(View view)
            {
                Intent newIntent = new Intent(PairsGame.this, GenScoreScreen.class);
                startActivity(newIntent);
            }
        });

        GridLayout pairsGameLayout = (GridLayout) findViewById(R.id.activity_pairs_game);

        cardsInGame = new MemoryGameCard[cardsInGrid];
        cardGraphics = new int[cardsInPlay];
        cardLocations = new int[cardsInGrid];

        loadGraphics();

        for (int i =0; i < cardsInGrid; i++)//loop to fill the 4x4 grid space
        {
            cardLocations[i] = i % cardsInPlay; //duplicates the card across the grid
        }

        shuffleCards();
        generateCards(pairsGameLayout);


    }
    /*
    generates cards which are then placed onto the game grid
    param: gameSpace -
    return: none
     */
    protected void generateCards(GridLayout gameSpace)
    {
        for (int i = 0; i < gameRows; i++)
        {
            for (int j = 0; j < gameColumns; j++)
            {
                int currentPosition = (i*gameColumns)+j;
                MemoryGameCard newCard = new MemoryGameCard(this, i, j, cardGraphics[cardLocations[currentPosition]]);// translate this two dimensional array into a one dimensional object
                gameSpace.setId(View.generateViewId()); //let android generate the IDs, could remove/lessen naming conflicts
                cardsInGame[currentPosition] = newCard; //save the card, just in case
                newCard.setOnClickListener(this);
                gameSpace.addView(newCard);
            }
        }
    }

    /*
    Takes the array of cards and shuffles them into different points on the 4x4 grid
    param: none
    return: none
     */
    protected void shuffleCards()
    {
        Random rand = new Random();

        for (int i =0; i < cardsInGrid; i++)//shuffles the cards into
        {
            int target = cardLocations[i];//card to swap

            int swapZone = rand.nextInt(cardsInGrid);//the range of where the value can go

            cardLocations[i] = cardLocations[swapZone];
            cardLocations[swapZone] = target;
        }
    }


    /*
    updates the score field in the corner of the game screen
    param: correct - the value in which the user matches a pair
    param:none
    return: none
     */
    public void updateScore()
    {
        TextView scoreDisplay = (TextView) findViewById(R.id.pairsScore);
        score += correct;
        scoreDisplay.setText(" ");
        scoreDisplay.setText(Integer.toString(score));
    }

    /*
    load card graphics on the cards which are in play
    param: none
    return: none
     */
    protected void loadGraphics()
    {
        cardGraphics[0] = R.drawable.clubs2;
        cardGraphics[1] = R.drawable.hearts_ace;
        cardGraphics[2] = R.drawable.diamond6;
        cardGraphics[3] = R.drawable.king_of_spades2;
        cardGraphics[4] = R.drawable.clubs8;
        cardGraphics[5] = R.drawable.hearts4;
        cardGraphics[6] = R.drawable.diamond10;
        cardGraphics[7] = R.drawable.spades_ace;
    }

    @Override
    public void onClick(View view)
    {

        if(isBusy)
        {
            return; //the main thread is doing something
        }

        MemoryGameCard gameCard = (MemoryGameCard) view; //create subclass to access the extended class, also acts as second selection

        if(gameCard.isMatch()) //case: has the selection already been matched?
        {
            return;
        }

        if(firstSelection == null) //case: Selection hasn't been matched and the user has made no selections
        {
            firstSelection = gameCard;
            firstSelection.flipCard();
            return;
        }

        if(gameCard.getImageID() == firstSelection.getImageID()) //case: a match has been found
        {
            gameCard.flipCard();

            //A match has been found so change their boolean values
            gameCard.setMatch(true);
            firstSelection.setMatch(true);
            //disabling the cards, so they cannot be used again
            firstSelection.setEnabled(false);
            gameCard.setEnabled(false);
            updateScore();
            firstSelection = null; //resets the selection, so the user can make another selction again
            return;
        }

        else //case: The selections by the user do not match
        {
            secondSelection = gameCard;
            secondSelection.flipCard();
            isBusy = true; //prevents the user from crashing the program

            /*
            this section is when both selections by the user are not a pair
            we store the second in the secondSelection field and then set the card back to the back
            and reset their values so the user can continue to play the game, a delay is incorperated so the user
            can see their selections for 1 second before disappearing
             */
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    firstSelection.flipCard();
                    secondSelection.flipCard();
                    firstSelection = null;
                    secondSelection = null;

                    isBusy = false;
                }
            }, 1000);
        }
    }
}
