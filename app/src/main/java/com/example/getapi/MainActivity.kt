package com.example.getapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler.Callback
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getapi.API.ApiConfig
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val morty = findViewById<RecyclerView>(R.id.rv_morty)

        ApiConfig.getService().getMorty().enqueue(object : retrofit2.Callback<ResponseMorty>{
            override fun onResponse(call: Call<ResponseMorty>, response: Response<ResponseMorty>) {
                if (response.isSuccessful) {
                    val responseMorty = response.body()
                    val dataMorty = responseMorty?.results
                    val mortyAdapter = MortyAdapter(dataMorty)
                    morty.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        mortyAdapter.notifyDataSetChanged()
                        adapter = mortyAdapter

                    }
                }
            }

            override fun onFailure(call: Call<ResponseMorty>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT). show()
            }

        })
    }
}