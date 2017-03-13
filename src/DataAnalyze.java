
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
		String line = "";
		String cvsSplitBy = ";";

		//Most polular license_id
		HashMap<String, Integer> license_id_count = new HashMap<String, Integer>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] entry = line.split(cvsSplitBy);
				
				String license_id = entry[4];
				if (license_id_count.containsKey(license_id)){
					int cur_val = license_id_count.get(license_id);
					int new_val = cur_val + 1;
					license_id_count.put(license_id, new_val);
				}else{
					license_id_count.put(license_id, 1);
				}

//				System.out.println(" [d_r_uuid = " + entry[0] 
//						+ " , dws=" + entry[1]
//						+ " , dns=" + entry[2]
//						+ " , so=" + entry[3]
//						+ " , version=" + entry[4]
//						+ " , license_id=" + entry[4] 
//						+ "]");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Find out what is the most popular version we have?
		System.out.println(license_id_count.size());
		
		//Iterators in HashTable
		 Set licenseSet = license_id_count.entrySet();
	     Iterator licenseSetIterator = licenseSet.iterator();
	      while(licenseSetIterator.hasNext()) {
	          Map.Entry licenseSetEntry = (Map.Entry)licenseSetIterator.next();
	          System.out.print("Key is: "+licenseSetEntry.getKey() + " & Value is: ");
	          System.out.println(licenseSetEntry.getValue());
	       }
		

	}

}
