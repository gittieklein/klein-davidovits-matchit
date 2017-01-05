package com.example.gittieklein.matchit;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

/**
 * Created by Gittie Klein on 1/3/2017.
 */

public class MatchItAdapter extends RecyclerView.Adapter<MatchItAdapter.ViewHolder>
{
    private int [] mData;
    private boolean[] mIsMatch;
    private int[] mPictures;
    private static OIClickListener sOIClickListener;
    private int mDefaultCard;
    private Context mContext;

    public  MatchItAdapter(Context context, int[] pictures, boolean[] isMatch, int defaultCard)
    {
        mData = new int[pictures.length];
        mPictures = pictures;
        mIsMatch = isMatch;
        mDefaultCard = defaultCard;
        mContext = context;

        setUpImages();
    }

    public void setUpImages()
    {
        for(int i = 0; i < mData.length;i++)
        {
            mData[i] = mDefaultCard;
        }
    }

    public void setOnItemClickListener (OIClickListener oiClickListener)
    {
        MatchItAdapter.sOIClickListener = oiClickListener;
    }

    //TODO
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        // must be public and final so that it is accessible in the outer class
        final Button mCurrentButton;

        // The constructor calls super and creates a public reference to this ViewHolder's ImageView
        // sets this current class to handle any clicks, which passes that to the calling Activity
        // if that calling activity implements OIClickListener, which it should
        public ViewHolder (View itemLayoutView)
        {
            super (itemLayoutView);
            mCurrentButton = (Button) itemLayoutView.findViewById (R.id.button);
            itemLayoutView.setOnClickListener (this);
        }

        @Override
        public void onClick (View v)
        {
            sOIClickListener.onItemClick (getAdapterPosition (), v);
        }
    }

    //TODO
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override public void onBindViewHolder (MatchItAdapter.ViewHolder holder, int position)
    {
        Button currentButton = holder.mCurrentButton;
        //TODO
        currentButton.setCompoundDrawables(ContextCompat.getDrawable(mContext, mData[position]),
                null,null,null);
        // set the picture to currentButton (mData[position]));

    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public interface OIClickListener
    {
        void onItemClick (int position, View v);
    }
}
