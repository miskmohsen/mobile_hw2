package com.example.hw1.Fragments

import ScoresActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw1.R
import com.google.android.material.color.utilities.Score

class HighScoreFragment : Fragment() {

    private lateinit var highScoresRecyclerView: RecyclerView
    private lateinit var highScoreAdapter: ScoresActivity.HighScoreAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_high_score, container, false)

        highScoresRecyclerView = view.findViewById(R.id.recyclerViewHighScores)
        highScoresRecyclerView.layoutManager = LinearLayoutManager(context)

        // התאם את הנתונים לטבלה
        highScoreAdapter = ScoresActivity.HighScoreAdapter(
            getHighScores(),
            object : ScoresActivity.HighScoreAdapter.ScoreClickListener {
                override fun onScoreClick(score: Score) {

                    (activity as ScoresActivity).onScoreClick(score)
                }
            })

        highScoresRecyclerView.adapter = highScoreAdapter
        return view
    }

    private fun getHighScores(): List<Score> {

        return listOf()
    }
}
