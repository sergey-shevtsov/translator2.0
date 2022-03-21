package com.sshevtsov.translator.main

import android.app.Application
import com.sshevtsov.translator.di.foreground
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationDelegate : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(applicationContext)
      modules(foreground)
    }
  }
}