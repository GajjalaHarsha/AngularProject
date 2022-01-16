import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class GuardcomponentadminService implements CanActivate {

  constructor(private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot,state: RouterStateSnapshot):boolean {
    const isAdminTrue=!!localStorage.getItem("adminName");
    const isUserTrue=!!localStorage.getItem("userId");
    if(isUserTrue) {
      this.router.navigate(['/specialization']);
      return isUserTrue;
    }
    else if(!isAdminTrue){
     this.router.navigate(['']);
     return isAdminTrue;
    }
   return isAdminTrue;
  
  }
}
