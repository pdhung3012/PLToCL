package edu.iastate.cs.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import util.FileIO;
import edu.iastate.cs.entities.GrammarRule;
import edu.iastate.cs.entities.Scope;
import edu.iastate.cs.entities.nlp.*;


public class VerifyingNLVisitor extends NLPVisitor {

	private LinkedHashMap<String, ArrayList<GrammarRule>> mapGrammars;
	private LinkedHashSet<String> setVarCheck;

	public VerifyingNLVisitor(String fileGrammar, String fileVarCheck) {
		// TODO Auto-generated constructor stub
		this.fileGrammar = fileGrammar;
		this.fileVarCheckNonTer = fileVarCheck;

		constructGrammar();

	}

	public void constructGrammar() {
		mapGrammars = new LinkedHashMap<>();
		String[] arrGrammars = FileIO.readStringFromFile(this.fileGrammar)
				.trim().split("\n");
		for (int i = 0; i < arrGrammars.length; i++) {
			String[] arrItems = arrGrammars[i].split(":");
			String lhs = arrItems[0].trim();
			String[] arrRhs = arrItems[1].trim().split("\\s+");
			ArrayList<String> lstRhs = new ArrayList<String>();
			for (int j = 0; j < lstRhs.size(); j++) {
				lstRhs.add(arrRhs[i]);
			}
			GrammarRule rule = new GrammarRule();
			rule.setLhs(lhs);
			rule.setLstRhs(lstRhs);
			if (!mapGrammars.containsKey(lhs)) {
				ArrayList<GrammarRule> lstGrammars = new ArrayList<GrammarRule>();
				lstGrammars.add(rule);
				mapGrammars.put(lhs, lstGrammars);
			} else {
				mapGrammars.get(lhs).add(rule);
			}
		}

		String[] arrVarChecks = FileIO
				.readStringFromFile(this.fileVarCheckNonTer).trim().split("\n");
		setVarCheck = new LinkedHashSet<String>();
		for (int i = 0; i < arrVarChecks.length; i++) {
			setVarCheck.add(arrVarChecks[i]);
		}

	}

	public boolean visit(ADJP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftADV node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ADVP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftBNF node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(CC node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(CD node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftCLF node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftCLR node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(CONJP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftDIR node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(DT node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftDTV node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(EX node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftEXT node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(FRAG node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(FW node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftHLN node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(IN node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(INTJ node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(JJ node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(JJR node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(JJS node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftLGS node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftLOC node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(LS node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(LST node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(MD node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftMNR node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(NAC node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(NN node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(NNS node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(NNP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(NNPS node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftNOM node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(NP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(NX node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(PDT node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(POS node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(PP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftPRD node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(PRN node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(PRP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftPRP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(PRPShiftS node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(PRT node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftPUT node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(QP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(RB node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(RBR node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(RBS node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(RP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(RRC node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(S node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(SBAR node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(SBARQ node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftSBJ node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(SINV node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(SQ node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(SYM node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftTMP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(TO node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftTPC node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftTTL node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(UCP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(UH node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(VB node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(VBD node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(VBG node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(VBN node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(VBP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(VBZ node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(ShiftVOC node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(VP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(WDT node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(WHADJP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(WHADVP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(WHNP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(WHPP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(WP node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(WPShiftS node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(WRB node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

	public boolean visit(X node, Scope scope) {
		boolean isValid = false;

		// get information from node
		ArrayList<NLPNode> lstChildren = node.getListChildren();
		String nodeSig = node.getName() + " : ";
		for (int i = 0; i < lstChildren.size(); i++) {
			nodeSig += lstChildren.get(i) + " ";
		}

		// check production rules
		ArrayList<GrammarRule> lstRules = mapGrammars.get(node.getName());
		for (int i = 0; i < lstRules.size(); i++) {
			if (nodeSig.equals(lstRules.get(i).print())) {
				isValid = true;
				break;
			}
		}

		// TODO: check non-terminal Variable.

		return isValid;
	}

}
