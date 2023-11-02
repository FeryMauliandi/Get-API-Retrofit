package com.example.getapi

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

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
        setContentView(R.layout.detail_morty)

        val tvName: TextView = findViewById(R.id.item_name_morty)
        val tvPhoto: ImageView = findViewById(R.id.item_image_Morty)
        val tvStatus: TextView = findViewById(R.id.item_status_morty)
        val tvSpecies: TextView = findViewById(R.id.item_species_morty)
        val tvDesc: TextView = findViewById(R.id.item_desc_morty)
        val tvGender: TextView = findViewById(R.id.item_gender_morty)

        val name = intent.getStringExtra(EXTRA_NAME)
        val photo = intent.getStringExtra(EXTRA_PHOTO)
        val status = intent.getStringExtra(EXTRA_STATUS)
        val species = intent.getStringExtra(EXTRA_SPECIES)
        val created = intent.getStringExtra(EXTRA_CREATED)
        val gender = intent.getStringExtra(EXTRA_GENDER)

        val description = "My name is $name, created when $created, gender is $gender, status now is $status, species is $species"
        tvDesc.text = description

        tvName.text = name
        tvStatus.text = status
        tvSpecies.text = species
        tvGender.text = gender

        Glide.with(this)
            .load(photo)
            .error(R.drawable.ic_launcher_background)
            .into(tvPhoto)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}