package br.pro.evslopes.maedaprole.ui.meudia.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.pro.evslopes.maedaprole.data.MeuDiaDao

class FormMeuDiaViewModelFactory(val application: Application, val meuDiaDao: MeuDiaDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormMeuDiaViewModel::class.java)){
            return FormMeuDiaViewModel(application, meuDiaDao) as T
        }
        throw IllegalArgumentException("Erro Classe ViewModel FormMeudia.")
    }
}