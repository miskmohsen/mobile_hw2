package com.example.hw1.model

class Obstacle(var row: Int, var column: Int) {

    fun moveDown() {
        row++
    }

    fun reachesCar(carRow: Int): Boolean {
        return row == carRow
    }
}