package application.di

import androidx.fragment.app.FragmentManager
import application.domain.login.LoginInteractor
import application.domain.login.LoginRepository
import application.domain.registration.RegistrationInteractor
import application.domain.registration.RegistrationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideLoginInteractor(
        loginRepository: LoginRepository
    ): LoginInteractor {
        return LoginInteractor(loginRepository)
    }

    @Provides
    fun provideRegistrationRepository(
        registrationRepository: RegistrationRepository
    ): RegistrationInteractor{
        return RegistrationInteractor(registrationRepository)
    }



}