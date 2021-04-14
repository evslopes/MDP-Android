package br.pro.evslopes.maedaprole.data

import com.google.firebase.firestore.Query

interface MinhaInspiracaoDao {

    fun read(key: String): Query

    fun all(): Query

}