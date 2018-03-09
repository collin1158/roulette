package com.fusha.roulette;

import java.awt.Color;
import java.awt.Graphics;

import com.fusha.main.Main;

public class Piece {

	public int x;
	public int y;
	public static int width = Main.GUI.getWidth()/15+6;
	public Color color;
	
	public Piece(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void draw(Graphics g) {
		Color temp = g.getColor();
		g.setColor(color);
		g.drawRect(x, y, width, 30);
		g.setColor(temp);
	}
	
	public void fill(Graphics g) {
		Color temp = g.getColor();
		g.setColor(color);
		if(x<-100) {
			x=Main.roulettePanel.getWidth();
		}
		g.fillRect(x, y, width, 30);
		g.setColor(temp);
	}
	
	public void move(int xAmount) {
		x += xAmount;
	}
	
	public void moveTo(int xPos) {
		x = xPos;
	}
	
	public boolean containsPoint(int x) {
		if(x>=this.x && x<=this.x+width) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		if(color.equals(Color.BLACK)) {
			return "Black";
		} else if(color.equals(Color.RED)) {
			return "Red";
		} else if(color.equals(Color.GREEN)) {
			return "Green";
		}
		return null;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}