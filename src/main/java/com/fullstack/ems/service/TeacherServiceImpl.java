package com.fullstack.ems.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fullstack.ems.common.Constants;
import com.fullstack.ems.entity.Teacher;
import com.fullstack.ems.repository.TeacherRepository;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private AbstractEnvironment environment;// all the environment variables

	String subjectUrl = Constants.SUBJECT_URL;

	@Autowired
	private TeacherRepository teacherRepository;

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public List<Teacher> getTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public Optional<Teacher> getTeacher(Long id) {
		return teacherRepository.findById(id);
	}

	@Override
	public Teacher saveTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public Teacher updateTeacher(Long id, Teacher teacher) {
		teacher.setId(id);
		return teacherRepository.save(teacher);
	}

	@Override
	public void deleteTeacher(Long id) {
		teacherRepository.deleteById(id);
		return;
	}

	// Using rest template to make api call
	@Override
	public String getSubjects(Long id) {
		return restTemplate.getForEntity(environment.getProperty("ems") + subjectUrl, String.class).getBody();
		// return restTemplate.getForEntity("http://localhost:8080/ems/v1/subject",
		// String.class).getBody();
	}

	// Using Web Client to make api call
	@Override
	public String getSubjects() {
		WebClient webClient = WebClient.builder().baseUrl(environment.getProperty("ems")).build();
		return webClient.get().uri(subjectUrl).retrieve().bodyToMono(String.class).block();
	}

	public void sendMail() throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("balraj.hanmanthugari@gmail.com", "BalMar@2024");
			}
		});

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("balraj.hanmanthugari@gmail.com", false));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("balraj.hanmanthugari@gmail.com"));
		msg.setSubject("balraj email");
		msg.setContent("balraj email", "text/html");
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("balraj email", "text/html");

		MimeBodyPart attachPart = new MimeBodyPart();
		attachPart.attachFile("C:/Users/HI/Desktop/Controller-RestController.png");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		multipart.addBodyPart(attachPart);

		msg.setContent(multipart);
		Transport.send(msg);
	}

}
