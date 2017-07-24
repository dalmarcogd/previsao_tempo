import { WeatherUserCity } from './../model/usercity/weather.usercity';
import { HttpService } from './../service/http/http.service';
import { TokenService } from './../service/token/token.service';
import { StateDTO } from './../model/state/state.dto';
import { CityDTO } from './../model/city/city.dto';
import { UserCityDTO } from './../model/usercity/usercity.dto';
import { UserCityService } from './usercity.service';
import { ServiceLocator } from './../service/locator/service.locator';
import { Component, OnInit, Input, Output, EventEmitter, ViewEncapsulation } from '@angular/core';
import {LocalDataSource, ViewCell} from 'ng2-smart-table';
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
      addButtonContent:'Cadastrar nova cidade'
    },
    edit: {
      editButtonContent: 'Editar /',
    },
    delete: {
      deleteButtonContent: 'Deletar',
    },
    columns: {
      cityname: {
        title: "Cidade",
        filter: true
      },
      button: {
        filter: false,
        title: 'Previsão do tempo',
        type: 'custom',
        renderComponent: ButtonViewComponent,
        onComponentInitFunction(instance: ButtonViewComponent) {
          instance.save.subscribe((row:any) => {
            
          });
        }
      }
    },
    pager: {
      display: true,
      perPage:10
    },
    noDataMessage: 'Nenhuma cidade cadastrada...'
  };
  source: LocalDataSource;
  opened: boolean = false;
  openedPrevisao: boolean = false;
  userCityCurrent: UserCityDTO = new UserCityDTO();
  stateCurrent: StateDTO = null;

  private userCityService() : UserCityService {
    return ServiceLocator.get(UserCityService);
  }

  ngOnInit() {
    this.load();
  }

  getPrevisoes() {
    if (this.userCityCurrent.weatherUserCity && this.userCityCurrent.weatherUserCity.city) {
      return this.userCityCurrent.weatherUserCity.city.list;
    }
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
    this.clean();
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
    this.userCityService().save(this.userCityCurrent).subscribe(() => {this.opened = false;this.clean(); this.load();});
  }

  clean() {
    this.stateCurrent = null;
  }

  onDeleteConfirm(event: any): void {
    console.log("fudeo")
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

  @Component({
  selector: 'button-view',
  template: `
    <button (click)="onClick()">{{ renderValue }}</button>
    <ngl-modal [(open)]="openedPrevisao" [directional]="directional" [header]="'Previsão do tempo'">
        <div body>
          <table style="width:100%">
            <tr>
              <th>Data</th>
              <th>Temperatura miníma</th> 
              <th>Temperatura máxima</th> 
            </tr>
            <tr *ngFor="let item of getItens()">
                <td>{{item.dt_txt}}</td>
                <td>{{(item.main.temp_min - 273.15) | number: '1.2'}} °C</td>
                <td>{{(item.main.temp_max - 273.15) | number: '1.2'}} °C</td>
            </tr>
          </table>
        </div>
        <template ngl-modal-footer>
          <button class="slds-button slds-button--brand" (click)="onExitPrevisao()">Sair</button>
        </template>
    </ngl-modal>
  `,
})
export class ButtonViewComponent implements ViewCell, OnInit {
  renderValue: string;
  openedPrevisao: boolean = false;

  @Input() value: string | number;
  @Input() rowData: any;

  @Output() save: EventEmitter<any> = new EventEmitter();

  ngOnInit() {
    this.renderValue = this.value.toString().toUpperCase();
  }

  onClick() {
    this.loadForecast();
  }

  getItens() : Array<any> {
    if (this.rowData instanceof UserCityDTO) {
      if (this.rowData.weatherUserCity != null && this.rowData.weatherUserCity.city != null) {
        return this.rowData.weatherUserCity.city.list;
      }
    }
    return new Array();
  }

  loadForecast() {
    if (this.rowData.weatherUserCity == null) {
      let obs = this.httpService().getOut("http://api.openweathermap.org/data/2.5/forecast", {params: new Map([["q", this.rowData.cityname],['appid','eb8b1a9405e659b2ffc78f0a520b1a46']])});
      obs.subscribe((value:any) => {
              this.rowData.weatherUserCity = new WeatherUserCity();
              this.rowData.weatherUserCity.city = value;
              this.openedPrevisao = true;
      });
    }
  }

  private httpService() : HttpService {
    return ServiceLocator.get(HttpService);
  }

  onExitPrevisao(event: any): void {
    this.openedPrevisao = false;
  }
}