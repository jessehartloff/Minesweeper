package minesweeper.game

import minesweeper.gui.MinesweeperButton

import scala.collection.mutable
import scala.util.Random


class Minesweeper(val width: Int, val height: Int, val numberOfMines: Int) {

  var mines: mutable.ArraySeq[Boolean] = null
  this.init()

  def init(): Unit ={
    val numberOfSpots: Int = this.width*this.height
    this.mines = Array.fill(numberOfSpots)(false)
    for(i <- 0 until numberOfMines){
      this.mines(i) = true
    }
    this.mines = Random.shuffle(this.mines)
  }

  def buttonToIndex(button: MinesweeperButton) : Int = {
    button.y * this.width + button.x
  }

  def buttonPressed(button: MinesweeperButton): Unit = {
    if(this.mines(buttonToIndex(button))){
      button.text = "*"
    }else{
      button.text = ""
    }
  }

}
