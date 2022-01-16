import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Doctors } from '../model/doctors';
import { TransferwithsiblingService } from '../service/transferwithsibling.service';

@Component({
  selector: 'app-doctorview',
  templateUrl: './doctorview.component.html',
  styleUrls: ['./doctorview.component.css']
})
export class DoctorviewComponent implements OnInit {

  doctorData: Doctors;
  constructor(private classData: TransferwithsiblingService, private router: Router) { }

  ngOnInit(): void {
    this.classData.On<Doctors>().subscribe(data=> {
      this.doctorData=data;
      console.log(this.doctorData);
    });
  }

  bookAppointment(doctorData: Doctors): void {
    console.log(doctorData.doctorId);
    this.router.navigate(['/bookappointment'])
    this.classData.emit<Doctors>(this.doctorData);
  }
}
