import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Doctors } from '../model/doctors';
import { Specialization } from '../model/specialization';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {


  baseUrl='http://localhost:8000/doctor';
  constructor(private htttpClient: HttpClient) { }

  getAllDoctorsInSpecialization(specialization: Specialization): Observable<Doctors[]>  {
    return this.htttpClient.get<Doctors[]>(this.baseUrl + '/get'+ '/'+ specialization.splName);
  }

  viewDoctorsData(id: number): Observable<Doctors> {
    return this.htttpClient.get<Doctors>(this.baseUrl+'/singledoctor'+'/'+id);
  }
}
