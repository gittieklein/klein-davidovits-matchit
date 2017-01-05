package com.example.gittieklein.matchit;

import java.util.Random;

/**
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
        int[] allpics = new int[10];
        allpics[0] = R.drawable.ic_apple;
        allpics[1] = R.drawable.ic_banana;
        allpics[2] = R.drawable.ic_cheese;
        allpics[3] = R.drawable.ic_chocolate;
        allpics[4] = R.drawable.ic_egg;
        allpics[5] = R.drawable.ic_hotdog;
        allpics[6] = R.drawable.ic_popcorn;
        allpics[7] = R.drawable.ic_sandwitch;
        allpics[8] = R.drawable.ic_soup;
        allpics[9] = R.drawable.ic_strawberry;
        int count = 0;
        for(int i = 0; i < picture.length && count < allpics.length;i+=2)
        {
            picture[i] = allpics[count];
            picture[i+1] = allpics[count];
            count++;

        }
    }

    public void shuffle()
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
}
