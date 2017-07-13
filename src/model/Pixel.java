package model;

public class Pixel {
	private int RGB;
	private int x;
	private int y;
	
	public Pixel(){};
	
	public Pixel(int x, int y, int RGB){
		this.x = x;
		this.y = y;
		this.RGB = RGB;
	}
	
	public int getRGB() {
		return RGB;
	}
	public void setRGB(int rGB) {
		RGB = rGB;
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
