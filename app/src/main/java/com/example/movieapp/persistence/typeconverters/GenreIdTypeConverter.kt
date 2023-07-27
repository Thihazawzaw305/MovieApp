package com.example.movieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdTypeConverter {
    @TypeConverter
    fun toString(genreId : List<Int>?): String{
        return Gson().toJson(genreId)

    }
    @TypeConverter
    fun toGenreId(genreIdJSONString : String): List<Int>?{
        val genreIdType = object : TypeToken<List<Int>?>(){}.type
        return Gson().fromJson(genreIdJSONString,genreIdType)
    }
}