"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var shared_module_1 = require("./../shared/shared.module");
var usercity_routing_1 = require("./usercity.routing");
var core_1 = require("@angular/core");
var usercity_component_1 = require("./usercity.component");
var ng2_smart_table_1 = require("ng2-smart-table");
var UserCityModule = (function () {
    function UserCityModule() {
    }
    return UserCityModule;
}());
UserCityModule = __decorate([
    core_1.NgModule({
        imports: [
            usercity_routing_1.routing,
            ng2_smart_table_1.Ng2SmartTableModule,
            shared_module_1.SharedModule
        ],
        declarations: [
            usercity_component_1.UserCityComponent,
            usercity_component_1.ButtonViewComponent
        ],
        entryComponents: [usercity_component_1.ButtonViewComponent],
        exports: [
            usercity_component_1.UserCityComponent,
            ng2_smart_table_1.Ng2SmartTableModule,
            usercity_component_1.ButtonViewComponent
        ],
        bootstrap: [
            usercity_component_1.UserCityComponent
        ],
        schemas: [
            core_1.CUSTOM_ELEMENTS_SCHEMA
        ],
        providers: []
    })
], UserCityModule);
exports.UserCityModule = UserCityModule;
//# sourceMappingURL=usercity.module.js.map