package com.rudearts.soundapp.util.loader

import android.content.Context
import android.content.ContextWrapper
import com.google.gson.stream.JsonReader

class AssetLoader(base: Context) : ContextWrapper(base) {

    fun loadAssetByObject(path: String, parser:(jsonReader:JsonReader)->Unit) {
        assets.open(path).use { inputStream ->
            JsonReader(inputStream.reader()).use { jsonReader ->
                jsonReader.beginArray()

                while(jsonReader.hasNext()) {
                    parser.invoke(jsonReader)
                }
            }
        }
    }
}