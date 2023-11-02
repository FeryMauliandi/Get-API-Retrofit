package com.example.getapi

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MortyAdapter(val dataMorty: List<ResultsItem?>?) : RecyclerView.Adapter<MortyAdapter.MyViewHolder>() {
    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val imgMorty = view.findViewById<ImageView>(R.id.item_image_Morty)
        val nameMorty = view.findViewById<TextView>(R.id.item_name_morty)
        val statusMorty = view.findViewById<TextView>(R.id.item_status_morty)
        val speciesMorty = view.findViewById<TextView>(R.id.item_species_morty)
//        val createdMorty = view.findViewById<TextView>(R.id.item_created_morty)
        val genderMorty = view.findViewById<TextView>(R.id.item_gender_morty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_morty, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(dataMorty != null) {
            return dataMorty.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val morty = dataMorty?.get(position)
        val photo = morty?.image

        holder.nameMorty.text = morty?.name
        holder.statusMorty.text = morty?.status
        holder.speciesMorty.text = morty?.species
//        holder.createdMorty.text = morty?.created
        holder.genderMorty.text = morty?.gender

        Glide.with(holder.imgMorty)
            .load(photo)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imgMorty)


        val context =holder.itemView.context

        holder.itemView.setOnClickListener {
            val move = Intent(context, DetailMorty::class.java)
            move.putExtra(DetailMorty.EXTRA_NAME, morty?.name)
            move.putExtra(DetailMorty.EXTRA_PHOTO, morty?.image)
            move.putExtra(DetailMorty.EXTRA_STATUS, morty?.status)
            move.putExtra(DetailMorty.EXTRA_SPECIES, morty?.species)
            move.putExtra(DetailMorty.EXTRA_CREATED, morty?.created)
            move.putExtra(DetailMorty.EXTRA_GENDER, morty?.gender)
            context.startActivity(move)
        }
    }

}