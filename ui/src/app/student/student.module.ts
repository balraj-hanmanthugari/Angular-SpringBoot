import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentFormComponent } from './student-form/student-form.component';
import { StudentListComponent } from './student-list/student-list.component';
import { StudentService } from './student.service';
import { RouterModule, Routes } from '@angular/router';
import { AgGridAngular } from 'ag-grid-angular'; // Angular Data Grid Component
import { HttpClientModule } from '@angular/common/http';

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
    AgGridAngular,
    HttpClientModule,
    RouterModule.forChild(routes)
  ]
})
export class StudentModule { }
