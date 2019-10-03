package `in`.squrlabs.kmm.ui.main

import `in`.squrlabs.kmm.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // Instantiate viewModel with Koin
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var movieAdapter: MovieAdapter
    private val disposable = CompositeDisposable()


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

    override fun onStart() {
        super.onStart()

        disposable.add( viewModel.moviesList
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { newMovies -> movieAdapter.updateData(newMovies!!)},
                { Log.e("Adapter", "Failed to get items")}
            )
        )
    }

    private fun initViewModel() {
        viewModel.showLoading.observe(this, Observer { showLoading ->
            pbMain.visibility = if (showLoading!!) View.VISIBLE else View.GONE
        })

        viewModel.showError.observe(this, Observer { showError ->
            Toast.makeText(this, showError, Toast.LENGTH_SHORT).show()
        })

        viewModel.sync()
    }
}
