package br.pro.evslopes.maedaprole.ui.meudia.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.pro.evslopes.maedaprole.data.MeuDiaDao

class FormMinhaInspiracaoViewModelFactory(val application: Application, val meuDiaDao: MeuDiaDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormMinhaInspiracaoViewModel::class.java)){
            return FormMinhaInspiracaoViewModel(application, meuDiaDao) as T
        }
        throw IllegalArgumentException("Erro Classe ViewModel FormMeudia.")
    }
}