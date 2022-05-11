package minesweeper.gui

import minesweeper.game.Minesweeper
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.GridPane

object GUI extends JFXApp {

  val game: Minesweeper = new Minesweeper(20, 10, 50)

  var buttons: List[MinesweeperButton] = List()
  for(i <- 0 until game.width){
    for(j <- 0 until game.height){
      buttons +:= new MinesweeperButton(i, j, game)
    }
  }

  this.stage = new PrimaryStage {
    this.title = "Minesweeper change"
    this.scene = new Scene(){
      this.content = List(
        new GridPane {
          for(button <- buttons){
            add(button, button.x, button.y)
          }
        }
      )
    }
  }

}
