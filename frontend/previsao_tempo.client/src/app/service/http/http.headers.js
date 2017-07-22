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
var http_1 = require("@angular/http");
/**
 * Header padrão para requisições.
 */
var HttpHeaders = (function (_super) {
    __extends(HttpHeaders, _super);
    function HttpHeaders() {
        var _this = _super.call(this) || this;
        _this.append("Content-Type", "application/json");
        _this.append("Accept", "application/json");
        _this.append('Access-Control-Allow-Headers', 'Authorization');
        return _this;
        //this.append('Access-Control-Allow-Methods', '*');
        //this.append('Access-Control-Allow-Headers', 'X-Requested-With,Content-Type');
    }
    return HttpHeaders;
}(http_1.Headers));
exports.HttpHeaders = HttpHeaders;
//# sourceMappingURL=http.headers.js.map