
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class DataAnalyze {

	static HashMap<String, Integer> uuid_map = new HashMap<String, Integer>();
	static HashMap<String, Integer> dws_map = new HashMap<String, Integer>();
	static HashMap<String, Integer> dns_map = new HashMap<String, Integer>();
	static HashMap<String, Integer> so_map = new HashMap<String, Integer>();
	static HashMap<String, Integer> version_map = new HashMap<String, Integer>();
	static HashMap<String, Integer> license_map = new HashMap<String, Integer>();

    // TODO update these
    static HashMap<String, ArrayList<String>> uuid_to_dns_graph = new  HashMap<String, ArrayList<String>>();
    static HashMap<String, ArrayList<String>> dns_to_uuid_graph = new  HashMap<String, ArrayList<String>>();
    static HashMap<String, Set<String>> graph = new  HashMap<String, Set<String>>();


    public static void main(String[] args) {
		// d_r_uuid object
		// dws object
		// dns object
		// so object
		// version object
		// license_id int64

        String sampleFile = new File("").getAbsolutePath().concat("/data/sample.csv");

        // !! cannot upload the real data file in project folder... git upload capacity exceeds
        // String dataFile = new File("").getAbsolutePath().concat("/data/ubc_data_workshop.csv");

        //String testFile = "/Users/jennylian/BlackDuck/sample.csv";
		//String dataFile = "/Users/jennylian/BlackDuck/ubc_data_workshop.csv";

		String line = "";
		String cvsSplitBy = ";";

		//err... let's build some graphs... for each uuid, what type of dws_map value can it have?
		

		try (BufferedReader br = new BufferedReader(new FileReader(sampleFile))) {

			while ((line = br.readLine()) != null) {

				// each line contains a project with all dws, dns, version, license
                // entry contains each element, i.e. entry[0] = project uuid, entry[1] = dns, etc.
				String[] entry = line.split(cvsSplitBy);

				populateAllHashMap(entry);

				// System.out.println(" [d_r_uuid = " + entry[0]
				// + " , dws=" + entry[1]
				// + " , dns=" + entry[2]
				// + " , so=" + entry[3]
				// + " , version=" + entry[4]
				// + " , license_id=" + entry[5]
				// + "]");

                // TODO update these
				//populate_uuid_to_dns_graph(entry);
				//populate_dns_to_uuid_graph(entry);
                //populate_graph(entry, "uuid", "dns");
                populate_graph(entry, "dns", "uuid");


			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Find out what is the most popular version we have?
		//printMostPoluarItems();


        printGraph(graph);
        System.out.println(graph.size());
//		printGraph(uuid_to_dns_graph);
//		printGraph(dns_to_uuid_graph);
//		System.out.println(uuid_to_dns_graph.size());
//		System.out.println(dns_to_uuid_graph.size());

//		System.out.println(uuid_to_dns_graph.get("09212528-db03-4fa1-8aa1-993a848d557c"));
//		System.out.println(dns_to_uuid_graph.get("bddc368dcc9f1b87c685dc1e828dfa03"));
	}

    /*
    For each line of data, return the data value of the specified data type
     */
    private static String getDataValue (String[] entry, String dataType) {

        switch(dataType) {
            case "uuid":
                return entry[0];
            case "dns":
                return entry[1];
            case "dws":
                return entry[2];
            case "so":
                return entry[3];
            case "version":
                return entry[4];
            case "license_id":
                return entry[5];
            default:
                return null;
        }
    }

    /*
    from given data type of 'from' or 'to', it populate graph as map of key (from) and value (to)
     */
	private static void populate_graph(String[] entry, String from, String to) {

        // TODO use HashMap
        String key = getDataValue(entry, from);
        String value = getDataValue(entry, to);

        HashMap<String, Integer> graph_map = new HashMap<String, Integer>();
		
		//Building it for uuid_to_dns
		Set<String> current_entries;

		if (graph.containsKey(key)){
            current_entries = graph.get(key);
            current_entries.add(value);
		}else{
            current_entries = new HashSet<String> ();
            current_entries.add(value);
		}
        graph.put(key, current_entries);
	}

//    private static void populate_uuid_to_dns_graph(String[] entry) {
//        String uuid = entry[0];
//        String dns = entry[1];
//
//        //Building it for uuid_to_dns
//        ArrayList<String> current_dns_entries;
//        if (uuid_to_dns_graph.containsKey(uuid)){
//            current_dns_entries = uuid_to_dns_graph.get(uuid);
//            current_dns_entries.add(dns);
//        }else{
//            current_dns_entries = new ArrayList<String>();
//            current_dns_entries.add(dns);
//        }
//        uuid_to_dns_graph.put(uuid, current_dns_entries);
//
//    }
//
//
//	private static void populate_dns_to_uuid_graph(String[] entry) {
//		String uuid = entry[0];
//		String dns = entry[1];
//
//		//Building it for uuid_to_dns
//		ArrayList<String> current_uuid_entries;
//		if (dns_to_uuid_graph.containsKey(dns)){
//			current_uuid_entries = dns_to_uuid_graph.get(dns);
//			current_uuid_entries.add(uuid);
//		}else{
//			current_uuid_entries = new ArrayList<String>();
//			current_uuid_entries.add(uuid);
//		}
//		dns_to_uuid_graph.put(dns, current_uuid_entries);
////		printGraph(dns_to_uuid_graph);
//
//	}


	private static void printMostPoluarItems() {
		printMostPoluarItem(uuid_map, "uuid");
		printMostPoluarItem(dws_map, "dws_map");
		printMostPoluarItem(dns_map, "dns_map");
		printMostPoluarItem(so_map, "so_map");
		printMostPoluarItem(version_map, "version");
		printMostPoluarItem(license_map, "license_id");
	}

    /*
    Produce all the counts of unique element (e.g. uuid) in the data
     */
	public static void populateAllHashMap(String[] entry) {
		// UUDI
		String uuid = entry[0];
		countItemsHashMap(uuid_map, uuid);

		// DWS
		String dws = entry[1];
		countItemsHashMap(dws_map, dws);

		// DNS
		String dns = entry[2];
		countItemsHashMap(dns_map, dws);

		// SO
		String so = entry[3];
		countItemsHashMap(so_map, so);

		// Version
		String version = entry[4];
		countItemsHashMap(version_map, version);

		// License_ID
		String license_id = entry[5];
		countItemsHashMap(license_map, license_id);
	}

    /*
        Gives key and value, where value is the count of how many times the key appear in the data
     */
	public static void countItemsHashMap(HashMap map_param, String key_param) {
		if (map_param.containsKey(key_param)) {
			int cur_val = (int) map_param.get(key_param);
			int new_val = cur_val + 1;
			map_param.put(key_param, new_val);
		} else {
			map_param.put(key_param, 1);
		}
	}

    /*

     */
	public static void printMostPoluarItem(HashMap hashmap_param, String printName) {
		// Iterators in HashTable
		Set convertedSet = hashmap_param.entrySet();
		Iterator convertedSetIterator = convertedSet.iterator();

		int popular_count = 0;
		String popular_name = "demo";
		while (convertedSetIterator.hasNext()) {
			Map.Entry setEntry = (Map.Entry) convertedSetIterator.next();

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
