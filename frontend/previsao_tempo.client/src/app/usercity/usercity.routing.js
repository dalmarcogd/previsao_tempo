"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var auth_guard_1 = require("./../service/auth/auth.guard");
/**
 * Router do login.
 */
var router_1 = require("@angular/router");
var usercity_component_1 = require("./usercity.component");
var routes = [
    {
        path: '',
        component: usercity_component_1.UserCityComponent,
        canActivate: [auth_guard_1.AuthGuard]
    }
];
exports.routing = router_1.RouterModule.forChild(routes);
//# sourceMappingURL=usercity.routing.js.map