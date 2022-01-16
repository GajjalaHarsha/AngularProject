import { Component, OnInit } from '@angular/core';
import { EmailValidator, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
// import { Console } from 'console';
import { UservalidationService } from '../service/uservalidation.service';
// interface Alert {
//   type: string;
//   message: string;
// }
// const ALERTS: Alert[] = [{
//   type: 'warning',
//   message: 'This is a warning alert',
// }
// ]
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  // alerts: Alert[];
  loginForm: FormGroup;
  constructor(private userValidation: UservalidationService, private router: Router) { }

  ngOnInit(): void {
    this.loginForm=new FormGroup({
      password: new FormControl(null, Validators.required), 
      name: new FormControl(null, Validators.required), //Note we can add more than one validator, if we have email we can add validator for the email.
      email: new FormControl(null, Validators.required)
    });
  }
  // close(alert: Alert) {
  //   this.alerts.splice(this.alerts.indexOf(alert), 1);
  // }
  message:String;
  isValid:boolean=true;
  onSubmit(): void {
    console.log(this.loginForm.value);
    this.userValidation.getUser(this.loginForm.value).subscribe(data=>{
        if(!Boolean(data)) {
          this.isValid=false;
          this.message="Invalid Credentials";
          this.loginForm.reset();
        }
        else {
          this.message="";
          this.isValid=true;
          localStorage.setItem("userId", data.id.toString());
          localStorage.setItem("userName", data.name);
          console.log(data);
          this.router.navigate(['/specialization']);
        }
    });

  }
  
}
