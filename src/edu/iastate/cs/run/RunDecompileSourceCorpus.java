package edu.iastate.cs.run;

import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.visitor.NaiveASTVisitor;

public class RunDecompileSourceCorpus {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String folderInput="/Users/hungphan/git/jdk-jar/source/java/";
		String folderOutput="/Users/hungphan/git/jdk-jar/";
		String[] arrInputJava=RunCommentExtractorVisitor.findAllJavaFiles(folderInput);
		System.out.println("Get data from "+arrInputJava.length);
		NaiveASTVisitor navisitor=new NaiveASTVisitor(arrInputJava,PathConstanct.PATH_JDKSOURCE);
		navisitor.loadSourceFiles();
		System.out.println("End load");
		navisitor.saveResultToFile(folderOutput+"source_sig.txt", folderOutput+"source_token.txt");
	}

}
