package br.edu.infnet.bonfire.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import br.edu.infnet.bonfire.model.AppDatabase
import br.edu.infnet.bonfire.model.PostDao
import br.edu.infnet.bonfire.model.PostEntity

class PostViewModel(application: Application) : AndroidViewModel(application) {

    val post: LiveData<List<PostEntity>>

    private val postDao: PostDao

    init {
        val dataBase = AppDatabase.getDataBase(application)

        postDao = dataBase.postDao()

        post = postDao.findAll()
    }


    fun create(post: PostEntity) {  //Responsável por criar o post no db - Indica a função create no DAO e o post como parâmetro
        Thread {
            postDao.create(post)
        }.start()
    }

    fun deleteAll() {   //Responsável por deletar tudo do db - Para melhorar a visualização nesse momento
        Thread {
            postDao.deleteAll()  //Indica a função deleteAll no DAO
        }.start()
    }
}

