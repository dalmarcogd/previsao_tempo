"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
Object.defineProperty(exports, "__esModule", { value: true });
var base_dto_1 = require("../base/base.dto");
/**
 * Representa uma cidade do usuário na aplicação.
 */
var UserCityDTO = (function (_super) {
    __extends(UserCityDTO, _super);
    function UserCityDTO() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.username = null;
        _this.city = null;
        _this.cityname = null;
        _this.weatherUserCity = null;
        _this.button = "Ver previsão";
        return _this;
    }
    return UserCityDTO;
}(base_dto_1.BaseDTO));
exports.UserCityDTO = UserCityDTO;
//# sourceMappingURL=usercity.dto.js.map