
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DataAnalyze {

	static HashMap<String, Integer> uuid_map = new HashMap<String, Integer>();
	static HashMap<String, Integer> dws_map = new HashMap<String, Integer>();
	static HashMap<String, Integer> dns_map = new HashMap<String, Integer>();
	static HashMap<String, Integer> so_map = new HashMap<String, Integer>();
	static HashMap<String, Integer> version_map = new HashMap<String, Integer>();
	static HashMap<String, Integer> license_map = new HashMap<String, Integer>();

	//Adjacency list representation
	static HashMap<String, ArrayList<String>> uuid_to_dns_graph = new  HashMap<String, ArrayList<String>>();
	static HashMap<String, ArrayList<String>> dns_to_uuid_graph = new  HashMap<String, ArrayList<String>>();

	public static void main(String[] args) {
		// d_r_uuid object
		// dws object
		// dns object
		// so object
		// version object
		// license_id int64
		String testFile = "/Users/jennylian/BlackDuck/sample.csv";
		String dataFile = "/Users/jennylian/BlackDuck/ubc_data_workshop.csv";
		String line = "";
		String cvsSplitBy = ";";
		
		//err... let's build some graphs... for each uuid, what type of dws_map value can it have?
		

		// Let's build some graphs....
		try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] entry = line.split(cvsSplitBy);

				populateAllHashMap(entry);

				// System.out.println(" [d_r_uuid = " + entry[0]
				// + " , dws=" + entry[1]
				// + " , dns=" + entry[2]
				// + " , so=" + entry[3]
				// + " , version=" + entry[4]
				// + " , license_id=" + entry[5]
				// + "]");
				
				populate_uuid_to_dns_graph(entry);
				populate_dns_to_uuid_graph(entry);


			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Find out what is the most popular version we have?

		printMostPoluarItems();
		
//		printGraph(uuid_to_dns_graph);
		printGraph(dns_to_uuid_graph);
		
		System.out.println(uuid_to_dns_graph.size());
		System.out.println(dns_to_uuid_graph.size());

//		System.out.println(uuid_to_dns_graph.get("09212528-db03-4fa1-8aa1-993a848d557c"));
//		System.out.println(dns_to_uuid_graph.get("bddc368dcc9f1b87c685dc1e828dfa03"));
	}

	private static void populate_uuid_to_dns_graph(String[] entry) {
		String uuid = entry[0];
		String dns = entry[1];
		
		//Building it for uuid_to_dns
		ArrayList<String> current_dns_entries;
		if (uuid_to_dns_graph.containsKey(uuid)){
			current_dns_entries = uuid_to_dns_graph.get(uuid);
			current_dns_entries.add(dns);
		}else{
			current_dns_entries = new ArrayList<String>();
			current_dns_entries.add(dns);
		}
		uuid_to_dns_graph.put(uuid, current_dns_entries);
		
	}
	

	private static void populate_dns_to_uuid_graph(String[] entry) {
		String uuid = entry[0];
		String dns = entry[1];
		
		//Building it for uuid_to_dns
		ArrayList<String> current_uuid_entries;
		if (dns_to_uuid_graph.containsKey(dns)){
			current_uuid_entries = dns_to_uuid_graph.get(dns);
			current_uuid_entries.add(uuid);
		}else{
			current_uuid_entries = new ArrayList<String>();
			current_uuid_entries.add(uuid);
		}
		dns_to_uuid_graph.put(uuid, current_uuid_entries);
		
	}
	
	

	private static void printMostPoluarItems() {
		// TODO Auto-generated method stub
		printMostPoluarItem(uuid_map, "uuid");
		printMostPoluarItem(dws_map, "dws_map");
		printMostPoluarItem(dns_map, "dns_map");
		printMostPoluarItem(so_map, "so_map");
		printMostPoluarItem(version_map, "version");
		printMostPoluarItem(license_map, "license_id");
	}

	public static void populateAllHashMap(String[] entry) {
		// TODO Auto-generated method stub
		// UUDI
		String uuid = entry[0];
		populateHashMap(uuid_map, uuid);

		// DWS
		String dws = entry[1];
		populateHashMap(dws_map, dws);

		// DNS
		String dns = entry[2];
		populateHashMap(dns_map, dws);

		// SO
		String so = entry[3];
		populateHashMap(so_map, so);

		// Version
		String version = entry[4];
		populateHashMap(version_map, version);

		// License_ID
		String license_id = entry[5];
		populateHashMap(license_map, license_id);
	}

	public static void populateHashMap(HashMap map_param, String key_param) {
		if (map_param.containsKey(key_param)) {
			int cur_val = (int) map_param.get(key_param);
			int new_val = cur_val + 1;
			map_param.put(key_param, new_val);
		} else {
			map_param.put(key_param, 1);
		}
	}

	public static void printMostPoluarItem(HashMap hashmap_param, String printName) {
		// Iterators in HashTable
		Set convertedSet = hashmap_param.entrySet();
		Iterator convertedSetIterator = convertedSet.iterator();

		int popular_count = 0;
		String popular_name = "demo";
		while (convertedSetIterator.hasNext()) {
			Map.Entry setEntry = (Map.Entry) convertedSetIterator.next();
			// System.out.print("Key is: " + setEntry.getKey() + " & Value is:
			// ");
			// System.out.println(setEntry.getValue());

			if ((int) setEntry.getValue() > popular_count) {
				popular_count = (int) setEntry.getValue();
				popular_name = (String) setEntry.getKey();
			}
		}
		System.out.println("Most popular " + printName + " item is: " + popular_name + " with:" + popular_count);
	}
	
	
	public static void printGraph(HashMap hashmap_param) {
		// Iterators in HashTable
		Set convertedSet = hashmap_param.entrySet();
		Iterator convertedSetIterator = convertedSet.iterator();

		int popular_count = 0;
		String popular_name = "demo";
		while (convertedSetIterator.hasNext()) {
			Map.Entry setEntry = (Map.Entry) convertedSetIterator.next();
			 System.out.print("Key is: " + setEntry.getKey() + " & Value is: ");
			 System.out.println(setEntry.getValue());
		}
	}

}
