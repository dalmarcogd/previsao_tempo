"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var usercity_dto_1 = require("./../model/usercity/usercity.dto");
var usercity_service_1 = require("./usercity.service");
var service_locator_1 = require("./../service/locator/service.locator");
var core_1 = require("@angular/core");
var ng2_smart_table_1 = require("ng2-smart-table");
var UserCityComponent = (function () {
    function UserCityComponent() {
        var _this = this;
        this.settings = {
            mode: 'external',
            actions: {
                columnTitle: 'Ações'
            },
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
                cityname: {
                    title: "Cidade",
                    filter: true
                }
            },
            pager: {
                display: true,
                perPage: 10
            }
        };
        this.opened = false;
        this.userCityCurrent = new usercity_dto_1.UserCityDTO();
        this.stateCurrent = null;
        this.searchCities = function (query) {
            if (!query) {
                return null;
            }
            return _this.userCityService().searchCities(query, !!_this.stateCurrent ? _this.stateCurrent.id : null);
        };
        this.searchStates = function (query) {
            if (!query) {
                return null;
            }
            return _this.userCityService().searchStates(query);
        };
    }
    UserCityComponent.prototype.userCityService = function () {
        return service_locator_1.ServiceLocator.get(usercity_service_1.UserCityService);
    };
    UserCityComponent.prototype.ngOnInit = function () {
        this.load();
    };
    UserCityComponent.prototype.load = function () {
        var _this = this;
        this.userCityService().list().subscribe(function (value) {
            _this.source = new ng2_smart_table_1.LocalDataSource();
            if (value instanceof Array) {
                value.forEach(function (value) {
                    var c = new usercity_dto_1.UserCityDTO();
                    c.username = value.username;
                    c.city = value.city;
                    c.cityname = value.city.name;
                    c.id = value.id;
                    c.version = value.version;
                    _this.source.add(c);
                });
            }
            else if (value instanceof usercity_dto_1.UserCityDTO) {
                var c = new usercity_dto_1.UserCityDTO();
                c.username = value.username;
                c.city = value.city;
                c.id = value.id;
                c.version = value.version;
                _this.source = new ng2_smart_table_1.LocalDataSource(new Array({ c: c }));
            }
        });
    };
    UserCityComponent.prototype.onCreate = function (event) {
        this.userCityCurrent = new usercity_dto_1.UserCityDTO();
        this.opened = true;
    };
    UserCityComponent.prototype.onEdit = function (event) {
        this.userCityCurrent = event.data;
        this.stateCurrent = this.userCityCurrent.city.state;
        this.opened = true;
    };
    UserCityComponent.prototype.onCancel = function () {
        this.userCityCurrent = new usercity_dto_1.UserCityDTO();
        this.opened = false;
    };
    UserCityComponent.prototype.onSaveConfirm = function () {
        var _this = this;
        this.userCityService().save(this.userCityCurrent).subscribe(function () { _this.opened = false; _this.load(); });
    };
    UserCityComponent.prototype.onDeleteConfirm = function (event) {
        var _this = this;
        this.userCityService().delete(event.data).subscribe(function () { _this.opened = false; _this.load(); });
    };
    return UserCityComponent;
}());
UserCityComponent = __decorate([
    core_1.Component({
        selector: 'app-usercity',
        templateUrl: './usercity.component.html',
        styleUrls: ['./usercity.component.css']
    })
], UserCityComponent);
exports.UserCityComponent = UserCityComponent;
//# sourceMappingURL=usercity.component.js.map