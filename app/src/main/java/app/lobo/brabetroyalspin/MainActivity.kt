package app.lobo.brabetroyalspin



import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var scoreTextView: TextView
    private lateinit var timerTextView: TextView
    private lateinit var coin1ImageView: ImageView
    private lateinit var coin2ImageView: ImageView
    private lateinit var bombImageView: ImageView

    private var score = 0
    private var isGameRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreTextView = findViewById(R.id.score)
        timerTextView = findViewById(R.id.timer)
        coin1ImageView = findViewById(R.id.coin1)
        coin2ImageView = findViewById(R.id.coin2)
        bombImageView = findViewById(R.id.bomb)

        coin1ImageView.setOnClickListener {
            increaseScore(20)
            resetCoinPosition(coin1ImageView)
        }
        coin2ImageView.setOnClickListener {
            increaseScore(20)
            resetCoinPosition(coin2ImageView)
        }
        bombImageView.setOnClickListener {
            endGame()
            finish() // Завершаем активность при проигрыше
        }

        startGame()
    }

    private fun startGame() {
        score = 0
        isGameRunning = true

        val gameDurationMillis: Long = 30000 // 30 seconds
        val countDownInterval: Long = 1000 // 1 second

        object : CountDownTimer(gameDurationMillis, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                timerTextView.text = secondsLeft.toString()
            }

            override fun onFinish() {
                isGameRunning = false
                timerTextView.text = "0"
                showGameOverMessage()
                finish() // Завершаем активность по окончании игры
            }
        }.start()

        startCoinAnimation(coin1ImageView)
        startCoinAnimation(coin2ImageView)
        startBombAnimation()
    }

    private fun startCoinAnimation(coinImageView: ImageView) {
        val screenWidth = resources.displayMetrics.widthPixels.toFloat()
        val screenHeight = resources.displayMetrics.heightPixels.toFloat()

        val coinAnimatorX = ObjectAnimator.ofFloat(
            coinImageView, View.TRANSLATION_X,
            0f, screenWidth - coinImageView.width
        )
        coinAnimatorX.duration = 2000
        coinAnimatorX.interpolator = LinearInterpolator()
        coinAnimatorX.repeatCount = ObjectAnimator.INFINITE
        coinAnimatorX.repeatMode = ObjectAnimator.REVERSE

        val coinAnimatorY = ObjectAnimator.ofFloat(
            coinImageView, View.TRANSLATION_Y,
            0f, screenHeight - coinImageView.height
        )
        coinAnimatorY.duration = 1500
        coinAnimatorY.interpolator = LinearInterpolator()
        coinAnimatorY.repeatCount = ObjectAnimator.INFINITE
        coinAnimatorY.repeatMode = ObjectAnimator.REVERSE

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(coinAnimatorX, coinAnimatorY)
        animatorSet.start()
    }

    private fun startBombAnimation() {
        val screenWidth = resources.displayMetrics.widthPixels.toFloat()
        val screenHeight = resources.displayMetrics.heightPixels.toFloat()

        val bombAnimatorX = ObjectAnimator.ofFloat(
            bombImageView, View.TRANSLATION_X,
            0f, screenWidth - bombImageView.width
        )
        bombAnimatorX.duration = 2500
        bombAnimatorX.interpolator = LinearInterpolator()
        bombAnimatorX.repeatCount = ObjectAnimator.INFINITE
        bombAnimatorX.repeatMode = ObjectAnimator.REVERSE

        val bombAnimatorY = ObjectAnimator.ofFloat(
            bombImageView, View.TRANSLATION_Y,
            0f, screenHeight - bombImageView.height
        )
        bombAnimatorY.duration = 2000
        bombAnimatorY.interpolator = LinearInterpolator()
        bombAnimatorY.repeatCount = ObjectAnimator.INFINITE
        bombAnimatorY.repeatMode = ObjectAnimator.REVERSE

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(bombAnimatorX, bombAnimatorY)
        animatorSet.start()
    }

    private fun increaseScore(points: Int) {
        if (isGameRunning) {
            score += points
            scoreTextView.text = score.toString()
        }
    }

    private fun resetCoinPosition(coinImageView: ImageView) {
        coinImageView.translationX = 0f
        coinImageView.translationY = 0f
    }

    private fun endGame() {
        isGameRunning = false
        showGameOverMessage()
    }

    private fun showGameOverMessage() {
        // You can customize this method to display a game over message or perform other actions
        // For example, showing a dialog with the final score and an option to restart the game.
    }
}