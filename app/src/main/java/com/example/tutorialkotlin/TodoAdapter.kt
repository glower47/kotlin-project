package com.example.tutorialkotlin

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(
    private var todos: MutableList<Todo>,
    private var filteredTodos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun filterTodos(query: String){
        var newTodos = filteredTodos.filter { todo -> todo.title.contains(query) }
        todos = newTodos.toMutableList()
        notifyDataSetChanged()
    }

    fun resetFilters(){
        todos = filteredTodos
        notifyDataSetChanged()
    }

    fun addTodo(todo: Todo){
        todos.add(todo)
        filteredTodos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos(){
        filteredTodos.removeAll{ todo ->
            todo.checked
        }
        todos.removeAll{ todo ->
            todo.checked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.checked
            toggleStrikeThrough(tvTodoTitle, curTodo.checked)
            cbDone.setOnCheckedChangeListener { _, checked ->
                toggleStrikeThrough(tvTodoTitle, checked)
                curTodo.checked = !curTodo.checked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}