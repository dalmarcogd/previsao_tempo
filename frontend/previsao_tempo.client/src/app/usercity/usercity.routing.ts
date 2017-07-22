/**
 * Router do login.
 */
import { Routes, RouterModule }  from '@angular/router';
import {UserCityComponent} from './usercity.component';

const routes: Routes = [
  {
    path: '',
    component: UserCityComponent
  }
];

export const routing = RouterModule.forChild(routes);
