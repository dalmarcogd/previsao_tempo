import { SharedModule } from './../shared/shared.module';
import { UserCityService } from './usercity.service';
/**
 * Module das tasks
 */
import { CommonModule } from '@angular/common';
import { routing } from './usercity.routing';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { UserCityComponent, ButtonViewComponent } from './usercity.component';
import { Ng2SmartTableModule } from 'ng2-smart-table';

@NgModule({
  imports: [
    routing,
    Ng2SmartTableModule,
    SharedModule
  ],
  declarations: [
    UserCityComponent,
    ButtonViewComponent  
  ],
  entryComponents: [ButtonViewComponent],
  exports: [
    UserCityComponent,
    Ng2SmartTableModule,
    ButtonViewComponent
  ],
  bootstrap: [
    UserCityComponent
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
  providers: [
  ]
})
export class UserCityModule { }
