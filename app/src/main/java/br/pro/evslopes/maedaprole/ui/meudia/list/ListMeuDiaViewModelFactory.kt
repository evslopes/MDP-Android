package br.pro.evslopes.maedaprole.ui.meudia.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.pro.evslopes.maedaprole.data.MeuDiaDao
import java.lang.IllegalArgumentException

class ListMeuDiaViewModelFactory(private val meuDiaDao: MeuDiaDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListMeuDiaViewModel::class.java))
            return ListMeuDiaViewModel(meuDiaDao) as T
        throw IllegalArgumentException("Erro: Classe ViewModel ListMeuDiaViewModelFactory")
    }
}