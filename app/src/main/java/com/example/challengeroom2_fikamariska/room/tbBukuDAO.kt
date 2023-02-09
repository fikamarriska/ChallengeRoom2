package com.example.challengeroom2_fikamariska.room

import androidx.room.*

@Dao
interface tbBukuDAO {
    @Insert
    fun addtbbuku(buku: tbBuku)

    @Update
    fun updatetbbuku(buku: tbBuku)

    @Delete
    fun deltbbuku (buku: tbBuku)

    @Query("SELECT * FROM tbBuku")
    fun tampilsemua () : List<tbBuku>

    @Query("SELECT * FROM tbBuku WHERE id_buku=:buku_id")
    fun tampilid(buku_id: Int) : List<tbBuku>

}