package com.example.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Notes")
data class Notes (
    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "sub_title")
    var subTitle: String? = null,

    @ColumnInfo(name = "date_time")
    var dateTime: String? = null,

    @ColumnInfo(name = "note_text")
    var noteText: String? = null

):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int=0
}