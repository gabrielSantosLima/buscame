package com.app.buscame.features.historic

import com.app.buscame.dto.HistoryDto

interface IHistoricManager {
    fun save(historic: HistoryDto)
    fun list(): List<HistoryDto>
    fun removeAll()
    fun exists(id: String): Boolean
    fun remove(id: String)
    fun groupByDate(): Map<String,List<HistoryDto>>
}