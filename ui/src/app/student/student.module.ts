import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentFormComponent } from './student-form/student-form.component';
import { StudentListComponent } from './student-list/student-list.component';
import { StudentService } from './student.service';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: StudentListComponent
  },
  {
    path: ':id',
    component: StudentFormComponent
  }
];

@NgModule({
  declarations: [
    StudentFormComponent,
    StudentListComponent
  ],
  providers: [ 
    StudentService 
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ]
})
export class StudentModule { }
