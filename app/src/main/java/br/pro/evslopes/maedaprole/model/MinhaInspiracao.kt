package br.pro.evslopes.maedaprole.model

import br.pro.evslopes.maedaprole.data.CriptoString
import com.google.firebase.firestore.DocumentId

class MinhaInspiracao (
    var titulo: String? = null,
    var descricao: CriptoString? = null,
    var tipo: String? = null,
    var data: String? = null,
    var hora: String? = null,
    var userId: String? = null,
    @DocumentId
    var id: String? = null
)