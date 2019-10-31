package com.example.mykotlinlistview

import android.content.Context
import org.json.JSONException
import org.json.JSONObject

class MyData(
    val title: String,
    val description: String,
    val imageUrl: String,
    val instructionUrl: String,
    val label: String) {

    companion object {

        fun getDataFromFile(filename: String, context: Context): ArrayList<MyData> {

            val recipeList = ArrayList<MyData>()

            try {
                // Load data
                val jsonString = loadJsonFromAsset(filename, context)
                val json = JSONObject(jsonString)
                val recipes = json.getJSONArray("recipes")

                // Get Recipe objects from data
                (0 until recipes.length()).mapTo(recipeList) {
                    MyData(recipes.getJSONObject(it).getString("title"),
                        recipes.getJSONObject(it).getString("description"),
                        recipes.getJSONObject(it).getString("image"),
                        recipes.getJSONObject(it).getString("url"),
                        recipes.getJSONObject(it).getString("dietLabel"))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return recipeList
        }

        private fun loadJsonFromAsset(filename: String, context: Context): String? {

            var json: String?

            try {
                val inputStream = context.assets.open(filename)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()

                //json = kotlin.String(buffer, Charsets.UTF_8)
                json = buffer.toString(Charsets.UTF_8)

            } catch (ex: java.io.IOException) {
                ex.printStackTrace()
                return null
            }

            return json
        }
    }
}