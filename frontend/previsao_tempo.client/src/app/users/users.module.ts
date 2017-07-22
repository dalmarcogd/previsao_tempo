/**
 * Module do user.
 */
import { CommonModule } from '@angular/common';
import { routing } from './users.routing';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { UsersComponent } from './users.component';

@NgModule({
  imports: [
    routing
  ],
  declarations: [
    UsersComponent
  ],
  exports: [
    UsersComponent
  ],
  bootstrap: [
    UsersComponent
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class UsersModule { }
