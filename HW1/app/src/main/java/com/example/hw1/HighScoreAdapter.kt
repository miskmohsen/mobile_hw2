package com.example.hw1

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hw1.model.HighScore


class HighScoreAdapter(
    private val HighScoreList: List<HighScore>,
    private val scoreClickListener: ScoreClickListener
) :
    RecyclerView.Adapter<HighScoreAdapter.ScoreViewHolder>() {
    interface ScoreClickListener {
        fun onScoreClick(score: HighScore?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.highscore_index, parent, false)
        return ScoreViewHolder(v)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val score = HighScoreList[position]
        holder.populate(score)
    }

    override fun getItemCount(): Int {
        return HighScoreList.size
    }

    inner class ScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val locationTextView: TextView =
            itemView.findViewById<TextView>(R.id.locationTextView)

        fun populate(score: HighScore) {
            locationTextView.text =
                "Longitude: " + score.longitude + ", Latitude: " + score.latitude
            itemView.setOnClickListener { v: View? ->
                scoreClickListener.onScoreClick(
                    score
                )
            }
        }
    }
}

