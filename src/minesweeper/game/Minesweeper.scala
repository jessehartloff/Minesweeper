package minesweeper.game

import minesweeper.gui.MinesweeperButton

import scala.collection.mutable
import scala.util.Random


class Minesweeper(val width: Int, val height: Int, val numberOfMines: Int) {

  var mines: mutable.ArraySeq[Boolean] = null
  var displayStrings: mutable.ArraySeq[String] = null
  this.init()

  def init(): Unit = {
    val numberOfSpots: Int = this.width * this.height
    this.mines = Array.fill(numberOfSpots)(false)
    this.displayStrings = Array.fill(numberOfSpots)("x")
    for (i <- 0 until numberOfMines) {
      this.mines(i) = true
    }
    this.mines = Random.shuffle(this.mines)
  }

  def getDisplayStrings: mutable.ArraySeq[String] = {
    this.displayStrings
  }

  def locationToIndex(x: Int, y: Int): Int = {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      -1
    } else {
      y * this.width + x
    }

  }

  def howManyNeighbors(x: Int, y: Int): Int = {
    val neighbors: List[Int] = List(
      locationToIndex(x + 1, y),
      locationToIndex(x - 1, y),
      locationToIndex(x, y + 1),
      locationToIndex(x, y - 1),
      locationToIndex(x + 1, y + 1),
      locationToIndex(x - 1, y + 1),
      locationToIndex(x + 1, y - 1),
      locationToIndex(x - 1, y - 1)
    ).filterNot(_ == -1)

    neighbors.count(this.mines(_))
  }

  def buttonPressed(x: Int, y: Int): Unit = {
    val index: Int = locationToIndex(x, y)
    if (this.mines(index)) {
      this.displayStrings(index) = "*"
    } else {
      val numberOfNeighboringMines: Int = howManyNeighbors(x, y)
      this.displayStrings(index) = if (numberOfNeighboringMines == 0) "" else numberOfNeighboringMines.toString
    }
  }

}
