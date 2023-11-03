package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

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
        }
    }

    private fun newGame() {
        for (button in buttons) {
            button!!.text = ""
        }

        turn!!.text = "Player X's turn!"
        currentPlayerX = true
    }
}