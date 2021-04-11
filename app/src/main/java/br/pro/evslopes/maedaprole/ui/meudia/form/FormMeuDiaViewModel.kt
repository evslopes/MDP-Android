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

class FormMeuDiaViewModel(application: Application, private val ticketDao: MeuDiaDao) : AndroidViewModel(application) {

    private var fotoPerfil: Bitmap? = null

    val app = application

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message

    var categoriaSelecionadaString : String? = null

    private val _imagemTicket = MutableLiveData<Uri>()
    var imagemTicket: LiveData<Uri> = _imagemTicket

    init {
        _status.value = false
        _message.value = null
    }

    fun salvarTicket(nome: String, local: CriptoString, data: String, hora: String, categoria: String) {
        _status.value = false
        _message.value = "Aguarde a persistência..."

        val meuDia = MeuDia(nome, local, data, hora, categoria)

        if(ObjetoUtil.meuDiaSelecionado != null) {
            meuDia.id = ObjetoUtil.meuDiaSelecionado!!.id
            meuDia.userId = ObjetoUtil.meuDiaSelecionado!!.userId
            if(fotoPerfil != null) {
                ticketDao.cadastrarImagemPerfil(fotoPerfil!!, UserFirebaseDao.firebaseAuth.currentUser.uid, meuDia.nome!!.trim())
                    .addOnSuccessListener {
                        ticketDao.edit(meuDia).addOnSuccessListener {
                            _status.value = true
                            _message.value = "Persistência concluída!"
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
                    ticketDao.cadastrarImagemPerfil(fotoPerfil!!, UserFirebaseDao.firebaseAuth.currentUser.uid, meuDia.nome!!.trim())
                        .addOnSuccessListener {
                            ticketDao.insert(meuDia).addOnSuccessListener {
                                _status.value = true
                                _message.value = "Persistência concluída!"
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

    fun receberFoto() {
        val file = File(app.cacheDir, "${Random.nextInt(0, Int.MAX_VALUE)}.jpeg")
        val usuarioId = UserFirebaseDao.firebaseAuth.currentUser.uid
        if(ObjetoUtil.meuDiaSelecionado!!.nome != null) {
            ticketDao.receberImagem(usuarioId, file, ObjetoUtil.meuDiaSelecionado!!.nome!!)
                    .addOnSuccessListener {
                        _imagemTicket.value = file.toUri()
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

    fun categoriaSelecionada(categoria : String) {
        categoriaSelecionadaString = categoria
    }
}