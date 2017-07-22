"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var application_error_1 = require("./application.error");
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var ApplicationErrorHandler = (function () {
    function ApplicationErrorHandler() {
    }
    ApplicationErrorHandler.prototype.handleError = function (error) {
        var errMsg;
        if (error instanceof http_1.Response) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return new application_error_1.ApplicationError(error.code, error.message, 'Ajuda', error.status);
    };
    return ApplicationErrorHandler;
}());
ApplicationErrorHandler = __decorate([
    core_1.Injectable()
], ApplicationErrorHandler);
exports.ApplicationErrorHandler = ApplicationErrorHandler;
//# sourceMappingURL=application.error.handler.js.map