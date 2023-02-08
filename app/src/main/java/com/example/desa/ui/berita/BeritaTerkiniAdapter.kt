package com.example.desa.ui.berita

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desa.R
import com.example.desa.response.DataItem
import com.example.desa.response.DataItemNews

class BeritaTerkiniAdapter (private val listReview: ArrayList<DataItemNews>) : RecyclerView.Adapter<BeritaTerkiniAdapter.ListViewHolder>()  {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_berita_terkini, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (id, kategori, judul, gambar, slug) = listReview[position]
        holder.judul.text = judul

        Glide.with(holder.itemView.context)
            .load("https://si.pecangaankulon.id/desa/upload/artikel/sedang_"+gambar)
            .skipMemoryCache(true)//for caching the image url in case phone is offline
            .into(holder.gambar)
        holder.gambar.setOnClickListener {
            onItemClickCallback.onItemClicked(listReview[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listReview.size
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var judul: TextView = view.findViewById(R.id.txtberitaterkini)
        var gambar: ImageView = view.findViewById(R.id.gambarberitaterkini)
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: DataItemNews)
    }
}