import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UservalidationService {

  baseUrl='http://localhost:8000/user';
  constructor(private httpClient: HttpClient) { }

  insertUser(user: User): Observable<User>  {
    return this.httpClient.post<User>(this.baseUrl + '/register', user);
  }

  getUser(user: User): Observable<User> {
    return this.httpClient.post<User>(this.baseUrl + '/login', user);
  }
}
