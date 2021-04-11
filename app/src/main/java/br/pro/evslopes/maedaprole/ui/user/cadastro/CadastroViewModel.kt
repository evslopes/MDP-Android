package br.pro.evslopes.maedaprole.ui.user.cadastro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.pro.evslopes.maedaprole.data.UserFirebaseDao

class CadastroViewModel : ViewModel() {
    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun salvarCadastro(email: String, password: String, nome: String, sobrenome: String) {
        UserFirebaseDao.cadastrarCredenciais(email, password).addOnSuccessListener {
            val uid = it.user!!.uid
            UserFirebaseDao.cadastrarPerfil(uid, nome, sobrenome).addOnSuccessListener {
                _status.value = true
                _msg.value = "Usuario cadastrado com sucesso!"
            }.addOnFailureListener {
                _msg.value = "${it.message}"
            }

        }.addOnFailureListener {
            _msg.value = "${it.message}"
        }
    }
}