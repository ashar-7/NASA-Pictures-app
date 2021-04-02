package com.example.nasapictures.data

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

private const val jsonPath = "data.json"

class ImagesRepository private constructor(private val moshi: Moshi) {

    suspend fun load(context: Context): List<NASAImage>? = withContext(Dispatchers.IO) {
        return@withContext try {
            val listType = Types.newParameterizedType(List::class.java, NASAImage::class.java)
            val jsonAdapter: JsonAdapter<List<NASAImage>> = moshi.adapter(listType)

            val json = context.assets.open(jsonPath).bufferedReader().use { it.readText() }
            jsonAdapter.fromJson(json)
        } catch (e: IOException) {
            null
        }
    }

    companion object {
        @Volatile
        private var instance: ImagesRepository? = null

        fun getInstance(): ImagesRepository {
            return instance ?: synchronized(this) {
                instance ?: ImagesRepository(Moshi.Builder().build()).also { instance = it }
            }
        }
    }
}
