package com.sshevtsov.translator.di

import com.sshevtsov.translator.data.api.TranslatorApi
import com.sshevtsov.translator.data.repository.TranslatorRepositoryImpl
import com.sshevtsov.translator.domain.model.TranslatorModel
import com.sshevtsov.translator.domain.model.TranslatorModelImpl
import com.sshevtsov.translator.domain.repository.TranslatorRepository
import com.sshevtsov.translator.ui.screens.query.QueryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val foreground = module {
  single { TranslatorApi.create() }
  single<TranslatorRepository> { TranslatorRepositoryImpl(get()) }
  single<TranslatorModel> { TranslatorModelImpl(get()) }
  viewModel { QueryViewModel(get()) }
}
