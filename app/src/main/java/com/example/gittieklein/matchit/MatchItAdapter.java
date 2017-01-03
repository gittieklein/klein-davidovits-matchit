package com.example.gittieklein.matchit;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Gittie Klein on 1/3/2017.
 */

public class MatchItAdapter extends RecyclerView.Adapter
{
    private int[][] mCurrentBoard;
    private boolean[][] mIsMatch;
    private int[][] mPictures;

    public  MatchItAdapter(int[][] pictures, boolean[][] isMatch)
    {
        mCurrentBoard = new int[5][4];
        mPictures = pictures;
        mIsMatch = isMatch;

        setUpImages();
    }

    public void setUpImages()
    {
        for(int i = 0; i < mCurrentBoard.length;i++)
        {
            for(int x = 0; x < mCurrentBoard[i].length; x+=2)
            {
                mCurrentBoard[i][x] = R.drawable.ic_back;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mCurrentBoard.length * mCurrentBoard[0].length;
    }

}
