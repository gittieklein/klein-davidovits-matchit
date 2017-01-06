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

public class MainActivity extends AppCompatActivity {

    private MatchItAdapter mAdapter;
    private MatchIt game;
    private boolean mGameOver;
    private View mSbParentView;
    private int mImagesRevealed;

    /**
     * This object of our custom Listener class handles events in the adapter; created here anon.
     * This leaves the Adapter to handle the Model part of MVC, not View or Controller
     */
    private final MatchItAdapter.OIClickListener
            listener = new MatchItAdapter.OIClickListener ()
    {
        public void onItemClick (int position, View view)
        {
            // if the game is already over then there is nothing more to do here
            if (mGameOver) {
                Snackbar.make (mSbParentView, "Game over!", Snackbar.LENGTH_INDEFINITE).show ();
            }
            else {
                if (mAdapter.getCurrentImageResourceAt (position) == R.drawable.ic_back) {
                    mAdapter.showActualImageAt (position);
                    mImagesRevealed++;

                    // TODO: if there are now two images revealed, check if they match
                    // TODO: if not, then hide both; if yes, they do then make them invisible:
                    //          by Setting isMatch[matchSquare1] and isMatch[matchSquare2] to true
                    //          and then calling mAdapter.notifyDataSetChanged ();
                    // TODO: Check for Game Over (i.e. if this was the last match): call game.isG.O.
                }
                else {      // otherwise, if this is NOT a back image then flip it back
                    mAdapter.hideActualImageAt (position);
                    mImagesRevealed--;
                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSbParentView = findViewById (R.id.content_main);

        startGame();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
    }

    public void startGame()
    {
        mGameOver = false;
        mImagesRevealed = 0;
        game = new MatchIt ();
        setUpBoard();
    }

    private void setUpBoard()
    {
        boolean[] isMatch = game.getIsMatch();
        int[] picture = game.getAllPictures();
        int columnsInGrid = (int) Math.sqrt (picture.length);

        RecyclerView objRecyclerView = (RecyclerView) findViewById(R.id.board);
        objRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager objLayoutManager = new GridLayoutManager (this, columnsInGrid);
        objLayoutManager.setAutoMeasureEnabled(true);

        mAdapter = new MatchItAdapter(getApplicationContext(), picture, isMatch, R.drawable.ic_back);
        mAdapter.setOnItemClickListener (listener);

        objRecyclerView.setLayoutManager(objLayoutManager);
        objRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
