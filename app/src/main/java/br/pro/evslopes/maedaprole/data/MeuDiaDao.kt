package br.pro.evslopes.maedaprole.data

import android.graphics.Bitmap
import br.pro.evslopes.maedaprole.model.MeuDia
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.UploadTask
import java.io.File

interface MeuDiaDao {
    fun insert(meuDia: MeuDia) : Task<DocumentReference>

    fun delete(meuDia: MeuDia) : Task<Void>

    fun all(): Query

    fun read(key: String): Query

    fun edit(meuDia: MeuDia): Task<Void>

    fun cadastrarImagemPerfil(imagem: Bitmap, uid: String, tituloMeuDia: String): UploadTask

    fun receberImagem(uid: String, file: File, tituloMeuDia: String): FileDownloadTask
}