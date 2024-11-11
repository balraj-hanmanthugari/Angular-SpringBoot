import { Component, OnInit } from '@angular/core';
import { StudentService } from '../student.service';
import { GridReadyEvent, SelectionChangedEvent } from 'ag-grid-community';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.scss',
})
export class StudentListComponent implements OnInit {

  addLabel: String = "Add";
  editLabel: String = "Edit";
  editDisabled: Boolean = true;
  deleteLabel: String = "Delete";
  deleteDisabled: Boolean = true;

  constructor(private studentService: StudentService) {

  }

  ngOnInit(): void {
    //this.getStudentList();
  }

  columnDefs: any = [
    { headerName: 'First Name', field: 'firstName', flex: 1, filter: true },
    { headerName: 'Last Name', field: 'lastName', flex: 1 }
  ];

  rowSelection: any = {
    mode: 'multiRow'
  };

  pagination = true;
  paginationPageSize = 200;
  paginationPageSizeSelector = [200, 500, 1000];

  rowData: any = [];

  selectedRows:any = [];

  getStudentList(): void {
    this.studentService.getAll().subscribe({
      next: (data) => {
        this.rowData = data;
      },
      error: (e) => console.error(e)
    });
  }

  onGridReady() {
    this.getStudentList();
  }

  onSelectionChanged(event: SelectionChangedEvent) {
    this.selectedRows = event.api.getSelectedNodes();
    this.editDisabled=this.selectedRows.length === 1?false:true;
    this.deleteDisabled=this.selectedRows.length ===1?false:true;
  }

  addStudent() {
    console.log("create");
  }

  editStudent() {
    console.log(this.selectedRows);
  }

  onCellClicked(event:any) {
    console.log("edit");
  }

  deleteSelectedStudent() {
    this.studentService.delete(this.selectedRows[0]?.data.id).subscribe({
      next: (data) => {
        console.log(data);
        this.getStudentList();
      },
      error: (e) => console.error(e)
    });
  }
}
