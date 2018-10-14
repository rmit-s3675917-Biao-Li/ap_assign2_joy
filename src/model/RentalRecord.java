package model;

public class RentalRecord {
	private String recordId; // 记录ID
	private String customerId="";
	private DateTime rentDate; // 租房日期
	private DateTime erDate; // 预计退房时间
	private DateTime arDate; // 实际退房时间
	private double rentalFee; // 未延期费
	private double lateFee; // 延期费

	public RentalRecord(String propertyId, String customerId, int setClockForwardInDays) {// 当天入住, 住几天
		this.rentDate = new DateTime();
		this.erDate = new DateTime(rentDate, setClockForwardInDays);
		this.recordId = propertyId + "_" + customerId + "_" + this.rentDate.getEightDigitDate();
		this.customerId = customerId;
	}

	public RentalRecord(String propertyId, String customerId, int day, int month, int year, int setClockForwardInDays) {// 某天入住，住几天
		this.rentDate = new DateTime(day, month, year);
		this.erDate = new DateTime(rentDate, setClockForwardInDays);
		this.recordId = propertyId + "_" + customerId + "_" + this.rentDate.getEightDigitDate();
		this.customerId = customerId;
	}

	public RentalRecord(String propertyId, String customerId, int day, int month, int year, int eday, int emonth,
			int eyear) {// 某天入住, 到几号
		this.rentDate = new DateTime(day, month, year);
		this.erDate = new DateTime(eday, emonth, eyear);
		this.recordId = propertyId + "_" + customerId + "_" + this.rentDate.getEightDigitDate();
		this.customerId = customerId;
	}

	public RentalRecord(String propertyId, String customerId, DateTime datetime, int setClockForwardInDays) {
		this.rentDate = datetime;
		this.erDate = new DateTime(rentDate, setClockForwardInDays);
		this.recordId = propertyId + "_" + customerId + "_" + this.rentDate.getEightDigitDate();
		this.customerId = customerId;
	}

	public RentalRecord(String propertyId, DateTime rentdate, DateTime estreturndate,
			DateTime actualreturndate, double rentalfee, double latefee) {
		this.recordId = propertyId + "_" + customerId + "_" + rentdate.getEightDigitDate();
		this.rentDate = rentdate;
		this.erDate = estreturndate;
		this.arDate = actualreturndate;
		this.rentalFee = rentalfee;
		this.lateFee = latefee;
	}

	public String getRecordId() {
		return recordId;
	}

	public DateTime getRentDate() {
		return rentDate;
	}

	public DateTime getErDate() {
		return erDate;
	}

	public DateTime getArDate() {
		return arDate;
	}

	public double getRentalFee() {
		return rentalFee;
	}

	public double getLateFee() {
		return lateFee;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public void setRentDate(DateTime rentDate) {
		this.rentDate = rentDate;
	}

	public void setErDate(DateTime erDate) {
		this.erDate = erDate;
	}

	public void setArDate(DateTime arDate) {
		this.arDate = arDate;
	}

	public void setRentalFee(double rentalFee) {
		this.rentalFee = rentalFee;
	}

	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String toString() {
		if (getArDate() == null)
			return getRecordId() + ":" + getRentDate().toString() + ":" + getErDate().toString() + ":none:none:none";
		return getRecordId() + ":" + getRentDate().toString() + ":" + getErDate().toString() + ":"
				+ getArDate().toString() + ":" + getRentalFee() + ":" + getLateFee();
	}

	public String getDetails() {
		String s;
		if (getArDate() == null) {
			String s1 = String.format("%-22s %s\n", "Record ID:", getRecordId());
			String s2 = String.format("%-22s %s\n", "Rent Date:", getRentDate().toString());
			String s3 = String.format("%-22s %s\n", "Estimated Return Date:", getErDate().toString());
			s = s1 + s2 + s3;
		} else {
			String s1 = String.format("%-22s %s\n", "Record ID:", getRecordId());
			String s2 = String.format("%-22s %s\n", "Rent Date:", getRentDate().toString());
			String s3 = String.format("%-22s %s\n", "Estimated Return Date:", getErDate().toString());
			String s4 = String.format("%-22s %s\n", "Actual Return Date:", getArDate().toString());
			String s5 = String.format("%-22s %.2f\n", "Rental Fee:", getRentalFee());
			String s6 = String.format("%-22s %.2f\n", "Late Fee:", getLateFee());
			s = s1 + s2 + s3 + s4 + s5 + s6;
		}
		return s;
	}
}
