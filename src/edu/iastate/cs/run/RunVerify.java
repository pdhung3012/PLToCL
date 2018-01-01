package edu.iastate.cs.run;

import java.io.File;

import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.exp.Verification;

public class RunVerify {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Verification ver=new Verification();
		String folderTest=PathConstanct.PATH_OUTPUTFOLDER+"cliffer_grammars"+File.separator+"filter_v1"+File.separator;
		String fileTrainingGrammar=PathConstanct.PATH_OUTPUTFOLDER+"apacheComments_v3_400K"+File.separator+"setRules.txt";
		String fileTest=folderTest+"test.txt";
		String fileDetails=folderTest+"detailResults.txt";
		String fileLabel=folderTest+"label.txt";
		ver.calculateGrammar(fileTrainingGrammar, fileTest,fileLabel,fileDetails);
		
	}

}
