package edu.iastate.cs.run;

import java.io.File;

import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.parser.GrammarHeuristic;

public class RunSingleSentenceParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileGrammar=PathConstanct.PATH_OUTPUTFOLDER+"jdkFilterComments"+File.separator+"setRules.txt";
		String fileInputText=PathConstanct.PATH_OUTPUTFOLDER+"query.txt";
		String folderOut=PathConstanct.PATH_OUTPUTFOLDER+File.separator+"qResult"+File.separator;
		GrammarHeuristic gh=new GrammarHeuristic();
		gh.getGrammarFromSetSentence(fileGrammar, fileInputText, folderOut);
	}

}
