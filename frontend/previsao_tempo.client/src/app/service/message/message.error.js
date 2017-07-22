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
var message_1 = require("./message");
var MessageErro = (function (_super) {
    __extends(MessageErro, _super);
    function MessageErro(message, error) {
        var _this = _super.call(this, message) || this;
        _this.error = error;
        return _this;
    }
    MessageErro.prototype.getError = function () {
        return this.error;
    };
    return MessageErro;
}(message_1.Message));
exports.MessageErro = MessageErro;
//# sourceMappingURL=message.error.js.map