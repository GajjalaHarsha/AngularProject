import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Appointments } from '../model/appointments';
// import { Console } from 'console';
import { AppointmentService } from '../service/appointment.service';

@Component({
  selector: 'app-bookedappointments',
  templateUrl: './bookedappointments.component.html',
  styleUrls: ['./bookedappointments.component.css']
})
export class BookedappointmentsComponent implements OnInit {

  constructor(private appointments: AppointmentService, private router: Router) { }

  app:Appointments[]
  userid: number;
  size: boolean;
  ngOnInit(): void {
    this.userid=+localStorage.getItem("userId");
    if(Boolean(this.userid)==false){
      this.router.navigate(['']);
    }
    else {
      this.appointments.GetAppointments(this.userid).subscribe(data=> {
        this.app=data;
        if(Boolean(data)==false || this.app.length==0){
          this.size=false;
        }
        else {
          this.size=true;
        }
      })
    }

  }

}
