package com.gmail.progstrl.vetrf2.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore


@Entity(tableName = "basename",
primaryKeys = ["id"])
class BaseName {



    @Ignore
    constructor(nameBase: String, issuerID: String){
        if(nameBase.isEmpty()){
            this.name = "Base"
        } else {
            this.name = nameBase
        }

        this.issuerid = issuerID
    }

    constructor(
        issuerid: String,
        name: String?,
        guid: String,
        login: String,
        password: String,
        apikey: String,
        serviceid: String,
        autoexchange: String,
        autorepay: String,
        userautoexchange: String,
        autocancallation: String
    ) {
        this.issuerid = issuerid
        this.name = name
        this.guid = guid
        this.login = login
        this.password = password
        this.apikey = apikey
        this.serviceid = serviceid
        this.autoexchange = autoexchange
        this.autorepay = autorepay
        this.userautoexchange = userautoexchange
        this.autocancallation = autocancallation
    }


    @NonNull
    @ColumnInfo(name = "id")
    var issuerid:String = ""

     var name: String? = null
     var guid:String = ""
     var login:String = ""
     var password:String = ""
     var apikey:String = ""
     var serviceid:String = ""


     var autoexchange:String = "0"
     var autorepay:String = "0"
     var userautoexchange:String = ""
     var autocancallation:String = "0"
}