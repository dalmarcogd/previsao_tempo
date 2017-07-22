"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
/**
 * Router da aplicação.
 */
var router_1 = require("@angular/router");
exports.routes = [
    { path: '', redirectTo: 'tasks', pathMatch: 'full' },
    { path: 'tasks', loadChildren: 'app/tasks/tasks.module#TasksModule' },
    { path: 'login', loadChildren: 'app/login/login.module#LoginModule' },
    { path: 'users', loadChildren: 'app/users/users.module#UsersModule' },
    { path: '**', redirectTo: 'tasks', pathMatch: 'full' }
];
exports.routing = router_1.RouterModule.forRoot(exports.routes, { useHash: true });
//# sourceMappingURL=app.routing.js.map