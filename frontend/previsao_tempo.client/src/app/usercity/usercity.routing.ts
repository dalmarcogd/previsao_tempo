import { AuthGuard } from './../service/auth/auth.guard';
/**
 * Router do login.
 */
import { Routes, RouterModule }  from '@angular/router';
import {UserCityComponent} from './usercity.component';

const routes: Routes = [
  {
    path: '',
    component: UserCityComponent, 
    canActivate: [AuthGuard]
  }
];

export const routing = RouterModule.forChild(routes);
