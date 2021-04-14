package br.pro.evslopes.maedaprole.ui.user.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.pro.evslopes.maedaprole.data.UserFirebaseDao

class LoginViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun verificandoCredenciais(email: String, senha: String) {
        UserFirebaseDao.verificarCredencias(email, senha).addOnSuccessListener {
            _status.value = true
        }.addOnFailureListener {
            _msg.value = it.message
        }
    }


//    keytool -exportcert -alias androiddebugkey -keystore C:\Users\evslo\.android\debug.keystore | C:\Openssl\bin\openssl sha1 -binary | C:\Openssl\bin\openssl base64


  //  keytool -exportcert -alias androiddebugkey -keystore "C:\Users\USERNAME\.android\debug.keystore" | "PATH_TO_OPENSSL_LIBRARY\bin\openssl" sha1 -binary | "PATH_TO_OPENSSL_LIBRARY\bin\openssl" base64





}