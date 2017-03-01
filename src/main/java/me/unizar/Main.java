package me.unizar;

import java.io.File;
import java.io.IOException;

import me.unizar.entity.Video;

public class Main {

	public static void main(String[] args) throws IOException {
		run("kittens");
		run("me_at_the_zoo");
		run("trending_today");
		run("videos_worth_spreading");
	}
	
	public static void run(String file) throws IOException{
		Video.cleanUp();
		System.out.println("Reading " + file + ".in");
		Runner.run(new File(file + ".in"));
		System.out.println("Generating " + file + ".out");
		Runner.generateOutput(new File(file + ".out"));
	}

}
