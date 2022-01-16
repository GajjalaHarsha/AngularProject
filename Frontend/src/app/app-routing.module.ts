import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminadddoctorComponent } from './adminadddoctor/adminadddoctor.component';
import { AdmindoctorseditComponent } from './admindoctorsedit/admindoctorsedit.component';
import { AdminloginComponent } from './adminlogin/adminlogin.component';
import { AdminlogoutComponent } from './adminlogout/adminlogout.component';
import { AdminselectspecializationComponent } from './adminselectspecialization/adminselectspecialization.component';
import { AdminupdatedoctorComponent } from './adminupdatedoctor/adminupdatedoctor.component';
import { AppointmentbookingComponent } from './appointmentbooking/appointmentbooking.component';
import { AppointmentstatusComponent } from './appointmentstatus/appointmentstatus.component';
import { BookedappointmentsComponent } from './bookedappointments/bookedappointments.component';
import { DepartmentdoctorsComponent } from './departmentdoctors/departmentdoctors.component';
import { DoctorviewComponent } from './doctorview/doctorview.component';
import { FirstpageComponent } from './firstpage/firstpage.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { RegisterComponent } from './register/register.component';
import { GuardcomponentadminService } from './service/guardcomponentadmin.service';
import { GuardcomponentuserService } from './service/guardcomponentuser.service';
import { SpecializationsComponent } from './specializations/specializations.component';

const routes: Routes = [
  {'path':'register', component:RegisterComponent},
  {'path':'login', component:LoginComponent},
  {'path':'', component:HomepageComponent},
  {'path':'firstpage', component:FirstpageComponent},
  {'path':'specialization', component:SpecializationsComponent, canActivate:[GuardcomponentuserService]},
  {'path': 'doctorsinspecialization', component: DepartmentdoctorsComponent, canActivate:[GuardcomponentuserService]},
  {'path': 'viewdoctordata', component: DoctorviewComponent, canActivate:[GuardcomponentuserService]},
  {'path': 'logout', component: LogoutComponent},
  {'path': 'bookappointment', component: AppointmentbookingComponent, canActivate:[GuardcomponentuserService]},
  {'path': 'appointmentStatus', component: AppointmentstatusComponent, canActivate:[GuardcomponentuserService]},
  {'path': 'bookedappointments', component: BookedappointmentsComponent, canActivate:[GuardcomponentuserService]},
  {'path': 'adminlogin', component: AdminloginComponent},
  {'path': 'adminselectspecialization', component: AdminselectspecializationComponent, canActivate: [GuardcomponentadminService]},
  {'path': 'doctor-edit/:specialization', component: AdmindoctorseditComponent, canActivate: [GuardcomponentadminService]},
  {'path': 'doctor-update/:specialization/:id', component: AdminupdatedoctorComponent, canActivate: [GuardcomponentadminService]},
  {'path': 'add-doctor/:specialization', component: AdminadddoctorComponent, canActivate: [GuardcomponentadminService]},
  {'path': 'adminlogout', component: AdminlogoutComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
