import { Component } from '@angular/core';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.scss',
})
export class StudentListComponent {
  
  columnDefs: any = [
    { headerName: 'First Name', field: 'firstName', editable: true },
    { headerName: 'Last Name', field: 'lastName' }
  ];

  rowData = [
    { firstName: 'Toyota', lastName: 'Celica' },
    { firstName: 'Ford', lastName: 'Mondeo' },
    { firstName: 'Porsche', lastName: 'Boxter' }
  ];
}
