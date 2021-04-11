package br.pro.evslopes.maedaprole.model

import br.pro.evslopes.maedaprole.data.CriptoString
import com.google.firebase.firestore.DocumentId

class MinhaInspiracao (
    var nome: String? = null,
    var local: CriptoString? = null,
    var data: String? = null,
    var hora: String? = null,
    var categoria: String? = null,
    var userId: String? = null,
    @DocumentId
    var id: String? = null
)