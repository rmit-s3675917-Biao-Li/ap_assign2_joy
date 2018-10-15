package model;

import java.io.File;
import java.util.ArrayList;

import model.RentalProperty;

public class DataStorage {
	private static ArrayList <RentalProperty> RP = new ArrayList <RentalProperty>();
	private static File f;
	public static void initialization() {
		
	}
	public static ArrayList<RentalProperty> getRP() {
		return RP;
	}
	public static void setRP(ArrayList<RentalProperty> rP) {
		RP = rP;
	}
	public static void setSelectImage(File file) {
		// TODO Auto-generated method stub
		f = file;
	}
	
	public static File getSelectImage() {
		// TODO Auto-generated method stub
		return f;
	}
	
}
