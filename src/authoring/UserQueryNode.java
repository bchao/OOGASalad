package authoring;

public class UserQueryNode {
	private String myString;
	private NPCResponseNode myParent;
	private NPCResponseNode myChild;
	public UserQueryNode(String s, NPCResponseNode p){
		myString = s;
		myParent = p;
	}
	public void setString(String s){
		myString = s;
	}
	public void setChild(NPCResponseNode n){
		myChild = n;
	}
	
	public String getString(){
		return myString;
	}
	public NPCResponseNode getChild(){
		return myChild;
	}
	public NPCResponseNode getParent(){
		return myParent;
	}
}