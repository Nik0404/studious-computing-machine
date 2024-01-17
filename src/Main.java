import java.io.File;
import java.util.*;

import org.ietf.jgss.Oid;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		int number = 0;
		String name = "";
		String region = "";
		String district = "";
		long population = 0;
		String foundation = "";
		ArrayList<City> citys = new ArrayList<>();

		try {
			File file = new File("../LMS/src/ddd.csv");
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] fields = line.split(";");
				number = (fields.length > 0 && Integer.parseInt(fields[0]) != 0) ? Integer.parseInt(fields[0]) : 0;
				name = (fields.length > 1 && fields[1] != null) ? fields[1] : "";
				region = (fields.length > 2 && fields[2] != null) ? fields[2] : "";
				district = (fields.length > 3 && fields[3] != null) ? fields[3] : "";
				population = (fields.length > 4 && Long.parseLong(fields[4]) != 0) ? Long.parseLong(fields[4]) : 0;
				foundation = (fields.length > 5 && fields[5] != null) ? fields[5] : "";
				City city = new City(name, region, district, population, foundation);
				citys.add(city);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		for (City city : sortingDistrictAndName(citys)) {
			System.out.println(city);
		}
		citySearch(citys);
	}

	public static ArrayList<City> sortingName(ArrayList<City> c) {
		c.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
		return c;
	}

	public static ArrayList<City> sortingDistrictAndName(ArrayList<City> c) {
		c.sort((c1, c2) -> {
			int r = c1.getDistrict().compareTo(c2.getDistrict());
			if (r == 0) {
				r = c1.getName().compareTo(c2.getName());
			}
			return r;
		});
		return c;
	}

	public static void citySearch(ArrayList<City> c) {
		Map<String, Integer> map = new HashMap<>();

		for (City city : c) {
			String region = city.getRegion();
			int count = map.getOrDefault(region, 0);
			map.put(region, count + 1);
		}

		System.out.println(map);
	}

}