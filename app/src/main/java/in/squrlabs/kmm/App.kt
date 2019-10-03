package `in`.squrlabs.kmm

import `in`.squrlabs.data.di.dataModule
import `in`.squrlabs.data.di.networkModule
import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(
                viewModelModule, networkModule, dataModule
            ))
            Fresco.initialize(this@App)
        }
    }
}