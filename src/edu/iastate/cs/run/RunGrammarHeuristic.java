package edu.iastate.cs.run;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.entities.GrammarRule;
import edu.iastate.cs.parser.GrammarHeuristic;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.trees.TreePrint;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;

import java.util.Collection;
import java.util.List;
import java.io.StringReader;

import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;

public class RunGrammarHeuristic {

	public void getAllRules(Tree node, ArrayList<GrammarRule> lstRules) {
		if (node != null) {
			// get the tag element of the node
			boolean isCorrectRule = true;
			GrammarRule gr = new GrammarRule();
			gr.setLhs(node.label().toString());

			if (node.isLeaf()) {
				isCorrectRule = false;
			}
			List<Tree> children = node.getChildrenAsList();
			ArrayList<String> lstChildren = new ArrayList<String>();
			for (Tree child : children) {
				if (child.isLeaf()) {
					isCorrectRule = false;
					// break;
				}
				lstChildren.add(child.label().toString());
				// getAllRules(child, lstRules);
			}
			gr.setLstRhs(lstChildren);

			if (isCorrectRule) {
				lstRules.add(gr);
			}
			for (Tree child : children) {
				getAllRules(child, lstRules);
			}

		}
	}

	


	
	public  Tree getTreeFromSentence(String strInput,LexicalizedParser lp) {
		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(
				new CoreLabelTokenFactory(), "");
		Tokenizer<CoreLabel> tok = tokenizerFactory
				.getTokenizer(new StringReader(strInput));
		List<CoreLabel> rawWords2 = tok.tokenize();
		Tree tree = lp.apply(rawWords2);
		return tree;
	}
	
	

	public static void main(String[] args) {
		GrammarHeuristic gh=new GrammarHeuristic();
		String fpInput=PathConstanct.PATH_OUTPUTFOLDER+"removeSlash_comment.txt";
		double threshold=0.5;
		ArrayList<GrammarRule> lstRules=gh.getGrammarsFromSentences(fpInput, threshold);


	}

}
