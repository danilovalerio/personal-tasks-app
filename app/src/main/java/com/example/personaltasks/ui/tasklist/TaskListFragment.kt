package com.example.personaltasks.ui.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.personaltasks.R

class TaskListFragment : Fragment() {

    private lateinit var taskListViewModel: TaskListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        taskListViewModel =
            ViewModelProviders.of(this).get(TaskListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_task_list, container, false)
        val textView: TextView = root.findViewById(R.id.text_task_list)
        taskListViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}