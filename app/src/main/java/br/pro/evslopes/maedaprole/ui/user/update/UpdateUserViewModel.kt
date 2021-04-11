package br.pro.evslopes.maedaprole.ui.user.update

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.pro.evslopes.maedaprole.data.UserFirebaseDao
import br.pro.evslopes.maedaprole.model.User

class UpdateUserViewModel : ViewModel() {
    private val _usuario = MutableLiveData<User?>()
    val usuario : LiveData<User?> = _usuario

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message

    fun updateUsuario(nome:String, sobrenome:String) {
        var usuarioId = UserFirebaseDao.firebaseAuth.currentUser.uid
        UserFirebaseDao.atualizarNomeSobrenome(nome, sobrenome, usuarioId)
            .addOnSuccessListener {
                _status.value = true
            }
            .addOnFailureListener {
                Log.e("upadteUsuario", "${it.message}")
            }
    }
}