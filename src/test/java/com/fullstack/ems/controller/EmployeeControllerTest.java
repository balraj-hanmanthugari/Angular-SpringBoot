//package com.fullstack.ems.controller;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.io.FileInputStream;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.util.StreamUtils;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fullstack.ems.entity.Employee;
//import com.fullstack.ems.service.EmployeeServiceImpl;
//
//
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class EmployeeControllerTest {
//	@MockBean
//	private EmployeeServiceImpl employeeServiceImpl;
//	@Autowired
//	private ObjectMapper objectMapper;
//	@Autowired
//    private MockMvc mockMvc;
//	
//	@Test
//	public void getEmployeesTest() throws Exception {
//		FileInputStream is = new FileInputStream("src/test/resources/employee/employee-list.json");
//	    String content = StreamUtils.copyToString(is, StandardCharsets.UTF_8);
//	    
//	    List<Employee> employeeList = objectMapper.readValue(content, new TypeReference<List<Employee>>(){}); 
//	    Mockito.when(employeeServiceImpl.getEmployees()).thenReturn(employeeList);
//		
//	    mockMvc.perform(get("/ems/employee/list")
//                .contentType(MediaType.ALL))
//                .andExpect(status().isOk());
//	}
//	
//	@Test
//	public void saveEmployeeTest() throws Exception {
//		FileInputStream is = new FileInputStream("src/test/resources/employee/employee.json");
//	    String content = StreamUtils.copyToString(is, StandardCharsets.UTF_8);
//	    
//	    Employee employee = objectMapper.readValue(content, Employee.class); 
//	   
//	    Mockito.when(employeeServiceImpl.saveEmployee(employee)).thenReturn(employee);
//	    
//	    mockMvc.perform(post("/ems/employee/form")
//                .contentType(MediaType.ALL)
//	    		.content(content))
//                .andExpect(status().isOk());
//	}
//
//}
