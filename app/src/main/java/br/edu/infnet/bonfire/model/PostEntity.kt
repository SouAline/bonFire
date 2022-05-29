package br.edu.infnet.bonfire.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class PostEntity(
    @PrimaryKey
    val id: Int?,
    val date: String,
    val userPost: String
)