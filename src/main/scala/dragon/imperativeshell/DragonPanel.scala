package dragon.imperativeshell

import dragon.*
import dragon.functionalcore.Direction.{East, North, South, West}
import dragon.functionalcore.*

import java.awt.{Color, Graphics}
import javax.swing.*

class DragonPanel(lineColour: Color, backgroundColour: Color) extends JPanel:

  override def paintComponent(g: Graphics): Unit =

    val panelHeight = getSize().height - 1

    def startPoint: Point =
      val panelWidth = getSize().width - 1
      val panelCentre = Point(panelWidth / 2, panelHeight / 2)
      panelCentre
        .translate(South, panelHeight / 7)
        .translate(West, panelWidth / 5)

    def draw(line: Line): Unit =
      val (ax, ay) = line.start.deviceCoords(panelHeight)
      val (bx, by) = line.end.deviceCoords(panelHeight)
      g.drawLine(ax, ay, bx, by)

    def drawDragon(start: Point, age: Int, length: Int, direction: Direction): Unit =
      Dragon(start, age, length, direction)
        .path
        .lines
        .foreach(draw)

    super.paintComponent(g)
    setBackground(backgroundColour)
    g.setColor(lineColour)

    drawDragon(startPoint, age = 17, length = 1, direction = East)