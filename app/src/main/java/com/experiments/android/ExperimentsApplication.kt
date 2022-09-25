package com.experiments.android

import android.app.Application
import com.experiments.android.di.ServiceLocator
import com.experiments.android.domain.repository.TxnRepository
import timber.log.Timber

class ExperimentsApplication : Application() {

    val txnRepository: TxnRepository
        get() = ServiceLocator.provideTransactionRepository(this)

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
