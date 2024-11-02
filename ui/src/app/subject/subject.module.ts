import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SubjectFormComponent } from './subject-form/subject-form.component';
import { SubjectListComponent } from './subject-list/subject-list.component';
import { SubjectService } from './subject.service';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: SubjectListComponent
  },
  {
    path: ':id',
    component: SubjectFormComponent
  }
];

@NgModule({
  declarations: [
    SubjectFormComponent,
    SubjectListComponent
  ],
  providers: [ 
    SubjectService 
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ]
})
export class SubjectModule { }
