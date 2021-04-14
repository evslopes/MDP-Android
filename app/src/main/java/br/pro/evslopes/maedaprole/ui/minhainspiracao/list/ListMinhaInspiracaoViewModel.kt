package br.pro.evslopes.maedaprole.ui.minhainspiracao.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.pro.evslopes.maedaprole.api.Api
import br.pro.evslopes.maedaprole.model.MinhaInspiracao
import kotlinx.coroutines.launch

class ListMinhaInspiracaoViewModel() : ViewModel() {
    private val _news = MutableLiveData<List<MinhaInspiracao>>()
    val news: LiveData<List<MinhaInspiracao>> = _news

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    init {
        viewModelScope.launch {
            try {
                val responseTypes =
                    Api
                        .getNewsService().all()
                val response = responseTypes.response
                _news.value = response!!.docs!!
            }catch (e: Exception){
                _msg.value = e.message
                Log.i("LCVWResponse", "${e.message}")
            }

        }
    }
}