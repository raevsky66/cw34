package telran.objects_streams;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import telran.objects_streams.dto.Address;
import telran.objects_streams.dto.Child;
import telran.objects_streams.dto.Employee;
import telran.objects_streams.dto.Person;

public class PersonRandomGeneratorAppl {

	private static final String FILE_NAME = "persons.data";
	static final int N_PERSONS = 100;
	static final int EMP_PROBABILITY = 40;

	static final int MAX_CHILD_YEAR = 2024;
	static final int MIN_CHILD_YEAR = 2010;

	static final int MAX_EMP_YEAR = 2011;
	static final int MIN_EMP_YEAR = 1957;

	static final int MIN_SALARY = 6000;
	static final int MAX_SALARY = 100_000;
	
	static final int N_CITY = 5;
	static final int N_COMPANY = 5;
	static final int N_GARTEN = 5;
	
	static Random rnd = new Random();
	
	
	
	
	public static void main(String[] args) {
		List<Person> persons = getRandomPersons();
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			out.writeObject(persons);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}




	private static List<Person> getRandomPersons() {
		// TODO Auto-generated method stub
		return Stream.generate(()->getRandomPerson()).limit(N_PERSONS).collect(Collectors.toList());
	}




	private static Person getRandomPerson() {
		Person pers = getRandomCommonPerson();
		return rnd.nextInt(1,101)<EMP_PROBABILITY?getRandomEmployee(pers):getRandomChild(pers);
	}




	private static Person getRandomEmployee(Person pers) {
		
		LocalDate birthDate = getRandomDate(MIN_EMP_YEAR,MAX_EMP_YEAR);
		String company = "company "+ rnd.nextInt(1,N_COMPANY);
		int salary = rnd.nextInt(MIN_SALARY,MAX_SALARY);
		String title = "title "+rnd.nextInt(1,5);
		return new Employee(pers.id, pers.name, pers.address, birthDate, company, salary, title);
	}

	private static LocalDate getRandomDate(int min, int max) {
		
		int year = rnd.nextInt(min,max);
		int month = rnd.nextInt(1,13);
		int day = rnd.nextInt(1,29);
		return LocalDate.of(year, month, day);
	}




	private static Person getRandomCommonPerson() {
		
		int id = rnd.nextInt(100000,999999);
		String name = "name"+ rnd.nextInt(1, 200);
		Address address = getRandomAddress();
		
		return new Person(id, name, address, null);
		
	}




	private static Address getRandomAddress() {
		String city = "city" + rnd.nextInt(1, N_CITY);
		String street = "street" + rnd.nextInt(1, N_CITY);
		int building = rnd.nextInt(1,100);
		int flat = rnd.nextInt(1,100);
		return new Address(city, street, building, flat);
	}




	private static Person getRandomChild(Person pers) {
		
		LocalDate birthDate = getRandomDate(MIN_CHILD_YEAR,MAX_CHILD_YEAR);
		String garten = "garten " + rnd.nextInt(1, N_GARTEN);
		return new Child(pers.id, pers.name, pers.address, birthDate, garten);
	}

}
