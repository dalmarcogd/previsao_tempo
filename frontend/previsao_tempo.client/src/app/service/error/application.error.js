"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var ApplicationError = (function () {
    function ApplicationError(code, message, helpMessage, status) {
        this.code = code;
        this.message = message;
        this.helpMessage = helpMessage;
        this.status = status;
    }
    return ApplicationError;
}());
exports.ApplicationError = ApplicationError;
//# sourceMappingURL=application.error.js.map