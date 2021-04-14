package br.pro.evslopes.maedaprole.ui.user.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.pro.evslopes.maedaprole.data.MeuDiaDao
import br.pro.evslopes.maedaprole.data.UserFirebaseDao
import br.pro.evslopes.maedaprole.model.MeuDia
import br.pro.evslopes.maedaprole.model.User

class DetailsUserViewModel(private val meuDiaDao: MeuDiaDao) : ViewModel() {
    private val _usuario = MutableLiveData<User?>()
    val usuario: LiveData<User?> = _usuario
    private var _meusDias = listOf<MeuDia>()

    init{
        meuDiaDao.all().addSnapshotListener { value, error ->
            if (error != null) {
                Log.i("FirebaseFirestore", "${error.message}")
            } else {
                if (value != null && !value.isEmpty) {
                    _meusDias = value.toObjects(MeuDia::class.java)
                }
            }
        }
    }
    fun updatePerfil() {
        UserFirebaseDao.consultarUsuario().addOnSuccessListener {
            val usuario = it.toObject(User::class.java)
            usuario!!.firebaseUser = UserFirebaseDao.firebaseAuth.currentUser
            usuario.email = UserFirebaseDao.firebaseAuth.currentUser.email
            usuario.qtsPostMeuDia = _meusDias.size
            _usuario.value = usuario!!
        }
    }

    fun encerrarSessao() {
        UserFirebaseDao.encerrarSessao()
        _usuario.value = null
    }
}