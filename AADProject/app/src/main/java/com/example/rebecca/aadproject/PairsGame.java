package com.example.rebecca.aadproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    static final int totalPairs = 8;
    static final int cardsInPlay = (gameColumns * gameRows)/2; //need half of the numbers as we need two of each card image
    private int _cardsInGrid = gameRows * gameColumns; //the number of cards in the 4x4 game space

    //Stores the selections made by the user to compare
    protected MemoryGameCard _firstSelection;
    protected MemoryGameCard _secondSelection;


    private MemoryGameCard[] _cardsInGame;
    private int[] _cardGraphics; //takes an ID from the drawable folder
    private int[] _cardLocations; //The locations of the cards in the 4x4 grid

    protected int _score = 0;
    private int _pairsFound;
    private boolean _isBusy = false; //checks if the app is already doing something

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pairs_game);

        GridLayout pairsGameLayout = (GridLayout) findViewById(R.id.activity_pairs_game);

        _cardsInGame = new MemoryGameCard[_cardsInGrid];
        _cardGraphics = new int[cardsInPlay];
        _cardLocations = new int[_cardsInGrid];

        loadGraphics();

        for (int i = 0; i < _cardsInGrid; i++)//loop to fill the 4x4 grid space
        {
            _cardLocations[i] = i % cardsInPlay; //duplicates the card across the grid
        }

        shuffleCards();
        generateCards(pairsGameLayout);
        _pairsFound = 1;
    }
    /*
    generates cards which are then placed onto the game grid
    param: gameSpace - the gridlayout for which the cards will occupy when generated
    return: none
     */
    protected void generateCards(GridLayout gameSpace)
    {
        for (int i = 0; i < gameRows; i++)
        {
            for (int j = 0; j < gameColumns; j++)
            {
                int currentPosition = (i*gameColumns)+j;
                MemoryGameCard newCard = new MemoryGameCard(this, i, j, _cardGraphics[_cardLocations[currentPosition]]);// translate this two dimensional array into a one dimensional object
                gameSpace.setId(View.generateViewId()); //let android generate the IDs, could remove/lessen naming conflicts
                _cardsInGame[currentPosition] = newCard; //save the card, just in case
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

        for (int i = 0; i < _cardsInGrid; i++)//shuffles the cards into
        {
            int target = _cardLocations[i];//card to swap

            int swapZone = rand.nextInt(_cardsInGrid);//the range of where the value can go

            _cardLocations[i] = _cardLocations[swapZone];
            _cardLocations[swapZone] = target;
        }
    }


    /*
    updates the score field in the corner of the game screen
    param:none
    return: none
     */
    public void updateScore()
    {
        TextView scoreDisplay = (TextView) findViewById(R.id.pairsScore);
        _score += correct;
        scoreDisplay.setText(" ");
        scoreDisplay.setText(Integer.toString(_score));
    }

    /*
    load card graphics on the cards which are in play
    param: none
    return: none
     */
    protected void loadGraphics()
    {
        _cardGraphics[0] = R.drawable.clubs2;
        _cardGraphics[1] = R.drawable.hearts_ace;
        _cardGraphics[2] = R.drawable.diamond6;
        _cardGraphics[3] = R.drawable.king_of_spades2;
        _cardGraphics[4] = R.drawable.clubs8;
        _cardGraphics[5] = R.drawable.hearts4;
        _cardGraphics[6] = R.drawable.diamond10;
        _cardGraphics[7] = R.drawable.spades_ace;
    }

    /*
    checks if all pairs have been found and exits the activity;
    if all pairs haven't been found increment the counter
     */
    protected void allPairsFound()
    {
        if (_pairsFound == totalPairs)
        {
            Intent newIntent = new Intent(PairsGame.this, GameCompletionScreen.class);
            Bundle bundle = new Bundle();
            bundle.putFloat("newScore", _score);
            bundle.putString("game", "Pairs");
            newIntent.putExtras(bundle);
            this.startActivity(newIntent);
            this.finish();
        }
        else
        {
            _pairsFound ++;
        }
    }

    @Override
    public void onClick(View view)
    {

        if(_isBusy)
        {
            return; //the main thread is doing something
        }

        MemoryGameCard gameCard = (MemoryGameCard) view; //create subclass to access the extended class, also acts as second selection

        if(gameCard.isMatch()) //case: has the selection already been matched?
        {
            return;
        }

        if(_firstSelection == null) //case: Selection hasn't been matched and the user has made no selections
        {
            _firstSelection = gameCard;
            _firstSelection.flipCard();
            return;
        }

        if(gameCard.getImageID() == _firstSelection.getImageID()) //case: a match has been found
        {
            gameCard.flipCard();

            //A match has been found so change their boolean values
            gameCard.setMatch(true);
            _firstSelection.setMatch(true);
            //disabling the cards, so they cannot be used again
            _firstSelection.setEnabled(false);
            gameCard.setEnabled(false);
            updateScore();
            _firstSelection = null; //resets the selection, so the user can make another selction again
            allPairsFound();
            return;
        }

        else //case: The selections by the user do not match
        {
            _secondSelection = gameCard;
            _secondSelection.flipCard();
            _isBusy = true; //prevents the user from crashing the program

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
                    _firstSelection.flipCard();
                    _secondSelection.flipCard();
                    _firstSelection = null;
                    _secondSelection = null;

                    _isBusy = false;
                }
            }, 1000);
        }
    }
}
