package norwegiangambit.psqt.action

import java.io.File
import norwegiangambit.psqt.PSQT_util
import norwegiangambit.psqt.pSQT.TDescription
import org.eclipse.xtext.junit4.InjectWith

@InjectWith(PSQTUiInjectorProvider)
class TestPSQT {

	var base = 0
	def createCodeFile(String path) {
		val in=new File(path)
		val dir = in.parentFile
		val model=PSQT_util::readModel(path)
		base = model.base
		val file = new File(dir + "/"+model.name+".txt")
		val code = '''
		«FOR table : model.tables»
		  { // «(table as TDescription).name»
		  «writeTable(table as TDescription)»
		  },
		«ENDFOR»
		'''
		FileUtils.string2file(code, file)
		return file
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
