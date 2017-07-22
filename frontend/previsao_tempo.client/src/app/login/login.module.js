"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var shared_module_1 = require("./../shared/shared.module");
var login_routing_1 = require("./login.routing");
var core_1 = require("@angular/core");
var login_component_1 = require("./login.component");
var LoginModule = (function () {
    function LoginModule() {
    }
    return LoginModule;
}());
LoginModule = __decorate([
    core_1.NgModule({
        imports: [
            login_routing_1.routing,
            shared_module_1.SharedModule
        ],
        declarations: [
            login_component_1.LoginComponent
        ],
        exports: [
            login_component_1.LoginComponent
        ],
        bootstrap: [
            login_component_1.LoginComponent
        ],
        schemas: [
            core_1.CUSTOM_ELEMENTS_SCHEMA
        ]
    })
], LoginModule);
exports.LoginModule = LoginModule;
//# sourceMappingURL=login.module.js.map