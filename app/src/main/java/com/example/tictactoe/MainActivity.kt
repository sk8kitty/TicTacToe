package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var buttons = Array<Button?>(9) { null }
    private var currentPlayerX = true
    private var turn: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)
        buttons = arrayOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        turn = findViewById<TextView>(R.id.turnsTextView)

        newGame()
    }


    fun btnClick(view: View) {
        val buttonSelected = view as Button
        playGame(buttonSelected)
    }
    fun newGameClick(view: View) {
        newGame()
    }

    private fun playGame(btnChoice: Button) {
        // button must have nothing in it to be chosen
        if (btnChoice.text.equals("")) {
            // if player X has chosen it...
            if (currentPlayerX) {
                btnChoice.text = "X"
                turn!!.text = getString(R.string.player_o_s_turn)
                currentPlayerX = false
            }
            // if player O has chosen it...
            else {
                btnChoice.text = "O"
                turn!!.text = getString(R.string.player_x_s_turn)
                currentPlayerX = true
            }

            // check for winning play or tied board
            checkWinner()
        }
    }

    private fun newGame() {
        // enable buttons
        for (button in buttons) {
            button?.isEnabled = true
        }

        // reset board
        for (button in buttons) {
            button!!.text = ""
        }

        // set turn
        turn!!.text = "Player X's turn!"
        currentPlayerX = true
    }

    private fun checkWinner() {
        // check rows
        for (i in 0 until 3) {
            if (buttons[i * 3]!!.text == buttons[i * 3 + 1]!!.text && buttons[i * 3 + 1]!!.text == buttons[i * 3 + 2]!!.text && buttons[i * 3]!!.text.isNotEmpty()) {
                toastWinner(buttons[i * 3]!!.text.toString())
                return
            }
        }

        // check columns
        for (i in 0 until 3) {
            if (buttons[i]!!.text == buttons[i + 3]!!.text && buttons[i + 3]!!.text == buttons[i + 6]!!.text && buttons[i]!!.text.isNotEmpty()) {
                toastWinner(buttons[i]!!.text.toString())
                return
            }
        }

        // check diagonals
        if (buttons[0]!!.text == buttons[4]!!.text && buttons[4]!!.text == buttons[8]!!.text && buttons[0]!!.text.isNotEmpty()) {
            toastWinner(buttons[0]!!.text.toString())
            return
        }
        if (buttons[2]!!.text == buttons[4]!!.text && buttons[4]!!.text == buttons[6]!!.text && buttons[2]!!.text.isNotEmpty()) {
            toastWinner(buttons[2]!!.text.toString())
            return
        }

        // check for tie
        if (isBoardFull()) {
            toastWinner("It's a tie!")
            return
        }
    }

    private fun toastWinner(winner: String) {
       val msg =
           if (winner == "It's a tie!") {
                "It's a tie!"
           }
           else {
               "Player $winner wins!"
           }

        // make the toast
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

        // disable buttons (re-enabled when new game is made)
        for (button in buttons) {
            button?.isEnabled = false
        }
    }

    private fun isBoardFull(): Boolean {
        for (button in buttons) {
            if (button!!.text.isEmpty()) {
                return false
            }
        }
        return true
    }
}