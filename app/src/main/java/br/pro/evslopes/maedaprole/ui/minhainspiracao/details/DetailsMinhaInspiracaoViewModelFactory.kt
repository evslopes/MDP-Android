package br.pro.evslopes.maedaprole.ui.meudia.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.pro.evslopes.maedaprole.data.MinhaInspiracaoDao

class DetailsMinhaInspiracaoViewModelFactory(private val application: Application, private val minhaInspiracaoDao: MinhaInspiracaoDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        TODO("Not yet implemented")
    }

}