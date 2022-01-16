
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Doctors } from '../model/doctors';
import { AdminService } from '../service/admin.service';
import { DatatransferwithsiblingsService } from '../service/datatransferwithsiblings.service';

@Component({
  selector: 'app-admindoctorsedit',
  templateUrl: './admindoctorsedit.component.html',
  styleUrls: ['./admindoctorsedit.component.css']
})
export class AdmindoctorseditComponent implements OnInit {

  constructor(private router: Router, private activatedRouter: ActivatedRoute, private adminService: AdminService) { }

  specializationName: string;
  doctors: Doctors[];
  
  ngOnInit(): void {
    this.activatedRouter.paramMap.subscribe(params=> {
      this.specializationName=params.get("specialization")
    });
    this.adminService.getAllDoctorsInSpecialization(this.specializationName).subscribe(data=> {

      console.log(data);
      this.doctors=data;
      console.log(this.doctors);
      
    });

  }
  deletedoctor(doctor: Doctors): void {
    this.adminService.deleteDoctor(doctor.doctorId).subscribe(data=> {
      console.log(data);
    });
    this.doctors=this.doctors.filter(e=>e !==doctor)
  }
  updatedoctor(doctor: Doctors): void {
    this.router.navigate(['/doctor-update/'+this.specializationName+'/'+doctor.doctorId])
  }

  adddoctor(): void {
    this.router.navigate(['/add-doctor/'+this.specializationName]);
  }

}
