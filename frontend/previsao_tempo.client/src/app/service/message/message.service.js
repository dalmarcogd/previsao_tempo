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
var core_1 = require("@angular/core");
var message_1 = require("./message");
var http_service_1 = require("../http/http.service");
var MessageService = (function () {
    function MessageService(httpService) {
        this.httpService = httpService;
    }
    MessageService.prototype.dispatchMessage = function (message) {
        if (message instanceof message_1.Message) {
            console.log(message.getMessage());
        }
        else {
            console.log("Parameto não reconhecido em MessageService#dispatchMessage:" + message);
        }
    };
    MessageService.prototype.dispatchString = function (message) {
        this.dispatchMessage(new message_1.Message(message));
    };
    MessageService.prototype.dispatchAny = function (message) {
        this.dispatchMessage(new message_1.Message(message));
    };
    return MessageService;
}());
MessageService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_service_1.HttpService])
], MessageService);
exports.MessageService = MessageService;
//# sourceMappingURL=message.service.js.map