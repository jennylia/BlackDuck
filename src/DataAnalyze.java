
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] entry = line.split(cvsSplitBy);

				System.out.println(" [d_r_uuid = " + entry[0] 
						+ " , dws=" + entry[1]
						+ " , dns=" + entry[2]
						+ " , so=" + entry[3]
						+ " , version=" + entry[4]
						+ " , license_id=" + entry[4] 
						+ "]");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
