import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Adminselectedspecilaization } from '../model/adminselectedspecilaization';
import { Doctors } from '../model/doctors';
import { Specialization } from '../model/specialization';
import { AdminService } from '../service/admin.service';
import { DatatransferwithsiblingsService } from '../service/datatransferwithsiblings.service';
import { DoctorService } from '../service/doctor.service';
import { SpecializationserviceService } from '../service/specializationservice.service';

@Component({
  selector: 'app-adminselectspecialization',
  templateUrl: './adminselectspecialization.component.html',
  styleUrls: ['./adminselectspecialization.component.css']
})
export class AdminselectspecializationComponent implements OnInit {

  constructor(private specializationService: SpecializationserviceService, private dataTransfer: DatatransferwithsiblingsService, private router: Router, private adminService: AdminService) { }

  specializationForm: FormGroup;
  specializations: Specialization[];
  admin:Adminselectedspecilaization;
  ngOnInit(): void {
    this.specializationService.getAllSpecializations().subscribe(data=> {
        console.log(data);
        this.specializations=data;
        console.log(this.specializations);
    });
   
    this.specializationForm=new FormGroup({
      specialization: new FormControl(null, Validators.required), 
      //Note we can add more than one validator, if we have email we can add validator for the email.
    });
  }

  onSubmit(): void {
  
    this.admin=this.specializationForm.value;
    this.router.navigate(['/doctor-edit/'+this.admin.specialization])
   
  }
 
  
}
