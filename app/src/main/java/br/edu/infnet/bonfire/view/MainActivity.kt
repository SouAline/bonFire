package br.edu.infnet.bonfire.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.bonfire.R
import br.edu.infnet.bonfire.model.PostEntity
import br.edu.infnet.bonfire.viewModel.PostViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        val viewPost = findViewById<TextView>(R.id.tvDate)
        val viewDate = findViewById<TextView>(R.id.tvPost)
        val viewId = findViewById<TextView>(R.id.tvId)
        var id = 0

        postViewModel.post.observe(this) {
            viewId.text = ""
            viewPost.text = ""
            viewDate.text = ""

            it.forEach { post ->
                viewId.append("${post.id}\n")
                viewPost.append("${post.userPost}\n")
                viewDate.append("${post.date}\n")
            }
        }

        val inputPost = findViewById<EditText>(R.id.etmPost)
        val btPost = findViewById<Button>(R.id.btPost)

        btPost.setOnClickListener {

            if (id == 0) {
                id = 1
            } else {
                id++
            }

            val datePost = date()
            val textPost = inputPost.text.toString()
            val newPost =
                PostEntity(id, datePost, textPost)
            postViewModel.create(newPost)

            inputPost.setText("")
        }

        val btDelete = findViewById<Button>(R.id.btDelete)

        btDelete.setOnClickListener {
            postViewModel.deleteAll()
        }
    }

    private fun date(): String {
        val date = Calendar.getInstance().time

        val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

        return dateTimeFormat.format(date)
    }
}