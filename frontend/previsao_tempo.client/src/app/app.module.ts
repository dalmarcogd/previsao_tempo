import { NglModule } from 'ng-lightning';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { TasksService } from './tasks/tasks.service';
import { TokenService } from './service/token/token.service';
import { MessageService } from './service/message/message.service';
import { ApplicationErrorHandler } from './service/error/application.error.handler';
import { HttpService } from './service/http/http.service';
import { StorageService } from './service/storage/storage.service';
import { AuthService } from './service/auth/auth.service';
import { routing } from './app.routing';
import { ServiceLocator } from './service/locator/service.locator';
import { Injector } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule, JsonpModule } from '@angular/http';

import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    routing,
    HttpModule,
    Ng2SmartTableModule,
    NglModule.forRoot()
  ],
  providers: [AuthService, 
              StorageService, 
              HttpService,
              ApplicationErrorHandler,
              MessageService,
              TokenService,
              TasksService],
  bootstrap: [AppComponent]
})
export class AppModule {

  constructor(injector: Injector) {
    ServiceLocator.setInjector(injector);
  }
 }