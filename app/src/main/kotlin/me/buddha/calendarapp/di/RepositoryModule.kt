package me.buddha.calendarapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import me.buddha.calendarapp.data.network.service.ApiService
import me.buddha.calendarapp.domain.repository.CalendarRepository

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideRepository(
    service: ApiService
  ): CalendarRepository = CalendarRepository(service)
}