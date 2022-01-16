import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomepageComponent } from './homepage/homepage.component';
import { FirstpageComponent } from './firstpage/firstpage.component';
import { SpecializationsComponent } from './specializations/specializations.component';
import { DepartmentdoctorsComponent } from './departmentdoctors/departmentdoctors.component';
import { DoctorviewComponent } from './doctorview/doctorview.component';
import { LogoutComponent } from './logout/logout.component';
import { AppointmentbookingComponent } from './appointmentbooking/appointmentbooking.component';
import { AppointmentstatusComponent } from './appointmentstatus/appointmentstatus.component';
import { BookedappointmentsComponent } from './bookedappointments/bookedappointments.component';
import { AdminloginComponent } from './adminlogin/adminlogin.component';
import { AdminselectspecializationComponent } from './adminselectspecialization/adminselectspecialization.component';
import { AdmindoctorseditComponent } from './admindoctorsedit/admindoctorsedit.component';
import { AdminupdatedoctorComponent } from './adminupdatedoctor/adminupdatedoctor.component';
import { AdminadddoctorComponent } from './adminadddoctor/adminadddoctor.component';
import { AdminlogoutComponent } from './adminlogout/adminlogout.component';
import { AdminheaderComponent } from './adminheader/adminheader.component';
import { UserheaderComponent } from './userheader/userheader.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomepageComponent,
    FirstpageComponent,
    SpecializationsComponent,
    DepartmentdoctorsComponent,
    DoctorviewComponent,
    LogoutComponent,
    AppointmentbookingComponent,
    AppointmentstatusComponent,
    BookedappointmentsComponent,
    AdminloginComponent,
    AdminselectspecializationComponent,
    AdmindoctorseditComponent,
    AdminupdatedoctorComponent,
    AdminadddoctorComponent,
    AdminlogoutComponent,
    AdminheaderComponent,
    UserheaderComponent,
    FooterComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
