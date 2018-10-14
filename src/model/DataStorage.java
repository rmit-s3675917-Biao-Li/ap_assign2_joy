package model;

import java.util.ArrayList;

import model.RentalProperty;

public class DataStorage {
	private static ArrayList <RentalProperty> RP = new ArrayList <RentalProperty>();
	public static void initialization() {
		
	}
	public static ArrayList<RentalProperty> getRP() {
		return RP;
	}
	public static void setRP(ArrayList<RentalProperty> rP) {
		RP = rP;
	}
	
	
}
