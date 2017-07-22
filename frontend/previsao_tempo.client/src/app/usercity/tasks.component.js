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
var task_dto_1 = require("./../model/task/task.dto");
var tasks_service_1 = require("./tasks.service");
var service_locator_1 = require("./../service/locator/service.locator");
var core_1 = require("@angular/core");
var ng2_smart_table_1 = require("ng2-smart-table");
var TasksComponent = (function () {
    function TasksComponent() {
        this.settings = {
            mode: 'external',
            add: {
                addButtonContent: 'Adicionar'
            },
            edit: {
                editButtonContent: 'Editar',
            },
            delete: {
                deleteButtonContent: 'Deletar',
                confirmDelete: true
            },
            columns: {
                title: {
                    title: "Título",
                    filter: true
                },
                taskStatus: {
                    title: "Status",
                    filter: true,
                },
                dateStart: {
                    title: "Início",
                    filter: true
                },
                dateUpdate: {
                    title: "Alteração",
                    filter: true
                },
                dateRemoção: {
                    title: "Remoção",
                    filter: true
                },
                dateEnd: {
                    title: "Finalização",
                    filter: true
                }
            }
        };
        this.opened = false;
        this.taskCurrent = new task_dto_1.TaskDTO();
    }
    TasksComponent.prototype.tasksService = function () {
        return service_locator_1.ServiceLocator.get(tasks_service_1.TasksService);
    };
    TasksComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.tasksService().list().then(function (value) {
            if (value instanceof Array) {
                _this.source = new ng2_smart_table_1.LocalDataSource(value);
            }
        });
    };
    TasksComponent.prototype.onCreate = function (event) {
        this.taskCurrent = new task_dto_1.TaskDTO();
        this.opened = true;
    };
    TasksComponent.prototype.onEdit = function (event) {
        this.taskCurrent = event.data;
        this.opened = true;
    };
    TasksComponent.prototype.onCancel = function () {
        this.taskCurrent = new task_dto_1.TaskDTO();
        this.opened = false;
    };
    TasksComponent.prototype.onSaveConfirm = function () {
        var _this = this;
        this.tasksService().save(this.taskCurrent).then(function () { return _this.opened = false; });
    };
    TasksComponent.prototype.onDeleteConfirm = function (event) {
        var _this = this;
        this.tasksService().delete(event.data).then(function () { return _this.opened = false; });
    };
    return TasksComponent;
}());
TasksComponent = __decorate([
    core_1.Component({
        selector: 'app-task',
        templateUrl: './tasks.component.html',
        styleUrls: ['./tasks.component.css']
    }),
    __metadata("design:paramtypes", [])
], TasksComponent);
exports.TasksComponent = TasksComponent;
//# sourceMappingURL=tasks.component.js.map