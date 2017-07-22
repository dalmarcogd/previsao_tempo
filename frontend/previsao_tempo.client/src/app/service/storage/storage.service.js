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
var message_service_1 = require("../message/message.service");
var StorageService = (function () {
    function StorageService(messageService) {
        this.messageService = messageService;
    }
    StorageService.prototype.put = function (key, data) {
        var jsonData;
        try {
            jsonData = JSON.stringify(data);
        }
        catch (e) {
            this.messageService.dispatchMessage(e);
        }
        localStorage.setItem(key, JSON.stringify(data));
    };
    StorageService.prototype.get = function (key) {
        var data = localStorage.getItem(key);
        if (!!data) {
            return JSON.parse(data);
        }
        return null;
    };
    return StorageService;
}());
StorageService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [message_service_1.MessageService])
], StorageService);
exports.StorageService = StorageService;
//# sourceMappingURL=storage.service.js.map