package com.example.getapi

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailMorty() : AppCompatActivity() {
    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_STATUS = "extra_status"
        const val EXTRA_SPECIES = "extra_species"
        const val EXTRA_CREATED = "extra_created"
        const val EXTRA_GENDER = "extra_gender"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tvName: TextView = findViewById(R.id.tv_name_morty)
        val tvPhoto: ImageView = findViewById(R.id.tv_image_Morty)
        val tvStatus: TextView = findViewById(R.id.tv_status_morty)
        val tvSpecies: TextView = findViewById(R.id.tv_species_morty)
        val tvCreated: TextView = findViewById(R.id.tv_created_morty)
        val tvGender: TextView = findViewById(R.id.tv_gender_morty)



    }
}