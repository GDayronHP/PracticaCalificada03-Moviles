package com.huayra.joseph.poketinder_2024_2_b

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel(context: Context) : ViewModel() {

    val inputsError = MutableLiveData<Boolean>()
    val emailError = MutableLiveData<Boolean>()
    val registerError = MutableLiveData<Boolean>()
    val authError = MutableLiveData<Boolean>()
    val loginSuccess = MutableLiveData<Boolean>()

    private var sharedPreferencesRepository: SharedPreferencesRepository =
        SharedPreferencesRepository().also {
            it.setSharedPreference(context)
        }

    fun validateInputs(email: String, password: String) {
        when {
            email.isEmpty() || password.isEmpty() -> {
                inputsError.postValue(true)
            }
            !isValidEmail(email) -> {
                emailError.postValue(true)
            }
            else -> {
                val emailSharedPreferences = sharedPreferencesRepository.getUserEmail()
                val passwordPreferences = sharedPreferencesRepository.getUserPassword()

                if (emailSharedPreferences.isEmpty()) {
                    registerError.postValue(true)
                } else if (emailSharedPreferences == email && passwordPreferences == password) {
                    loginSuccess.postValue(true)
                } else {
                    authError.postValue(true)
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
        return email.matches(emailPattern.toRegex())
    }
}
