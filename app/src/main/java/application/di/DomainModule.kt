package application.di

import application.domain.forgot_pass.ForgotPassInteractor
import application.domain.forgot_pass.ForgotPassRepository
import application.domain.items.ItemsInteractor
import application.domain.items.ItemsRepository
import application.domain.login.LoginInteractor
import application.domain.login.LoginRepository
import application.domain.logout.LogoutInteractor
import application.domain.logout.LogoutRepository
import application.domain.main_activiry.MainActivityInteractor
import application.domain.main_activiry.MainActivityRepository
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
    ): RegistrationInteractor {
        return RegistrationInteractor(registrationRepository)
    }

    @Provides
    fun provideForgotPasswordRepository(
        forgotPassRepository: ForgotPassRepository
    ): ForgotPassInteractor {
        return ForgotPassInteractor(forgotPassRepository)
    }

    @Provides
    fun provideMainActivityInteractor(
        mainActivityRepository: MainActivityRepository
    ): MainActivityInteractor {
        return MainActivityInteractor(mainActivityRepository)
    }

    @Provides
    fun provideLogoutRepository(
        logoutRepository: LogoutRepository
    ): LogoutInteractor {
        return LogoutInteractor(logoutRepository)
    }

    @Provides
    fun provideItemsInteractor(
        itemsRepository: ItemsRepository
    ): ItemsInteractor {
        return ItemsInteractor(itemsRepository)
    }
}