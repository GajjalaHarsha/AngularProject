import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Doctors } from '../model/doctors';
import { Specialization } from '../model/specialization';
import { DatatransferwithsiblingsService } from '../service/datatransferwithsiblings.service';
import { DoctorService } from '../service/doctor.service';
import { SpecializationserviceService } from '../service/specializationservice.service';

@Component({
  selector: 'app-specializations',
  templateUrl: './specializations.component.html',
  styleUrls: ['./specializations.component.css']
})
export class SpecializationsComponent implements OnInit {

  constructor(private router: Router, private specializationService: SpecializationserviceService,private datatTransferSibling: DatatransferwithsiblingsService, private doctorService: DoctorService) { }

  spec: Specialization[];
  doctors: Doctors[];

  ngOnInit(): void {
    this.specializationService.getAllSpecializations().subscribe(data=>{
      console.log(data);
      this.spec=data;
      console.log(this.spec);
    })
  }

  getDoctors(spec: Specialization): void {
    console.log(spec.splId+" "+spec.splName);
    this.doctorService.getAllDoctorsInSpecialization(spec).subscribe(data=>{
      console.log(data);
      this.doctors=data;
      this.datatTransferSibling.emit<Doctors>(this.doctors);
      console.log(this.doctors);
      this.router.navigate(['/doctorsinspecialization'])
    })
    
    
  }
}
