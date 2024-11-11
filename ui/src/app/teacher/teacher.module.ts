import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TeacherFormComponent } from './teacher-form/teacher-form.component';
import { TeacherListComponent } from './teacher-list/teacher-list.component';
import { TeacherService } from './teacher.service';
import { RouterModule, Routes } from '@angular/router';
import { AgGridAngular } from 'ag-grid-angular'; // Angular Data Grid Component

const routes: Routes = [
  {
    path: '',
    component: TeacherListComponent
  },
  {
    path: ':id',
    component: TeacherFormComponent
  }
];

@NgModule({
  declarations: [
    TeacherFormComponent,
    TeacherListComponent
  ],
  providers: [ 
    TeacherService
  ],
  imports: [
    CommonModule,
    AgGridAngular,
    RouterModule.forChild(routes)
  ]
})
export class TeacherModule { }
