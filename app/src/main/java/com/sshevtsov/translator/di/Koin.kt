package com.sshevtsov.translator.di

import com.sshevtsov.translator.data.api.TranslatorApi
import com.sshevtsov.translator.data.repository.TranslatorRepositoryImpl
import com.sshevtsov.translator.domain.model.TranslatorModel
import com.sshevtsov.translator.domain.model.TranslatorModelImpl
import com.sshevtsov.translator.domain.repository.TranslatorRepository
import com.sshevtsov.translator.ui.screens.detail.DetailsViewModel
import com.sshevtsov.translator.ui.screens.query.QueryViewModel
import com.sshevtsov.translator.util.NetworkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val foreground = module {
  single(qualifier = named("ModelScope")) {
    CoroutineScope(Dispatchers.IO + SupervisorJob())
  }
  single { NetworkManager(get()) }
  single { TranslatorApi.create() }
  single<TranslatorRepository> { TranslatorRepositoryImpl(get()) }
  single<TranslatorModel> { TranslatorModelImpl(get(), get(named("ModelScope"))) }
  viewModel { QueryViewModel(get(), get()) }
  viewModel { DetailsViewModel(get()) }
}
