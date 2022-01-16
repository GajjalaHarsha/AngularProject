import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Specialization } from '../model/specialization';

@Injectable({
  providedIn: 'root'
})
export class SpecializationserviceService {

  baseUrl='http://localhost:8000/specialization';
  constructor(private httpClient: HttpClient) { }

  getAllSpecializations(): Observable<Specialization[]>  {
    return this.httpClient.get<Specialization[]>(this.baseUrl + '/show');
  }
}
