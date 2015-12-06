package com.youtube.codingbash.pong;

import java.awt.Point;
import java.awt.Rectangle;

public class Ball {

	private int x;
	private int y;
	private int width;
	private int height;
	private Point velocity;
	private Rectangle circle;
	
	public Ball(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		circle =  new Rectangle(this.x, this.y, this.width, this.height);
		velocity = new Point(0,0);
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Point getVelocity() {
		return velocity;
	}

	public void setVelocity(Point velocity) {
		this.velocity = velocity;
	}

	public Rectangle getCircle() {
		return circle;
	}

	public void setCircle(Rectangle circle) {
		this.circle = circle;
	}

	public void setCircle(int x, int y, int width, int height) {
		this.circle = new Rectangle(x, y, width, height);
	}
}
