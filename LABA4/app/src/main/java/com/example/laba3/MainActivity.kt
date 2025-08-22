package com.example.laba3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laba3.ui.theme.LABA3Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var db: AppDatabase
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ItemAdapter(emptyList())
        recyclerView.adapter = adapter

        db = AppDatabase.getDatabase(this)

        CoroutineScope(Dispatchers.IO).launch {
            db.itemDao().insert(Item(title = "BMW", description = "Німецьке авто"))
            db.itemDao().insert(Item(title = "Audi", description = "Надійне авто"))
            db.itemDao().insert(Item(title = "Tesla", description = "Електрокар"))
            db.itemDao().insert(Item(title = "Toyota", description = "Японське авто"))

            val items = db.itemDao().getAll()

            runOnUiThread {
                adapter = ItemAdapter(items)
                recyclerView.adapter = adapter
            }
        }
    }
}