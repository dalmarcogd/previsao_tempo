"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var shared_module_1 = require("./../shared/shared.module");
var tasks_routing_1 = require("./tasks.routing");
var core_1 = require("@angular/core");
var tasks_component_1 = require("./tasks.component");
var ng2_smart_table_1 = require("ng2-smart-table");
var TasksModule = (function () {
    function TasksModule() {
    }
    return TasksModule;
}());
TasksModule = __decorate([
    core_1.NgModule({
        imports: [
            tasks_routing_1.routing,
            ng2_smart_table_1.Ng2SmartTableModule,
            shared_module_1.SharedModule
        ],
        declarations: [
            tasks_component_1.TasksComponent
        ],
        exports: [
            tasks_component_1.TasksComponent,
            ng2_smart_table_1.Ng2SmartTableModule
        ],
        bootstrap: [
            tasks_component_1.TasksComponent
        ],
        schemas: [
            core_1.CUSTOM_ELEMENTS_SCHEMA
        ],
        providers: []
    })
], TasksModule);
exports.TasksModule = TasksModule;
//# sourceMappingURL=tasks.module.js.map