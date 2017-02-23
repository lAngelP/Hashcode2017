package me.unizar;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import me.unizar.entity.Cache;
import me.unizar.entity.EndPoint;
import me.unizar.entity.Video;

public class Data {
	
	private static int vids;
	private static int endpoints;
	private static int requests;
	private static int cacheServers;
	private static int cacheSize;
	
	private static List<Video> videos;
	private static Map<Integer, EndPoint> endPoints = new TreeMap<Integer, EndPoint>();
	private static Map<Integer, Cache> caches = new TreeMap<Integer, Cache>();
	
	public static void construct(String[] args){
		Data.vids = Integer.parseInt(args[0]);
		Data.endpoints = Integer.parseInt(args[1]);
		Data.requests = Integer.parseInt(args[2]);
		Data.cacheServers = Integer.parseInt(args[3]);
		Data.cacheSize = Integer.parseInt(args[4]);
		
		videos = new ArrayList<Video>(vids);
	}
	
	public static void createVideo(int size){
		videos.add(new Video(size));
	}

	public static int getEndPoints() {
		return endpoints;
	}
	
	public static EndPoint getEndPoint(int id){
		return endPoints.get(id);
	}
	
	public static Cache getCache(int id){
		if(caches.containsKey(id)){
			return caches.get(id);
		}else{
			Cache c = new Cache();
			caches.put(id, c);
			return c;
		}
	}
	
	public static int getRequests(){
		return requests;
	}
	
	public static void addEndPoint(int id, EndPoint endPoint){
		endPoints.put(id, endPoint);
	}

	public static boolean existsEndpoint(int endpoint) {
		return endPoints.containsKey(endpoint);
	}

	public static void processRequest(int endpoint, int vId) {
		Video video = videos.get(vId);
		int cache = endPoints.get(endpoint).proccessVideo(video);
		if(cache >= 0){
			caches.get(cache).addVideo(video);
		}
	}

	public static int getCacheSize() {
		return cacheSize;
	}

	public static int getCaches() {
		return caches.size();
	}
	
	public static void generateCache(BufferedWriter w) throws IOException{
		for (Integer id : caches.keySet()) {
			w.append(String.valueOf(id));
			caches.get(id).generateOutput(w);
			w.newLine();
		}
	}

}
