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
    public list() : Promise<UserCityDTO[]> {
        return this.httpService.get('/usercity');
    }

    /**
     * Listar todas as tarefas.
     */
    public searchCities() : Promise<CityDTO[]> {
        return this.httpService.get('/city');
    }

    /**
     * Deletar uma tarefa.
     */
    public delete(userCity: UserCityDTO) : Promise<UserCityDTO[]> {
        return this.httpService.delete('/usercity', userCity);
    }

    /**
     * Salvar uma tarefa.
     */
    public save(userCity: UserCityDTO) : Promise<UserCityDTO> {
        return this.httpService.post('/usercity', userCity);
    }
}