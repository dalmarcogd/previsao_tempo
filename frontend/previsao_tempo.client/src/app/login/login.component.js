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
var auth_service_1 = require("./../service/auth/auth.service");
var core_1 = require("@angular/core");
var forms_1 = require("@angular/forms");
var LoginComponent = (function () {
    function LoginComponent(fb, authService) {
        this.fb = fb;
        this.authService = authService;
    }
    LoginComponent.prototype.doLogin = function (value) {
        console.log(value.username);
        console.log(value.password);
        this.authService.login(value.username, value.password).catch(function (error) { console.log(error); });
    };
    LoginComponent.prototype.ngOnInit = function () {
        this.form = this.fb.group({
            username: new forms_1.FormControl('', [forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.minLength(4)])]),
            password: new forms_1.FormControl('', [forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.minLength(4)])])
        });
    };
    return LoginComponent;
}());
LoginComponent = __decorate([
    core_1.Component({
        selector: 'app-login',
        templateUrl: './login.component.html',
        styleUrls: ['./login.component.css']
    }),
    __metadata("design:paramtypes", [forms_1.FormBuilder, auth_service_1.AuthService])
], LoginComponent);
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login.component.js.map