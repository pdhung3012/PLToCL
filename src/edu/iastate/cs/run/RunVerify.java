package edu.iastate.cs.run;

import java.io.File;

import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.exp.Verification;

public class RunVerify {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Verification ver=new Verification();
		String fileTrainingGrammar=PathConstanct.PATH_OUTPUTFOLDER+"apacheComments_v2"+File.separator+"setRules.txt";
		String fileTest=PathConstanct.PATH_OUTPUTFOLDER+"eclipseComments"+File.separator+"comment.txt";
		ver.calculateGrammar(fileTrainingGrammar, fileTest);
	}

}
