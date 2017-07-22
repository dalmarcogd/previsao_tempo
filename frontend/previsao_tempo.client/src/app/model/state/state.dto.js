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
 * Representa um estado da aplicação.
 */
var StateDTO = (function (_super) {
    __extends(StateDTO, _super);
    function StateDTO() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.name = null;
        return _this;
    }
    return StateDTO;
}(base_dto_1.BaseDTO));
exports.StateDTO = StateDTO;
//# sourceMappingURL=state.dto.js.map