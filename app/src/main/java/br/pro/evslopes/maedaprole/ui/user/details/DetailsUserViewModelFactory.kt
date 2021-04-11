package br.pro.evslopes.maedaprole.ui.user.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.pro.evslopes.maedaprole.data.MeuDiaDao

class DetailsUserViewModelFactory(private val meuDiaDao: MeuDiaDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailsUserViewModel::class.java))
            return DetailsUserViewModel(meuDiaDao) as T
        throw IllegalArgumentException("Erro: Classe ViewModel.")
    }
}