package com.cosmos.service;

import com.cosmos.model.Contact;
import com.cosmos.model.Users;
import com.cosmos.model.UsersList;
import com.cosmos.repository.UserRepository;
import com.cosmos.util.UserUtil;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserUtil userUtil;

	public void addUsersToExcelorCSV() {
		
		
	}
	public String addUsersFromExcel() {
		 String str = "List.xlsx";
		//String location ="D://AngulCustomers/";

		try {
			File file = new File(str);
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
			Long l = 0L;
			int i = 0;
			Users user = new Users();
			user.setAddedDate(LocalDate.now());
			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case STRING:
						i++;
						switch (i) {
						case 1:
							user.setUserName(cell.getStringCellValue());
							break;
						case 2:
							user.setLocation(cell.getStringCellValue());
							break;
						case 3:
							user.setUserSource(cell.getStringCellValue());
							break;
						}
						break;
					case NUMERIC:
						l = (long) cell.getNumericCellValue();
						user.setMobileNumber(l);
						break;

					}
				}
				user.setGotWhatsapp(false);
				user.setUsefull(true);
				userRepository.save(user);
				i = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Success";
	}
	public String addUsersFromExcel2() {

		 String str = "List.xlsx";

		try {
			File file = new File(str);
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
			Long l = 0L;
			int i = 0;
			Users user = new Users();
			user.setAddedDate(LocalDate.now());
			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case NUMERIC:
						l = (long) cell.getNumericCellValue();
						user.setMobileNumber(l);
						break;
					}
				}
				user.setUserSource("Bipad Vanjan");
				user.setLocation("Railway Colony");
				user.setGotWhatsapp(true);
				user.setUsefull(true);
				userRepository.save(user);
				i = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Success";
	
	}

	public Optional<Users> getUserByMobileNumber(Long mobileNumber) {
		// TODO Auto-generated method stub
		return userRepository.findById(mobileNumber);
	}

	public String updateUsers() {
		// TODO Auto-generated method stub
		List<Users> newusers = new LinkedList<Users>();
		List<Users> users = userRepository.findAll();
		for (Users user : users) {
			user.setGotWhatsapp(true);
			user.setUsefull(true);
			newusers.add(user);
		}
		userRepository.saveAll(newusers);
		return "Success";
	}

	public Optional<Users> updateByMobileNumber(Long mobileNumber, Users user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
		return null;
	}

	public Users saveUser(Users user) {
		// TODO Auto-generated method stub
		user.setAddedDate(LocalDate.now());
		return userRepository.save(user);
	}

	public UsersList getAllUsers() {
		// TODO Auto-generated method stub
		UsersList usersList = new UsersList();
		usersList.setUsersList(userRepository.findAll());
		return usersList;
	}

	public Optional<List<Users>> getAllUsersAddedYesterday() {
		// TODO Auto-generated method stub
		LocalDate yesterday = LocalDate.now().minusDays(1);
		System.out.println(yesterday);
		List<Users> users = userRepository.findByDate(yesterday);
		Optional<List<Users>> u = Optional.ofNullable(users);
		return u;
	}
	public UsersList getAllUsersWhatsappOnly(boolean b) {
		// TODO Auto-generated method stub
		UsersList usersList = new UsersList();
		usersList.setUsersList(userRepository.findWhatsappOnly(b));
		return usersList;
	}
	public List<Users> getAllUsersByAddedDate(LocalDate addedDate) {
		// TODO Auto-generated method stub
		System.out.println(addedDate);
		/*
		 * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 * //convert String to LocalDate LocalDate localDate =
		 * LocalDate.parse(addedDate, formatter);
		 */
		List<Users> users =userRepository.findByDate(addedDate);
		return users;
	}


    public String readCsv() throws FileNotFoundException {
		String path ="D:\\AngulCosmosProject\\ContactList.csv";
		List<Contact> beans = new CsvToBeanBuilder(new FileReader(path))
				.withType(Contact.class)
				.build()
				.parse();

		beans.forEach(s->{
			String mobile_number = s.getMobileNumber();
			Long mobileNumber = Long.parseLong(mobile_number);
			System.out.println(mobileNumber);
			String added_date = s.getAddedDate();
			final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate localDate = LocalDate.parse(added_date , DATE_FORMAT);
			Users users = new Users(mobileNumber,s.getUserName(),s.getLocation(),s.getUserSource(),s.isGotWhatsapp(),s.isUsefull(),localDate);
			userRepository.save(users);
		});
		return "Success";
    }
}
