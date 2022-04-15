package com.example.tutorialkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.SearchView
import androidx.core.content.FileProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.example.tutorialkotlin.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf(), mutableListOf())

        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }

//        fabCameraButton.setOnClickListener{
//            val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            val filePhoto = getPhotoFile("photo.jpg")
//            val providerFile = FileProvider.getUriForFile(this,"com.example.tutorialkotlin.fileprovider", filePhoto)
//            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, providerFile)
//        }

        svSearchTodos.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if(query.isEmpty()){
                    todoAdapter.resetFilters()
                }
                todoAdapter.filterTodos(query)
                return false
            }
        })

        btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }

//    override fun onSupportNavigateUp() =
//        findNavController(this, R.id.navHostFragment).navigateUp()

    private fun getPhotoFile(fileName: String): File {
        val directoryStorage = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", directoryStorage)
    }
}