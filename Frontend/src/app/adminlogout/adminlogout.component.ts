import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adminlogout',
  templateUrl: './adminlogout.component.html',
  styleUrls: ['./adminlogout.component.css']
})
export class AdminlogoutComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
    localStorage.removeItem("adminName");
    this.router.navigate(['']);
  }

}
