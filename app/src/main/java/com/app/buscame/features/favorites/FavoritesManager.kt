package com.app.buscame.features.favorites

import com.app.buscame.dto.FavoriteDto
import com.app.buscame.utils.randomNumberHex
import com.google.gson.Gson
import java.io.File


/**  Etapas - Favoritos

    # 1 - Verificar se o arquivo favorites.json existe
    # 1.1.1 - Caso exista, ler arquivo favorites.json e serializar para um array de favoritos
    # 1.1.2 - Adicionar novo favorito
    # 1.1.3 - Gravar no arquivo os favoritos
    # 1.2.1 - Criar arquivo
    # 1.2.2 - Gravar primeiros dados

    # 2 - Ler dados do favorites.json (Caso exista)
    # 2.1 - Buscar por favorites.json
    # 2.2 - Ler dados do arquivo
 */

// dir_default : applicationContext.filesDir.path
class FavoritesManager(private val dir: String, private val filename : String = "favorites.json") : IFavoritesManager{

    private val gson = Gson()

    override fun save(favorite: FavoriteDto) {

        //Gravar aquivo
        val file = File(dir,filename)
        val isCreatedFile = file.createNewFile()

        if(isCreatedFile){
            val id = randomNumberHex() //TODO Regra de negócio - deve mudar de lugar

            favorite.id = id //TODO Regra de negócio - deve mudar de lugar
            favorite.product.id = id //TODO Regra de negócio - deve mudar de lugar

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
