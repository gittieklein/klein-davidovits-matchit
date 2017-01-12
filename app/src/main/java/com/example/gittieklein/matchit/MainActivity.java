package com.example.gittieklein.matchit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    private MatchItAdapter mAdapter;
    private MatchIt game;
    private View mSbParentView;
    /**
     * This object of our custom Listener class handles events in the adapter; created here anon.
     * This leaves the Adapter to handle the Model part of MVC, not View or Controller
     */
    private final MatchItAdapter.OIClickListener
            listener = new MatchItAdapter.OIClickListener ()
    {
        public void onItemClick (int position, View view)
        {
            if (!game.isAlreadyHidden (position)) {
                // if the game is already over then there is nothing more to do here
                if (game.isGameOver ()) {
                    doGameOver ();
                }
                else {

                    // IF two images have already been shown AND not matched, then hide those
                    if (mAdapter.getVisibleImagesCount () == 2) {
                        mAdapter.hideAllVisibleImages ();
                    }

                    // Now that we've ensured all visible images are hidden, handle the click
                    if (mAdapter.getCurrentImageResourceAt (position) != R.drawable.ic_back) {
                        mAdapter.hideActualImageAt (position);
                    }
                    else if (mAdapter.getCurrentImageResourceAt (position) == R.drawable.ic_back) {
                        mAdapter.showActualImageAt (position);

                        if (mAdapter.getVisibleImagesCount () == 2) {
                            int position2 = mAdapter.getVisibleCardPositionOtherThan (position);
                            boolean flag =
                                    game.compare (mAdapter.getCurrentImageResourceAt (position),
                                                  mAdapter.getCurrentImageResourceAt (position2));
                            if (flag) {
                                game.setIsMatch (position);
                                game.setIsMatch (position2);
                                mAdapter.notifyDataSetChanged ();

                                if (game.isGameOver ()) {
                                    doGameOver ();
                                }
                            }
                            // otherwise, leave both cards showing until next click
                        }
                    }
                }
            }
        }
    };

    private void doGameOver ()
    {
        Snackbar.make (mSbParentView, "YOU WIN!\nIt took " + game.getNumTurns() + " turns to win:)", Snackbar.LENGTH_INDEFINITE).show ();

    }

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        mSbParentView = findViewById (R.id.content_main);

        startGame ();

        FloatingActionButton fab = (FloatingActionButton) findViewById (R.id.fab);
        fab.setOnClickListener (new View.OnClickListener ()
        {
            @Override
            public void onClick (View view)
            {
                startGame ();
            }
        });
    }

    public void startGame ()
    {
        game = new MatchIt ();
        setUpBoard ();
    }

    private void setUpBoard ()
    {
        boolean[] isMatch = game.getIsMatch ();
        int[] picture = game.getAllPictures ();
        int columnsInGrid = (int) Math.sqrt (picture.length);

        RecyclerView objRecyclerView = (RecyclerView) findViewById (R.id.board);
        objRecyclerView.setHasFixedSize (true);

        RecyclerView.LayoutManager objLayoutManager = new GridLayoutManager (this, columnsInGrid);
        objLayoutManager.setAutoMeasureEnabled (true);

        mAdapter =
                new MatchItAdapter (getApplicationContext (), picture, isMatch, R.drawable.ic_back);
        mAdapter.setOnItemClickListener (listener);

        objRecyclerView.setLayoutManager (objLayoutManager);
        objRecyclerView.setAdapter (mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ().inflate (R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected (item);
    }


}
