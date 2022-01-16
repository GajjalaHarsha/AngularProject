import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { __param } from 'tslib';
import { Doctors } from '../model/doctors';
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-adminupdatedoctor',
  templateUrl: './adminupdatedoctor.component.html',
  styleUrls: ['./adminupdatedoctor.component.css']
})
export class AdminupdatedoctorComponent implements OnInit {

  constructor(private adminService: AdminService, private activatedRouter: ActivatedRoute, private router: Router) { }

  doctor: Doctors;
  updateForm:FormGroup;
  id: number;
  ngOnInit(): void {
    // this.adminService.viewDoctorsData()
    this.activatedRouter.paramMap.subscribe(params=>{
      this.id=+params.get("id");
      console.log(this.id);  
  });
  this.adminService.viewDoctorsData(this.id).subscribe(data=> {
      this.doctor=data;
      this.updateForm.patchValue(data);
      console.log(this.doctor);
  });
    this.updateForm=new FormGroup({
      doctorId: new FormControl(null, Validators.required),
      doctorName: new FormControl(null, Validators.required), 
      experience: new FormControl(null, [Validators.required, Validators.min(0), Validators.max(50)]), //Note we can add more than one validator, if we have email we can add validator for the email.
      noOfOperationsTaken: new FormControl(null, [Validators.required, Validators.min(0), Validators.max(500)]), 
      operationsSuccess: new FormControl(null, [Validators.required, Validators.min(0)]),
      splName: new FormControl(null, Validators.required),
    });
    
  }

  func(): Boolean{
    // console.log("valid" +this.updateForm.valid);
    
    // console.log(this.addDoctorForm.get("operationsSuccess").value > this.addDoctorForm.get("noOfOperationsTaken").value);
  return this.updateForm.get("operationsSuccess").value > this.updateForm.get("noOfOperationsTaken").value;
  }
  onSubmit(): void {
    this.adminService.updateDoctor(this.updateForm.value).subscribe(data=> {
      console.log(data);
      this.router.navigate(['doctor-edit/'+this.doctor.splName]);
    })
  }

}
