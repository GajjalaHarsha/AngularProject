import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DatatransferwithsiblingsService {

  public subject=new BehaviorSubject<any>('');
  constructor() { }

  emit<T>(data: T[]) {
    this.subject.next(data);
  }

  On<T>(): Observable<T[]> {
    return this.subject.asObservable();
  }

}
