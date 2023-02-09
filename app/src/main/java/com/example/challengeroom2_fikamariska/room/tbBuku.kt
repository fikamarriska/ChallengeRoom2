package com.example.challengeroom2_fikamariska.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class tbBuku (
    @PrimaryKey
    val id_buku: Int,
    val jml_buku: Int,
    val kategori: String,
    val judul: String,
    val pengarang: String,
    val penerbit: String
)