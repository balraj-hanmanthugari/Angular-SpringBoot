//package com.fullstack.ems.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.fullstack.ems.entity.Employee;
//import com.fullstack.ems.repository.EmployeeRepository;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class EmployeeServiceImplTest {
//	@MockBean
//	private EmployeeRepository employeeRepository;
//	@Autowired
//	private EmployeeServiceImpl employeeServiceImpl;
//	
//	@Test
//	public void getEmployeesTest() throws Exception {
//		//FileInputStream is = new FileInputStream("src/test/resources/employee/employee-list.json");
//	    //String content = StreamUtils.copyToString(is, StandardCharsets.UTF_8);
//		List<Employee> employeeList = Stream.of(new Employee()).collect(Collectors.toList());
//	    //List<Employee> employeeList = objectMapper.readValue(content, new TypeReference<List<Employee>>(){}); 
//	    Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
//		
//	    List<Employee> employeeList1 = employeeServiceImpl.getEmployees();
//	    assertEquals(employeeList.size(), employeeList1.size());
//	}
//	
//	@Test
//	public void saveEmployeeTest() throws Exception {
//		Employee employee = new Employee();
//	   
//	    Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
//	    
//	    Employee employee1 = employeeServiceImpl.saveEmployee(employee);
//	    assertEquals(employee1.getId(), employee1.getId());
//	}
//
//}
