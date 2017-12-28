package edu.iastate.cs.exp;

import java.util.ArrayList;
import java.util.HashSet;

import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.entities.GrammarRule;
import edu.iastate.cs.parser.GrammarHeuristic;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.Tree;
import util.FileIO;

public class Verification {

	public void calculateGrammar(String fileTrainingGrammar, String fileTest) {
		String[] arrContentTrainGrammar = FileIO
				.readStringFromFile(fileTrainingGrammar).trim().split("\n");
		String[] arrContentTest = FileIO.readStringFromFile(fileTest).trim()
				.split("\n");

		GrammarHeuristic gh = new GrammarHeuristic();
		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(
				new CoreLabelTokenFactory(), "");
		LexicalizedParser lp = LexicalizedParser
				.loadModel(PathConstanct.PATH_DEFAULTPARSEMODEL);

		HashSet<String> setTrainedGrammar = new HashSet<String>();
		for (int i = 0; i < arrContentTrainGrammar.length; i++) {
			String[] arrItem = arrContentTrainGrammar[i].split(":");
			String strItem = arrItem[0].trim() + " : " + arrItem[1].trim();
			//System.out.println("Item: "+strItem);
			setTrainedGrammar.add(strItem);

		}
		
		System.out.println("set grammar: "+setTrainedGrammar.size());

		int numCountInCorrect = 0;
		int numForTest = 10000;
		for (int i = 0; i < numForTest; i++) {
			boolean isValid = true;
			//System.out.println(arrContentTest[i]);
			Tree tree = gh.getTreeFromSentence(arrContentTest[i], lp,
					tokenizerFactory);
			ArrayList<GrammarRule> lstRules = new ArrayList<GrammarRule>();
			gh.getAllRules(tree, lstRules);

			for (int j = 0; j < lstRules.size(); j++) {
				GrammarRule ruleItem = lstRules.get(j);
				if (!setTrainedGrammar.contains(ruleItem.print())) {
					isValid = false;
			//		System.out.println(ruleItem.print()+" not exist");
					break;
				}
			}

			if (!isValid) {
				numCountInCorrect++;
				System.out.println(i+1);
			} else{
				
			}

		}
		System.out.println("Verification results: " + numCountInCorrect + "/"
				+ numForTest);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
