package application.di

import application.data.forgot_pass.ForgotPasswordRepositoryImpl
import application.data.items.ItemsRepositoryImpl
import application.data.login.LoginRepositoryImpl
import application.data.logout.LogoutRepositoryImpl
import application.data.main_activity.MainActivityRepositoryImpl
import application.data.registration.RegisterRepositoryImpl
import application.domain.forgot_pass.ForgotPassRepository
import application.domain.items.ItemsRepository
import application.domain.login.LoginRepository
import application.domain.logout.LogoutRepository
import application.domain.main_activiry.MainActivityRepository
import application.domain.registration.RegistrationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    abstract fun bindRegisterRepository(
        registerRepositoryImpl: RegisterRepositoryImpl
    ): RegistrationRepository

    @Binds
    abstract fun bindForgotPassword(
        forgotPassRepositoryImpl: ForgotPasswordRepositoryImpl
    ): ForgotPassRepository

    @Binds
    abstract fun bindMainActivityRepository(
        mainActivityRepositoryImpl: MainActivityRepositoryImpl
    ): MainActivityRepository

    @Binds
    abstract fun bindLogOutRepository(
        logoutRepositoryImpl: LogoutRepositoryImpl
    ): LogoutRepository

    @Binds
    abstract fun bindItemsRepository(
        itemsRepositoryImpl: ItemsRepositoryImpl
    ): ItemsRepository
}