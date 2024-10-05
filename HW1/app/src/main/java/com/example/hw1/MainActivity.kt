package com.example.hw1

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hw1.utilities.Constants
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView


class MainActivity : AppCompatActivity() {

    private lateinit var game_display_area: FrameLayout
    private lateinit var car: ShapeableImageView
    private lateinit var main_FAB_left: FloatingActionButton
    private lateinit var main_FAB_right: FloatingActionButton
    private lateinit var scoreTextView : MaterialTextView
    private lateinit var gm: GameManager
    private lateinit var main_IMG_heart0: ShapeableImageView
    private lateinit var main_IMG_heart1: ShapeableImageView
    private lateinit var main_IMG_heart2: ShapeableImageView

    val handler: android.os.Handler = android.os.Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()
        gm = GameManager()
        updateLivesDisplay(gm.car.lives)
        initViews()
        startGameLoop()
        gm.collisionListener = {
            runOnUiThread {
                Toast.makeText(this, "Collision Detected!", Toast.LENGTH_SHORT).show()
                vibratePhone()
                updateLivesDisplay(gm.car.lives)
            }
        }
    }

    private fun startGameLoop() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (!gm.isGameOver) {
                    gm.updateGame()
                    updateUI()
                    displayObstacles()
                    updateLivesDisplay(gm.car.lives)
                    handler.postDelayed(this, Constants.GAME_UPDATE_DELAY)
                } else {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "Game Over!", Toast.LENGTH_SHORT).show()
                        updateLivesDisplay(0)
                    }
                    handler.postDelayed({
                        gm.restartGame()
                        updateLivesDisplay(gm.car.lives)
                        updateUI()
                        startGameLoop()
                    }, 2000)
                }
            }
        }, Constants.GAME_UPDATE_DELAY)
    }

    private fun initViews() {
        main_FAB_left.setOnClickListener {
            gm.moveLeft()
            updateCarPosition()
            updateUI()
        }
        main_FAB_right.setOnClickListener {
            gm.moveRight()
            updateCarPosition()
            updateUI()
        }
    }

    private fun updateCarPosition() {

        val columnWidth = game_display_area.width / Constants.COLUMNS  // 3 columns

        val carColumn = gm.car.column

        val params = car.layoutParams as FrameLayout.LayoutParams

        params.leftMargin = (carColumn * columnWidth) - columnWidth

        car.layoutParams = params
    }



    private fun findViews() {
        game_display_area = findViewById(R.id.game_display_area)
        car = findViewById(R.id.car)
        scoreTextView = findViewById(R.id.scoreTextView)
        main_FAB_left = findViewById(R.id.main_FAB_left)
        main_FAB_right = findViewById(R.id.main_FAB_right)
        main_IMG_heart0 = findViewById(R.id.main_IMG_heart0)
        main_IMG_heart1 = findViewById(R.id.main_IMG_heart1)
        main_IMG_heart2 = findViewById(R.id.main_IMG_heart2)
    }

    private fun updateLivesDisplay(lives: Int) {
        main_IMG_heart0.visibility = if (lives >= 1) View.VISIBLE else View.INVISIBLE
        main_IMG_heart1.visibility = if (lives >= 2) View.VISIBLE else View.INVISIBLE
        main_IMG_heart2.visibility = if (lives >= 3) View.VISIBLE else View.INVISIBLE
    }
    private fun updateUI() {
        scoreTextView.setText("Score: " + gm.car.score)
    }

    private fun displayObstacles() {
        game_display_area.removeAllViews()
        game_display_area.addView(car)

        val cellWidth = game_display_area.width / Constants.COLUMNS
        val cellHeight = game_display_area.height / Constants.ROWS

        for (obstacle in gm.obstacles) {
            val obstacleView = ImageView(this)
            obstacleView.setImageResource(R.drawable.obstacle)
            val params = FrameLayout.LayoutParams(cellWidth, cellHeight)

            val topMargin = obstacle.row * cellHeight
            val leftMargin = obstacle.column * cellWidth

            params.setMargins(leftMargin, topMargin, 0, 0)
            obstacleView.layoutParams = params
            game_display_area.addView(obstacleView)
        }
    }
    private fun vibratePhone() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(1500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(1500)
        }
    }
}
