package com.example.hw1

import HighScore
import android.content.Context
import android.content.SharedPreferences
import com.example.hw1.model.HighScore
import com.google.gson.Gson
import java.util.*
import java.util.stream.Collectors

class SharedPreferencesC<Gson>(context: Context) {
    private val shareP: SharedPreferences =
        context.getSharedPreferences("com.example.hw1", Context.MODE_PRIVATE)
    private val g: Gson = Gson()

    fun addScore(score: HighScore): Boolean {
        val allScores: MutableList<HighScore> = scores
        var position = -1
        for (i in allScores.indices) {
            if (score > allScores[i]) {
                position = i
                break
            }
        }

        if (position != -1 || allScores.size < 10) {
            if (position == -1) {
                allScores.add(score)
            } else {
                allScores.add(position, score)
            }

            while (allScores.size > 10) {
                allScores.removeAt(allScores.size - 1)
            }

            saveScores(allScores)
            return true
        }

        return false
    }

    private fun saveScores(allScores: List<HighScore>) {
        val scoresStrings = allScores.stream()
            .map(g::toJson)
            .collect(Collectors.toSet())

        shareP.edit().putStringSet("scores", scoresStrings)
            .apply()
    }

    val scores: MutableList<HighScore>
        get() {
            val scoresStrings = shareP.getStringSet("scores", emptySet()) ?: emptySet()
            val scores: MutableList<HighScore> = ArrayList()
            for (scoreJson in scoresStrings) {
                scores.add(g.fromJson(scoreJson, HighScore::class.java))
            }
            scores.sort()
            return scores
        }
}
