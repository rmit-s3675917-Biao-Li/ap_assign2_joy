package model;

import java.io.File;
import java.util.Calendar;

public class Apartment extends RentalProperty {
	private int minimum; // ����ס����
	private static int maximum = 28;
	private int rate; // ÿ��۸�

	public Apartment() {
	}

	public Apartment(String pid, String type, int sn, String sna, String sb, int nb, String sts, String description, File imageFile) {
		super(pid, type, sn, sna, sb, nb,sts, description, imageFile);
		if (nb == 3)
			this.rate = 319;
		else if (nb == 2)
			this.rate = 210;
		else if (nb == 1)
			this.rate = 143;
		setPropertyType("Apartment");
	}

	public int getMinimum(DateTime rentDate) { // ����һ�죬�������С��������
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(rentDate.getTime());
		if (calendar.get(Calendar.DAY_OF_WEEK) >= 1 && calendar.get(Calendar.DAY_OF_WEEK) <= 5) {
			minimum = 2;
		} else if (calendar.get(Calendar.DAY_OF_WEEK) == 6 || calendar.get(Calendar.DAY_OF_WEEK) == 7) {
			minimum = 3;
		}
		return minimum;
	}

	public int getRate() {
		return rate;
	}

	public static int getMaximum() {
		return maximum;
	}

	public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
		if (!getPropertyStatue().equals("available") || numOfRentDay < getMinimum(rentDate)
				|| numOfRentDay > getMaximum())
			return false;
		else {
			setPropertyStatue("rented"); // ���³���״̬
			for (int i = getRecord().length - 1; i >= 0; i--) { // ���¼�¼����
				if (getRecord()[i] != null && i != getRecord().length - 1) {
					getRecord()[i + 1] = getRecord()[i];
				}
			}
			getRecord()[0] = new RentalRecord(getPropertyId(), customerId, rentDate, numOfRentDay);
			return true;
		}
	}

	public boolean returnProperty(DateTime returnDate) {
		if (!getPropertyStatue().equals("rented") || returnDate.getTime() < getRecord()[0].getRentDate().getTime()
				|| DateTime.diffDays(returnDate, getRecord()[0].getRentDate()) < minimum)
			return false;
		else {
			setPropertyStatue("available"); // ���³���״̬
			getRecord()[0].setArDate(returnDate);
			// ����fee
			getRecord()[0].setRentalFee(DateTime.diffDays(returnDate, getRecord()[0].getRentDate()) * rate);
			if (DateTime.diffDays(returnDate, getRecord()[0].getErDate()) > 0) // ������
				getRecord()[0].setLateFee(1.15 * rate * DateTime.diffDays(returnDate, getRecord()[0].getErDate()));
			else
				getRecord()[0].setLateFee(0);
			return true;
		}
	}

	public boolean performMaintenance() {
		if (!getPropertyStatue().equals("available"))
			return false;
		else {
			setPropertyStatue("maintenance"); // ����ά��
			return true;
		}
	}

	public boolean completeMaintenance(DateTime completionDate) {
		if (!getPropertyStatue().equals("maintenance"))
			return false;
		else {
			setPropertyStatue("available"); // ά�����
			return true;
		}
	}

	public String toString() {
		return getPropertyId() + ":" + String.valueOf(getStreetNum()) + ":" + getStreetName() + ":" + getSuburb() + ":"
				+ getPropertyType() + ":" + String.valueOf(getNumBedroom()) + ":" + getPropertyStatue();
	}

	@Override
	String getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

//	public String getDetails() {
//		String S;
//		String s1 = String.format("%-15s %s\n", "Property ID:", getPropertyId());
//		String s2 = String.format("%-15s %s\n", "Address:",
//				String.valueOf(getStreetNum()) + " " + getStreetName() + " " + getSuburb());
//		String s3 = String.format("%-15s %s\n", "Type:", getPropertyType());
//		String s4 = String.format("%-15s %s\n", "Bedroom:", String.valueOf(getNumBedroom()));
//		String s5 = String.format("%-15s %s\n", "Status:", getPropertyStatue());
//		String s6 = String.format("%-15s ", "RENTAL RECORD:");
//		S = s1 + s2 + s3 + s4 + s5 + s6;
//		if (getRecord()[0] == null) {
//			String s7 = String.format("%-10s\n", "empty");
//			S += s7;
//		} else {
//			S += "\n";
//			for (int j = 0; j < getRecord().length; j++) {
//				if (getRecord()[j] != null) {
//					S = S + getRecord()[j].getDetails() + "--------------------------------------\n";
//				}
//			}
//		}
//		return S;
//	}
	// ���Ըĵݹ�
}
