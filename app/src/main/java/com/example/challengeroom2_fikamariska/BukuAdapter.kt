package com.example.challengeroom2_fikamariska

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeroom2_fikamariska.room.tbBuku
import kotlinx.android.synthetic.main.activity_buku_adapter.view.*

class BukuAdapter (private val buku:ArrayList<tbBuku>, private val listener: OnAdapterListener)
    : RecyclerView.Adapter<BukuAdapter.BukuViewHolder>(){

    //4
    class BukuViewHolder(val view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        return BukuViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_buku_adapter,parent, false)
        )
    }

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        val buk = buku[position]
        holder.view.TNama.setOnClickListener{ listener.onClick(buk)
            holder.view.imgEdit.setOnClickListener{ listener.onUpdate(buk)}
            holder.view.imgDelete.setOnClickListener{ listener.onDelete(buk) }}
        holder.view.TNama2.text = buk.kategori
        holder.view.TJudul2.text = buk.judul

    }

    override fun getItemCount() = buku.size

    fun setData(list: List<tbBuku>){
        buku.clear()
        buku.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(tbBuk: tbBuku)
        fun onUpdate(tbBuk: tbBuku)
        fun onDelete(tbBuk: tbBuku)

    }
}