package br.pro.evslopes.maedaprole.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

abstract class MinhaInspiracaoFirestoreDao : MinhaInspiracaoDao {
    private val collection = FirebaseFirestore.getInstance().collection("minhaInspiracao")
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun read(key: String): Query {
        return collection.whereEqualTo("id", key!!)
    }

    }
