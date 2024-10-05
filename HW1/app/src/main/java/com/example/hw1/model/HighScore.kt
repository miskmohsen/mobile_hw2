class HighScore(val score: Int) : Comparable<HighScore> {
    override fun compareTo(other: HighScore): Int {
        return this.score.compareTo(other.score)
    }
}
