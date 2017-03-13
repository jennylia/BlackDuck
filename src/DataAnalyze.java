
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DataAnalyze {

	public static void main(String[] args) {
		// d_r_uuid object
		// dws object
		// dns object
		// so object
		// version object
		// license_id int64
		String csvFile = "/Users/jennylian/BlackDuck/sample.csv";
		String dataFile = "/Users/jennylian/BlackDuck/ubc_data_workshop.csv";
		String line = "";
		String cvsSplitBy = ";";

		HashMap<String, Integer> dws_map = new HashMap<String, Integer>();
		HashMap<String, Integer> dns_map = new HashMap<String, Integer>();
		HashMap<String, Integer> so_map = new HashMap<String, Integer>();

		HashMap<String, Integer> license_map = new HashMap<String, Integer>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] entry = line.split(cvsSplitBy);

				//DWS
				String dws = entry[1];
				if (dws_map.containsKey(dws)) {
					int cur_val = dws_map.get(dws);
					int new_val = cur_val + 1;
					dws_map.put(dws, new_val);
				} else {
					dws_map.put(dws, 1);
				}
				
				//License_ID
				String license_id = entry[4];
				if (license_map.containsKey(license_id)) {
					int cur_val = license_map.get(license_id);
					int new_val = cur_val + 1;
					license_map.put(license_id, new_val);
				} else {
					license_map.put(license_id, 1);
				}

				// System.out.println(" [d_r_uuid = " + entry[0]
				// + " , dws=" + entry[1]
				// + " , dns=" + entry[2]
				// + " , so=" + entry[3]
				// + " , version=" + entry[4]
				// + " , license_id=" + entry[4]
				// + "]");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Find out what is the most popular version we have?
		System.out.println(license_map.size());

		printMostPoluarItem(license_map, "license_id");
	}
	
	public static void printMostPoluarItem(HashMap hashmap_param, String printName ){
		// Iterators in HashTable
				Set convertedSet = hashmap_param.entrySet();
				Iterator convertedSetIterator = convertedSet.iterator();
				
				int popular_count = 0;
				String popular_name = "demo";
				while (convertedSetIterator.hasNext()) {
					Map.Entry setEntry = (Map.Entry) convertedSetIterator.next();
					System.out.print("Key is: " + setEntry.getKey() + " & Value is: ");
					System.out.println(setEntry.getValue());
					
					if ((int) setEntry.getValue() > popular_count){
						popular_count = (int) setEntry.getValue();
						popular_name = (String) setEntry.getKey();
					}
				}
				System.out.println("Most popular " + printName + " item is: " + popular_name + "with:" + popular_count);
	}

}
