package controller;

import java.io.File;
import java.util.ArrayList;

import exception.AddPropertyException;
import model.Apartment;
import model.DataStorage;

public class AddPropertyHandler {
	private static ArrayList<model.RentalProperty> PL;

	public static void addProperty(String id, String aorP, String stnum, String stname, String suburb, String bednum,
			String lastMaintenanceDate, String desription, File selectImage) throws AddPropertyException {
		PL = model.DataStorage.getRP();

		if (aorP.equals("Apartment") && !id.startsWith("A_"))
			id = "A_" + id;
		else if (aorP.equals("Premium Suite") && !id.startsWith("S_"))
			id = "S_" + id;

		if (repeatId(id))
			throw new AddPropertyException(1);

		if (id.equals("") || aorP.isEmpty() || stnum.equals("") || stname.equals("") || suburb.equals("")
				|| bednum.equals(""))
			throw new AddPropertyException(2);
		else {

			if (aorP.equals("Apartment"))
				try {
					if (Integer.parseInt(bednum) < 1 && Integer.parseInt(bednum) > 3)
						throw new AddPropertyException(5);
					PL.add(new Apartment(id, aorP, stnum, stname, suburb, Integer.parseInt(bednum), "Available",
							desription, selectImage));
				} catch (Exception e) {
					throw new AddPropertyException(2);
				}
			else {
				try {
					model.DateTime maintenanceDate = new model.DateTime(lastMaintenanceDate);
					System.out.println(maintenanceDate.getFormattedDate());
					System.out.println(maintenanceDate.diffDays(new model.DateTime(-1), maintenanceDate));
					if (maintenanceDate.diffDays(new model.DateTime(-1), maintenanceDate) < 0)
						throw new AddPropertyException(3);

					model.PremiumSuite newSuite = new model.PremiumSuite(id, aorP, stnum, stname, suburb, "Available",
							desription, selectImage, maintenanceDate);
					PL.add(newSuite);

				} catch (Exception e) {
					throw new AddPropertyException(4);
				}

			}

		}

		DataStorage.setRP(PL);
		DataStorage.setSelectImage(null);
	}

	private static boolean repeatId(String id) {
		int i = 0;
		while (i < model.DataStorage.getRP().size()) {
			if (model.DataStorage.getRP().get(i).getPropertyId().equals(id))
				return true;
			i++;
		}
		return false;
	}

}
