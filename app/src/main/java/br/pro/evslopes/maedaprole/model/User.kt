package br.pro.evslopes.maedaprole.model

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentId

open class User (
        var nome: String? = null,
        var sobrenome: String? = null,
        var email: String? = null,
        var firebaseUser : FirebaseUser? = null,
        var doula: Boolean = false,
        var qtsPostMeuDia: Int? = 0,
        @DocumentId
        var uid: String? = null

)