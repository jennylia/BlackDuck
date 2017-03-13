
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

		HashMap<String, Integer> uuid_map = new HashMap<String, Integer>();
		HashMap<String, Integer> dws_map = new HashMap<String, Integer>();
		HashMap<String, Integer> dns_map = new HashMap<String, Integer>();
		HashMap<String, Integer> so_map = new HashMap<String, Integer>();
		HashMap<String, Integer> version_map = new HashMap<String, Integer>();
		HashMap<String, Integer> license_map = new HashMap<String, Integer>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] entry = line.split(cvsSplitBy);
				
				//UUDI
				String uuid = entry[0];
				populateHashMap(uuid_map, uuid);

				//DWS
				String dws = entry[1];
				populateHashMap(dws_map, dws);
				
				//DNS
				String dns = entry[2];
				populateHashMap(dns_map, dws);
				
				//SO
				String so = entry[3];
				populateHashMap(so_map, so);
				
				//Version
				String version = entry[4];
				populateHashMap(version_map, version);
				
				//License_ID
				String license_id = entry[5];
				populateHashMap(license_map, license_id);

				// System.out.println(" [d_r_uuid = " + entry[0]
				// + " , dws=" + entry[1]
				// + " , dns=" + entry[2]
				// + " , so=" + entry[3]
				// + " , version=" + entry[4]
				// + " , license_id=" + entry[5]
				// + "]");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Find out what is the most popular version we have?

		printMostPoluarItem(uuid_map, "uuid");
		printMostPoluarItem(dws_map, "dws_map");
		printMostPoluarItem(dns_map, "dns_map");
		printMostPoluarItem(so_map, "so_map");
		printMostPoluarItem(version_map, "version");
		printMostPoluarItem(license_map, "license_id");

	}
	
	public static void populateHashMap(HashMap map_param, String key_param){
		if (map_param.containsKey(key_param)) {
			int cur_val = (int) map_param.get(key_param);
			int new_val = cur_val + 1;
			map_param.put(key_param, new_val);
		} else {
			map_param.put(key_param, 1);
		}
	}
	public static void printMostPoluarItem(HashMap hashmap_param, String printName ){
		// Iterators in HashTable
				Set convertedSet = hashmap_param.entrySet();
				Iterator convertedSetIterator = convertedSet.iterator();
				
				int popular_count = 0;
				String popular_name = "demo";
				while (convertedSetIterator.hasNext()) {
					Map.Entry setEntry = (Map.Entry) convertedSetIterator.next();
//					System.out.print("Key is: " + setEntry.getKey() + " & Value is: ");
//					System.out.println(setEntry.getValue());
					
					if ((int) setEntry.getValue() > popular_count){
						popular_count = (int) setEntry.getValue();
						popular_name = (String) setEntry.getKey();
					}
				}
				System.out.println("Most popular " + printName + " item is: " + popular_name + " with:" + popular_count);
	}

}
