package com.lnight.rickandmortyfacts.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lnight.rickandmortyfacts.domain.model.CharactersData

@Database(
    entities = [CharactersData::class],
    version = 3,
    exportSchema = true
)
abstract class RickAndMortyDatabase: RoomDatabase() {

    abstract val dao: RickAndMortyDao

    companion object {
        const val DATABASE_NAME = "characters_db"
    }
}