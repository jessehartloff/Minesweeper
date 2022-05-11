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

  def neighbors(point: Point): List[Point] = {
    val x: Int = point.x
    val y: Int = point.y
    val points: List[Point] = List(
      new Point(x + 1, y),
      new Point(x - 1, y),
      new Point(x, y + 1),
      new Point(x, y - 1),
      new Point(x + 1, y + 1),
      new Point(x - 1, y + 1),
      new Point(x + 1, y - 1),
      new Point(x - 1, y - 1)
    ).filterNot((p: Point) => p.x < 0 || p.x >= this.width || p.y < 0 || p.y >= this.height)

    points
  }

  def howManyNeighbors(x: Int, y: Int): Int = {
    neighbors(new Point(x,y)).count((p: Point) => this.mines(locationToIndex(p.x, p.y)))

//    val neighbors: List[Int] = List(
//      locationToIndex(x + 1, y),
//      locationToIndex(x - 1, y),
//      locationToIndex(x, y + 1),
//      locationToIndex(x, y - 1),
//      locationToIndex(x + 1, y + 1),
//      locationToIndex(x - 1, y + 1),
//      locationToIndex(x + 1, y - 1),
//      locationToIndex(x - 1, y - 1)
//    ).filterNot(_ == -1)
//1
//    neighbors.count(this.mines(_))
  }

  def buttonPressed(x: Int, y: Int): Unit = {
    val index: Int = locationToIndex(x, y)
    if (this.mines(index)) {
      this.displayStrings(index) = "*"
    } else {
      val numberOfNeighboringMines: Int = howManyNeighbors(x, y)
      this.displayStrings(index) = if (numberOfNeighboringMines == 0) "" else numberOfNeighboringMines.toString
      if (numberOfNeighboringMines == 0){
        neighbors(new Point(x,y)).foreach((p: Point)=> if(this.displayStrings(locationToIndex(p.x, p.y)) == "x") buttonPressed(p.x, p.y))
      }
    }
  }

}
