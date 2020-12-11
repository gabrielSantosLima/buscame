package com.app.buscame.features.historic

import com.app.buscame.dto.HistoryDto
import com.app.buscame.utils.formatDateByDefaultPattern
import com.app.buscame.utils.randomNumberHex
import com.google.gson.Gson
import java.io.File

class HistoricManagerJson(private val dir: String, private val filename : String = "historic.json") : IHistoricManager{

    private val gson = Gson()

    override fun save(history: HistoryDto) {

        val file = File(dir,filename)
        val isCreatedFile = file.createNewFile()

        if(isCreatedFile){
            writeJson(file, listOf(history))
            return
        }

        val notMutableHistoric: Array<HistoryDto> = gson.fromJson(file.readText(), Array<HistoryDto>::class.java)
        val mutableHistoric: MutableList<HistoryDto> = notMutableHistoric.toMutableList()

        mutableHistoric.add(history)

        writeJson(file, mutableHistoric)
    }

    override fun list(): List<HistoryDto> {
        val file = File(dir,filename)
        val isFileExists = file.exists()

        if(!isFileExists) return emptyList()

        val historic = gson.fromJson(file.readText(), Array<HistoryDto>::class.java)

        return historic.toList()
    }

    override fun remove(id: String) {
        val historic = list()

        val file = File(dir,filename)
        val isFileExists = file.exists()

        if(!isFileExists) return

        val newHistoric = historic.filter { it.id != id }
        val isEquals = newHistoric.containsAll(historic)

        if(isEquals) return

        writeJson(file, newHistoric)
    }

    override fun groupByDate(): Map<String,List<HistoryDto>> {
        val historic = list()
        val groupedHistoric = historic.groupBy {
            formatDateByDefaultPattern(it.date)
        }

        return groupedHistoric
    }

    override fun removeAll() {
        val file = File(dir,filename)
        val isFileExists = file.exists()

        if(!isFileExists) return

        clearJson(file)
    }

    override fun exists(id: String): Boolean {
        val historic = list()

        val file = File(dir,filename)
        val isFileExists = file.exists()

        if(!isFileExists) return false

        val history = historic.find { it.id == id }

        history ?: return false

        return true
    }

    private fun writeJson(file : File, historic: List<HistoryDto>){
        val json = gson.toJson(historic)
        file.writeText(json)
    }

    private fun clearJson(file : File){
        file.writeText("[]")
    }
}