/**
 * Router da aplicação.
 */
import { RouterModule } from '@angular/router';

export const routes = [
  {path: '', redirectTo: 'usercity', pathMatch: 'full'},
  {path: 'usercity', loadChildren: 'app/usercity/usercity.module#UserCityModule' },
  {path: 'login', loadChildren: 'app/login/login.module#LoginModule' },
  {path: 'users', loadChildren: 'app/users/users.module#UsersModule' },
  {path: '**', redirectTo: 'tasks', pathMatch: 'full'}
];

export const routing = RouterModule.forRoot(routes, { useHash: true });
