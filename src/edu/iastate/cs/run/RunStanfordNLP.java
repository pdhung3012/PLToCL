package edu.iastate.cs.run;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import edu.iastate.cs.entities.GrammarRule;
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

public class RunStanfordNLP {

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

	/**
	 * demoDP demonstrates turning a file into tokens and then parse trees. Note
	 * that the trees are printed by calling pennPrint on the Tree object. It is
	 * also possible to pass a PrintWriter to pennPrint if you want to capture
	 * the output. This code will work with any supported language.
	 */
	public static void demoDP(LexicalizedParser lp, String filename) {
		// This option shows loading, sentence-segmenting and tokenizing
		// a file using DocumentPreprocessor.
		TreebankLanguagePack tlp = lp.treebankLanguagePack(); // a
																// PennTreebankLanguagePack
																// for English
		GrammaticalStructureFactory gsf = null;
		if (tlp.supportsGrammaticalStructures()) {
			gsf = tlp.grammaticalStructureFactory();
		}
		// You could also create a tokenizer here (as below) and pass it
		// to DocumentPreprocessor
		for (List<HasWord> sentence : new DocumentPreprocessor(filename)) {
			Tree parse = lp.apply(sentence);
			parse.pennPrint();
			System.out.println();

			if (gsf != null) {
				GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
				Collection tdl = gs.typedDependenciesCCprocessed();
				System.out.println(tdl);
				System.out.println();
			}
		}
	}

	/**
	 * demoAPI demonstrates other ways of calling the parser with already
	 * tokenized text, or in some cases, raw text that needs to be tokenized as
	 * a single sentence. Output is handled with a TreePrint object. Note that
	 * the options used when creating the TreePrint can determine what results
	 * to print out. Once again, one can capture the output by passing a
	 * PrintWriter to TreePrint.printTree. This code is for English.
	 */
	public static void demoAPI(LexicalizedParser lp) {
		// This option shows parsing a list of correctly tokenized words
//		String[] sent = { "This", "is", "an", "easy", "sentence", "." };
//		List<CoreLabel> rawWords = Sentence.toCoreLabelList(sent);
//		Tree parse = lp.apply(rawWords);
//		parse.pennPrint();
//		System.out.println();

		// This option shows loading and using an explicit tokenizer
		String sent2 = "swap minimum with last value .";
		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(
				new CoreLabelTokenFactory(), "");
		Tokenizer<CoreLabel> tok = tokenizerFactory
				.getTokenizer(new StringReader(sent2));
		List<CoreLabel> rawWords2 = tok.tokenize();
		Tree parse = lp.apply(rawWords2);

		TreebankLanguagePack tlp = lp.treebankLanguagePack(); // PennTreebankLanguagePack
																// for English
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
//		List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
//		System.out.println(tdl);
//		System.out.println();

		// You can also use a TreePrint object to print trees and dependencies
		TreePrint tp = new TreePrint("penn,typedDependenciesCollapsed");
		tp.printTree(parse);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Properties props = PropertiesUtils.asProperties("annotators",
		// "tokenize, ssplit, pos, parse",
		// "parse.model",
		// "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz",
		// "tokenize.language", "en");
		// StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		// String text = "swap minimum with last value .";
		// Annotation annotation = new Annotation(text);
		// pipeline.annotate(annotation);
		// List<CoreMap> sentences = annotation.get(SentencesAnnotation.class);
		//
		// RunStanfordNLP rsNLP = new RunStanfordNLP();
		// ArrayList<GrammarRule> lstResult = new ArrayList<GrammarRule>();
		// for (CoreMap sentence : sentences) {
		// Tree tree = sentence.get(TreeAnnotation.class);
		// rsNLP.getAllRules(tree, lstResult);
		// }
		//
		// System.out.println("Number of rules extract:");
		// for (GrammarRule item : lstResult) {
		// System.out.println(item.print());
		// }
//
		String parserModel = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
		if (args.length > 0) {
			parserModel = args[0];
		}
		LexicalizedParser lp = LexicalizedParser.loadModel(parserModel);
		demoAPI(lp);


	}

}
