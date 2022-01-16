import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Doctors } from '../model/doctors';
import { DatatransferwithsiblingsService } from '../service/datatransferwithsiblings.service';
import { DoctorService } from '../service/doctor.service';
import { TransferwithsiblingService } from '../service/transferwithsibling.service';

@Component({
  selector: 'app-departmentdoctors',
  templateUrl: './departmentdoctors.component.html',
  styleUrls: ['./departmentdoctors.component.css']
})
export class DepartmentdoctorsComponent implements OnInit {

  constructor(private router: Router, private transferClassData: TransferwithsiblingService, private dataTransferWithSiblings: DatatransferwithsiblingsService, private doctorService: DoctorService) { }

  doctorData: Doctors;
  spec: String;
  doctors: Doctors[];
  isPresent: Boolean;
  ngOnInit(): void {
    this.dataTransferWithSiblings.On<Doctors>().subscribe(data=> {
       this.doctors=data;
      //  this.doc=this.doctors.slice(1, 2);
      if(this.doctors.length>0) {
        // for (let index = 0; index < this.doctors.length; index++) {
        //   const element = this.doctors[index];
        //   this.spec=element.splName;
        //   this.isPresent=true;
        //   break;  
        // }
        this.spec=this.doctors[0].splName;

        this.isPresent=true;
      }
      else {
        this.isPresent=false;
      }
       console.log(this.doctors);
    });
  }
  viewEmployee(doctor: Doctors): void {

    console.log(doctor);
    this.doctorService.viewDoctorsData(doctor.doctorId).subscribe(data=> {
      this.doctorData=data;
      this.transferClassData.emit<Doctors>(this.doctorData);
      console.log(data);
      this.router.navigate(['/viewdoctordata']);
    });
  }

}
