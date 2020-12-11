package com.app.buscame.features.favorites

import com.app.buscame.dto.FavoriteDto
import com.app.buscame.utils.randomNumberHex
import com.google.gson.Gson
import java.io.File

// default_dir: applicationContext.filesDir.path
class FavoritesManagerJson(private val dir: String, private val filename : String = "favorites.json") : IFavoritesManager{

    private val gson = Gson()

    override fun save(favorite: FavoriteDto) {
        val file = File(dir,filename)
        val isCreatedFile = file.createNewFile()

        if(isCreatedFile){
            writeJson(file, listOf(favorite))
            return
        }

        val notMutableFavorites: Array<FavoriteDto> = gson.fromJson(file.readText(), Array<FavoriteDto>::class.java)
        val mutableFavorites: MutableList<FavoriteDto> = notMutableFavorites.toMutableList()

        mutableFavorites.add(favorite)

        writeJson(file, mutableFavorites)
    }

    override fun list(): List<FavoriteDto> {
        val file = File(dir,filename)
        val isFileExists = file.exists()

        if(!isFileExists) return emptyList()

        val favorites = gson.fromJson(file.readText(), Array<FavoriteDto>::class.java)

        return favorites.toList()
    }

    override fun exists(id: String): Boolean {
        val favorites = list()

        val file = File(dir,filename)
        val isFileExists = file.exists()

        if(!isFileExists) return false

        val favorite = favorites.find { it.id == id }

        favorite ?: return false

        return true
    }

    override fun remove(id: String) {
        val favorites = list()

        val file = File(dir,filename)
        val isFileExists = file.exists()

        if(!isFileExists) return

        val newFavorites = favorites.filter { it.id != id }
        val isEquals = newFavorites.containsAll(favorites)

        if(isEquals) return

        writeJson(file, newFavorites)
    }

    override fun removeAll() {
        val file = File(dir,filename)
        val isFileExists = file.exists()

        if(!isFileExists) return

        clearJson(file)
    }

    private fun writeJson(file : File, favorites: List<FavoriteDto>){
        val json = gson.toJson(favorites)
        file.writeText(json)
    }

    private fun clearJson(file : File){
        file.writeText("[]")
    }
}
