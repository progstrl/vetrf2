package com.gmail.progstrl.vetrf2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    primaryKeys = ["id", "businessentityguid"],
    foreignKeys = [ForeignKey(
        entity = BaseName::class,
        parentColumns = ["id"],
        childColumns = ["businessentityguid"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
        deferred = true
    )],
    indices = [Index("id"), Index("businessentityguid")]
)
class Users(name: String, businessEntityGuid: String) {

    @ColumnInfo(name = "id")
    var name = ""
    @ColumnInfo(name = "businessentityguid")
    var businessEntityGuid = ""
    var synonym = ""

    override fun toString(): String {
        return name
    }

    init {
        this.name = name
        this.businessEntityGuid = businessEntityGuid
    }
}