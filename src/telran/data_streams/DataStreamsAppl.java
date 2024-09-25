package telran.data_streams;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataStreamsAppl {

	static final double[] PRICES = {19.9, 9.99,5,4.9,90};
	static final int[] UNITS = {12, 8,6,3,1};
	static final String[] GOODS = {"butter", "bread","meat","water","candy"};
	
	public static void main(String[] args) {
		// "100000" -> 7 bytes
		//  100000 -> 4 bytes
		try(DataOutputStream out = new DataOutputStream(new FileOutputStream("invoice.data"))){
			for(int i=0;i<PRICES.length;i++) {
				out.writeDouble(PRICES[i]);
				out.writeInt(UNITS[i]);
				out.writeUTF(GOODS[i]);
			}
		}catch(Exception e) {
			
		}
		
		double price;
		int unit;
		String good;
		double total = 0;
		
		try (DataInputStream in = new DataInputStream(new FileInputStream("invoice.data"))) {
			while(true) {
			price = in.readDouble();
			unit = in.readInt();
			good = in.readUTF();
			
			System.out.printf("Your order %d units of %s at $%.2f\n", unit,good,price);
			total += price*unit;
			}
			
		} catch (EOFException e) {
			System.out.println(e.getMessage());
		}
		
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Total costs in $ is " + total);
	}

}
