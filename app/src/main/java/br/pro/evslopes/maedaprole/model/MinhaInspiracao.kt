package br.pro.evslopes.maedaprole.model

import com.google.firebase.firestore.DocumentId

class MinhaInspiracao(

    @DocumentId
    var id: String? = null,

    val web_url: String? = null,
    val snippet: String? = null,
    val print_page: Int? = null,
    val source: String? = null,
    val multimedia: List<Multimedia>? = null,
    val keywords: List<Keywords>? = null,
    val headline: Headline? = null,

    )

class Keywords(
    val name: String? = null
)

class Headline(
    val main: String? = null
)

class Multimedia(
    val credit: String? = null,
)


class Response (
    val docs: List<MinhaInspiracao>? = null
)

class ResponseTypes (
    val status: String? = null,
    val copyright: String? = null,
    val response: Response? = null
)