package me.unizar.entity;

public class Video {
	
	private static int vids = 0;
	
	private int id;
	private int size;
	
	public Video(int size) {
		this.id = vids++;
		this.size = size;
	}
	
	
	public int getId() {
		return id;
	}
	public int getSize() {
		return size;
	}
	
	public static void cleanUp(){
		vids = 0;
	}
	

}
