import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admin } from '../model/admin';
import { Adminselectedspecilaization } from '../model/adminselectedspecilaization';
import { Doctors } from '../model/doctors';
import { Specialization } from '../model/specialization';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private httpClient: HttpClient) { }


  baseUrl='http://localhost:8000/';
  isValidAdmin(admin: Admin): Observable<Admin> {
    return this.httpClient.post<Admin>(this.baseUrl+'admin/login', admin);
  }

  getAllDoctorsInSpecialization(specializations: string): Observable<Doctors[]>  {
    return this.httpClient.get<Doctors[]>(this.baseUrl + 'doctor/get'+ '/'+ specializations);
  }

  deleteDoctor(id: number) {
    return this.httpClient.delete<Doctors>(this.baseUrl+"doctor/delete/"+id);
  }

  updateDoctor(doctor: Doctors): Observable<Doctors> {
    return this.httpClient.put<Doctors>(this.baseUrl+ '/doctor/update/'+ doctor.doctorId, doctor);
  }

  viewDoctorsData(id: number): Observable<Doctors> {
    return this.httpClient.get<Doctors>(this.baseUrl+'doctor/singledoctor'+'/'+id);
  }

  addDoctorData(doctor: Doctors, specialization: String): Observable<Doctors> {
    return this.httpClient.post<Doctors>(this.baseUrl+'/doctor/add/'+specialization, doctor);
  }
}
