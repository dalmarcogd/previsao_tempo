import { TokenService } from './../service/token/token.service';
import { StateDTO } from './../model/state/state.dto';
import { CityDTO } from './../model/city/city.dto';
import { UserCityDTO } from './../model/usercity/usercity.dto';
import { UserCityService } from './usercity.service';
import { ServiceLocator } from './../service/locator/service.locator';
import { Component, OnInit } from '@angular/core';
import {LocalDataSource} from 'ng2-smart-table';
import {Observable, Observer} from 'rxjs';

@Component({
  selector: 'app-usercity',
  templateUrl: './usercity.component.html',
  styleUrls: ['./usercity.component.css']
})
export class UserCityComponent implements OnInit {

  settings = {
    mode:'external',
    actions: {
      columnTitle: 'Ações'
    },
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
      cityname: {
        title: "Cidade",
        filter: true
      }
    },
    pager: {
      display: true,
      perPage:10
    }
  };
  source: LocalDataSource;
  opened: boolean = false;
  userCityCurrent: UserCityDTO = new UserCityDTO();
  stateCurrent: StateDTO = null;

  private userCityService() : UserCityService {
    return ServiceLocator.get(UserCityService);
  }

  ngOnInit() {
    this.load();
  }

  load() {
    this.userCityService().list().subscribe((value: any) => {
      this.source = new LocalDataSource();
      if (value instanceof Array) {
        value.forEach((value) => {
          let c: UserCityDTO = new UserCityDTO();
          c.username = value.username;
          c.city = value.city;
          c.cityname = value.city.name;
          c.id = value.id;
          c.version = value.version;
          this.source.add(c);
        });
      } else if (value instanceof UserCityDTO){
        let c: UserCityDTO = new UserCityDTO();
        c.username = value.username;
        c.city = value.city;
        c.id = value.id;
        c.version = value.version;
        this.source = new LocalDataSource(new Array({c}));
      }
    });
  }

  onCreate(event: any): void {
    this.userCityCurrent = new UserCityDTO();
    this.opened = true;
  }

  onEdit(event: any): void {
    this.userCityCurrent = event.data;
    this.stateCurrent = this.userCityCurrent.city.state;
    this.opened = true;
  }

  onCancel(): void {
    this.userCityCurrent = new UserCityDTO();
    this.opened = false;
  }

  onSaveConfirm(): void {
    this.userCityService().save(this.userCityCurrent).subscribe(() => {this.opened = false; this.load();});
  }

  onDeleteConfirm(event: any): void {
    this.userCityService().delete(event.data).subscribe(() => {this.opened = false; this.load();});
  }

  searchCities = (query: string) : Observable<CityDTO[]> => {
    if (!query) {
      return null;
    }
    return this.userCityService().searchCities(query, !!this.stateCurrent? this.stateCurrent.id : null);
  }

  searchStates = (query: string) : Observable<StateDTO[]> => {
    if (!query) {
      return null;
    }
    return this.userCityService().searchStates(query);
  }
}

  interface Weather {
    temp: string;
    city: string;
  }