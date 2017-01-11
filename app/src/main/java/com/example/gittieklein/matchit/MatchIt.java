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

        //create a random number to create a random board
        Random rand = new Random();
        int val = rand.nextInt(4);
        if(val == 0)
            createFood();
        else if (val == 1)
            createSchool();
        else if(val == 2)
            createAnimal();
        else
            createTransport();

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

    private void createSchool() {
        int[] allPics = new int[10];
        allPics[0] = R.drawable.ic_bag;
        allPics[1] = R.drawable.ic_books;
        allPics[2] = R.drawable.ic_stapler;
        allPics[3] = R.drawable.ic_calculator;
        allPics[4] = R.drawable.ic_crayons;
        allPics[5] = R.drawable.ic_glue;
        allPics[6] = R.drawable.ic_paint;
        allPics[7] = R.drawable.ic_pencil;
        allPics[8] = R.drawable.ic_ruler;
        allPics[9] = R.drawable.ic_tape;

        for (int i = 0, count = 0; i < picture.length && count < allPics.length; i += 2, count++) {
            picture[i] = allPics[count];
            picture[i + 1] = allPics[count];
        }
    }

    private void createAnimal() {
        int[] allPics = new int[10];
        allPics[0] = R.drawable.ic_bird;
        allPics[1] = R.drawable.ic_zebra;
        allPics[2] = R.drawable.ic_rhino;
        allPics[3] = R.drawable.ic_lion;
        allPics[4] = R.drawable.ic_koala;
        allPics[5] = R.drawable.ic_hippo;
        allPics[6] = R.drawable.ic_fish;
        allPics[7] = R.drawable.ic_cow;
        allPics[8] = R.drawable.ic_dog;
        allPics[9] = R.drawable.ic_tiger;

        for (int i = 0, count = 0; i < picture.length && count < allPics.length; i += 2, count++) {
            picture[i] = allPics[count];
            picture[i + 1] = allPics[count];
        }
    }

    private void createTransport() {
        int[] allPics = new int[10];
        allPics[0] = R.drawable.ic_bike;
        allPics[1] = R.drawable.ic_train;
        allPics[2] = R.drawable.ic_rocket;
        allPics[3] = R.drawable.ic_schoolbus;
        allPics[4] = R.drawable.ic_hotair;
        allPics[5] = R.drawable.ic_helicopter;
        allPics[6] = R.drawable.ic_car;
        allPics[7] = R.drawable.ic_bus;
        allPics[8] = R.drawable.ic_boat;
        allPics[9] = R.drawable.ic_plane;

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
//        boolean gameOver = false;
//
//        for (int i = 0; i < isMatch.length && !gameOver; i++) {
//            if (!isMatch[i]) {
//                gameOver = true;
//            }
//        }
//        return gameOver;
        if(numMatches * 2 == picture.length)
            return true;
        return false;
    }

    public void setIsMatch(int position)
    {
        isMatch[position] = true;
    }

    public boolean isAlreadyHidden (int position)
    {
        return isMatch[position];
    }
}
