package com.example.hw1.model

class Coin(var row: Int, var column: Int) {
    fun moveDown() {
        row++
    }

    fun reachesCar(CarRow: Int): Boolean {
        return this.row == CarRow
    }
}