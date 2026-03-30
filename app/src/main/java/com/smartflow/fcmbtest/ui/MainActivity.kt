package com.smartflow.fcmbtest.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartflow.fcmbtest.R

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NamesAdapter
    private lateinit var textWatcher: TextWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        setContentView(R.layout.activity_main)

        val searchInput = findViewById<EditText>(R.id.searchInput)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val emptyState = findViewById<LinearLayout>(R.id.emptyState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        adapter = NamesAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        viewModel.names.observe(this) { list ->
            adapter.submitList(list)
            emptyState.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        }

        textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.filter(s?.toString().orEmpty())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        searchInput.addTextChangedListener(textWatcher)
    }

    override fun onDestroy() {
        super.onDestroy()
        findViewById<EditText>(R.id.searchInput)
            .removeTextChangedListener(textWatcher)
    }
}
