import { Component, ElementRef, afterRender, ViewChild, AfterContentChecked, AfterViewInit, AfterViewChecked, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentService } from '../student.service';
import { Student } from '../student.model';
import { NavigationService } from '../../shared/service/navigation.service';
import { Form, NgForm } from '@angular/forms';
import { combineLatest, concat, map, merge } from 'rxjs';

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrl: './student-form.component.scss'
})
export class StudentFormComponent implements AfterViewChecked, OnInit{
  addTitle: String = "Add Student";
  editTitle: String = "Edit Student";
  isEdit: Boolean = false;

  hideSuccessMessage: Boolean = true;
  hideFailureMessage: Boolean = true;

  student: any = {
    firstName: "",
    lastName: "",
    address: {
      id: 0,
      description: ""
    }
  };

  cities: any = [{id: 1, description: "Hyderbad"}, {id: 2, description: "Bangalore"}, {id: 3, description: "Mumbai"}, {id: 4, description: "Delhi"}, {id: 5, description: "Kolkata"}];
  city: any;
  selectedItem: any;

  @ViewChild('studentForm') stuForm: NgForm | undefined; //template reference variable

  constructor(private studentService: StudentService, private route: ActivatedRoute,
    private router: Router, private navigationService: NavigationService) {
    this.studentService.getSelectedStudent().subscribe((data: any) => {
          this.student = data;
    });
    route.paramMap.subscribe((data: any) => {
      if (data.params.id) {
        this.student.id = data.params.id;
        this.isEdit = true;
        
        if(!this.student || !this.student.firstName) {
          this.getStudent(data.params.id);
        }
      } else {
        this.isEdit = false;
        this.studentService.setSelectedStudent(new Student());
      }
    });
  }

  ngOnInit() {
    combineLatest([this.route.params, this.route.queryParams]).pipe(map(([params, queryParams]) => {
      return {
        ...params,
        ...queryParams
      }
    })).subscribe((data) => {
      console.log(data);
    }); 
  }

  ngAfterViewChecked() {
    if(this.stuForm) {
      this.setFormChangesExist(this.stuForm.form.touched);
    }
  }

  getStudent(id: any) {
    this.studentService.get(id).subscribe({
      next: (data) => {
        this.student = data;
      },
      error: (error) => {
        this.showAlert('failure');
      }
    })
  }

  saveStudent(studentForm: any) {
    if(studentForm.form.value.addressId) {
      this.selectedItem = this.cities.filter((city: any) => {
        return city.id === Number(studentForm.form.value.addressId);
      });
      this.student.address = (this.selectedItem && this.selectedItem.length) ? this.selectedItem[0]: {};
    }
    if (this.isEdit) {
      this.studentService.update(this.student.id, this.student).subscribe({
        next: (data) => {
          this.showAlert('success');
          studentForm.form.markAsUntouched();
        }, error: (e) => {
          this.showAlert('failure');
        }
      });
    } else {
      this.studentService.create(this.student).subscribe({
        next: (data) => {
          this.showAlert('success');
          this.student = data;
          this.isEdit = true;
          studentForm.form.markAsUntouched();
        }, error: (e) => {
          this.showAlert('failure');
        }
      });
    }
  }

  setFormChangesExist(val: any) {
    this.navigationService.setUnSavedChangesExist(val);
  }

  showAlert(type: any) {
    type === 'success'? this.hideSuccessMessage = false: this.hideFailureMessage = false;
  }

  closeAlert(type: any) {
    type === 'success'? this.hideSuccessMessage = true: this.hideFailureMessage = true;
  }
}
