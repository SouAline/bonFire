package br.edu.infnet.bonfire.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDao {
    @Query("SELECT * FROM PostEntity")
    fun findAll(): LiveData<List<PostEntity>>

    @Insert
    fun create(post: PostEntity)

    @Query("DELETE FROM PostEntity")
    fun deleteAll()
}