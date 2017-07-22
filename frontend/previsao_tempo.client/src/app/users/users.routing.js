"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
/**
 * Router do login.
 */
var router_1 = require("@angular/router");
var users_component_1 = require("./users.component");
var routes = [
    {
        path: '',
        component: users_component_1.UsersComponent
    }
];
exports.routing = router_1.RouterModule.forChild(routes);
//# sourceMappingURL=users.routing.js.map