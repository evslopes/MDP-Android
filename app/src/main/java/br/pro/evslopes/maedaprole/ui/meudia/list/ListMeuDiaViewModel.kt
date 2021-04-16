package br.pro.evslopes.maedaprole.ui.meudia.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.pro.evslopes.maedaprole.data.MeuDiaDao
import br.pro.evslopes.maedaprole.model.MeuDia

class ListMeuDiaViewModel(private val meuDiaDao: MeuDiaDao) : ViewModel() {
    private val _meuDia = MutableLiveData<MutableList<MeuDia>>()
    val meuDia: LiveData<MutableList<MeuDia>> = _meuDia

    fun attListMeudia () {
        meuDiaDao.all().addSnapshotListener { value, error ->
            if (error != null) {
                Log.i("FirebaseFirestore", "${error.message}")
            } else {
                if (value != null && !value.isEmpty) {
                    _meuDia.value = value.toObjects(MeuDia::class.java)
                }
            }
        }
    }
}