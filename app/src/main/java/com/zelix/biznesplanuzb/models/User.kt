package com.zelix.biznesplanuzb.models

class User {

    var id: Int? = null
    var email: String? = null
    var ismi: String? = null
    var familiya: String? = null
    var tel: String? = null
    var biznesTuri: String? = null
    var biznesQisqacha: String? = null
    var time: String? = null
    var biznesToliq: String? = null
    var isAllowed: Boolean? = null

    constructor(
        id: Int?,
        email: String?,
        ismi: String?,
        familiya: String?,
        tel: String?,
        biznesTuri: String?,
        biznesQisqacha: String?,
        time: String?,
        biznesToliq: String?
    ) {
        this.id = id
        this.email = email
        this.ismi = ismi
        this.familiya = familiya
        this.tel = tel
        this.biznesTuri = biznesTuri
        this.biznesQisqacha = biznesQisqacha
        this.time = time
        this.biznesToliq = biznesToliq
    }

    constructor(
        email: String?,
        ismi: String?,
        familiya: String?,
        tel: String?,
        biznesTuri: String?,
        biznesQisqacha: String?,
        time: String?,
        biznesToliq: String?,
        isAllowed: Boolean?
    ) {
        this.email = email
        this.ismi = ismi
        this.familiya = familiya
        this.tel = tel
        this.biznesTuri = biznesTuri
        this.biznesQisqacha = biznesQisqacha
        this.time = time
        this.biznesToliq = biznesToliq
        this.isAllowed = isAllowed
    }


}