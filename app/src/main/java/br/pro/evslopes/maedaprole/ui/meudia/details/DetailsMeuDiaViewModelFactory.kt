package br.pro.evslopes.maedaprole.ui.meudia.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.pro.evslopes.maedaprole.data.MeuDiaDao

class DetailsMeuDiaViewModelFactory(private val application: Application, private val ticketDao: MeuDiaDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsMeuDiaViewModel::class.java)){
            return DetailsMeuDiaViewModel(application, ticketDao) as T
        }
        throw IllegalArgumentException("Erro Classe ViewModel DetailsMeuDiaViewModel")
    }
}