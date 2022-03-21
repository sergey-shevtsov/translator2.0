package com.sshevtsov.translator.di

import com.sshevtsov.translator.data.api.TranslatorApi
import com.sshevtsov.translator.data.repository.TranslatorRepositoryImpl
import com.sshevtsov.translator.domain.model.TranslatorModel
import com.sshevtsov.translator.domain.model.TranslatorModelImpl
import com.sshevtsov.translator.domain.repository.TranslatorRepository
import org.koin.dsl.module

val foreground = module {
  single { TranslatorApi.create() }
  single<TranslatorRepository> { TranslatorRepositoryImpl(get()) }
  single<TranslatorModel> { TranslatorModelImpl(get()) }
}