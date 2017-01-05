package com.example.gittieklein.matchit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    MatchItAdapter mAdapter;
    MatchIt game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
       game = new MatchIt();
        setUpBoard();
    }

    private void setUpBoard()
    {
        boolean[] isMatch = game.getIsMatch();
        int[] picture = game.getAllPictures();

        RecyclerView objRecyclerView = (RecyclerView) findViewById(R.id.board);
        objRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager objLayoutManager = new GridLayoutManager(this,picture.length);
        objLayoutManager.setAutoMeasureEnabled(true);

        mAdapter = new MatchItAdapter(getApplicationContext(), picture, isMatch, R.drawable.ic_back);

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
