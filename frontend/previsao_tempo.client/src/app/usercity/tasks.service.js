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
var TasksService = (function () {
    function TasksService() {
    }
    Object.defineProperty(TasksService.prototype, "httpService", {
        get: function () {
            return service_locator_1.ServiceLocator.get(http_service_1.HttpService);
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(TasksService.prototype, "tokenService", {
        get: function () {
            return service_locator_1.ServiceLocator.get(token_service_1.TokenService);
        },
        enumerable: true,
        configurable: true
    });
    /**
     * Listar todas as tarefas.
     */
    TasksService.prototype.list = function () {
        return this.httpService.get('/tasks', null, new Map([["username", this.tokenService.getToken().username]]));
    };
    /**
     * Deletar uma tarefa.
     */
    TasksService.prototype.delete = function (task) {
        return this.httpService.delete('/tasks', task);
    };
    /**
     * Salvar uma tarefa.
     */
    TasksService.prototype.save = function (task) {
        return this.httpService.post('/tasks', task);
    };
    return TasksService;
}());
TasksService = __decorate([
    core_1.Injectable()
], TasksService);
exports.TasksService = TasksService;
//# sourceMappingURL=tasks.service.js.map