package minesweeper.gui

import javafx.event.EventHandler
import javafx.event.ActionEvent

class ButtonListener(button: MinesweeperButton) extends EventHandler[ActionEvent]{

  override def handle(event: ActionEvent): Unit = {
    button.game.buttonPressed(button.x, button.y)
  }

}
