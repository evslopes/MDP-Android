package br.pro.evslopes.maedaprole.ui.meudia.details

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.pro.evslopes.maedaprole.data.MeuDiaDao
import br.pro.evslopes.maedaprole.data.ObjetoUtil
import br.pro.evslopes.maedaprole.data.UserFirebaseDao
import java.io.File
import kotlin.random.Random

class DetailsMeuDiaViewModel(application: Application, private val meuDiaDao: MeuDiaDao) : AndroidViewModel(application) {
    val app = application

    private val _imagemPerfil = MutableLiveData<Uri>()
    var imagemPerfil: LiveData<Uri> = _imagemPerfil

    fun downloadImagem() {
        val file = File(app.cacheDir, "${Random.nextInt(0, Int.MAX_VALUE)}.jpeg")
        val usuarioId = UserFirebaseDao.firebaseAuth.currentUser.uid
        if(ObjetoUtil.Selecionado!!.titulo != null) {
            meuDiaDao.receberImagem(usuarioId, file, ObjetoUtil.Selecionado!!.titulo!!)
                .addOnSuccessListener {
                    _imagemPerfil.value = file.toUri()
                }
                .addOnFailureListener {
                    Log.i("UploadImagem", "${it.message}")
                }
        }
    }
}