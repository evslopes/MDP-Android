package br.pro.evslopes.maedaprole.ui.meudia.form

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.pro.evslopes.maedaprole.data.CriptoString
import br.pro.evslopes.maedaprole.data.MeuDiaDao
import br.pro.evslopes.maedaprole.data.ObjetoUtil
import br.pro.evslopes.maedaprole.data.UserFirebaseDao
import br.pro.evslopes.maedaprole.model.MeuDia
import java.io.File
import kotlin.random.Random

class FormMinhaInspiracaoViewModel(application: Application, private val meuDiaDao: MeuDiaDao) : AndroidViewModel(application) {

    private var fotoPerfil: Bitmap? = null

    val app = application

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message

    var tipoSelecionadoString : String? = null

    private val _imagemMeuDia = MutableLiveData<Uri>()
    var imagemMeuDia: LiveData<Uri> = _imagemMeuDia

    init {
        _status.value = false
        _message.value = null
    }

    fun salvarMeuDia(titulo: String, descricao: CriptoString, data: String, hora: String, tipo: String) {
        _status.value = false
        _message.value = "Aguarde um instante, salvando"

        val meuDia = MeuDia(titulo, descricao, data, hora, tipo)

        if(ObjetoUtil.Selecionado != null) {
            meuDia.id = ObjetoUtil.Selecionado!!.id
            meuDia.userId = ObjetoUtil.Selecionado!!.userId
            if(fotoPerfil != null) {
                meuDiaDao.cadastrarImagemPerfil(fotoPerfil!!, UserFirebaseDao.firebaseAuth.currentUser.uid, meuDia.titulo!!.trim())
                    .addOnSuccessListener {
                        meuDiaDao.edit(meuDia).addOnSuccessListener {
                            _status.value = true
                            _message.value = "Salvo"
                        }.addOnFailureListener {
                            Log.e("meuDiaFirestore", "${it.message}")
                        }
                    }
                    .addOnFailureListener {
                        Log.e("FotoFirestore", "${it.message}")
                    }
            }

        }
        else {
            if(fotoPerfil != null) {
                    meuDiaDao.cadastrarImagemPerfil(fotoPerfil!!, UserFirebaseDao.firebaseAuth.currentUser.uid, meuDia.titulo!!.trim())
                        .addOnSuccessListener {
                            meuDiaDao.insert(meuDia).addOnSuccessListener {
                                _status.value = true
                                _message.value = "Salvo"
                            }.addOnFailureListener{
                                Log.e("meuDiaFirestore", "${it.message}")
                            }
                        }
                        .addOnFailureListener {
                            Log.e("FotoFirestore", "${it.message}")
                        }
            }
            else{
                _message.value = "Favor incluir uma foto!."
            }
        }
    }

    fun downloadFoto() {
        val file = File(app.cacheDir, "${Random.nextInt(0, Int.MAX_VALUE)}.jpeg")
        val usuarioId = UserFirebaseDao.firebaseAuth.currentUser.uid
        if(ObjetoUtil.Selecionado!!.titulo != null) {
            meuDiaDao.receberImagem(usuarioId, file, ObjetoUtil.Selecionado!!.titulo!!)
                    .addOnSuccessListener {
                        _imagemMeuDia.value = file.toUri()
                        val bitmap = BitmapFactory.decodeFile(file.path)
                        fotoPerfil = bitmap
                    }
                    .addOnFailureListener {
                        Log.i("UploadImagem", "${it.message}")
                    }
        }
    }

    fun alterarImagemPerfil(img: Bitmap) {
        fotoPerfil = img
    }

    fun tipoSelecionado(tipo : String) {
        tipoSelecionadoString = tipo
    }
}