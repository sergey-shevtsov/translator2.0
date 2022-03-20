package com.sshevtsov.translator.di

import com.sshevtsov.translator.data.api.TranslatorApi
import org.koin.dsl.module

val foreground = module {
  single { TranslatorApi.create() }
}