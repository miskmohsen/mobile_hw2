package com.example.hw1.model

import com.example.hw1.utilities.Constants


class Car(var row: Int, var column: Int) {
    var lives: Int = 3
     var score : Int =0



    fun loseLife() {
        if (lives > 0) {
            lives--
        }
    }

    fun isAlive(): Boolean {
        return lives > 0
    }
    fun moveLeft() {
        if (column > -1) {
            column--
        }  }

    fun moveRight() {
        if (column < Constants.COLUMNS - 1) {
            column++
        }  }
    fun reset() {
        score=0
        lives = 3

    }
    fun resetScore() {
        this.score = 0
    }

    fun addScore(points: Int) {
     score+=points
    }
}
