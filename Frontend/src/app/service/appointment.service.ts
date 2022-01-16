import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Appointments } from '../model/appointments';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  baseUrl='http://localhost:8000/';
  constructor(private httpClient: HttpClient) { }

  BookAppointment(appointment: Appointments, doctorId: number, userId: number): Observable<Appointments>  {
    return this.httpClient.post<Appointments>(this.baseUrl + 'appointments/user'+'/'+userId+'/doctor'+'/'+doctorId, appointment);
  }

  GetAppointments( userId: number): Observable<Appointments[]>  {
    return this.httpClient.get<Appointments[]>(this.baseUrl + 'user'+'/appointments/'+userId);
  }
}
