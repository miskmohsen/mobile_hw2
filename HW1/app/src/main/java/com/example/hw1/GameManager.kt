package com.example.hw1

import android.util.Log
import com.example.hw1.model.Car
import com.example.hw1.model.Obstacle
import com.example.hw1.utilities.Constants
import kotlin.random.Random

class GameManager(private val lifeCount: Int = 3) {
  val car: Car = Car(Constants.ROWS - 1, 1)
  val obstacles: MutableList<Obstacle> = mutableListOf()
  var isGameOver: Boolean = false
  var collisionListener: (() -> Unit)? = null

  init {
    startGame()
  }

  fun startGame() {
    obstacles.clear()
    car.reset()
    isGameOver = false
  }

  fun updateGame() {
    if (!isGameOver) {
      moveObstacles()
      checkCollisions()
      generateObstacle()
    }
  }

  private fun moveObstacles() {
    val iterator = obstacles.iterator()
    while (iterator.hasNext()) {
      val obstacle = iterator.next()
      obstacle.moveDown()
      if (obstacle.row >= Constants.ROWS) {
        iterator.remove()
        car.addScore(10)
      }
    }
  }

  private fun checkCollisions() {
    for (obstacle in obstacles) {
      if (obstacle.column == car.column && obstacle.row == car.row) {
        car.loseLife()
        Log.d("GameManager", "Collision detected. Lives left: ${car.lives}")

        if (!car.isAlive()) {
          isGameOver = true
          Log.d("GameManager", "Game Over!")
        }
        collisionListener?.invoke()
        break
      }
    }
  }

  private fun generateObstacle() {
    val random = Random
    if (random.nextInt(3) == 0) {
      val column = random.nextInt(Constants.COLUMNS)
      obstacles.add(Obstacle(0, column))
    }
  }

  fun moveLeft() {
    if (car.column > -1) {  // Prevent moving out of bounds
      car.column--
    }
  }

  fun moveRight() {
    if (car.column < Constants.COLUMNS - 1) {
      car.column++
    }
  }

  fun restartGame() {
    startGame()
  }

}
