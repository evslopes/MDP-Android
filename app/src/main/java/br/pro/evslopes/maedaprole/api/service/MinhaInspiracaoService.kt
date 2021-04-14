package br.pro.evslopes.maedaprole.api.service

import br.pro.evslopes.maedaprole.model.ResponseTypes
import retrofit2.http.GET

interface MinhaInspiracaoService {

    ///articlesearch.json?q={query}&fq={filter}
    //https://api.nytimes.com/svc/archive/v1/2019/1.json?api-key=yourkey

    @GET("svc/search/v2/articlesearch.json")
    suspend fun all(@retrofit2.http.Query("api-key")
                    minhaInspiracao: String = "9rZiFilwWXDJtFmN7DgLcaCp4qcvLl4W")
            : ResponseTypes


    //https://api.nytimes.com/svc/search/v2/articlesearch.json?q=family&api-key=9rZiFilwWXDJtFmN7DgLcaCp4qcvLl4W
    @GET("search/v2/articlesearch.json?q=family")
    suspend fun get(@retrofit2.http.Query("api-key")
                    minhaInspiracao: String = "9rZiFilwWXDJtFmN7DgLcaCp4qcvLl4W")
            : ResponseTypes

    /*{"status":"OK"
        ,"copyright":"Copyright (c) 2021 The New York Times Company. All Rights Reserved."
        ,"response":
            {"docs":
                [{"abstract":"We were spread across three continents, at the mercy of vaccine geopolitics. Which of us would be inoculated last?",
                "web_url":"https://www.nytimes.com/2021/04/11/health/coronavirus-vaccines-global-journey.html",
                "snippet":"We were spread across three continents, at the mercy of vaccine geopolitics. Which of us would be inoculated last?",
                "lead_paragraph":"In early February, my sister posted a video in our family’s WhatsApp group.",
                "print_section":"BU",
                "print_page":"1",
                "source":"The New York Times",
                "multimedia":[{"rank":0,
                 "subtype":"xlarge"
                 ,"caption":null,"credit":null,"type":"image",
                 "url":"images/2021/04/11/business/00vaccine-family1/merlin_186044433_9ef941e7-6970-4692-ae5a-647c869f47c6-articleLarge.jpg",
                 "height":390,"width":600,
                 "legacy":{"xlarge":"images/2021/04/11/business/00vaccine-family1/merlin_186044433_9ef941e7-6970-4692-ae5a-647c869f47c6-articleLarge.jpg",
                 "xlargewidth":600,"xlargeheight":390},"subType":"xlarge","crop_name":"articleLarge"},
                 {"rank":0,"subtype":"popup","caption":null,"credit":null,"type":"image","url":
                 "images/2021/04/11/business/00vaccine-family1/merlin_186044433_9ef941e7-6970-4692-ae5a-647c869f47c6-popup.jpg",
                 "height":422,"width":650,"legacy":{},"subType":"popup","crop_name":"popup"},
                 {"rank":0,"subtype":"blog480","caption":null,"credit":null,"type":"image","url":
                 "images/2021/04/11/business/00vaccine-family1/merlin_186044433_9ef941e7-6970-4692-ae5a-647c869f47c6-blog480.jpg",
                 "height":312,"width":480,
                 "legacy":{},"subType":"blog480","crop_name":"blog480"},
                 {"rank":0,"subtype":"blog533","caption":null,"credit":null,"type":
                 "image","url":"images/2021/04/11/business/00vaccine-family1/merlin_186044433_9ef941e7-6970-4692-ae5a-647c869f47c6-blog533.jpg",
                 "height":346,"width":533,"legacy":{},"subType":"blog533","crop_name":"blog533"},{"rank":0,"subtype":"blog427","caption":null,"credit":null,"type":"image",
                 "url":"images/2021/04/11/business/00vaccine-family1/merlin_186044433_9ef941e7-6970-4692-ae5a-647c869f47c6-blog427.jpg",
                 "height":277,"width":427,"legacy":{},"subType":"blog427","crop_name":"blog427"},
                 {"rank":0,"subtype":"tmagSF","caption":null,"credit":null,"type":"image","url":
                 "images/2021/04/11/business/00vaccine-family1/merlin_186044433_9ef941e7-6970-4692-ae5a-647c869f47c6-tmagSF.jpg",
                 "height":235,"width":362,"legacy":{},"subType":"tmagSF","crop_name":"tmagSF"},
    */
}