package edu.iastate.cs.parser;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import util.FileIO;
import util.SortUtil;
import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.entities.GrammarRule;
import edu.iastate.cs.entities.nlp.*;
import edu.iastate.cs.entities.nlp.ROOT;
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

	public NLPNode getNLPNodeFromTreeLabel(String label) {
		NLPNode result = null;
		switch (label) {
		case "ROOT":
			result = new ROOT();
			break;
		case "ADJP":
			result = new ADJP();
			break;
		case "ShiftADV":
			result = new ShiftADV();
			break;
		case "ADVP":
			result = new ADVP();
			break;
		case "ShiftBNF":
			result = new ShiftBNF();
			break;
		case "CC":
			result = new CC();
			break;
		case "CD":
			result = new CD();
			break;
		case "ShiftCLF":
			result = new ShiftCLF();
			break;
		case "ShiftCLR":
			result = new ShiftCLR();
			break;
		case "CONJP":
			result = new CONJP();
			break;
		case "ShiftDIR":
			result = new ShiftDIR();
			break;
		case "DT":
			result = new DT();
			break;
		case "ShiftDTV":
			result = new ShiftDTV();
			break;
		case "EX":
			result = new EX();
			break;
		case "ShiftEXT":
			result = new ShiftEXT();
			break;
		case "FRAG":
			result = new FRAG();
			break;
		case "FW":
			result = new FW();
			break;
		case "ShiftHLN":
			result = new ShiftHLN();
			break;
		case "IN":
			result = new IN();
			break;
		case "INTJ":
			result = new INTJ();
			break;
		case "JJ":
			result = new JJ();
			break;
		case "JJR":
			result = new JJR();
			break;
		case "JJS":
			result = new JJS();
			break;
		case "ShiftLGS":
			result = new ShiftLGS();
			break;
		case "ShiftLOC":
			result = new ShiftLOC();
			break;
		case "LS":
			result = new LS();
			break;
		case "LST":
			result = new LST();
			break;
		case "MD":
			result = new MD();
			break;
		case "ShiftMNR":
			result = new ShiftMNR();
			break;
		case "NAC":
			result = new NAC();
			break;
		case "NN":
			result = new NN();
			break;
		case "NNS":
			result = new NNS();
			break;
		case "NNP":
			result = new NNP();
			break;
		case "NNPS":
			result = new NNPS();
			break;
		case "ShiftNOM":
			result = new ShiftNOM();
			break;
		case "NP":
			result = new NP();
			break;
		case "NX":
			result = new NX();
			break;
		case "PDT":
			result = new PDT();
			break;
		case "POS":
			result = new POS();
			break;
		case "PP":
			result = new PP();
			break;
		case "ShiftPRD":
			result = new ShiftPRD();
			break;
		case "PRN":
			result = new PRN();
			break;
		case "PRP":
			result = new PRP();
			break;
		case "ShiftPRP":
			result = new ShiftPRP();
			break;
		case "PRPShiftS":
			result = new PRPShiftS();
			break;
		case "PRT":
			result = new PRT();
			break;
		case "ShiftPUT":
			result = new ShiftPUT();
			break;
		case "QP":
			result = new QP();
			break;
		case "RB":
			result = new RB();
			break;
		case "RBR":
			result = new RBR();
			break;
		case "RBS":
			result = new RBS();
			break;
		case "RP":
			result = new RP();
			break;
		case "RRC":
			result = new RRC();
			break;
		case "S":
			result = new S();
			break;
		case "SBAR":
			result = new SBAR();
			break;
		case "SBARQ":
			result = new SBARQ();
			break;
		case "ShiftSBJ":
			result = new ShiftSBJ();
			break;
		case "SINV":
			result = new SINV();
			break;
		case "SQ":
			result = new SQ();
			break;
		case "SYM":
			result = new SYM();
			break;
		case "ShiftTMP":
			result = new ShiftTMP();
			break;
		case "TO":
			result = new TO();
			break;
		case "ShiftTPC":
			result = new ShiftTPC();
			break;
		case "ShiftTTL":
			result = new ShiftTTL();
			break;
		case "UCP":
			result = new UCP();
			break;
		case "UH":
			result = new UH();
			break;
		case "VB":
			result = new VB();
			break;
		case "VBD":
			result = new VBD();
			break;
		case "VBG":
			result = new VBG();
			break;
		case "VBN":
			result = new VBN();
			break;
		case "VBP":
			result = new VBP();
			break;
		case "VBZ":
			result = new VBZ();
			
			break;
		case "ShiftVOC":
			result = new ShiftVOC();
			break;
		case "VP":
			result = new VP();
			break;
		case "WDT":
			result = new WDT();
			break;
		case "WHADJP":
			result = new WHADJP();
			break;
		case "WHADVP":
			result = new WHADVP();
			break;
		case "WHNP":
			result = new WHNP();
			break;
		case "WHPP":
			result = new WHPP();
			break;
		case "WP":
			result = new WP();
			break;
		case "WPShiftS":
			result = new WPShiftS();
			break;
		case "WRB":
			result = new WRB();
			break;
		case "X":
			result = new X();
			break;

		}
		return result;

	}

	public NLPNode getNLPTree(Tree tree) {
		NLPNode nlpNode = null;
		if (tree != null) {
			// get the tag element of the node
			boolean isNonTerminalRule = true;

			if (tree.isLeaf()) {
				isNonTerminalRule = false;
			}
			List<Tree> children = tree.getChildrenAsList();
			ArrayList<String> lstChildren = new ArrayList<String>();

			if (isNonTerminalRule) {
				nlpNode=getNLPNodeFromTreeLabel(tree.label().toString());
				ArrayList<NLPNode> lstNodes=new ArrayList<NLPNode>();
				for (Tree child : children) {
					
					NLPNode nodeChild=getNLPTree(child);
//					if(child.label().toString().equals("VBZ")){
//						System.out.println(nodeChild.toString());
//					}
					if(nodeChild!=null){
						lstNodes.add(nodeChild);
					}
					
				}
				nlpNode.setListChildren(lstNodes);
			}
		}
		
		return nlpNode;
	}

	public GrammarHeuristic() {
	}

	public Tree getTreeFromSentence(String strInput, LexicalizedParser lp,
			TokenizerFactory<CoreLabel> tokenizerFactory) {
		Tokenizer<CoreLabel> tok = tokenizerFactory
				.getTokenizer(new StringReader(strInput));
		List<CoreLabel> rawWords2 = tok.tokenize();
		Tree tree = lp.apply(rawWords2);
		return tree;
	}

	public void getGrammarFromSetSentence(String fileTrainingGrammar,
			String fpInput, String folderOutput) {
		// check each line, extract grammar of each line
		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(
				new CoreLabelTokenFactory(), "");
		LexicalizedParser lp = LexicalizedParser
				.loadModel(PathConstanct.PATH_DEFAULTPARSEMODEL);
		String[] arrSentences = FileIO.readStringFromFile(fpInput).trim()
				.split("\n");

		File fOut = new File(folderOutput);
		if (!fOut.isDirectory()) {
			fOut.mkdir();
		}

		String[] arrContentTrainGrammar = FileIO
				.readStringFromFile(fileTrainingGrammar).trim().split("\n");

		GrammarHeuristic gh = new GrammarHeuristic();

		HashSet<String> setTrainedGrammar = new HashSet<String>();
		for (int i = 0; i < arrContentTrainGrammar.length; i++) {
			String[] arrItem = arrContentTrainGrammar[i].split(":");
			int numCount = Integer.parseInt(arrItem[2].trim());
			if (numCount <= 50) {
				continue;
			}
			String strItem = arrItem[0].trim() + " : " + arrItem[1].trim();
			// System.out.println("Item: "+strItem);
			setTrainedGrammar.add(strItem);

		}

		System.out.println("set grammar: " + setTrainedGrammar.size());

		for (int i = 0; i < arrSentences.length; i++) {
			StringBuilder sbRule = new StringBuilder();
			String strContent = arrSentences[i].trim();
			Tree tree = getTreeFromSentence(strContent, lp, tokenizerFactory);
			ArrayList<GrammarRule> lstRules = new ArrayList<GrammarRule>();
			sbRule.append(tree.pennString() + "\n");
			getAllRules(tree, lstRules);
			for (int j = 0; j < lstRules.size(); j++) {
				String strRuleContent = lstRules.get(j).print();
				sbRule.append(strRuleContent + "\t");
				if (setTrainedGrammar.contains(strRuleContent)) {
					sbRule.append("true\n");
				} else {
					sbRule.append("false\n");
				}
			}
			FileIO.writeStringToFile(sbRule.toString(), folderOutput
					+ File.separator + (i + 1) + ".txt");
		}

	}

	public LinkedHashMap<String, ArrayList<GrammarRule>> getGrammarsFromSentences(
			String fpInput) {

		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(
				new CoreLabelTokenFactory(), "");
		LexicalizedParser lp = LexicalizedParser
				.loadModel(PathConstanct.PATH_DEFAULTPARSEMODEL);
		String[] arrSentences = FileIO.readStringFromFile(fpInput).trim()
				.split("\n");

		HashMap<String, Integer> mapRules = new HashMap<>();

		// StringBuilder sbTemp=new StringBuilder();
		for (int i = 0; i < arrSentences.length; i++) {
			// sbTemp.append(arrSentences[i]+"\n");

			if ((i + 1) % 1000 == 0 || i == arrSentences.length - 1) {
				System.out.println("Parse sentence " + (i + 1));
				if (i + 1 > 400000) {
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
					mapRules.put(strRuleContent,
							mapRules.get(strRuleContent) + 1);
				}
			}

		}
		System.out.println(mapRules.size());
		LinkedHashMap<String, Integer> sortedMapRules = SortUtil
				.sortHashMapByValues(mapRules, false);

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

	public void saveRuleToFiles(
			LinkedHashMap<String, ArrayList<GrammarRule>> lstRules,
			double threshold, String fileOut, String folderOut,
			String fileNonTerminal) {
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
			int numberOfRulesNeed = (int) Math.round(lstPerNonTerminalRules
					.size() * threshold);
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

			FileIO.writeStringToFile(sbRule.toString(),
					folder.getAbsolutePath() + File.separator + lhs + "_.txt");
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
