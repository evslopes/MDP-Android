package br.pro.evslopes.maedaprole.data

import com.google.firebase.firestore.Query
import java.io.File

interface MinhaInspiracaoDao {

    fun read(key: String): Query

    fun all(): Query

    fun receberImagem(usuarioId: String, file: File, titulo: String): Any

}