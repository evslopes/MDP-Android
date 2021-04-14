package br.pro.evslopes.maedaprole.ui.meudia.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.pro.evslopes.maedaprole.data.MinhaInspiracaoFirestoreDao
import java.lang.IllegalArgumentException

class ListMinhaInspiracaoModelFactory(private val MinhaInspiracaoDao: MinhaInspiracaoFirestoreDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListMinhaInspiracaoViewModel::class.java))
            return ListMinhaInspiracaoViewModel(MinhaInspiracaoDao) as T
        throw IllegalArgumentException("Erro: Classe ViewModel ListMeuDiaViewModelFactory")
    }
}