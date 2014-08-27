package norwegiangambit.psqt

import java.io.File
import norwegiangambit.psqt.pSQT.TDescription
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

@InjectWith(PSQTInjectorProvider)
@RunWith(XtextRunner)
class TestPSQT {

	var base = 0
	val out_dir = "testdata/output"

	@Test
	def void parsePSQT() {
		createCodeFile("testdata/Classic.psqt")
		createCodeFile("testdata/StockFish.psqt")
	}

	def createCodeFile(String path) {
		val dir = new File(out_dir)
		if (!dir.exists)
			dir.mkdir
		val model=PSQT_util::readModel(path)
		base = model.base
		val file = new File(out_dir + "/"+model.name+".txt")
		val code = '''
		«FOR table : model.tables»
		  { // «(table as TDescription).name»
		  «writeTable(table as TDescription)»
		  },
		«ENDFOR»
		'''
		FileUtils.string2file(code, file)
	}

	def String writeTable(TDescription table) {
		val m=PSQT_util::getPSQT(table,false)
		val e=PSQT_util::getPSQT(table,true)
		val sb=new StringBuilder
		for(r : 0..7){
			for(c : 0..7){
				val p=r*8+c
				if(c!=0)
					sb.append(",")
				sb.append(" S("+format(Integer.toString(m.get(p)))+","+format(Integer.toString(e.get(p)))+")")
			}
			if(r!=7)
				sb.append(",")
			sb.append("\n")
		}
		return sb.toString		
	}
	
	def format(String txt) {
		return "    ".substring(txt.length)+txt
	}
	
}
