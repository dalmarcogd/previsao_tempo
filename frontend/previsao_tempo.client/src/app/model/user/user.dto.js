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
 * Representa um usuario.
 */
var UserDTO = (function (_super) {
    __extends(UserDTO, _super);
    function UserDTO() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.name = null;
        _this.username = null;
        _this.password = null;
        _this.email = null;
        return _this;
    }
    return UserDTO;
}(base_dto_1.BaseDTO));
exports.UserDTO = UserDTO;
//# sourceMappingURL=user.dto.js.map