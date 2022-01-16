import { isGeneratedFile } from '@angular/compiler/src/aot/util';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Doctors } from '../model/doctors';
import { AppointmentService } from '../service/appointment.service';
import { TransferwithsiblingService } from '../service/transferwithsibling.service';

@Component({
  selector: 'app-appointmentbooking',
  templateUrl: './appointmentbooking.component.html',
  styleUrls: ['./appointmentbooking.component.css']
})
export class AppointmentbookingComponent implements OnInit {

  constructor(private router: Router, private classData: TransferwithsiblingService, private appointmentBooking: AppointmentService) { }

  doctor: Doctors;
  appointmentForm: FormGroup;
  userId: number;
  ngOnInit(): void {
    
    this.appointmentForm=new FormGroup({
      appointmentDate: new FormControl(null, Validators.required),
      appointmentTime: new FormControl(null, Validators.required), 
    });
  }
  onSubmit(): void {

   
  
    this.classData.On<Doctors>().subscribe(data=> {
      this.doctor=data;
      console.log(this.doctor);
    });

    this.userId=+localStorage.getItem("userId")
    console.log(Boolean(this.doctor));
    console.log(this.userId);
    console.log(this.appointmentForm.value);
    if(Boolean(this.userId)===false) {
      this.router.navigate(['']);
    }
   
    
    if(Boolean(this.doctor)===false) {
      this.router.navigate(['/specialization'])
    }

    else {
     
      this.appointmentBooking.BookAppointment(this.appointmentForm.value, this.doctor.doctorId, this.userId).subscribe(data=> {

        this.classData.emit<Boolean>(Boolean(data));
        this.router.navigate(['/appointmentStatus']);
        if(Boolean(data)) {
          console.log("Appointment Booked Successfully");
        }
      });
    }
  }

}
