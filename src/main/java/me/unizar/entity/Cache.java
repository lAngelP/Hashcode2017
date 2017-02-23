package me.unizar.entity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.unizar.Data;

public class Cache {
	
	private int size;
	private List<Video> videos = new ArrayList<Video>();
	
	public Cache() {
		size = Data.getCacheSize();
	}
	
	public boolean fitsVideo(Video v){
		return size >= v.getSize();
	}
	
	public boolean isInCache(Video v){
		for (Video video : videos) {
			if(v.getId() == video.getId()){
				return true;
			}
		}
		
		return false;
	}
	
	public void addVideo(Video v){
		videos.add(v);
		size -= v.getSize();
	}

	public void generateOutput(BufferedWriter w) throws IOException {
		for (Video video : videos) {
			w.append(" " + video.getId());
		}
	}

	public boolean hasVideos() {
		return !videos.isEmpty();
	}

}
