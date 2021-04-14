package br.pro.evslopes.maedaprole.ui.meudia.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.pro.evslopes.maedaprole.data.MeuDiaDao
import br.pro.evslopes.maedaprole.data.MinhaInspiracaoDao
import br.pro.evslopes.maedaprole.model.MinhaInspiracao

class ListMinhaInspiracaoViewModel(private val minhaInspiracaoDao: MinhaInspiracaoDao) : ViewModel() {
    private val _minhaInspiracao = MutableLiveData<MutableList<MinhaInspiracao>>()
    val minhaInspiracao: MutableLiveData<MutableList<MinhaInspiracao>> = _minhaInspiracao

    fun attListMinhaInspiracao () {
        minhaInspiracaoDao.all().addSnapshotListener { value, error ->
            if (error != null) {
                Log.i("FirebaseFirestore", "${error.message}")
            } else {
                if (value != null && !value.isEmpty) {
                    _minhaInspiracao.value = value.toObjects(MinhaInspiracao::class.java)
                }
            }
        }
    }
}