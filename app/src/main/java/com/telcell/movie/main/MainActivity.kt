package com.telcell.movie.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.telcell.movie.R
import com.telcell.movie.main.movies.MoviesFragment
import com.telcell.movie.main.movies.Vod
import com.telcell.movie.main.vodDetail.VodDetailFragment

class MainActivity : AppCompatActivity(), MainConnector {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        attachFirstFragmentIfNeed()
    }

    private fun attachFirstFragmentIfNeed() {
        val moviesFragment = supportFragmentManager.findFragmentByTag(MoviesFragment::class.java.simpleName)
        if (moviesFragment == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, MoviesFragment.newInstance(), MoviesFragment::class.java.simpleName)
                .commit()
        }
    }

    override fun onVodDetailAction(vod: Vod) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, VodDetailFragment.newInstance(vod), MoviesFragment::class.java.simpleName)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
}