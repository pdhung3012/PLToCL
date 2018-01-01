package edu.iastate.cs.run;

import java.io.File;

import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.parser.GenerateFalseData;

public class RunGenFalseData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String folderTest= PathConstanct.PATH_OUTPUTFOLDER+"cliffer_grammars"+File.separator+"filter_v1"+File.separator;
		String fpIn=folderTest+"comment.txt";
		String fpOutResult=folderTest+"test.txt";
		String fpOutLabel=folderTest+"label.txt";
		GenerateFalseData gfd=new GenerateFalseData();
		gfd.generateFalseData(fpIn, fpOutResult, fpOutLabel);
	}

}
