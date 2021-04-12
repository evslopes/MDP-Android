package br.pro.evslopes.maedaprole.data
import android.graphics.Bitmap
import br.pro.evslopes.maedaprole.model.MeuDia
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import java.io.File


class MeuDiaDaoFirestore : MeuDiaDao {

    private val collection = FirebaseFirestore.getInstance().collection("meudia")
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val storage = FirebaseStorage.getInstance().reference.child("imagensMeuDia")

    override fun insert(meuDia: MeuDia): Task<DocumentReference> {
        meuDia.userId = firebaseAuth.currentUser.uid
        return collection.add(meuDia)
    }

    override fun delete(meuDia: MeuDia): Task<Void> {
        return collection.document(meuDia.id!!).delete()
    }

    override fun all(): Query {
        return collection.whereEqualTo("userId", firebaseAuth.currentUser.uid)
    }

    override fun read(key: String): Query {
        return collection.whereEqualTo("id", key)
    }

    override fun edit(meuDia: MeuDia): Task<Void> {
        return collection.document(meuDia.id!!).set(meuDia)
    }

    override fun cadastrarImagemPerfil(imagem: Bitmap, uid: String, tituloMeuDia: String): UploadTask {
        val outPutStream = ByteArrayOutputStream()
        imagem.compress(Bitmap.CompressFormat.JPEG, 100, outPutStream)
        return storage.child("${uid}/${tituloMeuDia}.jpeg").putBytes(outPutStream.toByteArray())
    }

    override fun receberImagem(uid: String, file: File, tituloMeuDia: String): FileDownloadTask {
        return storage.child("${uid}/${tituloMeuDia}.jpeg").getFile(file)
    }
}