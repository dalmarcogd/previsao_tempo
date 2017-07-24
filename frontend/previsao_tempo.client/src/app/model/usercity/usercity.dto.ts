import { WeatherUserCity } from './weather.usercity';
import { CityDTO } from './../city/city.dto';
import { UserDTO } from './../user/user.dto';
import {BaseDTO} from "../base/base.dto";

/**
 * Representa uma cidade do usuário na aplicação.
 */
export class UserCityDTO extends BaseDTO {

  username: string = null;
  city: CityDTO = null;
  cityname: string = null;
  weatherUserCity: WeatherUserCity = null;
  button: string = "Ver previsão"
  
}