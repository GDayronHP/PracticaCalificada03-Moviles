package com.huayra.joseph.poketinder_2024_2_b

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    val inputsError = MutableLiveData<Boolean>()
    val emailError = MutableLiveData<Boolean>()
    val passwordLengthError = MutableLiveData<Boolean>()
    val passwordsMismatchError = MutableLiveData<Boolean>()
    val userExistsError = MutableLiveData<Boolean>()
    val registrationSuccess = MutableLiveData<Boolean>()

    private var sharedPreferencesRepository: SharedPreferencesRepository =
        SharedPreferencesRepository().also {
            it.setSharedPreference(application.applicationContext)
        }

    fun registerUser(email: String, password: String, confirmPassword: String) {
        when {
            email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                inputsError.postValue(true)
            }
            !isValidEmail(email) -> {
                emailError.postValue(true)
            }
            password.length <= 8 -> {
                passwordLengthError.postValue(true)
            }
            password != confirmPassword -> {
                passwordsMismatchError.postValue(true)
            }
            sharedPreferencesRepository.getUserEmail() == email -> {
                userExistsError.postValue(true)
            }
            else -> {
                sharedPreferencesRepository.saveUserEmail(email)
                sharedPreferencesRepository.saveUserPassword(password)
                registrationSuccess.postValue(true)
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
        return email.matches(emailPattern.toRegex())
    }
}
