import { Observable } from 'rxjs/Observable';
import { CityDTO } from './../model/city/city.dto';
import { UserCityDTO } from './../model/usercity/usercity.dto';
import { TokenService } from './../service/token/token.service';
import { TaskDTO } from './../model/task/task.dto';
import { Http } from '@angular/http';
import { ServiceLocator } from './../service/locator/service.locator';
import { HttpService } from './../service/http/http.service';
import { Injectable } from '@angular/core';

@Injectable()
export class UserCityService {

    private get httpService() : HttpService {
        return ServiceLocator.get(HttpService);
    }

    private get tokenService() : TokenService {
        return ServiceLocator.get(TokenService);
    }

    /**
     * Listar todas as tarefas.
     */
    public list() : Observable<UserCityDTO[]> {
        return this.httpService.get('/usercities');
    }

    /**
     * Listar as cidades.
     */
    public searchCities(query: string, idState: any) : Observable<CityDTO[]> {
        return this.httpService.get('/cities/search', {params: new Map([["query", query], ["idState", idState]])});
    }

    /**
     * Listar os estados.
     */
    public searchStates(query: string) : Observable<CityDTO[]> {
        return this.httpService.get('/states' , {params: new Map([["query", query]])});
    }

    /**
     * Deletar uma tarefa.
     */
    public delete(userCity: UserCityDTO) : Observable<UserCityDTO[]> {
        return this.httpService.delete('/usercities', {params: new Map([['id', userCity.id]])});
    }

    /**
     * Salvar uma tarefa.
     */
    public save(userCity: UserCityDTO) : Observable<UserCityDTO> {
        userCity.username = this.tokenService.getToken().username;
        return this.httpService.post('/usercities', {data: userCity});
    }
}