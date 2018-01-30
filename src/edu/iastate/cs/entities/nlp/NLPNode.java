package edu.iastate.cs.entities.nlp;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTNode;

import edu.iastate.cs.entities.Scope;
import edu.iastate.cs.visitor.NLPVisitor;

public abstract class NLPNode {
	protected ArrayList<NLPNode> listChildren=new ArrayList<NLPNode>();
	
	protected String name;

	
	
	public ArrayList<NLPNode> getListChildren() {
		return listChildren;
	}

	public void setListChildren(ArrayList<NLPNode> listChildren) {
		this.listChildren = listChildren;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void traverse(NLPVisitor visitor, 	Scope scope) throws Exception {
		// do nothing here
	}
	
	public String printTree(NLPNode tree,int indent){
		StringBuilder sb=new StringBuilder();
		if(tree!=null){
			for(int i=0;i<indent;i++){
				sb.append("\t");
			}
			sb.append(tree.getName()+"\n");
			for(int i=0;i<tree.listChildren.size();i++){
				sb.append(printTree(tree.listChildren.get(i), indent+1));
			}
		}
		
		return sb.toString();
	}
	
	public String getRhs(){
		String strChildContent="";
		for(int i=0;i<listChildren.size();i++){
			strChildContent+=listChildren.get(i).getName()+" ";
		}
		return strChildContent;
	}
	
}
