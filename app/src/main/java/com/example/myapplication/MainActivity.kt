package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder

data class Person(val firstname: String, val lastname: String)
class MainActivity : AppCompatActivity() {

    private lateinit var persons: List<Person>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultString = application.assets.open("test.json")
                            .bufferedReader().use { it.readText() }
        // GSON => Google SON
        val gson = GsonBuilder().create()
        persons = gson.fromJson(resultString, Array<Person>::class.java).toList()
        // persons.get(0).lastname
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = MyAdapter(persons)
    }
}