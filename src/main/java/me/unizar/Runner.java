package me.unizar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import me.unizar.entity.EndPoint;

public class Runner {
	
	
	public static void run(File f) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(f));
		
		String line = reader.readLine();
		Data.construct(line.split(" ")); //Read line 0.
		
		String[] args = reader.readLine().split(" "); //Read line 1
		for(String arg : args){
			Data.createVideo(Integer.parseInt(arg));
		}
		
		for(int i = 0; i < Data.getEndPoints(); i++){
			args = reader.readLine().split(" ");
			EndPoint endPoint = new EndPoint(Integer.parseInt(args[0]));
			int cacheServers = Integer.parseInt(args[1]);
			if(cacheServers != 0){
				for(int j = 0; j < cacheServers; j++){
					args = reader.readLine().split(" ");
					endPoint.addCacheServer(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
				}
				Data.addEndPoint(i, endPoint);
			}
		}
		
		for(int i = 0; i < Data.getRequests(); i++){
			args = reader.readLine().split(" ");
			int vId = Integer.parseInt(args[0]);
			int endpoint = Integer.parseInt(args[1]);
			int nums = Integer.parseInt(args[2]);
			
			if(Data.existsEndpoint(endpoint)){
				Data.processRequest(endpoint, vId);
			}
		}
		
		reader.close();
	}
	
	public static void generateOutput(File f) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		
		writer.write(Data.getCaches() + "\n");
		Data.generateCache(writer);
		
		writer.close();
	}

}
