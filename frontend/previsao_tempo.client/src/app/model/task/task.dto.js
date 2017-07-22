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
 * Representa um token de autenticação do usuario.
 */
var TaskDTO = (function (_super) {
    __extends(TaskDTO, _super);
    function TaskDTO() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.title = null;
        _this.description = null;
        _this.taskStatus = "OPENED";
        _this.dateStart = null;
        _this.dateUpdate = null;
        _this.dateRemove = null;
        _this.dateEnd = null;
        return _this;
    }
    return TaskDTO;
}(base_dto_1.BaseDTO));
exports.TaskDTO = TaskDTO;
//# sourceMappingURL=task.dto.js.map