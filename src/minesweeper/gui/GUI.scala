package minesweeper.gui

import minesweeper.game.Minesweeper
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.GridPane

object GUI extends JFXApp {

  val game: Minesweeper = new Minesweeper(20, 10, 50)

  var buttons: List[MinesweeperButton] = List()
  for (j <- 0 until game.height) {
    for (i <- 0 until game.width) {
      buttons :+= new MinesweeperButton(i, j, game)
    }
  }

  this.stage = new PrimaryStage {
    this.title = "Minesweeper"
    this.scene = new Scene() {
      this.content = List(
        new GridPane {
          hgap = 1
          vgap = 1
          for (button <- buttons) {
            add(button, button.x, button.y)
          }
        }
      )
    }

    AnimationTimer(_ => {
      for (i <- game.displayStrings.indices) {
        buttons(i).text = game.displayStrings(i)
      }
    }).start()

  }

}
