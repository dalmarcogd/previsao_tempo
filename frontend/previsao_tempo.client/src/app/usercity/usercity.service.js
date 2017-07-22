"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var token_service_1 = require("./../service/token/token.service");
var service_locator_1 = require("./../service/locator/service.locator");
var http_service_1 = require("./../service/http/http.service");
var core_1 = require("@angular/core");
var UserCityService = (function () {
    function UserCityService() {
    }
    Object.defineProperty(UserCityService.prototype, "httpService", {
        get: function () {
            return service_locator_1.ServiceLocator.get(http_service_1.HttpService);
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(UserCityService.prototype, "tokenService", {
        get: function () {
            return service_locator_1.ServiceLocator.get(token_service_1.TokenService);
        },
        enumerable: true,
        configurable: true
    });
    /**
     * Listar todas as tarefas.
     */
    UserCityService.prototype.list = function () {
        return this.httpService.get('/usercity');
    };
    /**
     * Listar todas as tarefas.
     */
    UserCityService.prototype.searchCities = function () {
        return this.httpService.get('/city');
    };
    /**
     * Deletar uma tarefa.
     */
    UserCityService.prototype.delete = function (userCity) {
        return this.httpService.delete('/usercity', userCity);
    };
    /**
     * Salvar uma tarefa.
     */
    UserCityService.prototype.save = function (userCity) {
        return this.httpService.post('/usercity', userCity);
    };
    return UserCityService;
}());
UserCityService = __decorate([
    core_1.Injectable()
], UserCityService);
exports.UserCityService = UserCityService;
//# sourceMappingURL=usercity.service.js.map