package com.lnight.rickandmortyfacts.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lnight.rickandmortyfacts.domain.model.DetailCharacterData

@Database(
    entities = [DetailCharacterData::class],
    version = 1
)
abstract class RickAndMortyDatabase: RoomDatabase() {

    abstract val dao: RickAndMortyDao

    companion object {
        const val DATABASE_NAME = "characters_db"
    }
}