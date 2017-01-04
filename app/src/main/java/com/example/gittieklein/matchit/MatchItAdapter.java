package com.example.gittieklein.matchit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Gittie Klein on 1/3/2017.
 */

public class MatchItAdapter extends RecyclerView.Adapter<MatchItAdapter.ViewHolder>
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

    //TODO
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.rv_item, parent, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.mButton.setText(Integer.toString(position));
    }

    @Override
    public int getItemCount() {
        return mCurrentBoard.length * mCurrentBoard[0].length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final Button mButton;

        public ViewHolder (View itemView)
        {
            super(itemView);
            mButton = (Button) itemView.findViewById(R.id.button);
        }
    }
}
