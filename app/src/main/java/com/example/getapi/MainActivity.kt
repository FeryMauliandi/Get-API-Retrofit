package com.example.getapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getapi.API.ApiConfig
import retrofit2.Call
import retrofit2.Response
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var mortyAdapter: MortyAdapter
    private var dataMorty = emptyList<ResultsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.searchView)
        val morty = findViewById<RecyclerView>(R.id.rv_morty)

        mortyAdapter = MortyAdapter(dataMorty)

        morty.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = mortyAdapter
        }


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterData(newText)
                return true
            }
        })

        ApiConfig.getService().getMorty().enqueue(object : retrofit2.Callback<ResponseMorty> {
            override fun onResponse(call: Call<ResponseMorty>, response: Response<ResponseMorty>) {
                if (response.isSuccessful) {
                    val responseMorty = response.body()
                    dataMorty = (responseMorty?.results?: emptyList()) as List<ResultsItem>

                    mortyAdapter.setFilteredList(dataMorty)

                }
            }

            override fun onFailure(call: Call<ResponseMorty>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

    }

    fun filterData(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<ResultsItem>()
            for (i in dataMorty) {
                if (i.name?.lowercase(Locale.ROOT)?.contains(query) == true) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                mortyAdapter.setFilteredList(filteredList)
            }
        }
    }
}
