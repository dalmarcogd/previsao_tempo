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
var weather_usercity_1 = require("./../model/usercity/weather.usercity");
var http_service_1 = require("./../service/http/http.service");
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
                addButtonContent: 'Cadastrar nova cidade'
            },
            edit: {
                editButtonContent: 'Editar /',
            },
            delete: {
                deleteButtonContent: 'Deletar',
            },
            columns: {
                cityname: {
                    title: "Cidade",
                    filter: true
                },
                button: {
                    filter: false,
                    title: 'Previsão do tempo',
                    type: 'custom',
                    renderComponent: ButtonViewComponent,
                    onComponentInitFunction: function (instance) {
                        instance.save.subscribe(function (row) {
                        });
                    }
                }
            },
            pager: {
                display: true,
                perPage: 10
            },
            noDataMessage: 'Nenhuma cidade cadastrada...'
        };
        this.opened = false;
        this.openedPrevisao = false;
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
    UserCityComponent.prototype.getPrevisoes = function () {
        if (this.userCityCurrent.weatherUserCity && this.userCityCurrent.weatherUserCity.city) {
            return this.userCityCurrent.weatherUserCity.city.list;
        }
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
        this.clean();
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
        this.userCityService().save(this.userCityCurrent).subscribe(function () { _this.opened = false; _this.clean(); _this.load(); });
    };
    UserCityComponent.prototype.clean = function () {
        this.stateCurrent = null;
    };
    UserCityComponent.prototype.onDeleteConfirm = function (event) {
        var _this = this;
        console.log("fudeo");
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
var ButtonViewComponent = (function () {
    function ButtonViewComponent() {
        this.openedPrevisao = false;
        this.save = new core_1.EventEmitter();
    }
    ButtonViewComponent.prototype.ngOnInit = function () {
        this.renderValue = this.value.toString().toUpperCase();
    };
    ButtonViewComponent.prototype.onClick = function () {
        this.loadForecast();
    };
    ButtonViewComponent.prototype.getItens = function () {
        if (this.rowData instanceof usercity_dto_1.UserCityDTO) {
            if (this.rowData.weatherUserCity != null && this.rowData.weatherUserCity.city != null) {
                return this.rowData.weatherUserCity.city.list;
            }
        }
        return new Array();
    };
    ButtonViewComponent.prototype.loadForecast = function () {
        var _this = this;
        if (this.rowData.weatherUserCity == null) {
            var obs = this.httpService().getOut("http://api.openweathermap.org/data/2.5/forecast", { params: new Map([["q", this.rowData.cityname], ['appid', 'eb8b1a9405e659b2ffc78f0a520b1a46']]) });
            obs.subscribe(function (value) {
                _this.rowData.weatherUserCity = new weather_usercity_1.WeatherUserCity();
                _this.rowData.weatherUserCity.city = value;
                _this.openedPrevisao = true;
            });
        }
    };
    ButtonViewComponent.prototype.httpService = function () {
        return service_locator_1.ServiceLocator.get(http_service_1.HttpService);
    };
    ButtonViewComponent.prototype.onExitPrevisao = function (event) {
        this.openedPrevisao = false;
    };
    return ButtonViewComponent;
}());
__decorate([
    core_1.Input(),
    __metadata("design:type", Object)
], ButtonViewComponent.prototype, "value", void 0);
__decorate([
    core_1.Input(),
    __metadata("design:type", Object)
], ButtonViewComponent.prototype, "rowData", void 0);
__decorate([
    core_1.Output(),
    __metadata("design:type", core_1.EventEmitter)
], ButtonViewComponent.prototype, "save", void 0);
ButtonViewComponent = __decorate([
    core_1.Component({
        selector: 'button-view',
        template: "\n    <button (click)=\"onClick()\">{{ renderValue }}</button>\n    <ngl-modal [(open)]=\"openedPrevisao\" [directional]=\"directional\" [header]=\"'Previs\u00E3o do tempo'\">\n        <div body>\n          <table style=\"width:100%\">\n            <tr>\n              <th>Data</th>\n              <th>Temperatura min\u00EDma</th> \n              <th>Temperatura m\u00E1xima</th> \n            </tr>\n            <tr *ngFor=\"let item of getItens()\">\n                <td>{{item.dt_txt}}</td>\n                <td>{{(item.main.temp_min - 273.15) | number: '1.2'}} \u00B0C</td>\n                <td>{{(item.main.temp_max - 273.15) | number: '1.2'}} \u00B0C</td>\n            </tr>\n          </table>\n        </div>\n        <template ngl-modal-footer>\n          <button class=\"slds-button slds-button--brand\" (click)=\"onExitPrevisao()\">Sair</button>\n        </template>\n    </ngl-modal>\n  ",
    })
], ButtonViewComponent);
exports.ButtonViewComponent = ButtonViewComponent;
//# sourceMappingURL=usercity.component.js.map