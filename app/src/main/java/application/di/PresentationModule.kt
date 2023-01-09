package application.di

import application.domain.login.LoginInteractor
import application.domain.logout.MainFragmentInteractor
import application.presentation.MainActivityPresenter
import application.presentation.main_fragment.MainFragmentPresenter
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
    ): MainActivityPresenter {
        return MainActivityPresenter(loginInteractor)
    }

    @Provides
    fun provideMainFragmentPresentation(
        mainFragmentInteractor: MainFragmentInteractor
    ): MainFragmentPresenter {
        return MainFragmentPresenter(mainFragmentInteractor)
    }
}