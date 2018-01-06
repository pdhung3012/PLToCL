package edu.iastate.cs.entities.nlp;


/**
 * @author hungphan
 * -TPC ("topicalized") - marks elements that appear before the subject in a declarative sentence, but in two cases only:
if the front element is associated with a *T* in the position of the gap.
if the fronted element is left-dislocated (i.e. it is associated with a resumptive pronoun in the position of the gap).
 */
public class ShiftTPC extends NLPNode{
	public ShiftTPC(){
		name="ShiftTPC";
	}
}
