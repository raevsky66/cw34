package telran.objects_streams;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import telran.objects_streams.dto.Person;

public class RestorePersonsFromFileAppl {
	private static final String FILE_NAME = "persons.data";
	
	public static void main(String[] args) {
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			@SuppressWarnings("unchecked")
			List<Person> persons = (List<Person>) in.readObject();
			persons.forEach(System.out::println);
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}

	}

}
