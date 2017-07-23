"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var Observable_1 = require("rxjs/Observable");
var service_locator_1 = require("./../locator/service.locator");
var http_service_1 = require("./../http/http.service");
var core_1 = require("@angular/core");
/**
 * Serviço que gerencia o token do usuario
 */
var TOKEN = 'token';
var DATE_TOKEN = 'datetoken';
var TokenService = (function () {
    function TokenService() {
    }
    Object.defineProperty(TokenService.prototype, "httpService", {
        get: function () {
            return service_locator_1.ServiceLocator.get(http_service_1.HttpService);
        },
        enumerable: true,
        configurable: true
    });
    /**
     * Atribui o token atual do usuario e a data.
     */
    TokenService.prototype.setToken = function (token, date) {
        localStorage.setItem(TOKEN, JSON.stringify(token));
        localStorage.setItem(DATE_TOKEN, JSON.stringify(date));
    };
    /**
     * Retorna o token to usuário
     */
    TokenService.prototype.resetToken = function () {
        localStorage.removeItem(DATE_TOKEN);
        localStorage.removeItem(TOKEN);
    };
    /**
     * Retorna o token to usuário
     */
    TokenService.prototype.isTokenValid = function () {
        var tokenDTO = JSON.parse(localStorage.getItem(TOKEN));
        var value = !!tokenDTO ? tokenDTO.token : null;
        if (!!value) {
            console.log("Valiando: " + value);
            return this.httpService.put('/auth', { data: new ValidToken(value) });
        }
        return Observable_1.Observable.create(function () { return false; });
    };
    /**
     * Retorna o token to usuário
     */
    TokenService.prototype.getToken = function () {
        return JSON.parse(localStorage.getItem(TOKEN));
    };
    /**
     * Retorna a data que o token foi gerado.
     */
    TokenService.prototype.getDate = function () {
        return JSON.parse(localStorage.getItem(DATE_TOKEN));
    };
    return TokenService;
}());
TokenService = __decorate([
    core_1.Injectable()
], TokenService);
exports.TokenService = TokenService;
var ValidToken = (function () {
    function ValidToken(token) {
        this.token = token;
    }
    return ValidToken;
}());
//# sourceMappingURL=token.service.js.map