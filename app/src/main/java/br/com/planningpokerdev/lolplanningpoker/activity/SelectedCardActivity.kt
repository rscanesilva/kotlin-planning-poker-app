package br.com.planningpokerdev.lolplanningpoker.activity

import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.planningpokerdev.lolplanningpoker.R
import kotlinx.android.synthetic.main.activity_selected_card.*
import android.graphics.drawable.BitmapDrawable
import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import br.com.planningpokerdev.lolplanningpoker.activity.ShakeDetector.OnShakeListener

class SelectedCardActivity : AppCompatActivity() {
    private var mShakeDetector: ShakeDetector? = null
    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null
    var freeToFlip = true
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_card)
        setImage(R.drawable.back_card)

        val cardSelected = intent.getSerializableExtra("cardSelected") as String
        val drawableId = resources.getIdentifier(cardSelected, "drawable", packageName)

        imageView.setOnClickListener{
            rotateImage(drawableId)
        }

        // ShakeDetector initialization
        /*mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mShakeDetector = ShakeDetector(OnShakeListener {
            rotateImage(drawableId)
        })*/
    }

    override fun onResume() {
        super.onResume()
        mSensorManager?.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    override fun onPause() {
        mSensorManager?.unregisterListener(mShakeDetector);
        super.onPause()
    }
    fun rotateImage(drawableId: Int){
        if (freeToFlip){
            imageView.setRotationY(0f)
            imageView.animate().rotationY(90f).setListener(object : AnimatorListener {

                override fun onAnimationStart(animation: Animator) {
                    freeToFlip = false
                }

                override fun onAnimationRepeat(animation: Animator) {}

                override fun onAnimationEnd(animation: Animator) {
                    if (count == 0){
                        setImage(drawableId)
                        count+=1
                    } else {
                        setImage(R.drawable.back_card)
                        count-=1
                    }

                    imageView.setRotationY(270f)
                    imageView.animate().rotationY(360f).setListener(null)
                    freeToFlip = true
                }

                override fun onAnimationCancel(animation: Animator) {}
            })
        }
    }
    fun setImage(drawableId: Int){
        val mbitmap = (resources.getDrawable(drawableId, theme) as BitmapDrawable).bitmap
        val imageRounded = Bitmap.createBitmap(mbitmap.width, mbitmap.height, mbitmap.config)
        val canvas = Canvas(imageRounded)
        val mpaint = Paint()
        mpaint.setAntiAlias(true)
        mpaint.setShader(BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP))
        canvas.drawRoundRect(RectF(0f, 0f, mbitmap.width.toFloat(), mbitmap.height.toFloat()), 50f, 50f, mpaint)// Round Image Corner 100 100 100 100
        imageView.setImageBitmap(imageRounded)
    }
}
