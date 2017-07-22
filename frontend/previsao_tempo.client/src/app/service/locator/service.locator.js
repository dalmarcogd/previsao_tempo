"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var ServiceLocator = (function () {
    function ServiceLocator() {
    }
    ServiceLocator.setInjector = function (injector) {
        this.injector = injector;
    };
    ServiceLocator.get = function (token, notFoundValue) {
        return this.injector.get(token);
    };
    return ServiceLocator;
}());
exports.ServiceLocator = ServiceLocator;
//# sourceMappingURL=service.locator.js.map