package `in`.squrlabs.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class DetailActivity : AppCompatActivity() {

    private val loadFeatures by lazy { loadKoinModules(detailModule) }
    private fun injectFeatures() = loadFeatures

    // Instantiate viewModel with Koin
    private val viewModel by viewModel<DetailViewModel>()
    private val disposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        injectFeatures()
    }

    override fun onStart() {
        super.onStart()

        disposable.add( viewModel.moviesList
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { newMovies -> Log.i("Detail", newMovies.toString())},
                { Log.e("Adapter", "Failed to get items")}
            )
        )
    }
}
