import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-adminadddoctor',
  templateUrl: './adminadddoctor.component.html',
  styleUrls: ['./adminadddoctor.component.css']
})
export class AdminadddoctorComponent implements OnInit {

  addDoctorForm:FormGroup;
  specializationName: String;
  constructor(private adminService: AdminService, private router: Router, private activatedRouter: ActivatedRoute) { }

  check: boolean;
  
  
  ngOnInit(): void {
    this.activatedRouter.paramMap.subscribe(params=> {
      this.specializationName=params.get("specialization")
    });
    this.addDoctorForm=new FormGroup({
      doctorName: new FormControl(null, Validators.required), 
      experience: new FormControl(null, [Validators.required, Validators.min(0), Validators.max(50)]), //Note we can add more than one validator, if we have email we can add validator for the email.
      noOfOperationsTaken: new FormControl(null, [Validators.required, Validators.min(0), Validators.max(500)]), 
      operationsSuccess: new FormControl(null, [Validators.required, Validators.min(0)])
    });
   
  }

  func(): Boolean{
    // console.log("valid" +this.addDoctorForm.valid);
    // // console.log("success"+this.addDoctorForm.get('operationsSuccess').errors.required);    
    // console.log(this.addDoctorForm.get("operationsSuccess").value > this.addDoctorForm.get("noOfOperationsTaken").value);
  return this.addDoctorForm.get("operationsSuccess").value > this.addDoctorForm.get("noOfOperationsTaken").value;
  }

  onSubmit(): void {
    this.adminService.addDoctorData(this.addDoctorForm.value, this.specializationName).subscribe(data=> {
        console.log(data);
        this.router.navigate(['/doctor-edit/'+this.specializationName]);
    });
  }
}
