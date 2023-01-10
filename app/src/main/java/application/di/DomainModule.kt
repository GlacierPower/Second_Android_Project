package application.di

import application.domain.forgot_pass.ForgotPassInteractor
import application.domain.forgot_pass.ForgotPassRepository
import application.domain.items.ItemsInteractor
import application.domain.items.ItemsRepository
import application.domain.login.LoginInteractor
import application.domain.login.LoginRepository
import application.domain.main_fragment.MainFragmentInteractor
import application.domain.main_fragment.MainFragmentRepository
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
    fun provideItemsRepository(
        itemsRepository: ItemsRepository
    ): ItemsInteractor {
        return ItemsInteractor(itemsRepository)
    }

    @Provides
    fun provideMainFragmentRepository(
        mainFragmentRepository: MainFragmentRepository
    ): MainFragmentInteractor {
        return MainFragmentInteractor(mainFragmentRepository)
    }
}