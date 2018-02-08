package edu.iastate.cs.run;

import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.entities.Scope;
import edu.iastate.cs.entities.nlp.NLPNode;
import edu.iastate.cs.parser.GrammarHeuristic;
import edu.iastate.cs.visitor.NLPVisitor;
import edu.iastate.cs.visitor.VerifyingNLVisitor;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.Tree;

public class RunASTParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileGrammar=PathConstanct.PATH_OUTPUTFOLDER+"testGrammar.txt";
		String fileVarCheck=PathConstanct.PATH_OUTPUTFOLDER+"testVar.txt";
		VerifyingNLVisitor verifyVisitor=new VerifyingNLVisitor(fileGrammar, fileVarCheck); 
		Scope scope=new Scope();
//		String strInput="j is now index of child with greater value";
		String strInput="j is now index of child with a a a a b greater value";
		GrammarHeuristic gh=new GrammarHeuristic();
		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
		LexicalizedParser lp = LexicalizedParser.loadModel(PathConstanct.PATH_DEFAULTPARSEMODEL);
		
		Tree tree = gh.getTreeFromSentence(strInput, lp, tokenizerFactory);
		NLPNode nlpTree=gh.getNLPTree(tree);
		System.out.println(nlpTree.printTree(nlpTree, 0));
		try {
			nlpTree.traverse(verifyVisitor, scope);
			System.out.println("Parse the tree success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
