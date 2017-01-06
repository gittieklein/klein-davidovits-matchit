package com.example.gittieklein.matchit;

import java.util.Random;

/**
 * Matching Game Adapter for RecyclerView
 * Created by Gittie Klein on 1/3/2017.
 */

public class MatchIt
{
    private boolean[] isMatch;
    private int[] picture;
    private int numMatches;
    private int numTurns;

    //need to pass in the type of game
    //and based on that it will fill the array
    public MatchIt()
    {
        picture = new int[20];
        isMatch = new boolean[20];
        for(int i = 0; i < isMatch.length;i++)
        {
            isMatch[i] = false;
        }

        createFood();

        shuffle();

        numMatches = 0;
        numTurns = 0;
    }

    private void createFood() {
        int[] allPics = new int[10];
        allPics[0] = R.drawable.ic_apple;
        allPics[1] = R.drawable.ic_banana;
        allPics[2] = R.drawable.ic_cheese;
        allPics[3] = R.drawable.ic_chocolate;
        allPics[4] = R.drawable.ic_egg;
        allPics[5] = R.drawable.ic_hotdog;
        allPics[6] = R.drawable.ic_popcorn;
        allPics[7] = R.drawable.ic_sandwitch;
        allPics[8] = R.drawable.ic_soup;
        allPics[9] = R.drawable.ic_strawberry;

        for (int i = 0, count = 0; i < picture.length && count < allPics.length; i += 2, count++) {
            picture[i] = allPics[count];
            picture[i + 1] = allPics[count];
        }
    }

    private void shuffle ()
    {
        Random rand = new Random();
        int temp;
        int row;
        for(int i = 0; i < picture.length; i++)
        {
            row = rand.nextInt(20);
            temp = picture[i];
            picture[i] = picture[row];
            picture[row] = temp;

        }
    }

    public int getPicture(int x)
    {
        return picture[x];
    }

    public boolean compare(int pic1, int pic2)
    {
        numTurns++;
        if (pic1 == pic2)
        {
            numMatches++;
            return true;
        }
        else
            return false;
    }

    public int getNumMatches()
    {
        return numMatches;
    }

    public int getNumTurns()
    {
        return numTurns;
    }

    public boolean[] getIsMatch()
    {
        return isMatch;
    }

    public int[] getAllPictures()
    {
        return picture;
    }

    public boolean isGameOver ()
    {
        boolean gameOver = false;

        for (int i = 0; i < isMatch.length && !gameOver; i++) {
            if (!isMatch[i]) {
                gameOver = true;
            }
        }
        return gameOver;
    }
}
