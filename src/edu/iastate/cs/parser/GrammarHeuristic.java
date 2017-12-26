package edu.iastate.cs.parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

	public  Tree getTreeFromSentence(String strInput,LexicalizedParser lp) {
		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(
				new CoreLabelTokenFactory(), "");
		Tokenizer<CoreLabel> tok = tokenizerFactory
				.getTokenizer(new StringReader(strInput));
		List<CoreLabel> rawWords2 = tok.tokenize();
		Tree tree = lp.apply(rawWords2);
		return tree;
	}
	
	public ArrayList<GrammarRule> getGrammarsFromSentences(String fpInput, double threshold) {
		
		LexicalizedParser lp = LexicalizedParser.loadModel(PathConstanct.PATH_DEFAULTPARSEMODEL);
		String[] arrSentences=FileIO.readStringFromFile(fpInput).trim().split("\n");
		HashMap<String,Integer> mapRules=new HashMap<>();
		for(int i=0;i<arrSentences.length;i++){
			Tree tree=getTreeFromSentence(arrSentences[i], lp);
			ArrayList<GrammarRule> lstRules=new ArrayList<GrammarRule>();
			getAllRules(tree, lstRules);
			for(int j=0;j<lstRules.size();j++){
				String strRuleContent=lstRules.get(j).print();
				if(!mapRules.containsKey(strRuleContent)){
					mapRules.put(strRuleContent,1);
				}else{
					mapRules.put(strRuleContent, mapRules.get(strRuleContent)+1);
				}
			}
			
			
		}
		System.out.println(mapRules.size());
		LinkedHashMap<String,Integer> sortedMapRules= SortUtil.sortHashMapByValues(mapRules,false);
		if(threshold>1){
			throw new IllegalArgumentException("Invallid threshold");
		}
		int numberOfRuleCount=(int)Math.round(threshold*mapRules.size());
		ArrayList<GrammarRule> lstResult=new ArrayList<GrammarRule>();
		int index=0;
		for(String strKey :sortedMapRules.keySet()){
			GrammarRule gr=new GrammarRule();
			gr.setCount(sortedMapRules.get(strKey));
			gr.setLhs(strKey.split(":")[0].trim());
			String[] arrRHS=strKey.split(":")[1].trim().split("\\s+");
			ArrayList<String> lstRHS=new ArrayList<String>();
			for(String item:arrRHS){
				lstRHS.add(item.trim());
			}
			gr.setLstRhs(lstRHS);
			lstResult.add(gr);
			index++;
			if(index>=numberOfRuleCount){
				break;
			}
		}
		
		return lstResult;
		 
	}
	
	public void saveRuleToFiles(ArrayList<GrammarRule> lstRules,String fileOut){
		StringBuilder sbMain=new StringBuilder();
		for(GrammarRule gr:lstRules){
			sbMain.append(gr.print()+"\n");
		}
		FileIO.writeStringToFile(sbMain.toString(), fileOut);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
