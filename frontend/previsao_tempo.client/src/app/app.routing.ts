/**
 * Router da aplicação.
 */
import { RouterModule } from '@angular/router';

export const routes = [
  {path: '', redirectTo: 'tasks', pathMatch: 'full'},
  {path: 'tasks', loadChildren: 'app/tasks/tasks.module#TasksModule' },
  {path: 'login', loadChildren: 'app/login/login.module#LoginModule' },
  {path: 'users', loadChildren: 'app/users/users.module#UsersModule' },
  {path: '**', redirectTo: 'tasks', pathMatch: 'full'}
];

export const routing = RouterModule.forRoot(routes, { useHash: true });
