import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentService } from '../student.service';
import { Student } from '../student.model';

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrl: './student-form.component.scss'
})
export class StudentFormComponent {
  addTitle: String = "Add Student";
  editTitle: String = "Edit Student";
  isEdit: Boolean = false;

  showSuccessMessage: Boolean = false;
  showFailureMessage: Boolean = false;

  student: Student = {
    firstName: "",
    lastName: ""
  }

  skills: any = ["Angular", "React", "NodeJS", "Spring Boot"];
  skill: any;

  constructor(private studentService: StudentService, private route: ActivatedRoute,
    private router: Router) {
    route.paramMap.subscribe((data: any) => {
      if (data.params.id) {
        this.student.id = data.params.id;
        this.student.firstName = data.params.firstName;
        this.student.lastName = data.params.lastName;
        this.isEdit = true;
      } else {
        this.isEdit = false;
      }
    });
  }

  saveStudent() {
    if (this.isEdit) {
      this.studentService.update(this.student.id, this.student).subscribe((data) => {
        this.showSuccessMessage = true;
      });
    } else {
      this.studentService.create(this.student).subscribe((data) => {
        this.showSuccessMessage = true;
      });
    }
  }
}
