import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class GuardcomponentuserService implements CanActivate {

  constructor(private router:Router) { }

  canActivate(route: ActivatedRouteSnapshot,state: RouterStateSnapshot): boolean {
    const isAdminTrue=!!localStorage.getItem("adminName");
    const isUserTrue=!!localStorage.getItem("userId");
    if(isAdminTrue){
      this.router.navigate(['/adminselectspecialization']);
      return isAdminTrue;
     }
    if(!isUserTrue) {
      this.router.navigate(['']);
      return isUserTrue;
    }
   return isUserTrue;
  }
}
