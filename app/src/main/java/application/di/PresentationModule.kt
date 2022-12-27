package application.di

import application.domain.login.LoginInteractor
import application.presentation.MainActivityPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {

    @Provides
    fun provideMainActivityPresentation(
        loginInteractor: LoginInteractor
    ):MainActivityPresenter{
        return  MainActivityPresenter(loginInteractor)
    }
}