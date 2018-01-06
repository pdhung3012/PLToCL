package edu.iastate.cs.entities.nlp;


/**
 * @author hungphan
 * -VOC (vocative) - marks nouns of address, regardless of their position in the sentence. It is not coindexed to the subject and not get -TPC when it is sentence-initial.
 */
public class ShiftVOC extends NLPNode{
	public ShiftVOC(){
		name="ShiftVOC";
	}
}
