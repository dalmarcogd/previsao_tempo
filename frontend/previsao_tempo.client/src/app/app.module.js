"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var ng_lightning_1 = require("ng-lightning");
var ng2_smart_table_1 = require("ng2-smart-table");
var tasks_service_1 = require("./tasks/tasks.service");
var token_service_1 = require("./service/token/token.service");
var message_service_1 = require("./service/message/message.service");
var application_error_handler_1 = require("./service/error/application.error.handler");
var http_service_1 = require("./service/http/http.service");
var storage_service_1 = require("./service/storage/storage.service");
var auth_service_1 = require("./service/auth/auth.service");
var app_routing_1 = require("./app.routing");
var service_locator_1 = require("./service/locator/service.locator");
var core_1 = require("@angular/core");
var platform_browser_1 = require("@angular/platform-browser");
var core_2 = require("@angular/core");
var http_1 = require("@angular/http");
var app_component_1 = require("./app.component");
var AppModule = (function () {
    function AppModule(injector) {
        service_locator_1.ServiceLocator.setInjector(injector);
    }
    return AppModule;
}());
AppModule = __decorate([
    core_2.NgModule({
        declarations: [
            app_component_1.AppComponent
        ],
        imports: [
            platform_browser_1.BrowserModule,
            app_routing_1.routing,
            http_1.HttpModule,
            ng2_smart_table_1.Ng2SmartTableModule,
            ng_lightning_1.NglModule.forRoot()
        ],
        providers: [auth_service_1.AuthService,
            storage_service_1.StorageService,
            http_service_1.HttpService,
            application_error_handler_1.ApplicationErrorHandler,
            message_service_1.MessageService,
            token_service_1.TokenService,
            tasks_service_1.TasksService],
        bootstrap: [app_component_1.AppComponent]
    }),
    __metadata("design:paramtypes", [core_1.Injector])
], AppModule);
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map