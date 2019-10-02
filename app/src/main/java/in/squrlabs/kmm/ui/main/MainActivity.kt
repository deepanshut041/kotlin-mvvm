package `in`.squrlabs.kmm.ui.main

import `in`.squrlabs.kmm.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // Instantiate viewModel with Koin
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var movieAdapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieAdapter = MovieAdapter()
        rvMovies.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.moviesList.observe(this, Observer {newMovies ->
            movieAdapter.updateData(newMovies!!)
        })

        viewModel.showLoading.observe(this, Observer { showLoading ->
            pbMain.visibility = if (showLoading!!) View.VISIBLE else View.GONE
        })

        viewModel.showError.observe(this, Observer { showError ->
            Toast.makeText(this, showError, Toast.LENGTH_SHORT).show()
        })

        viewModel.loadMovies()
    }
}
