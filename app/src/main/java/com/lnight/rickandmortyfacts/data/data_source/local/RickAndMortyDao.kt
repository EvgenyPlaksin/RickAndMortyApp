package com.lnight.rickandmortyfacts.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lnight.rickandmortyfacts.domain.model.CharactersData
import kotlinx.coroutines.flow.Flow

@Dao
interface RickAndMortyDao {

    @Query("SELECT * FROM charactersdata")
    fun getAllCharacters(): Flow<List<CharactersData>>

    @Query("SELECT * FROM charactersdata WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharactersData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(character: CharactersData)

}