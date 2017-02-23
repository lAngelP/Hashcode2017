package me.unizar.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import me.unizar.Data;

public class EndPoint {
	
	private int latDataCenter;
	private Map<Integer, Integer> cacheConnected = new TreeMap<Integer, Integer>();
	private Map<Integer, List<Integer>> cacheConnectedPing = new TreeMap<Integer, List<Integer>>();
	
	public EndPoint(int pingDataCenter){
		this.latDataCenter = pingDataCenter;
	}
	
	public void addCacheServer(int id, int ping){
		cacheConnected.put(id, ping);
		List<Integer> list = cacheConnectedPing.get(ping);
		if(list != null){
			list.add(id);
		}else{
			list = new ArrayList<Integer>();
			list.add(id);
			cacheConnectedPing.put(ping, list);
		}
	}
	
	public int proccessVideo(Video v){
		for (Integer ping : cacheConnectedPing.keySet()) {
			if(ping > latDataCenter){
				return -1;
			}
		}
		
		for(Integer id : cacheConnected.keySet()){
			if(Data.getCache(id).isInCache(v)){
				return id;
			}
		}
		
		for (Integer ping : cacheConnectedPing.keySet()) {
			List<Integer> ids = cacheConnectedPing.get(ping);
			for(Integer id : ids){
				if(Data.getCache(id).fitsVideo(v)){
					return id;
				}
			}
		}
		
		return -1;
	}
}
