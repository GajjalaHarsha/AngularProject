import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  homeImage: string="assets/images/homepage.png";
  constructor() { }

  ngOnInit(): void {
  }

}
