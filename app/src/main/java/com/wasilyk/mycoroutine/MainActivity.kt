package com.wasilyk.mycoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.*
import java.lang.StringBuilder
import java.util.concurrent.Executors
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(CommentViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.liveData.observe(this) {
            renderData(it)
        }

        findViewById<MaterialButton>(R.id.insert).setOnClickListener {
            var maxId = 0
            viewModel.selectMaxId().observe(this) {
                maxId = it + 1
                viewModel.insert(Comment(maxId, "comment #$maxId"))
            }
        }
    }

    private fun renderData(data: List<Comment>) {
        val result = StringBuilder()
        data.forEach { comment ->
            result.append("${comment.id} ${comment.comment}\n")
        }
        findViewById<TextView>(R.id.tv).text = result
    }
}