package norwegiangambit.psqt

import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import norwegiangambit.psqt.pSQT.FDescription
import norwegiangambit.psqt.pSQT.TDescription
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

@InjectWith(PSQTInjectorProvider)
@RunWith(XtextRunner)
class CreatePSQTimages {

	var base = 0
	val out_dir = "testdata/output"

	@Test
	def void createPSQTimages() {
		createImages("testdata/Classic.psqt")
		createImages("testdata/StockFish.psqt")
	}

	def createImages(String path) {
		val dir = new File(out_dir)
		if (!dir.exists)
			dir.mkdir
		val model=PSQT_util::readModel(path)
		base = model.base
		for (table : model.tables) {
			createImage(table as TDescription, model.name, false)
			createImage(table as TDescription, model.name, true)
		}
		val file = new File(out_dir + "/psqt_"+model.name+".html")
		FileUtils.string2file(createHTML(model), file)
	}

	def createImage(TDescription table, String name, boolean isEnd) {
		val img = new BufferedImage(260, 260, BufferedImage.TYPE_INT_ARGB);
		val gc = img.createGraphics
		gc.setColor(Color.YELLOW)
		gc.fillRect(0, 0, 260, 260)
		var min = 0;
		var max = 0;
		val psqt = PSQT_util::getPSQT(table,isEnd)
		for (v : psqt) {
			min = Math::min(min, v)
			max = Math::max(max, v)
		}
		val adj = (max + min) / 2
		var p = 0
		for (v : psqt)
			gc.rect(p++, v, adj)
		gc.setColor(Color.BLACK)
		for (i : 0 .. 7) {
			gc.drawString("ABCDEFGH".substring(i, i + 1), 30 + i * 30, 255)
			gc.drawString(Integer.toString(8 - i), 5, 20 + 30 * i)
		}
		ImageIO::write(img, "PNG",
			new File(out_dir + "/" + name + "_" + (if(isEnd) "End" else "Mid") + table.name + ".png"))
	}

	def String createHTML(FDescription model) {
		'''
			<html>
			<head>
				<title>PSQT: «model.name»</title>
			</head>
			<body>
			<h2>Piece Square Tables: «model.name»</h2>
			<table>
			«FOR table : model.tables»
				«val n = (table as TDescription).name»
				<tr>
				  <td>MidGame «n»<br><img src="«model.name»_Mid«n».png"></td>
				  <td>EndGame «n»<br><img src="«model.name»_End«n».png"></td>
				</tr>
			«ENDFOR»
			<table>
			</body>
			</html>
		'''
	}

	def rect(Graphics2D gc, int p, int i, int adj) {
		val c = p % 8
		val r = p / 8
		val str = (255 - (Math::abs(i - adj) as float) * 255 / base) as int
		val col = if(i - adj > 0) new Color(str, 255, str) else new Color(255, str, str)
		gc.setColor(col)
		val x = c * 30 + 20
		val y = (7 - r) * 30
		gc.fillRect(1 + x, 1 + y, 28, 28)
		gc.setColor(Color.BLACK)
		gc.drawRect(x, y, 29, 29)
		gc.drawString(Integer.toString(i), 2 + x, 15 + y)
	}

}
