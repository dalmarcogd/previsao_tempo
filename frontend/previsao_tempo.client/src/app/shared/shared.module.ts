/**
 *
 */
import { CommonModule } from '@angular/common';
import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {NglModule} from 'ng-lightning';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    NglModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [
    CommonModule,
    NglModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SharedModule {
}
