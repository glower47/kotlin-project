package com.example.tutorialkotlin.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorialkotlin.R
import com.example.tutorialkotlin.Todo
import com.example.tutorialkotlin.TodoAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var todoAdapter: TodoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_dashboard, container, false)
//        inflater.inflate(R.layout.fragment_dashboard, container, false)

        view.rvTodoItems.layoutManager = LinearLayoutManager(context)

        todoAdapter = TodoAdapter(mutableListOf(), mutableListOf())
        view.rvTodoItems.adapter = todoAdapter

        view.btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }

        view.svSearchTodos.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
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

        view.btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }


        return view

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

//    fun onClick(v: View?) {
//        when (v?.id) {
//            R.id.btnAddTodo -> {
//                val todoTitle = etTodoTitle.text.toString()
//                if(todoTitle.isNotEmpty()){
//                    val todo = Todo(todoTitle)
//                    todoAdapter.addTodo(todo)
//                    etTodoTitle.text.clear()
//                }
//            }
//
//            else -> {
//            }
//        }
//    }
}