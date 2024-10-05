import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hw1.Fragments.MapLocationFragment
import com.example.hw1.R
import com.google.android.material.color.utilities.Score

class ScoresActivity : AppCompatActivity(), ScoresActivity.HighScoreAdapter.ScoreClickListener {
    class HighScoreAdapter {

    }

    private lateinit var mapFragment: MapLocationFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        mapFragment = MapLocationFragment()
        val scoreListFragment = HighScoreFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.mapFragment, mapFragment)
            .commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.scoreListFragment, scoreListFragment)
            .commit()
    }

    override fun onScoreClick(score: Score) {
        mapFragment.choose(score.latitude, score.longitude)
    }
}
