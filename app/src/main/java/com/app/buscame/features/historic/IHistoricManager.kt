package com.app.buscame.features.historic

import com.app.buscame.dto.HistoryDto

interface IHistoricManager {
    fun save(historyDto: HistoryDto)
}