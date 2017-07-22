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
var service_locator_1 = require("./../locator/service.locator");
/**
 * Created by Guilherme on 06/04/2017.
 */
var core_1 = require("@angular/core");
require("rxjs/add/observable/of");
require("rxjs/add/operator/do");
require("rxjs/add/operator/delay");
var token_service_1 = require("../token/token.service");
var http_service_1 = require("../http/http.service");
var auth_credentials_1 = require("./auth.credentials");
var router_1 = require("@angular/router");
var AuthService = (function () {
    function AuthService(router) {
        this.router = router;
    }
    Object.defineProperty(AuthService.prototype, "httpService", {
        get: function () {
            return service_locator_1.ServiceLocator.get(http_service_1.HttpService);
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(AuthService.prototype, "tokenService", {
        get: function () {
            return service_locator_1.ServiceLocator.get(token_service_1.TokenService);
        },
        enumerable: true,
        configurable: true
    });
    /**
     * Efetua a autenticação no servidor, com os parametros recebidos.
     */
    AuthService.prototype.login = function (username, password) {
        var _this = this;
        return this.httpService.post('/auth', new auth_credentials_1.AuthCredentials(username, password)).then(function (data) {
            console.log(data);
            if (data instanceof Object) {
                _this.tokenService.setToken(data, new Date());
                _this.router.navigate(['/tasks']);
                return true;
            }
            else {
                _this.tokenService.resetToken();
                _this.router.navigate(['/login']);
                return false;
            }
        });
    };
    AuthService.prototype.isLoggedIn = function () {
        return this.tokenService.isTokenValid();
    };
    AuthService.prototype.logout = function () {
        this.tokenService.resetToken();
        this.router.navigate(['/login']);
    };
    return AuthService;
}());
AuthService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [router_1.Router])
], AuthService);
exports.AuthService = AuthService;
//# sourceMappingURL=auth.service.js.map