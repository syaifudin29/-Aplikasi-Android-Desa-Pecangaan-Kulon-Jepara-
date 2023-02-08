package com.example.desa.ui.pesan

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.desa.R
import com.example.desa.response.DataPesanItem

class PesanAdapter(private val listReview: ArrayList<DataPesanItem>) : RecyclerView.Adapter<PesanAdapter.ListViewHolder>()  {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_pesan, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (waktu, jenis, id, deskripsi, judul) = listReview[position]
        holder.waktu.text = waktu
        holder.jenis.text = jenis
        holder.deskripsi.text = deskripsi
        holder.judul.text = judul
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listReview[holder.adapterPosition])
        }
        if (id == 1){
            holder.jenis.setTextColor(Color.parseColor("#BFDD3A4B"))
            holder.bunder.setBackgroundColor(Color.parseColor("#BFDD3A4B"))
            holder.deskripsi.setTextColor(Color.parseColor("#BF282828"))
            holder.judul.setTextColor(Color.parseColor("#BF282828"))
            holder.lyt?.setBackgroundColor(Color.parseColor("#80D9D9D9"))

        }
    }

    override fun getItemCount(): Int {
        return  listReview.size
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var jenis: TextView = view.findViewById(R.id.txtJenisPesan)
        var judul: TextView = view.findViewById(R.id.txtJudulPesan)
        var deskripsi: TextView = view.findViewById(R.id.txtDeskripsiPesan)
        var waktu: TextView = view.findViewById(R.id.txtWaktuPesan)
        var bunder: ImageView = view.findViewById(R.id.gmrTitik)
        var lyt: View? = view.findViewById(R.id.lytitmpsn)
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: DataPesanItem)
    }
}