package com.example.rebecca.aadproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Luis Parcon on 13/02/2017.
 * controller for the pairs game in order to be compliant with the MVC design decision
 * decided in the requirements document
 */

public class PairsController
{
    private PairsGame _screen;

    //just some static values
    private static final int GAME_ROWS = 4;
    private static final int GAME_COLUMNS = 4;
    private int totalPairs = (GAME_COLUMNS * GAME_ROWS)/2;
    private static final int CARDS_IN_PLAY = (GAME_COLUMNS * GAME_ROWS)/2; //need half of the numbers as we need two of each card image
    private int _cardsInGrid = GAME_ROWS * GAME_COLUMNS; //the number of cards in the 4x4 game space

    //Stores the selections made by the user to compare
    protected PairsModel _firstSelection;
    protected PairsModel _secondSelection;


    private int[] _cardGraphics; //takes an ID from the drawable folder
    private int[] _cardLocations; //The locations of the cards in the 4x4 grid

    protected int _turns;
    private int _pairsFound;
    private boolean _isBusy; //checks if the app is already doing something

    /*
    constructor for the pairs game which initialises values within the class
    param: pairsGame - The activity in which the game will be loaded and played
    return: none
     */
    public PairsController(PairsGame pairsGame)
    {
        _turns = 0;
        _isBusy = false;
        _firstSelection = null;
        _secondSelection = null;
        _pairsFound = 1;
        _screen = pairsGame;
        _cardGraphics = new int[CARDS_IN_PLAY];
        _cardLocations = new int[_cardsInGrid];
    }
    /*
    Set up the pairs game and allows for the game to be played by the user
    param: none
    return: none
     */
    public void setupGame()
    {
        GridLayout pairsGameLayout = (GridLayout) _screen.findViewById(R.id.activity_pairs_game);
        loadGraphics();

        for (int i = 0; i < _cardsInGrid; i++)//loop to fill the 4x4 grid space
        {
            _cardLocations[i] = i % CARDS_IN_PLAY; //duplicates the card across the grid
        }

        shuffleCards();
        generateCards(pairsGameLayout);
    }
    /*

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
    Takes the array of cards and shuffles them into different points on the 4x4 grid
    param: none
    return: none
    */
    protected void shuffleCards()
    {
        Random rand = new Random();

        for (int i = 0; i < _cardsInGrid; i++)//shuffles the cards in the 4x4 space
        {
            int target = _cardLocations[i];//card to swap

            int swapZone = rand.nextInt(_cardsInGrid);//the range of where the value can go

            _cardLocations[i] = _cardLocations[swapZone];
            _cardLocations[swapZone] = target;
        }
    }

    /*
    updates the score field in the game screen
    param: none
    return: none
    */
    private void updateTurns()
    {
        _turns++;

        TextView scoreDisplay = (TextView) _screen.findViewById(R.id.pairsScore);
        scoreDisplay.setText(Integer.toString(_turns));
    }

    /*
    generates cards which are then placed onto the game grid during runtime
    param: gameSpace - the gridlayout for which the cards will occupy when generated
    return: none
    */
    protected void generateCards(GridLayout gameSpace)
    {
        for (int i = 0; i < GAME_ROWS; i++)
        {
            for (int j = 0; j < GAME_COLUMNS; j++)
            {
                int currentPosition = (i* GAME_COLUMNS)+j;
                PairsModel newCard = new PairsModel(_screen, i, j, _cardGraphics[_cardLocations[currentPosition]]);// translate this two dimensional array into a one dimensional object
                gameSpace.setId(View.generateViewId()); //let android generate the IDs, could remove/lessen naming conflicts
                newCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        {

                            if(_isBusy)
                            {
                                return; //the main thread is doing something
                            }

                            PairsModel gameCard = (PairsModel) view; //create subclass to access the extended class, also acts as second selection

                            if(gameCard.isMatch()) //case: has the selection already been matched?
                            {
                                return;
                            }

                            if(_firstSelection == null) //case: Selection hasn't been matched and the user has made no selections
                            {
                                _firstSelection = gameCard;
                                _firstSelection.flipCard();
                                _firstSelection.setEnabled(false); //should fix the problem with the user pressing the same card twice
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
                                updateTurns();
                                _firstSelection = null; //resets the selection, so the user can make another selction again
                                allPairsFound();
                            }

                            else //case: The selections by the user do not match
                            {
                                _secondSelection = gameCard;
                                _secondSelection.flipCard();
                                updateTurns();
                                _isBusy = true; //prevents the user from crashing the program
                            /*
                            this section is when both selections by the user are not a pair
                            we store the second in the secondSelection field and then set the card back to the back
                            and reset their values so the user can continue to play the game, a delay is incorporated so the user
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
                                        _firstSelection.setEnabled(true); //first selection is no longer locked
                                        _firstSelection = null;
                                        _secondSelection = null;

                                        _isBusy = false;
                                    }
                                }, 1000);
                            }
                        }
                    }
                });
                gameSpace.addView(newCard);
            }
        }
    }

    /*
    checks if all pairs have been found and exits the activity;
    if all pairs haven't been found increment the counter
    param: none
    return: none
 */
    protected void allPairsFound()
    {
        if (_pairsFound == totalPairs)
        {
            Integer score = calculateScore();
            Intent newIntent = new Intent(_screen, GameCompletionScreen.class);
            Bundle bundle = new Bundle();
            bundle.putFloat("newScore", score);
            bundle.putString("game", "Pairs");
            newIntent.putExtras(bundle);
            _screen.startActivity(newIntent);
            _screen.finish();
        }
        else
        {
            _pairsFound ++;
        }
    }

    private Integer calculateScore() {
        if (_turns <= 15) {
            return 60;
        }
        else if (_turns >= 16 && _turns <= 30){
            return 40;
        }
        return 20;
    }
}