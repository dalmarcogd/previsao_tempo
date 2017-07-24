"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var Observable_1 = require("rxjs/Observable");
var token_service_1 = require("./../token/token.service");
var service_locator_1 = require("./../locator/service.locator");
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var http_headers_1 = require("./http.headers");
require("rxjs/add/operator/toPromise");
require("rxjs/add/operator/catch");
require("rxjs/add/observable/throw");
var application_error_handler_1 = require("../error/application.error.handler");
var http_constants_1 = require("./http.constants");
/**
 * Serviço que implementa métodos de comunicação Http.
 */
var HttpService = (function () {
    function HttpService() {
    }
    Object.defineProperty(HttpService.prototype, "tokenService", {
        get: function () {
            return service_locator_1.ServiceLocator.get(token_service_1.TokenService);
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(HttpService.prototype, "applicationErrorHandler", {
        get: function () {
            return service_locator_1.ServiceLocator.get(application_error_handler_1.ApplicationErrorHandler);
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(HttpService.prototype, "http", {
        get: function () {
            return service_locator_1.ServiceLocator.get(http_1.Http);
        },
        enumerable: true,
        configurable: true
    });
    /**
     * Realiza um put no endereço especificado.
     */
    HttpService.prototype.delete = function (url, config) {
        var _this = this;
        var headers = this.getConfigHeaders();
        var finalURL = this.formatURL(url);
        console.log('Method delete: ' + finalURL);
        var options = this.buildRequestOptions(headers, config);
        return this.http.delete(http_constants_1.URL_SERVER + '' + url, options).map(this.extractData).catch(function (error) { return _this.handleError(error); });
    };
    /**
     * Realiza um put no endereço especificado.
     */
    HttpService.prototype.getOut = function (url, config) {
        var _this = this;
        var headers = new http_1.Headers();
        headers.delete("Content-Type");
        headers.delete("Accept");
        console.log('Method get: ' + url);
        var options = this.buildRequestOptions(headers, config);
        return this.http.get(url, options).map(this.extractData).catch(function (error) { return _this.handleError(error); });
    };
    /**
     * Realiza um put no endereço especificado.
     */
    HttpService.prototype.get = function (url, config) {
        var _this = this;
        var headers = this.getConfigHeaders();
        var finalURL = this.formatURL(url);
        console.log('Method get: ' + finalURL);
        var options = this.buildRequestOptions(headers, config);
        return this.http.get(http_constants_1.URL_SERVER + '' + url, options).map(this.extractData).catch(function (error) { return _this.handleError(error); });
    };
    /**
     * Realiza um put no endereço especificado.
     */
    HttpService.prototype.put = function (url, config) {
        var _this = this;
        var headers = this.getConfigHeaders();
        var finalURL = this.formatURL(url);
        console.log('Method put: ' + finalURL);
        var options = this.buildRequestOptions(headers, config);
        return this.http.put(http_constants_1.URL_SERVER + '' + url, { name: name }, options).map(this.extractData).catch(function (error) { return _this.handleError(error); });
    };
    /**
     * Realiza um post no endereço especificado.
     */
    HttpService.prototype.post = function (url, config) {
        var _this = this;
        var headers = this.getConfigHeaders();
        var finalURL = this.formatURL(url);
        console.log('Method post: ' + finalURL);
        var options = this.buildRequestOptions(headers, config);
        return this.http.post(finalURL, { name: name }, options).map(this.extractData).catch(function (error) { return _this.handleError(error); });
    };
    /**
     * Método para criação padronizada do RequestOptions
     */
    HttpService.prototype.buildRequestOptions = function (headers, config) {
        var options = new http_1.RequestOptions({ headers: headers, body: !!config && !!config.data ? JSON.stringify(config.data) : null, params: new URLSearchParams() });
        if (!!config && !!config.params) {
            config.params.forEach(function (value, key) {
                options.params.set(key, value);
            });
        }
        return options;
    };
    HttpService.prototype.getConfigHeaders = function () {
        var headers = new http_headers_1.HttpHeaders();
        var token = this.tokenService.getToken();
        if (!!token) {
            console.log('Token: ' + token.token);
            headers.append('Authorization', token.token);
        }
        return headers;
    };
    HttpService.prototype.formatURL = function (url) {
        return http_constants_1.URL_SERVER + url;
    };
    HttpService.prototype.extractData = function (res) {
        return res.json();
    };
    HttpService.prototype.handleError = function (error) {
        service_locator_1.ServiceLocator.get(application_error_handler_1.ApplicationErrorHandler).handleError(error);
        return Observable_1.Observable.throw(error);
    };
    return HttpService;
}());
HttpService = __decorate([
    core_1.Injectable()
], HttpService);
exports.HttpService = HttpService;
//# sourceMappingURL=http.service.js.map