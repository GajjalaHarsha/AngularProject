import { Component, OnInit } from '@angular/core';
import { TransferwithsiblingService } from '../service/transferwithsibling.service';

@Component({
  selector: 'app-appointmentstatus',
  templateUrl: './appointmentstatus.component.html',
  styleUrls: ['./appointmentstatus.component.css']
})
export class AppointmentstatusComponent implements OnInit {

  isBooked: Boolean;
  constructor(private classData: TransferwithsiblingService) { }

  ngOnInit(): void {
    this.classData.On<Boolean>().subscribe(data=> {
      this.isBooked=data;
    });
  }

}
