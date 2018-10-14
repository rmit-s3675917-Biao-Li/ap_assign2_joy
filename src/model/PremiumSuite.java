package model;

import java.io.File;

public class PremiumSuite extends RentalProperty {
	private static int minimum = 1;
	private static int rate = 554;
	private static int lRate = 662;
	private DateTime lmDate;

	public PremiumSuite() {
	}

	public PremiumSuite(String pid, String type, int sn, String sna, String sb, String sts, String description, File imageFile, DateTime dt) {
		super(pid, type, sn, sna, sb, 3, description, sts, imageFile);
		this.lmDate = dt;
		setPropertyType("Premium Suite");
	}

	public static int getMinimum() {
		return minimum;
	}

	public static int getRate() {
		return rate;
	}

	public static int getlRate() {
		return lRate;
	}

	public DateTime getLmDate() {
		return lmDate;
	}

	public static void setMinimum(int minimum) {
		PremiumSuite.minimum = minimum;
	}

	public static void setRate(int rate) {
		PremiumSuite.rate = rate;
	}

	public static void setlRate(int lRate) {
		PremiumSuite.lRate = lRate;
	}

	public void setLmDate(DateTime lmDate) {
		this.lmDate = lmDate;
	}

	public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
		if (!getPropertyStatue().equals("available") || numOfRentDay < getMinimum()
				|| (new DateTime(rentDate, numOfRentDay).getTime() > new DateTime(lmDate, 10).getTime()))
			// ���ܱ���
			return false; // �ⷿʧ��
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
				|| DateTime.diffDays(returnDate, getRecord()[0].getRentDate()) < minimum) // û���߿���
			return false; // �˷�ʧ��
		else {
			setPropertyStatue("available"); // ���³���״̬
			getRecord()[0].setArDate(returnDate);
			// ����fee
			getRecord()[0].setRentalFee(DateTime.diffDays(returnDate, getRecord()[0].getRentDate()) * rate);
			if (DateTime.diffDays(returnDate, getRecord()[0].getErDate()) > 0) // ������
				getRecord()[0].setLateFee(lRate * DateTime.diffDays(returnDate, getRecord()[0].getErDate()));
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
			this.lmDate = completionDate;
			setPropertyStatue("available"); // ά�����
			return true;
		}
	}

	public String toString() {
		return getPropertyId() + ":" + String.valueOf(getStreetNum()) + ":" + getStreetName() + ":" + getSuburb() + ":"
				+ getPropertyType() + ":" + String.valueOf(getNumBedroom()) + ":" + getPropertyStatue() + ":"
				+ getLmDate().toString();
	}

	public String getDetails() {
		String S;
		String s1 = String.format("%-20s %s\n", "Property ID:", getPropertyId());
		String s2 = String.format("%-20s %s\n", "Address:",
				String.valueOf(getStreetNum()) + " " + getStreetName() + " " + getSuburb());
		String s3 = String.format("%-20s %s\n", "Type:", getPropertyType());
		String s4 = String.format("%-20s %s\n", "Bedroom:", String.valueOf(getNumBedroom()));
		String s5 = String.format("%-20s %s\n", "Status:", getPropertyStatue());
		String s6 = String.format("%-20s %s\n", "Last maintenance:", getLmDate().toString());
		String s7 = String.format("%-20s ", "RENTAL RECORD:");
		S = s1 + s2 + s3 + s4 + s5 + s6 + s7;
		if (getRecord()[0] == null) {
			String s8 = String.format("%-10s\n", "empty");
			S += s8;
		} else {
			S += "\n";
			for (int j = 0; j < getRecord().length; j++) {
				if (getRecord()[j] != null) {
					S = S + getRecord()[j].getDetails() + "--------------------------------------\n";
				}
			}
		}
		return S;
	}
	// �ɸĵݹ�
}
