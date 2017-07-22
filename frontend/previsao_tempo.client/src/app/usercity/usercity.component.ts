import { UserCityDTO } from './../model/usercity/usercity.dto';
import { UserCityService } from './usercity.service';
import { ServiceLocator } from './../service/locator/service.locator';
import { Component, OnInit } from '@angular/core';
import {LocalDataSource} from 'ng2-smart-table';

@Component({
  selector: 'app-usercity',
  templateUrl: './usercity.component.html',
  styleUrls: ['./usercity.component.css']
})
export class UserCityComponent implements OnInit {

  settings = {
      mode:'external',
      add: {
        addButtonContent:'Adicionar'

      },
      edit: {
        editButtonContent: 'Editar',
      },
      delete: {
        deleteButtonContent: 'Deletar',
        confirmDelete: true
      },
      columns: {
        name: {
          title: "Cidade",
          filter: true
        }
      }
    };
    source: LocalDataSource;
    opened: boolean = false;
    userCityCurrent: UserCityDTO = new UserCityDTO();

  constructor() { }

  private userCityService() : UserCityService {
    return ServiceLocator.get(UserCityService);
  }

  ngOnInit() {
    this.userCityService().list().then((value: any) => {
      if (value instanceof Array) {
        this.source = new LocalDataSource(value);
      }
    });
  }

  onCreate(event: any): void {
    this.userCityCurrent = new UserCityDTO();
    this.opened = true;
  }

  onEdit(event: any): void {
    this.userCityCurrent = event.data;
    this.opened = true;
  }

  onCancel(): void {
    this.userCityCurrent = new UserCityDTO();
    this.opened = false;
  }

  onSaveConfirm(): void {
    this.userCityService().save(this.userCityCurrent).then(() => this.opened = false);
  }

  onDeleteConfirm(event: any): void {
    this.userCityService().delete(event.data).then(() => this.opened = false);
  }

  searchCities() {
    return this.userCityService().searchCities();
  }
}
