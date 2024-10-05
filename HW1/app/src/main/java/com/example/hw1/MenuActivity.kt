package com.example.hw1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)

        val btnButtonMode: Button = findViewById(R.id.button_mode)
        val btnSensorMode: Button = findViewById(R.id.sensor_mode)
        val btnHighScores: Button = findViewById(R.id.high_scores)

        btnButtonMode.setOnClickListener {
            // הפניה לפעילות המשחק במצב לחצנים
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("CONTROL_MODE", "BUTTONS")
            startActivity(intent)
        }

        btnSensorMode.setOnClickListener {
            // הפניה לפעילות המשחק במצב סנסורים
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("CONTROL_MODE", "SENSORS")
            startActivity(intent)
        }

        btnHighScores.setOnClickListener {
            // הפניה למסך טבלת השיאים
            startActivity(Intent(this, ScoresActivity::class.java))
        }
    }
}
