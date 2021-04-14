package br.pro.evslopes.maedaprole.ui.meudia.details

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.pro.evslopes.maedaprole.data.MinhaInspiracaoDao
import br.pro.evslopes.maedaprole.data.ObjetoUtil
import br.pro.evslopes.maedaprole.data.UserFirebaseDao
import java.io.File
import kotlin.random.Random

class DetailsMinhaInspiracaoViewModel(application: Application, private val minhaInspiracaoDao: MinhaInspiracaoDao) : AndroidViewModel(application) {

}