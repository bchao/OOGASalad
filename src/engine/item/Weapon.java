package engine.item;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import engine.Statistic;
import engine.battle.Attack;
import engine.battle.BattleExecutable;
import engine.dialogue.MatrixNode;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;

public class Weapon extends Pickupable implements MatrixNode, BattleExecutable{
	private Statistic myDamage;
	private Statistic mySpeed;
	private List<Attack> myAttacks;
	private String myImage;
	public final static int DEFAULT_SPEED=1;
	public final static int DEFAULT_DAMAGE=1;
	public final static int DEFAULT_MAX=100;
	
	public Weapon(String image, String name, List<Attack> attacks) {
		super(name, image);
		myAttacks = attacks;
		myImage=image;
	}

	public void addAttack(Attack attack){
		myAttacks.add(attack);
	}
	public void removeAttack(Attack attack){
		myAttacks.remove(attack);
	}
	
	public List<Attack> getAttackList(){
		return myAttacks;
	}
	
	public Statistic getDamage(){
		return myDamage;
	}
	
	public Statistic getSpeed(){
		return mySpeed;
	}
	public void setDamage(int value, int max){
		myDamage = new Statistic("damage",value, max);
	}
	public void setSpeed(int value, int max){
		mySpeed = new Statistic("speed",value, max);
	}

	@Override
	public void pickUp(Player player) {
		player.addWeapon(this);
	}

	@Override
	public Image getImage() {
		return new ScaledImage(150,150,myImage).scaleImage();
	}

}
