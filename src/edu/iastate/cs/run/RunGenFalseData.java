package edu.iastate.cs.run;

import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.parser.GenerateFalseData;

public class RunGenFalseData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fpIn=PathConstanct.PATH_OUTPUTFOLDER+"cliffer_grammars/removeSlash_comment.txt";
		String fpOutResult=PathConstanct.PATH_OUTPUTFOLDER+"cliffer_grammars/test.txt";
		String fpOutLabel=PathConstanct.PATH_OUTPUTFOLDER+"cliffer_grammars/label.txt";
		GenerateFalseData gfd=new GenerateFalseData();
		gfd.generateFalseData(fpIn, fpOutResult, fpOutLabel);
	}

}
