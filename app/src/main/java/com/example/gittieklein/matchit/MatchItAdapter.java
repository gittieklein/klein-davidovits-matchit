package com.example.gittieklein.matchit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by Gittie Klein on 1/3/2017.
 */

public class MatchItAdapter extends RecyclerView.Adapter<MatchItAdapter.ViewHolder>
{
    private static OIClickListener sOIClickListener;
    private int[] mCurrentBoard;
    // array of current board, including flipped/not flipped
    private boolean[] mIsMatch;         // master array of match status; visible/invisible in onBind
    private int[] mPictures;            // master array of all pictures, regardless of flip status
    private int mDefaultCard;
    private Context mContext;

    public MatchItAdapter (Context context, int[] pictures, boolean[] isMatch, int defaultCard)
    {
        mCurrentBoard = new int[pictures.length];
        mPictures = pictures;
        mIsMatch = isMatch;
        mDefaultCard = defaultCard;
        mContext = context;

        setUpImages ();
    }

    private void setUpImages ()
    {
        for (int i = 0; i < mCurrentBoard.length; i++) {
            mCurrentBoard[i] = !mIsMatch[i] ? mDefaultCard : mPictures[i];
        }
    }

    public int getVisibleCardPositionOtherThan (int position1)
    {
        int position2 = -1;
        for (int i = 0; i < mCurrentBoard.length && position2 == -1; i++) {
            if (mCurrentBoard[i] != mDefaultCard && !mIsMatch[i] && i != position1) {
                position2 = i;
            }
        }

        return position2;
    }

    /**
     * Custom method used to replace a board space's image with image on bottom
     *
     * @param position Element number of array in which to change from blank
     */
    public void showActualImageAt (int position)
    {
        // update currently shown space with actual picture from master array in Game class
        mCurrentBoard[position] = mPictures[position];

        // Update view to reflect updates to model
        notifyDataSetChanged ();
    }

    /**
     * Custom method used to replace a board space's image with "blank" image on top     *
     * @param position Element number of array in which to change from blank
     */
    public void hideActualImageAt (int position)
    {
        // change back to the default card
        mCurrentBoard[position] = mDefaultCard;
        notifyDataSetChanged ();
    }

    public int getCurrentImageResourceAt (int position)
    {
        return mCurrentBoard[position];
    }

    public int getVisibleImagesCount ()
    {
        int visibleImagesCount = 0;
        for (int i = 0; i < mCurrentBoard.length; i++) {
            visibleImagesCount += mCurrentBoard[i] == mDefaultCard || mIsMatch[i] ? 0 : 1;
        }
        return visibleImagesCount;
    }

    public void hideAllVisibleImages ()
    {
        for (int i = 0; i < mCurrentBoard.length; i++) {
            if (mCurrentBoard[i] != mDefaultCard && !mIsMatch[i]) {
                mCurrentBoard[i] = mDefaultCard;
            }
        }
    }


    @Override
    public int getItemCount ()
    {
        return mCurrentBoard.length;
    }

    public void setOnItemClickListener (OIClickListener oiClickListener)
    {
        MatchItAdapter.sOIClickListener = oiClickListener;
    }

    //TODO
    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType)
    {
        // Inflate a new layout that consists of what is contained in the RV Item XML file
        View itemLayoutView = LayoutInflater.from (parent.getContext ())
                .inflate (R.layout.rv_item, parent, false);

        // Create and return a new ViewHolder with that newly-inflated View
        return new ViewHolder (itemLayoutView);
    }

    @Override public void onBindViewHolder (MatchItAdapter.ViewHolder holder, int position)
    {
        ImageButton currentButton = holder.mCurrentButton;
        currentButton.setImageResource (mCurrentBoard[position]);

        // Here is where we check isMatch to see if the button should be hidden
        currentButton.setVisibility (mIsMatch[position] ? View.INVISIBLE : View.VISIBLE);
    }

    public interface OIClickListener
    {
        void onItemClick (int position, View v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        // must be public and final so that it is accessible in the outer class
        final ImageButton mCurrentButton;

        // The constructor calls super and creates a public reference to this ViewHolder's ImageView
        // sets this current class to handle any clicks, which passes that to the calling Activity
        // if that calling activity implements OIClickListener, which it should
        public ViewHolder (View itemLayoutView)
        {
            super (itemLayoutView);
            mCurrentButton = (ImageButton) itemLayoutView.findViewById (R.id.button);
            itemLayoutView.setOnClickListener (this);
        }

        @Override
        public void onClick (View v)
        {
            sOIClickListener.onItemClick (getAdapterPosition (), v);
        }
    }
}
