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
var usercity_dto_1 = require("./../model/usercity/usercity.dto");
var usercity_service_1 = require("./usercity.service");
var service_locator_1 = require("./../service/locator/service.locator");
var core_1 = require("@angular/core");
var ng2_smart_table_1 = require("ng2-smart-table");
var UserCityComponent = (function () {
    function UserCityComponent() {
        this.settings = {
            mode: 'external',
            add: {
                addButtonContent: 'Adicionar'
            },
            edit: {
                editButtonContent: 'Editar',
            },
            delete: {
                deleteButtonContent: 'Deletar',
                confirmDelete: true
            },
            columns: {
                name: {
                    title: "Cidade",
                    filter: true
                }
            }
        };
        this.opened = false;
        this.userCityCurrent = new usercity_dto_1.UserCityDTO();
    }
    UserCityComponent.prototype.userCityService = function () {
        return service_locator_1.ServiceLocator.get(usercity_service_1.UserCityService);
    };
    UserCityComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.userCityService().list().then(function (value) {
            if (value instanceof Array) {
                _this.source = new ng2_smart_table_1.LocalDataSource(value);
            }
        });
    };
    UserCityComponent.prototype.onCreate = function (event) {
        this.userCityCurrent = new usercity_dto_1.UserCityDTO();
        this.opened = true;
    };
    UserCityComponent.prototype.onEdit = function (event) {
        this.userCityCurrent = event.data;
        this.opened = true;
    };
    UserCityComponent.prototype.onCancel = function () {
        this.userCityCurrent = new usercity_dto_1.UserCityDTO();
        this.opened = false;
    };
    UserCityComponent.prototype.onSaveConfirm = function () {
        var _this = this;
        this.userCityService().save(this.userCityCurrent).then(function () { return _this.opened = false; });
    };
    UserCityComponent.prototype.onDeleteConfirm = function (event) {
        var _this = this;
        this.userCityService().delete(event.data).then(function () { return _this.opened = false; });
    };
    UserCityComponent.prototype.searchCities = function () {
        return this.userCityService().searchCities();
    };
    return UserCityComponent;
}());
UserCityComponent = __decorate([
    core_1.Component({
        selector: 'app-usercity',
        templateUrl: './usercity.component.html',
        styleUrls: ['./usercity.component.css']
    }),
    __metadata("design:paramtypes", [])
], UserCityComponent);
exports.UserCityComponent = UserCityComponent;
//# sourceMappingURL=usercity.component.js.map