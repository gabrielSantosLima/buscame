package com.app.buscame.features.historic

import com.app.buscame.dto.HistoryDto

interface IHistoricManager {
    fun save(historic: HistoryDto)
    fun list(): List<HistoryDto>
    fun removeAll()
    fun remove(id: String)
}