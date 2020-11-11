package com.app.buscame.features.favorites

import com.app.buscame.dto.FavoriteDto

interface IFavoritesManager {
    fun save(favorite: FavoriteDto)
    fun list(): List<FavoriteDto>
    fun exists(id: String): Boolean
    fun remove(id : String)
    fun removeAll()
}