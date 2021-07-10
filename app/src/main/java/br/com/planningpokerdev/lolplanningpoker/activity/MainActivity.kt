package br.com.planningpokerdev.lolplanningpoker.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.planningpokerdev.lolplanningpoker.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonZero.setOnClickListener{
            startActivity<SelectedCardActivity>("cardSelected" to "zero")
        }
        buttonMiddle.setOnClickListener{
            startActivity<SelectedCardActivity>("cardSelected" to "middle")
        }
        buttonOne.setOnClickListener{
            startActivity<SelectedCardActivity>("cardSelected" to "one")
        }
        buttonTwo.setOnClickListener{
            startActivity<SelectedCardActivity>("cardSelected" to "two")
        }
        buttonThree.setOnClickListener{
            startActivity<SelectedCardActivity>("cardSelected" to "three")
        }
        buttonFive.setOnClickListener{
            startActivity<SelectedCardActivity>("cardSelected" to "five")
        }
        buttonEight.setOnClickListener{
            startActivity<SelectedCardActivity>("cardSelected" to "eight")
        }
        buttonThirteen.setOnClickListener{
            startActivity<SelectedCardActivity>("cardSelected" to "thirteen")
        }
        buttonTwenty.setOnClickListener{
            startActivity<SelectedCardActivity>("cardSelected" to "twenty")
        }
        buttonForty.setOnClickListener{
            startActivity<SelectedCardActivity>("cardSelected" to "forty")
        }
        buttonHundred.setOnClickListener{
            startActivity<SelectedCardActivity>("cardSelected" to "hundred")
        }
        buttonQuestion.setOnClickListener{
            startActivity<SelectedCardActivity>("cardSelected" to "question")
        }
        buttonCoffee.setOnClickListener{
            startActivity<SelectedCardActivity>("cardSelected" to "coffee")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.xml.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_planning_poker -> {
                startActivity<PlanningPokerActivity>()
            }
            R.id.action_about -> {
                toast("Sobre")
            }
        }
        return true

    }

    fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
