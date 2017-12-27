package edu.iastate.cs.parser;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import util.FileIO;
import util.SortUtil;
import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.entities.GrammarRule;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.Tree;

public class GrammarHeuristic {

	private LinkedHashSet<String> setNonTerminal;

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

	public GrammarHeuristic() {
	}

	public Tree getTreeFromSentence(String strInput, LexicalizedParser lp,
			TokenizerFactory<CoreLabel> tokenizerFactory) {
		Tokenizer<CoreLabel> tok = tokenizerFactory.getTokenizer(new StringReader(strInput));
		List<CoreLabel> rawWords2 = tok.tokenize();
		Tree tree = lp.apply(rawWords2);
		return tree;
	}

	public LinkedHashMap<String, ArrayList<GrammarRule>> getGrammarsFromSentences(String fpInput) {

		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");

		LexicalizedParser lp = LexicalizedParser.loadModel(PathConstanct.PATH_DEFAULTPARSEMODEL);
		String[] arrSentences = FileIO.readStringFromFile(fpInput).trim().split("\n");

		HashMap<String, Integer> mapRules = new HashMap<>();

		// StringBuilder sbTemp=new StringBuilder();
		for (int i = 0; i < arrSentences.length; i++) {
			// sbTemp.append(arrSentences[i]+"\n");

			if ((i + 1) % 1000 == 0 || i == arrSentences.length - 1) {
				System.out.println("Parse sentence " + (i + 1));
				if(i+1>40000) {
					break;
				}
			}
			String strContent = arrSentences[i].trim();
			Tree tree = getTreeFromSentence(strContent, lp, tokenizerFactory);
			ArrayList<GrammarRule> lstRules = new ArrayList<GrammarRule>();
			getAllRules(tree, lstRules);
			for (int j = 0; j < lstRules.size(); j++) {
				String strRuleContent = lstRules.get(j).print();
				if (!mapRules.containsKey(strRuleContent)) {
					mapRules.put(strRuleContent, 1);
				} else {
					mapRules.put(strRuleContent, mapRules.get(strRuleContent) + 1);
				}
			}

		}
		System.out.println(mapRules.size());
		LinkedHashMap<String, Integer> sortedMapRules = SortUtil.sortHashMapByValues(mapRules, false);

		// split the map to each pre terminal
		LinkedHashMap<String, ArrayList<GrammarRule>> mapSortedGrammar = new LinkedHashMap<String, ArrayList<GrammarRule>>();

		for (String strKey : sortedMapRules.keySet()) {
			String lhs = strKey.split(":")[0].trim();
			GrammarRule gr = new GrammarRule();
			gr.setCount(sortedMapRules.get(strKey));
			gr.setLhs(strKey.split(":")[0].trim());
			String[] arrRHS = strKey.split(":")[1].trim().split("\\s+");
			ArrayList<String> lstRHS = new ArrayList<String>();
			for (String item : arrRHS) {
				lstRHS.add(item.trim());
			}
			gr.setLstRhs(lstRHS);
			if (!mapSortedGrammar.containsKey(lhs)) {
				ArrayList<GrammarRule> lstRules = new ArrayList<GrammarRule>();
				lstRules.add(gr);
				mapSortedGrammar.put(lhs, lstRules);
			} else {
				mapSortedGrammar.get(lhs).add(gr);
			}
		}

		return mapSortedGrammar;

	}

	public void saveRuleToFiles(LinkedHashMap<String, ArrayList<GrammarRule>> lstRules, double threshold,
			String fileOut, String folderOut, String fileNonTerminal) {
		if (threshold > 1) {
			throw new IllegalArgumentException("Invallid threshold");
		}
		StringBuilder sbMain = new StringBuilder();
		File folder = new File(folderOut);
		if (!folder.exists() || !folder.isDirectory()) {
			folder.mkdir();
		}
		setNonTerminal = new LinkedHashSet<>();
		for (String lhs : lstRules.keySet()) {
			ArrayList<GrammarRule> lstPerNonTerminalRules = lstRules.get(lhs);
			int numberOfRulesNeed = (int) Math.round(lstPerNonTerminalRules.size() * threshold);
			setNonTerminal.add(lhs);
			StringBuilder sbRule = new StringBuilder();
			for (int i = 0; i < numberOfRulesNeed; i++) {
				for (String strRHS : lstPerNonTerminalRules.get(i).getLstRhs()) {
					setNonTerminal.add(strRHS);
				}
				String strItem = lstPerNonTerminalRules.get(i).print() + " : "
						+ lstPerNonTerminalRules.get(i).getCount() + "\n";
				sbMain.append(strItem);
				sbRule.append(strItem);
			}

			FileIO.writeStringToFile(sbRule.toString(), folder.getAbsolutePath() + File.separator + lhs + "_.txt");
		}

		StringBuilder sbNonTerminal = new StringBuilder();
		for (String strItem : setNonTerminal) {
			sbNonTerminal.append(strItem + "\n");
		}
		FileIO.writeStringToFile(sbNonTerminal.toString(), fileNonTerminal);
		FileIO.writeStringToFile(sbMain.toString(), fileOut);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
