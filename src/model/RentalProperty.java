package model;

import java.io.File;

public abstract class RentalProperty {
	private String propertyId;
	private int streetNum;
	private String streetName, description;
	private String suburb;
	private int numBedroom;
	private String propertyType;
	private String propertyStatue;
	private File imageFile;
	private String type;
	
	private RentalRecord[] record = new RentalRecord[11];

	public RentalProperty() {
	}

	public RentalProperty(String pid, String type, int sn, String sna, String sb, int nb, String sts, String description, File imageFile) {
		this.propertyId = pid;
		this.type = type;
		this.streetNum = sn;
		this.streetName = sna;
		this.suburb = sb;
		this.numBedroom = nb;
		this.propertyStatue = sts;
		this.description = description;
		this.imageFile = imageFile;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public int getStreetNum() {
		return streetNum;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getSuburb() {
		return suburb;
	}

	public int getNumBedroom() {
		return numBedroom;
	}
	
	public String getDescription() {
		return description;
	}

	public File getImageFile() {
		return imageFile;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public void setRecord(RentalRecord[] record) {
		this.record = record;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public String getPropertyStatue() {
		return propertyStatue;
	}

	public RentalRecord[] getRecord() {
		return record;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public void setStreetNum(int streetNum) {
		this.streetNum = streetNum;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public void setNumBedroom(int numBedroom) {
		this.numBedroom = numBedroom;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public void setPropertyStatue(String propertyStatue) {
		this.propertyStatue = propertyStatue;
	}

	abstract boolean rent(String customerId, DateTime rentDate, int numOfRentDay);

	abstract boolean returnProperty(DateTime returnDate);

	abstract boolean performMaintenance();

	abstract boolean completeMaintenance(DateTime completionDate);

	abstract String getDetails();

	public String toString() {
		return propertyId + ":" + String.valueOf(streetNum) + ":" + streetName + ":" + suburb + ":" + propertyType + ":"
				+ String.valueOf(numBedroom) + ":" + propertyStatue;
	}

	public void setRentRecords(RentalRecord[] records) {
		// TODO Auto-generated method stub
		record = records;
	}
}
