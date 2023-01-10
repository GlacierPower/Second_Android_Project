package application.presentation.registration


interface RegisterView {
    fun onUserNameEmpty()
    fun onEmailEmpty()
    fun  onEmailInvalid()
    fun onPasswordEmpty()
    fun onPasswordToShort()
    fun onConfirmPasswordEmpty()
    fun onConfirmPasswordNotMatch()
    fun onRegisterStart()
    fun onProgress(visibility: Int)
    fun onRegisterSuccess()
    fun onRegisterFailed()
}