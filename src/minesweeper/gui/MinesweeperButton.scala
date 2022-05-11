package minesweeper.gui

import minesweeper.game.Minesweeper
import scalafx.scene.control.Button

class MinesweeperButton(val x: Int, val y: Int, val game: Minesweeper) extends Button {
  this.maxHeight = 20
  this.maxWidth = 20
  this.text = "error"
  this.style = "-fx-font: 12 ariel"
  this.onAction = new ButtonListener(this)
}
